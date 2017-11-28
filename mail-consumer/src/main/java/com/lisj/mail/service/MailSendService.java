package com.lisj.mail.service;

import com.lisj.mail.entity.MailSend;
import com.lisj.mail.enumeration.MailStatus;
import com.lisj.mail.helper.GeneratorMailTemplateHelper;
import com.lisj.mail.mapper.MailSend1Mapper;
import com.lisj.mail.mapper.MailSend2Mapper;
import com.lisj.mail.vo.MailData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private GeneratorMailTemplateHelper generatorMailTemplateHelper;

    /**
     * @description: 发送
     * @author 16519
     * @date 2017/11/22 18:13
     * @version 1.0
     */
    public void sendMessage4Order(MailSend ms) {
        try {


            //1.准备数据
            Map<String, Object> param = new HashMap<>();
            param.put("userName", ms.getSendUserId());
            param.put("createDate", DateFormatUtils.format(ms.getUpdateTime(), "yyyy-MM-dd"));
            param.put("exportUrl", "www.baidu.com");
            MailData mailData = new MailData();
            mailData.setUserId(ms.getSendUserId());
            mailData.setTemplateName("SHEET");
            mailData.setFrom("layoutlog@163.com");
            mailData.setTo(ms.getSendTo());
            mailData.setSubject("{订单通知}");
            mailData.setParam(param);

            //2.模板

            generatorMailTemplateHelper.generatorAndSend(mailData);

            //3.修改数据库状态,并使用版本号(乐观更新)
            int hashCode = ms.getSendId().hashCode();
            if(hashCode % 2 == 0){
                mailSend1Mapper.updateByPrimaryKeyAndVersion(ms);
            }else{
                mailSend2Mapper.updateByPrimaryKeyAndVersion(ms);
            }
            LOGGER.info("发送邮件成功,id{},userId:{}",ms.getSendId(),ms.getSendUserId());
        } catch (MessagingException e) {

        } catch (Exception e){
            //count
            if(ms.getSendCount() > 4){
                ms.setSendStatus(MailStatus.NEED_ERR.getCode());
                LOGGER.info("发送邮件失败,id{},userId:{}",ms.getSendId(),ms.getSendUserId());
            } else {
                ms.setSendStatus(MailStatus.DRAFT.getCode());
                LOGGER.info("发送邮件失败,等待重新发送 id{},userId:{}",ms.getSendId(),ms.getSendUserId());
            }

            int hashCode = ms.getSendId().hashCode();
            if(hashCode % 2 == 0){
                mailSend1Mapper.updateByPrimaryKeyAndVersion(ms);
            }else{
                mailSend2Mapper.updateByPrimaryKeyAndVersion(ms);
            }

            throw new RuntimeException();
        }

    }
}
