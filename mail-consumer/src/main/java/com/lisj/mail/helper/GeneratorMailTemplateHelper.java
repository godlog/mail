package com.lisj.mail.helper;

import com.lisj.mail.constant.Const;
import com.lisj.mail.vo.MailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/22 23:14
 * @version:1.0.0
 */
@Service
public class GeneratorMailTemplateHelper {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void generatorAndSend(MailData mailData) throws MessagingException {
        Context context =new Context();
        context.setLocale(Locale.CHINA);
        context.setVariables(mailData.getParam());
        String templateLoaciton=mailData.getTemplateName();
        String content = templateEngine.process(templateLoaciton,context);

        mailData.setContent(content);


        send(mailData);
    }

    private void send(MailData mailData) throws MessagingException {
        MimeMessage mime = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeHelp =new MimeMessageHelper(mime,true, Const.CHARSET_UTF8);
        mimeHelp.setFrom(mailData.getFrom());
        mimeHelp.setTo(mailData.getTo());
        mimeHelp.setSubject(mailData.getSubject());
        mimeHelp.setText(mailData.getContent(),true);
        javaMailSender.send(mime);
    }


}
