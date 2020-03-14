import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzScheduler {

    public void scheduleCSVRead(){
        try {
            JobDetail job = JobBuilder.newJob(PyJob.class)
                    .withIdentity("ReadCSVJob", "group1").build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("ReadCSVJob", "group1")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0 0/15 * 1/1 * ? *"))
                    .build();

            //schedule it
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public void scheduleCSVGen(){
        try {
            JobDetail job = JobBuilder.newJob(PyJob.class)
                    .withIdentity("RunCSVgenJob", "group1").build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("RunCSVgenJob", "group1")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0 0/15 * 1/1 * ? *"))
                    .build();

            //schedule it
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    public void schedulePy() {
        try {
            JobDetail job = JobBuilder.newJob(PyJob.class)
                    .withIdentity("PyJob", "group1").build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("PyJob", "group1")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0 0 0/1 1/1 * ? *"))
                    .build();

            //schedule it
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

