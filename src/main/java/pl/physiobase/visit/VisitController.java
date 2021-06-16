package pl.physiobase.visit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.physiobase.visit.Visit;
import pl.physiobase.patient.PatientRepository;
import pl.physiobase.visit.VisitRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/visit")
public class VisitController {

    @PersistenceContext
    private EntityManager em;
    private VisitRepository visitRepository;
    private PatientRepository patientRepository;

    public VisitController(VisitRepository visitRepository, PatientRepository patientRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/all")
    public String showVisits(Model m){
        List<Visit> visits = visitRepository.findAll();
        m.addAttribute("visits", visits);
        return "visit/allvisits";
    }

    @PostMapping("/all")
    public String showVisits(HttpServletRequest request, Model m){

        List<Visit> search_visits = visitRepository.findAllByDate(request.getParameter("date"));
        m.addAttribute("visits", search_visits);


        return "visit/allvisits";

    }


    @GetMapping("/addvisit")
    public String addVisit(Model m){
        m.addAttribute("visit",new Visit());
        return "visit/visitform";
    }

    @PostMapping("/addvisit")
    public String addVisit(@ModelAttribute @Valid Visit visit, BindingResult violation ){
        visitRepository.save(visit);
        return "redirect:all";
    }

    @GetMapping("/editvisit/{id}")
    public String editVisit(Model m, @PathVariable long id){
        m.addAttribute("visit", visitRepository.findById(id));
        return "visit/visitform";
    }

    @PostMapping("/editvisit/{id}")
    public String editVisit(@ModelAttribute @Valid Visit visit, BindingResult violation){
        visitRepository.save(visit);
        return "redirect:../all";
    }

    @GetMapping("/deletevisit/{id}")
    public String deleteVisit(Model m, @PathVariable long id){
        m.addAttribute("visit", visitRepository.findById(id));
        return "visit/deletevisitform";
    }

    @PostMapping("/deletevisit/{id}")
    public String deleteVisit(@ModelAttribute @Valid Visit visit, BindingResult violation){
        visitRepository.delete(visit);
        return "redirect:../all";
    }


//    @GetMapping("/editpatientvisit/{visitId}/{patientId}")
//    public String editPatientVisit(Model m, @PathVariable long visitId,@PathVariable long patientId){
//        m.addAttribute("visit", visitRepository.findById(visitId));
//        return "visit/visitform";
//    }
//
//    @PostMapping("/editpatientvisit/{visitId}/{patientId}")
//    public String editPatientVisit(@ModelAttribute @Valid Visit visit, BindingResult violation,@PathVariable long visitId,@PathVariable long patientId){
//        visitRepository.save(visit);
//        return "redirect:http://localhost:8080/patient/showvisits/{patientId}";
//    }
}
