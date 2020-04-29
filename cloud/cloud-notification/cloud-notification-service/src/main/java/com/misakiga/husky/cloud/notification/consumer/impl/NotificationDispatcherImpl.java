package com.misakiga.husky.cloud.notification.consumer.impl;

import com.misakiga.husky.cloud.notification.consumer.NotificationDispatcher;
import com.misakiga.husky.cloud.notification.exchanger.NotificationExchanger;
import com.misakiga.husky.cloud.notification.sink.NotificationSink;
import com.misakiga.husky.cloud.notification.task.NotificationTask;
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
public class NotificationDispatcherImpl implements ApplicationContextAware, NotificationDispatcher {


    private Collection<NotificationExchanger> exchangers;

    private ExecutorService executorService;

    private Logger logger = LoggerFactory.getLogger(NotificationDispatcherImpl.class);

    public NotificationDispatcherImpl(){
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int numOfThreads = availableProcessors << 1;
        executorService = new ThreadPoolExecutor(numOfThreads, numOfThreads, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

        logger.info("初始化通知处理中心");
    }

    @Override
    @StreamListener(NotificationSink.TOPIC)
    public void dispatch(@Payload Object notification){
        if(notification != null && exchangers != null){
            exchangers.forEach((exchanger) ->{
                if(exchanger.support(notification)){
                    //添加到线程池处理
                    executorService.submit(new NotificationTask(exchanger,notification));
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
