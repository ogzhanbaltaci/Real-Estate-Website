
package codezilla.estate.api.controllers;

import codezilla.estate.business.abstracts.ProductService;
import codezilla.estate.business.abstracts.ReportService;
import codezilla.estate.business.concretes.ReportManager;
import codezilla.estate.dataAccess.abstracts.ReportDao;

import codezilla.estate.entities.concretes.Product;
import codezilla.estate.entities.concretes.Puan;
import codezilla.estate.entities.concretes.Report;
import codezilla.estate.entities.concretes.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class ReportController{
    @Autowired
    private JavaMailSender javaMailSender;

    private ReportService reportService;

    @Autowired
    private ReportDao reportDao;

    ReportManager reportManager;

    @Autowired
    ProductService productService;



    public ReportController() {

    }


    public ReportController(ReportService reportService){
        this.reportService=reportService;
    }

    @GetMapping("/ilanbildir/{ilan_id}")
    public String reportPage(Model model,Model model1,@PathVariable(name = "ilan_id" )int id){

        Product product = productService.getByIlan_id(id); //ilanın idsini çekebilmek için
        model.addAttribute("ilanlar", product);
        model1.addAttribute("report",new Report());


        return "ilanbildir";
    }

    @PostMapping("/report_process/r/{ilan_id}")
    public String reportSubmit(HttpServletRequest request, Report report , @PathVariable(name = "ilan_id") int id) {

        Product product = productService.getByIlan_id(id);

        int ilanid1= product.getIlan_id();
        String email = request.getParameter("bildir_email");
        String content = request.getParameter("bildir_icerik");
        report.setProductid(ilanid1);//ilgili ilan id yi bildir tablosuna yazdırmak için yapılıyor.
        SimpleMailMessage message =new SimpleMailMessage();
        message.setFrom("codezillacompany2@gmail.com\n");
        message.setTo("codezillacompany2@gmail.com\n");

        String mailSubject= email +" "+ ilanid1 +" Numaralı ilanı bildirdi ";
        String mailContent = "Gönderen E-mail: " + email +"\n";
        mailContent += "İçerik : " + content +"\n" + "İlgili ilanın ID numarası:" + ilanid1 +"\n";

        message.setSubject(mailSubject);
        message.setText(mailContent);

        javaMailSender.send(message);

        reportDao.save(report);

        return "result";
    }



}
