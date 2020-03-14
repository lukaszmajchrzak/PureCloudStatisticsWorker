

import java.io.*;
public class RunPy {
    private String scriptPath;
    private String pythonPath;

    public RunPy(String scriptPath, String pythonPath) {
        this.scriptPath = scriptPath;
        this.pythonPath = pythonPath;
    }

    public void runPyScript() {
        try {
           Process process = Runtime.getRuntime().exec(this.pythonPath + " " + this.scriptPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
