package com.misakiga.husky.message.consumer;

import com.misakiga.husky.cloud.notification.consumer.NotificationDispatcher;
import com.misakiga.husky.cloud.notification.exchanger.NotificationExchanger;
import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.cloud.notification.model.Notification;
import com.misakiga.husky.cloud.notification.model.SmsNotification;
import com.misakiga.husky.commons.utils.Assert;
import com.misakiga.husky.commons.utils.MapperUtils;
import com.misakiga.husky.message.sink.NotificationSink;
import com.misakiga.husky.message.task.NotificationTask;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通知处理
 * @author MISAKIGA
 */
@Service(version = "1.0.0")
public class NotificationDispatcherImpl implements NotificationDispatcher,ApplicationContextAware {


    private Collection<NotificationExchanger> exchangers;

    private ExecutorService executorService;

    private Logger logger = LoggerFactory.getLogger(NotificationDispatcherImpl.class);

    private final String EMAIL = "email";
    private final String SMS ="sms";

    public NotificationDispatcherImpl(){
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int numOfThreads = availableProcessors << 1;
        executorService = new ThreadPoolExecutor(numOfThreads, numOfThreads, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

        logger.info("初始化通知处理中心");
    }

    @Override
    @StreamListener(NotificationSink.TOPIC)
    public void dispatch(String notificationJson){

        Notification notification = null;
        try {
            String type = MapperUtils.json2pojoByTree(notificationJson,"type",String.class);
            if(EMAIL.equals(type)) {
                notification = MapperUtils.json2pojoByTree(notificationJson, "data", EmailNotification.class);
            } else if(SMS.equals(type)){
                notification = MapperUtils.json2pojoByTree(notificationJson, "data", SmsNotification.class);
            }
            Assert.notNull(notification,"Json转换失败!");
        } catch (Exception e) {
            logger.error("消息中心:" + e.getMessage());
        }

        if(notification != null && exchangers != null){
            Notification finalNotification = notification;
            exchangers.forEach((exchanger) ->{
                if(exchanger.support(finalNotification)){
                    //添加到线程池处理
                    executorService.submit(new NotificationTask(exchanger, finalNotification));
                }
            });
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, NotificationExchanger> beansOfType = applicationContext.getBeansOfType(NotificationExchanger.class);
        this.exchangers = beansOfType.values();
    }
}
