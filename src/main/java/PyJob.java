import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class PyJob implements Job {
    private String PyPath = "C:\\Users\\lukmaj\\AppData\\Local\\Programs\\Python\\Python38-32\\python.exe";
    private String PyScriptpath = "C:\\Users\\lukmaj\\Desktop\\pcStats\\purecloud.py";
    private RunPy runPy = new RunPy(PyScriptpath,PyPath);

    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        runPy.runPyScript();
    }
}
