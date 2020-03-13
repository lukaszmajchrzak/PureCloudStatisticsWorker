import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RunCSVgenJob {
    private String macroPath = "C:\\Users\\lukmaj\\Desktop\\pcStats\\CSVGen.xlsm";
    private RunCSVgen runCSVgen = new RunCSVgen(macroPath,"C:\\Users\\lukmaj\\test");

    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        runCSVgen.runExcelScript();
    }
}
