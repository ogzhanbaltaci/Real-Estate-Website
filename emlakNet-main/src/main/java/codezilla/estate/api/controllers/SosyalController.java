package codezilla.estate.api.controllers;
import codezilla.estate.business.abstracts.SosyalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SosyalController {

    @Autowired
    private SosyalService sosyalService;

    @GetMapping("/Takip")
    public String getFacebook( Model model){

        model.addAttribute("facebook",sosyalService.getBySosyal_id(2).getSosyal_name());
        model.addAttribute("instagram",sosyalService.getBySosyal_id(1).getSosyal_name());
        model.addAttribute("twitter",sosyalService.getBySosyal_id(3).getSosyal_name());

        return "Takip";
    }

}
