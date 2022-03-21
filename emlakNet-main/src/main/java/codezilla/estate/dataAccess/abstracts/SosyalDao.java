package codezilla.estate.dataAccess.abstracts;


import codezilla.estate.entities.concretes.Sosyal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SosyalDao extends JpaRepository<Sosyal, Integer> {

    @Query("select s from Sosyal s where s.sosyal_id=:sosyal_id")
    Sosyal getBySosyal_id(@Param("sosyal_id") int sosyal_id);
}
