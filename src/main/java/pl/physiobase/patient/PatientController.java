package pl.physiobase.patient;


import org.hibernate.Hibernate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.physiobase.admin.Admin;
import pl.physiobase.admin.AdminRepository;
import pl.physiobase.path.ImagePathRepository;
import pl.physiobase.path.Imagepath;
import pl.physiobase.training.Training;
import pl.physiobase.visit.Visit;
import pl.physiobase.training.TrainingRepository;
import pl.physiobase.visit.VisitRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private VisitRepository visitRepository;
    private PatientRepository patientRepository;
    private TrainingRepository trainingRepository;
    private ImagePathRepository imagePathRepository;
    private AdminRepository adminRepository;

    public PatientController(VisitRepository visitRepository, PatientRepository patientRepository, TrainingRepository trainingRepository, ImagePathRepository imagePathRepository, AdminRepository adminRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.trainingRepository = trainingRepository;
        this.imagePathRepository = imagePathRepository;
        this.adminRepository = adminRepository;
    }

    @GetMapping("/home")
    public String homePage(Model m, HttpSession session){

        String email = (String) session.getAttribute("email");

        Admin admin = adminRepository.findByEmail(email);

        m.addAttribute("admin", admin);
        return "home/homepage";

    }

    @GetMapping("/all")
    @Transactional
    public String showPatients(Model m){
        List<Patient> patients=patientRepository.findAllPatientsWithVisits();
//        List<Patient> patients=patientRepository.findAllPatientsWithVisitsAndTrainings();

        for (Patient p:patients) {
            Hibernate.initialize(p.getTrainings());
        }


        m.addAttribute("patients", patients);
        return "patient/allpatients";
    }

    @PostMapping("/all")
    @Transactional
    public String showPatients(HttpServletRequest request, Model m){

        List<Patient> search_patients = patientRepository.findAllByLastName(request.getParameter("lastName"));

        for (Patient p:search_patients) {
            Hibernate.initialize(p.getTrainings());
            Hibernate.initialize(p.getVisits());
        }

        m.addAttribute("patients", search_patients);

        return "patient/allpatients";

    }


    @GetMapping("/addpatient")
    public String addPatient(Model m){
        m.addAttribute("patient", new Patient());
        return "patient/patientform";
    }

    @PostMapping("/addpatient")
    public String addPatient(@ModelAttribute @Valid Patient patient, BindingResult violation){

        if(violation.hasErrors()){
            return "patient/patientform";
        }

        patientRepository.save(patient);
        return "redirect:all";
    }

    @GetMapping("/editpatient/{id}")
    @Transactional
    public String editPatient(Model m, @PathVariable long id){
        Patient patient=patientRepository.findById(id);
        Hibernate.initialize(patient.getVisits());
        Hibernate.initialize(patient.getTrainings());
        m.addAttribute("patient", patient);
        return "patient/patientform";
    }

    @PostMapping("/editpatient/{id}")
    public String editPatient(@ModelAttribute @Valid Patient patient, BindingResult violation){
        if(violation.hasErrors()){
            return "patient/patientform";
        }

        patientRepository.save(patient);
        return "redirect: ../showvisits/{id}";
    }

    @GetMapping("/deletepatient/{id}")
    public String deletePatient(Model m, @PathVariable long id){
        Patient patient=patientRepository.findById(id);
        Hibernate.initialize(patient.getVisits());
        Hibernate.initialize(patient.getTrainings());
        m.addAttribute("patient", patient);
        return "patient/deletepatientform";
    }

    @PostMapping("/deletepatient/{id}")
    public String deletePatient(@ModelAttribute @Valid Patient patient, BindingResult violation){
        patientRepository.delete(patient);
        return "redirect:../all";
    }

    @GetMapping("/showvisits/{id}")
    @Transactional
    public String showPatientsVisits(Model m, @PathVariable long id){

        Patient patient = patientRepository.findById(id);
        m.addAttribute("patient", patient);
        Hibernate.initialize(patient.getVisits());
        Hibernate.initialize(patient.getTrainings());

        m.addAttribute("visits", patient.getVisits());
        m.addAttribute("trainings", patient.getTrainings());

        return "visit/allpatientvisits";
    }

    @PostMapping("/showvisits/{id}")
    @Transactional
    public String showPatientsVisits(@PathVariable long id, HttpServletRequest request, Model m){
        Patient patient = patientRepository.findById(id);
        m.addAttribute("patient", patient);

        if(request.getParameter("date").equals("")){
            Hibernate.initialize(patient.getVisits());
            Hibernate.initialize(patient.getTrainings());
            m.addAttribute("visits", patient.getVisits());
            m.addAttribute("trainings", patient.getTrainings());
            return "visit/allpatientvisits";
        }

        List<Visit> search_visits=new ArrayList<>();
        List<Training> search_trainings=new ArrayList<>();

        for (Visit v: patient.getVisits()) {
            if(v.getDate().equals(request.getParameter("date"))){
                search_visits.add(v);
            }
        }

        for (Training t: patient.getTrainings()) {
            if(t.getDate().equals(request.getParameter("date"))){
                search_trainings.add(t);
            }
        }

        m.addAttribute("visits", search_visits);
        m.addAttribute("trainings", search_trainings);

        return "visit/allpatientvisits";
    }


    @GetMapping("/addvisit/{patientId}")
    @Transactional
    public String addVisitsToPatient(Model m, @PathVariable long patientId){
        Patient patient=patientRepository.findById(patientId);
        Hibernate.initialize(patient.getVisits());
        Hibernate.initialize(patient.getTrainings());
        m.addAttribute("patient", patient);
        Visit visit = new Visit();
        visit.setPatient(patient);
        m.addAttribute("visit", visit);
        return "visit/patientvisitform";
    }


    @PostMapping("/addvisit/{patientId}")
    @Transactional
    public String addVisitsToPatient(@ModelAttribute Visit visit, @PathVariable long patientId){
        visitRepository.save(visit);
        Patient patient=patientRepository.findById(patientId);
        Hibernate.initialize(patient.getVisits());
        patient.getVisits().add(visit);
        patientRepository.save(patient);
        return "redirect:../showvisits/{patientId}";
    }

    @GetMapping("/editvisit/{patientId}/{visitId}")
    @Transactional
    public String modifyPatientVisit(Model m, @PathVariable long patientId, @PathVariable long visitId){
        Patient patient = patientRepository.findByIdWithVisits(patientId);
        Hibernate.initialize(patient.getVisits());
        Hibernate.initialize(patient.getTrainings());
        m.addAttribute("visit", visitRepository.findById(visitId));
        m.addAttribute("patient", patient);
        return "visit/patientvisitform";
    }

    @PostMapping("/editvisit/{patientId}/{visitId}")
    @Transactional
    public String modifyPatientVisit(@ModelAttribute Visit visit, @PathVariable long patientId, @PathVariable long visitId){
        visitRepository.save(visit);
        Patient patient = patientRepository.findByIdWithVisits(patientId);
        Hibernate.initialize(patient.getVisits());
        Hibernate.initialize(patient.getTrainings());


        return "redirect:../../showvisits/{patientId}";
    }

    @GetMapping("/deletevisit/{patientId}/{visitId}")
    @Transactional
    public String deletePatientVisit(Model m, @PathVariable long patientId, @PathVariable long visitId){
        Patient patient = patientRepository.findByIdWithVisits(patientId);
        Hibernate.initialize(patient.getVisits());
        Hibernate.initialize(patient.getTrainings());
        m.addAttribute("visit", visitRepository.findById(visitId));
        m.addAttribute("patient", patient);
        return "visit/patientvisitform";
    }

    @PostMapping("/deletevisit/{patientId}/{visitId}")
    public String deletePatientVisit(@ModelAttribute Visit visit, @PathVariable long patientId, @PathVariable long visitId){
        visitRepository.delete(visit);
        return "redirect:../../showvisits/{patientId}";
    }

    @GetMapping("/addtraining/{patientId}")
    @Transactional
    public String addTrainingToPatient(Model m, @PathVariable long patientId){
        Patient patient=patientRepository.findById(patientId);
        Hibernate.initialize(patient.getVisits());
        Hibernate.initialize(patient.getTrainings());
        m.addAttribute("patient", patient);
        Training training=new Training();
        training.setPatient(patient);
        m.addAttribute("training", training);
        return "visit/patienttrainingform";
    }


    @PostMapping("/addtraining/{patientId}")
    @Transactional
    public String addTrainingToPatient(@ModelAttribute Training training, @PathVariable long patientId){
        trainingRepository.save(training);
        Patient patient=patientRepository.findById(patientId);
        Hibernate.initialize(patient.getTrainings());
        patient.getTrainings().add(training);
        patientRepository.save(patient);
        return "redirect:../showvisits/{patientId}";
    }

    @GetMapping("/edittraining/{patientId}/{trainingId}")
    @Transactional
    public String modifyPatientTraining(Model m, @PathVariable long patientId, @PathVariable long trainingId){
        Patient patient = patientRepository.findByIdWithVisits(patientId);
        Hibernate.initialize(patient.getVisits());
        Hibernate.initialize(patient.getTrainings());
        m.addAttribute("training", trainingRepository.findById(trainingId));
        m.addAttribute("patient", patient);
        return "visit/patienttrainingform";
    }

    @PostMapping("/edittraining/{patientId}/{trainingId}")
    public String modifyPatientTraining(@ModelAttribute Training training, @PathVariable long patientId, @PathVariable long trainingId){
        trainingRepository.save(training);
        return "redirect:../../showvisits/{patientId}";
    }

    @GetMapping("/deletetraining/{patientId}/{trainingId}")
    @Transactional
    public String deletePatientTraining(Model m, @PathVariable long patientId, @PathVariable long trainingId){
        Patient patient = patientRepository.findByIdWithVisits(patientId);
        Hibernate.initialize(patient.getTrainings());
        m.addAttribute("training", trainingRepository.findById(trainingId));
        m.addAttribute("patient", patient);
        return "visit/patienttrainingform";
    }

    @PostMapping("/deletetraining/{patientId}/{trainingId}")
    public String deletePatientTraining(@ModelAttribute Training training, @PathVariable long patientId, @PathVariable long trainingId){
        trainingRepository.delete(training);
        return "redirect:../../showvisits/{patientId}";
    }

    @GetMapping("/addfile/{id}")
    @Transactional
    public String addFile(@PathVariable long id) {
        Patient patient = patientRepository.findById(id);
        Hibernate.initialize(patient.getImagePaths());
        List<Imagepath> imagepath_list =patient.getImagePaths();
        JFileChooser picchooser = new JFileChooser();
        picchooser.setDialogTitle("Select Image");
        picchooser.showOpenDialog(null);
        File pic = picchooser.getSelectedFile();
        String path = pic.getAbsolutePath().replace('\\', '/');
        String[] path_arr=path.split("/");
        path= "/"+path_arr[path_arr.length-2]+"/"+path_arr[path_arr.length-1];

        Imagepath pathtoAdd=new Imagepath();
        pathtoAdd.setPath(path);
        pathtoAdd.setPatient(patient);
        imagePathRepository.save(pathtoAdd);
        imagepath_list.add(pathtoAdd);

        patient.setImagePaths(imagepath_list);
        patientRepository.save(patient);

        return "redirect:../showvisits/{id}";
    }


        @GetMapping("/showfile/{id}")
        @Transactional
        public String showFile(@PathVariable long id, Model m) {
        Patient patient = patientRepository.findById(id);
        Hibernate.initialize(patient.getImagePaths());
        m.addAttribute("patient", patient);

        return "patient/showimage";
    }

//        try{
//            File image = new File(path);
//            FileInputStream fis = new FileInputStream(image);
//            ByteArrayOutputStream baos= new ByteArrayOutputStream();
//            byte[] buff = new byte[1024];
//            for(int readNum; (readNum=fis.read(buff)) !=-1 ; ){
//                baos.write(buff,0,readNum);
//            }
////            userimage=baos.toByteArray();
//        }
//        catch(Exception e){
//            JOptionPane.showMessageDialog(null, e);
//        }

//    @GetMapping("/print")
//    private static void generatePDFFromHTML(String filename) throws ParserConfigurationException, IOException, DocumentException {
//        File html = new File("/home/filip/physiobase/src/main/webapp/WEB-INF/views/visit/allpatientvisits.jsp");
//        byte[] xhtml = Jsoup.parse(html, "US-ASCII").html().getBytes();
//        File dir = new File("/home/filip/physiobase/src/main/resources/results");
//        dir.mkdirs();
//        FileOutputStream fos = new FileOutputStream(new File(dir, String.valueOf(xhtml)));
//        fos.write(xhtml);
//        fos.close();


        // Create and initialize URL
//        URL oracleURL = new URL("http://localhost:8080/patient/showvisits/1");

// Get web page as input stream
//        InputStream is = oracleURL.openStream();

// Initialize HTML load options
//        HtmlLoadOptions htmloptions = new HtmlLoadOptions();

// Load stream into Document object
//        Document pdfDocument = new Document(is, htmloptions);

// Save output as PDF format
//        pdfDocument.save("HTML-to-PDF.pdf");

//        Document document = new Document();
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/filip/physiobase/src/main/resources/output/html.pdf"));
//        document.open();
//        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("/home/filip/physiobase/src/main/webapp/WEB-INF/views/visit/allpatientvisits.jsp"));
//        document.close();

//        Document document = new Document();
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/filip/physiobase/src/main/resources/output/html.pdf"));
//        document.open();
//        XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
//        document.close();
//    }

//    public static void tidyUp(String path) throws IOException {
//        File html = new File("/home/filip/physiobase/src/main/webapp/WEB-INF/views/visit/allpatientvisits.jsp");
//        byte[] xhtml = Jsoup.parse(html, "US-ASCII").html().getBytes();
//        File dir = new File("/home/filip/physiobase/src/main/resources/results");
//        dir.mkdirs();
//        FileOutputStream fos = new FileOutputStream(new File(dir, html.getName()));
//        fos.write(xhtml);
//        fos.close();
//    }


}
