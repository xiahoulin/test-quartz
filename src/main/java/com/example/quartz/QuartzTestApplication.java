package com.example.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzTestApplication {

    public static void main(String[] args) throws SchedulerException{
        SpringApplication.run(QuartzTestApplication.class, args);

        //创建一个JobDetail实例，将该实例与HelloJob绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob", "group1")
                                        .usingJobData("message","hello myJob1")
                                        .usingJobData("mathJobValue",3.14)
                                        .build();
        //创建一个Trigger实例，定义该job立即执行，并且每隔两秒执行一次
        Trigger trigger = TriggerBuilder.
                newTrigger().
                withIdentity("myTrigger", "group1").
                startNow().
                withSchedule(SimpleScheduleBuilder.simpleSchedule().
                        withIntervalInSeconds(2).
                        repeatForever()).
                usingJobData("message","hello myTrigger1").
                usingJobData("doubleTriggerValue",2.0D).
                build();

        //创建Scheduler实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }

}
