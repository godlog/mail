package com.lisj.mail.service.api;

import com.lisj.mail.constant.Const;
import com.lisj.mail.entity.MailSend;
import com.lisj.mail.enumeration.MailStatus;
import com.lisj.mail.service.MailSendService;
import com.lisj.mail.utils.KeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/19 20:41
 * @version:1.0.0
 */
@RestController
public class ProducerController {
    private static Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private MailSendService mailSendService;


    @RequestMapping(value = "/send", produces = {"application/json;charset=UTF-8"})
    public void send(@RequestBody(required = true) MailSend mailSend) throws Exception {
        //1. validate  校验数据
        //2.insert

        try {
            mailSend.setSendId(KeyUtil.generatorUUID());
            mailSend.setSendStatus(MailStatus.DRAFT.getCode());
            mailSend.setVersion(0L);
            mailSend.setSendCount(0L);
            mailSend.setUpdateBy(Const.SYS_RUNTIME);
            mailSendService.insert(mailSend);
            //3.数据写到redis 队列
            mailSendService.sendRedis(mailSend);
        } catch (Exception e) {
            LOGGER.error("异常信息{}", e);
            throw new RuntimeException(e);
        }


    }

}
