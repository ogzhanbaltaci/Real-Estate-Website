package codezilla.estate.business.abstracts;

import codezilla.estate.core.utilities.results.Result;
import codezilla.estate.entities.concretes.Product;
import codezilla.estate.entities.concretes.Report;

public interface ReportService {
    Result add(Report report);
    Product getByIlan_id(int id);
}
