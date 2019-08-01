package com.example.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建人：xiahoulin
 * 创建时间：2019-07-25  16:49
 * 描述：
 */
public class HelloJob implements Job {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //打印当前执行的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("调用一次任务 " + simpleDateFormat.format(date));
        //编写业务逻辑
//        System.out.println("HelloWorld");
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("My job name and group are: " + jobKey.getName() + ":" + jobKey.getGroup());
        TriggerKey triggerKey = context.getTrigger().getKey();
        System.out.println("My Trigger name and group are: " + triggerKey.getName() + ":" + triggerKey.getGroup());
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        JobDataMap tdataMap = context.getJobDetail().getJobDataMap();
        String jobMsg = dataMap.getString("message");

    }
}
