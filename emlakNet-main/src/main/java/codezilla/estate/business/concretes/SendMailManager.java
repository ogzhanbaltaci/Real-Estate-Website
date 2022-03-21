package codezilla.estate.business.concretes;

import codezilla.estate.business.abstracts.SendMailService;
import codezilla.estate.core.utilities.results.Result;
import codezilla.estate.core.utilities.results.SuccessResult;
import codezilla.estate.dataAccess.abstracts.SendMailDao;
import codezilla.estate.entities.concretes.SendMail;
import org.springframework.stereotype.Service;

@Service
public class SendMailManager implements SendMailService {
    private SendMailDao sendMailDao;

    @Override
    public Result mailadd(SendMail sendMail) {
        this.sendMailDao.save(sendMail);
        return new SuccessResult();

    }
    public SendMailManager(SendMailDao sendMailDao){
        this.sendMailDao = sendMailDao;

    }
}
