package codezilla.estate.business.abstracts;

import codezilla.estate.core.utilities.results.Result;
import codezilla.estate.entities.concretes.SendMail;

public interface SendMailService {

    Result mailadd(SendMail sendMail);

}
