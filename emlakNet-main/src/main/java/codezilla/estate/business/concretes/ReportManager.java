package codezilla.estate.business.concretes;

import codezilla.estate.business.abstracts.ReportService;
import codezilla.estate.core.utilities.results.Result;
import codezilla.estate.core.utilities.results.SuccessResult;
import codezilla.estate.dataAccess.abstracts.ReportDao;

import codezilla.estate.entities.concretes.Product;
import codezilla.estate.entities.concretes.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportManager implements ReportService{

    @Autowired
    private ReportDao reportDao;

    @Override
    public Result add(Report report) {
        this.reportDao.save(report);
        return new SuccessResult();
    }
    public ReportManager(ReportDao reportDao){
        this.reportDao = reportDao;

    }
    public Product getByIlan_id(int id) {
        return reportDao.getByIlan_id(id);
    }

}
