package com.lisj.mail.task;

import com.lisj.mail.entity.MailSend;
import com.lisj.mail.service.MailSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/28 20:54
 * @version:1.0.0
 */
@Component
public class RetryTask {
    private static Logger LOGGER = LoggerFactory.getLogger(RetryTask.class);
    @Autowired
    private MailSendService mailSendService;

    /**
     * @description: 重发失败的邮件
     * @author 16519
     * @date 2017/11/28 21:15
     * @version 1.0
     */
    @Scheduled(initialDelay = 5000,fixedDelay = 10000)
    public void retry(){
        List<MailSend> list= mailSendService.queryDraftList();
        for (MailSend ms: list) {
            mailSendService.sendRedis(ms);
        }
    }
}
