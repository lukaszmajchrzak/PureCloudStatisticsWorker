public class Main {
    public static void main(String[] args) {
        QuartzScheduler scheduler = new QuartzScheduler();
//        ReadCSV readCSV = new ReadCSV("C:\\Users\\lukmaj\\Desktop\\test\\csv\\AgentMetricsSD", new DbConnect());
//        readCSV.readCSV();
        scheduler.schedulePy();
        scheduler.scheduleCSVGen();
          scheduler.scheduleCSVRead();
    }
}
