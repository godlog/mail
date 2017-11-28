package com.lisj.mail.service;

import com.lisj.mail.entity.MailSend;
import com.lisj.mail.enumeration.MailStatus;
import com.lisj.mail.enumeration.RedisPriorityQueue;
import com.lisj.mail.mapper.MailSend1Mapper;
import com.lisj.mail.mapper.MailSend2Mapper;
import com.lisj.mail.utils.FastJsonConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/19 19:52
 * @version:1.0.0
 */
@Service
public class MailSendService {
    private static Logger LOGGER = LoggerFactory.getLogger(MailSendService.class);
    @Autowired
    private MailSend1Mapper mailSend1Mapper;
    @Autowired
    private MailSend2Mapper mailSend2Mapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public void insert(MailSend mailSend) throws Exception {
        int bashCode = mailSend.getSendId().hashCode();
        if (bashCode % 2 == 0) {
            mailSend2Mapper.insert(mailSend);
        } else {
            mailSend1Mapper.insert(mailSend);
        }
    }


    public void sendRedis(MailSend mailSend) {
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        long priority = mailSend.getSendPriority();
        mailSend = get(mailSend.getSendId());
        long ret = 0L;
        long size = 0L;
        if (priority < 4L) {
            //延迟队列  返回结果是最新的容器的长度
            ret = opsForList.rightPush(RedisPriorityQueue.DEFER_QUEUE.getCode(), FastJsonConvertUtil.convertObjectToJSON(mailSend));
            size = opsForList.size(RedisPriorityQueue.DEFER_QUEUE.getCode());
        } else if (priority < 7L) {
            //普通队列
            ret = opsForList.rightPush(RedisPriorityQueue.NORMAL_QUEUE.getCode(), FastJsonConvertUtil.convertObjectToJSON(mailSend));
            size = opsForList.size(RedisPriorityQueue.NORMAL_QUEUE.getCode());
        } else {
            // 紧急队列
            ret = opsForList.rightPush(RedisPriorityQueue.FAST_QUEUE.getCode(), FastJsonConvertUtil.convertObjectToJSON(mailSend));
            size = opsForList.size(RedisPriorityQueue.FAST_QUEUE.getCode());
        }
        mailSend.setSendStatus(MailStatus.SEND_IN.getCode());
        if (ret == size) {
            LOGGER.info("-----------进入队列成功,id{}", mailSend.getSendId());
        }
        //失败
        else {
            mailSend.setSendContent(mailSend.getSendContent() + 1);
            LOGGER.error("--------------进入队列失败,id{}", mailSend.getSendId());
        }
        update(mailSend);
    }

    public MailSend get(String id) {
        if (id.hashCode() % 2 == 0) {
            return mailSend2Mapper.selectByPrimaryKey(id);
        } else {
            return mailSend1Mapper.selectByPrimaryKey(id);
        }
    }

    public void update(MailSend mailSend) {
        if (mailSend.getSendId().hashCode() % 2 == 0) {
            mailSend2Mapper.updateByPrimaryKeySelective(mailSend);
        } else {
            mailSend1Mapper.updateByPrimaryKeySelective(mailSend);
        }
    }

}
