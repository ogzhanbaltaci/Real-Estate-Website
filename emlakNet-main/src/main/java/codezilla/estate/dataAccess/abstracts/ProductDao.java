package codezilla.estate.dataAccess.abstracts;

import codezilla.estate.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends JpaRepository<Product, Integer> {


    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.degerlendirme_ort =:ort where p.ilan_id=:id")
    void updateOrt(@Param("id") int id, @Param("ort") double ort);


    //Ilan_id ismi sıkıntı olduğu için query yazdım.
    @Query("select p from Product p where p.ilan_id=:id")
    Product getByIlan_id(@Param("id")int id);

}
