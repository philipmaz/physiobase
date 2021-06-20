package pl.physiobase.admin;

import org.hibernate.id.GUIDGenerator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/login")
    public String loginAdmin(Model m){
        m.addAttribute("admin",new Admin());
        return "admin/loginform";
    }

    @PostMapping("/login")
    public String loginAdmin(@ModelAttribute @Valid Admin admin, BindingResult violations,  HttpServletRequest request){
        if(violations.hasErrors()){
            return "admin/loginform";
        }

        HttpSession session = request.getSession();

        String email = admin.getEmail();
        String password = admin.getPassword();

        List<Admin> adminList = adminRepository.findAll();
        for (Admin a : adminList) {
            if (Objects.equals(a.getEmail(), email) && BCrypt.checkpw(password, a.getPassword())) {
                session.setAttribute("email", a.getEmail());
                session.setAttribute("id",UUID.randomUUID().toString());

                return "redirect:../patient/home";
            }
        }

        return "redirect:login";
    }
}
