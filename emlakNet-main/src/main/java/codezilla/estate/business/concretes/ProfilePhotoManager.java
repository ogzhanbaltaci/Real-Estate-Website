package codezilla.estate.business.concretes;

import codezilla.estate.business.abstracts.ProfilePhotoService;
import codezilla.estate.dataAccess.abstracts.ProfilePhotoDao;
import codezilla.estate.entities.concretes.ProfilePhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ProfilePhotoManager implements ProfilePhotoService {


    @Autowired
    ProfilePhotoDao profilePhotoDao;

    @Override
    public void saveProfilePhoto(MultipartFile file) {
        ProfilePhoto profilePhoto = new ProfilePhoto();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if(fileName.contains("..")){
            System.out.println("not a valid file");
        }
        try {
            profilePhoto.setPp(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        profilePhotoDao.save(profilePhoto);

    }
}
