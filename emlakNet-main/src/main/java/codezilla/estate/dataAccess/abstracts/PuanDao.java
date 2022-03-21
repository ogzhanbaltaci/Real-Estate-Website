package codezilla.estate.dataAccess.abstracts;

import codezilla.estate.entities.concretes.Puan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuanDao extends JpaRepository<Puan, Integer> {

    List<Puan> getByIlanid(int id);
}
