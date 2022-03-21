package codezilla.estate.business.abstracts;

import codezilla.estate.entities.concretes.Sosyal;
import org.springframework.data.repository.query.Param;

public interface SosyalService {
    Sosyal getBySosyal_id(int sosyal_id);
}
