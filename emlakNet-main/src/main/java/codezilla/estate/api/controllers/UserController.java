package codezilla.estate.api.controllers;

import codezilla.estate.business.abstracts.UserService;
import codezilla.estate.core.utilities.results.DataResult;
import codezilla.estate.entities.concretes.CustomUserDetails;
import codezilla.estate.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    //Kayıt ol sayfasında User modeli oluşturuyoruz.
    @GetMapping("/kayitol")
    public String kayitOlForm(Model model){
        model.addAttribute("user", new User());
        return "kayitol";
    }

    //Kullanıcı kaydını butona basınca gerçekleştiriyor.
    @PostMapping("/process_register")
    public String kayitOlSubmit(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        userService.add(user);

        return "result";
    }

    @GetMapping("/giris")
    public String girisPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "giris";
        }
        return "redirect:/profil";
    }


    @PostMapping (value = "/deleteUser")
    public String deletetById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName =((CustomUserDetails)authentication.getPrincipal()).getUsername();
        DataResult<User> userForDelete = userService.findByEmail(userName);
        userService.deleteAccountById(userForDelete.getData().getId());
        authentication.setAuthenticated(false);
        return "result";
    }


    @GetMapping("/IDChange")
    public String ıdchancePage(){

        return "IDChange";
    }


    @PostMapping("/editnick")
    public String updateUserNickName(@RequestParam("nick") String nickName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName =((CustomUserDetails)authentication.getPrincipal()).getUsername();
        DataResult<User> userForEdit = userService.findByEmail(userName);
        int userForEditId = userForEdit.getData().getId();

        userService.updateNickNameById(userForEditId,nickName);

        return "result";
    }

}
