package codezilla.estate.business.concretes;

import codezilla.estate.dataAccess.abstracts.SSSDao;
import codezilla.estate.entities.concretes.SSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SSSManager {
    @Autowired
    private SSSDao sss;

    public List<SSS> listAll() {

        return sss.findAll();
    }


}
