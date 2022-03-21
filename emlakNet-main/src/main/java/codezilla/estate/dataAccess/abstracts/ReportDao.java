package codezilla.estate.dataAccess.abstracts;

import codezilla.estate.entities.concretes.Product;
import codezilla.estate.entities.concretes.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportDao extends JpaRepository<Report,Integer> {

    @Query("select r from Report r where r.productid=:id")
    Product getByIlan_id(int id);

}
