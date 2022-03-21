package codezilla.estate.dataAccess.abstracts;

import codezilla.estate.entities.concretes.SSS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SSSDao extends JpaRepository<SSS,Integer> {
}
