package com.lisj.mail.vo;

import java.util.Map;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/22 18:14
 * @version:1.0.0
 */
public class MailData {

    String userId; // 收件人
    String subject; // 邮件主题
    String from; // 邮件发送人
    String to; // 邮件收件人
    String content; // 邮件内容
    String templateName; // 邮件模板名称
    Map<String, Object> param; // 参数集

    public MailData() {
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    public Map<String, Object> getParam() {
        return param;
    }
    public void setParam(Map<String, Object> param) {
        this.param = param;
    }
}
