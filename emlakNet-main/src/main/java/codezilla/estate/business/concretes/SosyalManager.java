package codezilla.estate.business.concretes;

import codezilla.estate.business.abstracts.SosyalService;
import codezilla.estate.dataAccess.abstracts.SosyalDao;
import codezilla.estate.entities.concretes.Sosyal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SosyalManager implements SosyalService {

    private SosyalDao sosyalDao;

    @Autowired
    public SosyalManager(SosyalDao sosyalDao) {
        this.sosyalDao = sosyalDao;
    }

    @Override
    public Sosyal getBySosyal_id(int sosyal_id) {
        return sosyalDao.getBySosyal_id(sosyal_id);
    }
}
