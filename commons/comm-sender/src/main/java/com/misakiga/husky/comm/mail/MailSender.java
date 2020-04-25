package com.misakiga.husky.comm.mail;


import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送服务
 *
 * @author LIQIU
 * @date 2017年12月12日16:53:19
 */
public class MailSender {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	private JavaMailSender javaMailSender;

	private FreeMarkerConfigurer freeMarkerConfigurer;
	
    private String name;
    
    private String from;

    public MailSender(){
        logger.info("初始化邮件组件");
    }

    /**
     * 发送邮件
     */
    public void sendSimpleMail(MailSenderParams params) {
    	 MimeMessage message = null;
         try {
             message = javaMailSender.createMimeMessage();
             MimeMessageHelper helper = new MimeMessageHelper(message, true);
             helper.setFrom(new InternetAddress(this.getFrom(), MimeUtility.encodeText(this.name,"UTF-8", "B")));
             helper.setTo(params.getMailTo());
             helper.setSubject(params.getTitle());
             helper.setText(params.getContent(), true);
             this.addAttachment(helper,params);
         } catch (Exception e) {
             logger.error("发送邮件异常!" + e);
             throw new RuntimeException("发送邮件异常! from: " + name+ "! to: " + params.getMailTo());
         }
         javaMailSender.send(message);
    }

    /**
     * 发送html邮件
     */
    public void sendHtmlMail(MailSenderParams params) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.getFrom());
            helper.setTo(params.getMailTo());
            helper.setSubject(params.getTitle());
            helper.setText(params.getContent(), true);
            this.addAttachment(helper,params);
        } catch (Exception e) {
            logger.error("发送邮件异常!" + e);
            throw new RuntimeException("发送邮件异常! from: " + name+ "! to: " + params.getMailTo());
        }
        javaMailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     */
    public void sendAttachmentMail(MailSenderParams params) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(this.getFrom(), MimeUtility.encodeText(this.name,"UTF-8", "B")));
            helper.setTo(params.getMailTo());
            helper.setSubject(params.getTitle());
            helper.setText(params.getContent(), true);
            this.addAttachment(helper,params);
        } catch (Exception e) {
            logger.error("发送邮件异常!" + e);
            throw new RuntimeException("发送邮件异常! from: " + name + "! to: " + params.getMailTo());
        }
        javaMailSender.send(message);
    }

    /**
     * 发送模板邮件
     */
    public void sendTemplateMail(MailSenderParams params) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(this.getFrom(), MimeUtility.encodeText(this.name,"UTF-8", "B")));
            helper.setTo(params.getMailTo());
            helper.setSubject(params.getTitle());

            this.addAttachment(helper,params);
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(params.getTemplateFile());
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, params.getTemplateModels());
            helper.setText(html, true);
        } catch (Exception e) {
            logger.error("发送邮件异常!" + e);
            throw new RuntimeException("发送邮件异常! from: " + name + "! to: " + params.getMailTo());
        }
        javaMailSender.send(message);
    }

    /**
     * 添加附件
     * @param helper
     * @param params
     * @throws MessagingException
     */
    private void addAttachment(MimeMessageHelper helper, MailSenderParams params) throws MessagingException {
        if(params.getAttachments() != null){
            List<File> attachments = params.getAttachments();
            for (File file:attachments){
                FileSystemResource attachment = new FileSystemResource(file);
                helper.addAttachment(file.getName(), file);
            }
        }
    }

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public FreeMarkerConfigurer getFreeMarkerConfigurer() {
		return freeMarkerConfigurer;
	}

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}


    public static void main(String[] args) {

        JavaMailSenderImpl js=new JavaMailSenderImpl();
        js.setHost("smtp.qq.com");
        js.setUsername("galoliy@foxmail.com");
        js.setPassword("hsewzbzhzaczhibj");
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.timeout", 25000);
        js.setJavaMailProperties(props);
        MimeMessage message = null;
        try {
            message = js.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
           // helper.setFrom(new InternetAddress(this.getFrom(), MimeUtility.encodeText(this.name,"UTF-8", "B")));
            helper.setFrom("galoliy@foxmail.com");
            helper.setTo("2595903671@qq.com");
            helper.setSubject("测试");
            helper.setText("<h1>这是测试内容哟</h1>:您好欢迎注册husky!您的验证码为：565487");
            //addAttachmentStatic(helper,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        js.send(message);
    }


}
