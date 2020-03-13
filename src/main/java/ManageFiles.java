import java.io.File;

public class ManageFiles {
    private String mainFolder;
    private String[] subFolders;

    public ManageFiles(String mainFolder, String[] subFolders) {
        this.mainFolder = mainFolder;
        this.subFolders = subFolders;
    }
    public void deleteFiles(){
        File file = new File(mainFolder);

        for(String f : file.list()){
            if(!compareSubFolders(f)){
                new File(mainFolder + ((char) 92) + f).delete();
            }
        }
    }

    private boolean compareSubFolders(String f){
        for(String folders : this.subFolders){
            if(folders.equals(f))
                return true;
        }
        return false;
    }
}
