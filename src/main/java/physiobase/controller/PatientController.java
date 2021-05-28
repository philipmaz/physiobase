package physiobase.controller;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import physiobase.entity.Patient;
import physiobase.entity.Training;
import physiobase.entity.Visit;
import physiobase.repository.PatientRepository;
import physiobase.repository.TrainingRepository;
import physiobase.repository.VisitRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @PersistenceContext
    private EntityManager em;
    private VisitRepository visitRepository;
    private PatientRepository patientRepository;
    private TrainingRepository trainingRepository;

    public PatientController(VisitRepository visitRepository, PatientRepository patientRepository, TrainingRepository trainingRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.trainingRepository = trainingRepository;
    }

    @GetMapping("/all")
    public String showPatients(Model m){
        List<Patient> patients=patientRepository.findAllPatientsWithVisits();
        m.addAttribute("patients", patients);
        return "patient/allpatients";
    }

    @PostMapping("/all")
    public String showPatients(HttpServletRequest request, Model m){

        List<Patient> search_patients = patientRepository.findAllByLastName(request.getParameter("lastName"));
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
        patientRepository.save(patient);
        return "redirect:all";
    }

    @GetMapping("/editpatient/{id}")
    public String editPatient(Model m, @PathVariable long id){
        m.addAttribute("patient", patientRepository.findById(id));
        return "patient/patientform";
    }

    @PostMapping("/editpatient/{id}")
    public String editPatient(@ModelAttribute @Valid Patient patient, BindingResult violation){
        patientRepository.save(patient);
        return "redirect:../all";
    }

    @GetMapping("/deletepatient/{id}")
    public String deletePatient(Model m, @PathVariable long id){
        m.addAttribute("patient", patientRepository.findById(id));
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


    @GetMapping("/addvisit/{patientId}")
    public String addVisitsToPatient(Model m, @PathVariable long patientId){
        Patient patient=patientRepository.findById(patientId);
        m.addAttribute("patient", patient);
        m.addAttribute("visit", new Visit());
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
    public String modifyPatientVisit(Model m, @PathVariable long patientId, @PathVariable long visitId){
        Patient patient = patientRepository.findByIdWithVisits(patientId);
        Hibernate.initialize(patient.getVisits());
        m.addAttribute("visit", visitRepository.findById(visitId));
        m.addAttribute("patient", patient);
        return "visit/patientvisitform";
    }

    @PostMapping("/editvisit/{patientId}/{visitId}")
    public String modifyPatientVisit(@ModelAttribute Visit visit, @PathVariable long patientId, @PathVariable long visitId){
        visitRepository.save(visit);
        return "redirect:../../showvisits/{patientId}";
    }

    @GetMapping("/deletevisit/{patientId}/{visitId}")
    @Transactional
    public String deletePatientVisit(Model m, @PathVariable long patientId, @PathVariable long visitId){
        Patient patient = patientRepository.findByIdWithVisits(patientId);
        Hibernate.initialize(patient.getVisits());
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
    public String addTrainingToPatient(Model m, @PathVariable long patientId){
        Patient patient=patientRepository.findById(patientId);
        m.addAttribute("patient", patient);
        m.addAttribute("training", new Training());
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
    public String modifyPatientTraining(Model m, @PathVariable long patientId, @PathVariable long trainingId){
        Patient patient = patientRepository.findByIdWithVisits(patientId);
        Hibernate.initialize(patient.getVisits());
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

}
