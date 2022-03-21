package codezilla.estate.dataAccess.abstracts;

import codezilla.estate.entities.concretes.SendMail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendMailDao extends JpaRepository<SendMail ,Integer> {
}
