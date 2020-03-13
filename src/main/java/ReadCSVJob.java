import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ReadCSVJob {
    DbConnect dbConnect = new DbConnect();
    private String reportPath = "C:\\Users\\lukmaj\\Desktop\\test\\csv\\AgentMetricsSD";
    private ReadCSV readCSV = new ReadCSV(reportPath,dbConnect);
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        readCSV.readCSV();
    }
}
