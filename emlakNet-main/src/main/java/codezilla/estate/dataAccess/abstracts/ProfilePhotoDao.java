package codezilla.estate.dataAccess.abstracts;

import codezilla.estate.entities.concretes.ProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePhotoDao extends JpaRepository<ProfilePhoto, Integer> {

}
