package com.lisj.mail.task;

import com.lisj.mail.entity.MailSend;
import com.lisj.mail.enumeration.RedisPriorityQueue;
import com.lisj.mail.service.MailSendService;
import com.lisj.mail.utils.FastJsonConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/22 15:27
 * @version:1.0.0
 */
@Component
public class ConsumerMailTask {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private MailSendService mailSendService;

    @Scheduled(initialDelay = 5000,fixedDelay = 2000)
    public void intervalFast() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        String ret = listOperations.leftPop(RedisPriorityQueue.FAST_QUEUE.getCode());

        if(!StringUtils.isBlank(ret)){
            System.err.println(ret);
            MailSend mailSend =  FastJsonConvertUtil.convertJSONToObject(ret, MailSend.class);
            mailSendService.sendMessage4Order(mailSend);
        }
    }

//    @Scheduled(initialDelay = 5000,fixedDelay = 10000)
//    public void intervalNormal(){
//
//    }
//
//    @Scheduled(initialDelay = 5000,fixedDelay = 60000)
//    public void intervalDefer(){
//
//    }
}
