package codezilla.estate.api.controllers;

import codezilla.estate.business.abstracts.SendMailService;
import codezilla.estate.dataAccess.abstracts.SendMailDao;
import codezilla.estate.entities.concretes.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping
public class SendMailController {

    @Autowired
    private JavaMailSender mailSender;

    private SendMailService sendMailService;

    @Autowired
    private SendMailDao sendMailDao;

    @Autowired
    public SendMailController(SendMailService sendMailService) {
        this.sendMailService = sendMailService;
    }

    public SendMailController() {


    }

    @GetMapping("/Destek")
    public String DestekPage(Model model){
        model.addAttribute("sendMail",new SendMail());
        return "Destek";
    }
    @PostMapping("/process")
    public String destekSubmit(HttpServletRequest request,SendMail sendMail )  throws MessagingException, UnsupportedEncodingException {

        String email = request.getParameter("email");
        String konuBasligi = request.getParameter("konuBasligi");
        String content = request.getParameter("icerik");

        SimpleMailMessage message =new SimpleMailMessage();
        message.setFrom("codezillacompany2@gmail.com\n");
        message.setTo("codezillacompany2@gmail.com\n");

        String mailSubject= email +" Bir mesaj gönderdi.";
        String mailContent = "Gönderen E-mail: " + email +"\n";
        mailContent += "Konu Başlığı  : " + konuBasligi + "\n";
        mailContent += "İçerik : " + content + "\n";

        message.setSubject(mailSubject);
        message.setText(mailContent);
        mailSender.send(message);
        sendMailDao.save(sendMail);



        return "result";
    }
    /*@PostMapping("/process")
    public String destekSubmit(SendMail sendMail){
        sendMailDao.save(sendMail);
        return "result";
    }*/

}
