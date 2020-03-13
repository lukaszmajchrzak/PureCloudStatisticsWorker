import java.io.File;
import java.io.IOException;

public class RunCSVgen {
    private String macroPath;
    private String[] subFolders = {"csv"};
    private ManageFiles fileManager;
    public RunCSVgen(String macroPath,String destination) {
        this.macroPath = macroPath;
        fileManager = new ManageFiles(destination,subFolders);

    }

    public void runExcelScript(){
        try {
            File f = new File(macroPath);
            if (f.exists()) {
                Process process = Runtime.getRuntime().exec(macroPath);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        fileManager.deleteFiles();
    }
}
