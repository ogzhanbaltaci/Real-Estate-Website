package codezilla.estate.api.controllers;

import codezilla.estate.business.concretes.CustomerServices;
import codezilla.estate.core.exceptions.CustomerNotFoundException;
import codezilla.estate.entities.concretes.User;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CustomerServices customerService;


    @GetMapping("/gmailform")
    public String showForgotPasswordForm(Model model) {
        model.addAttribute("pagetitle", "Forgot Password");
        return "gmail";
    }



    @PostMapping("/gmailform")
    public String processForgotPassword(HttpServletRequest request, Model model) throws CustomerNotFoundException, MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        customerService.updateResetPasswordToken(token, email);
        String resetPasswordLink = Utility.getSiteURL(request) + "/sifremi_unuttumform?token=" + token;
        sendEmail(email, resetPasswordLink);
        model.addAttribute("message", "Linki gönderdik lütfen kontrol edin.");

        return "gmail";
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("emlaknet.com", "CodeZilla Destek");
        helper.setTo(recipientEmail);

        String subject = "Şifre değiştirme";

        String content = "<p>Merhaba,</p>"
                + "<p>Şifre değiştirme isteğinde bulundunuz.</p>"
                + "<p>Aşağıdaki linke tıklayarak şifrenizi değiştirin:</p>"
                + "<p><a href=\"" + link + "\">Şifremi değiştir</a></p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    @GetMapping("/sifremi_unuttumform")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = customerService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "sifremi_unuttum";
    }

    @PostMapping("/sifremi_unuttumform")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = customerService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            customerService.updatePassword(user, password);

            model.addAttribute("message", "Şifrenizi başarılı bir şekilde değiştirdiniz.");
        }
        return "result";
    }
}
