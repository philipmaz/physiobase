package pl.physiobase.admin;

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

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/home")
    public String homePage(Model m, HttpSession session){

        String email = (String) session.getAttribute("email");

        Admin admin = adminRepository.findByEmail(email);

        m.addAttribute("admin", admin);
        return "home/homepage";

    }

//    @GetMapping("/addadmin")
//    @ResponseBody
//    public String addAdmin(){
//        Admin admin = new Admin().setEmail("admin3@gmail.com").setPassword(BCrypt.hashpw("password3", BCrypt.gensalt()));
//
//        adminRepository.save(admin);
//
//        return "Admin added successfully";
//
//    }


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
                return "redirect:home";
            }
        }

        return "redirect:login";
    }
}
