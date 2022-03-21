package codezilla.estate.business.concretes;

import codezilla.estate.dataAccess.abstracts.PuanDao;
import codezilla.estate.entities.concretes.Puan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuanManager {

    @Autowired
    private PuanDao puanDao;

    public void add(Puan puan) {
        puanDao.save(puan);
    }

    public List<Puan> getByIlan_id(int id) {
        return puanDao.getByIlanid(id);
    }

    public void savePuanToDB(int puan, int ilan_id){
        Puan p = new Puan();

        p.setPuan(puan);
        p.setIlanid(ilan_id);
        puanDao.save(p);

    }

}
