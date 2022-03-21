package codezilla.estate.business.abstracts;

import org.springframework.web.multipart.MultipartFile;

public interface ProfilePhotoService {

    public void saveProfilePhoto(MultipartFile file);
}
