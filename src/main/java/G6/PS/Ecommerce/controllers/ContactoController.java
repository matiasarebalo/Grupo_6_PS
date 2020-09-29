package G6.PS.Ecommerce.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import G6.PS.Ecommerce.helpers.ViewRouteHelper;

@Controller
public class ContactoController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/contacto")
    public ModelAndView contacto() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM_CONTACT);

        String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

        boolean admin = false;
        if (roleString.equals("[ROLE_ADMIN]")) {
            admin = true;
        }
        mAV.addObject("admin", admin);
        return mAV;
    }

    @PostMapping("/contacto")
    public ModelAndView submitContacto(HttpServletRequest request)
            throws MessagingException, UnsupportedEncodingException {

        ModelAndView mAV = new ModelAndView(ViewRouteHelper.INDEX);
        String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

		boolean admin = false;
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
        mAV.addObject("admin", admin);
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");

        MimeMessage messageSend = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messageSend);

        String mailSubject = firstName + " " + lastName + " " + "ha enviado un mensaje";
        String mailContent = "<p><b>Enviado por:</b>" + " " +  firstName + "</p>";
        mailContent += "<p><b>Email:</b>" + " " +  email + "</p>";
        mailContent += "<p><b>Telefono:</b>" + " " +  phone + "</p>";
        mailContent += "<p><b>Mensaje:</b>" + " " +  message + "</p>";

        helper.setFrom("el.deporte.online.g6@gmail.com", "El Deporte Online");
        helper.setTo("el.deporte.online.g6@gmail.com");
        helper.setSubject(mailSubject);
        helper.setText(mailContent, true);

        mailSender.send(messageSend);

        return mAV;
    }

}
