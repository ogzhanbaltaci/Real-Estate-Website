package codezilla.estate.api.controllers;

import codezilla.estate.business.abstracts.ProfilePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProfilePhotoController {

    @Autowired
    ProfilePhotoService profilePhotoService;

    @PostMapping("/process_pp")
    public String saveProfilePhoto(@RequestParam("profilephoto") MultipartFile file) {
        profilePhotoService.saveProfilePhoto(file);

        return "result";
    }
}
