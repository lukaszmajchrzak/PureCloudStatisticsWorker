import org.quartz.utils.DBConnectionManager;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadCSV {
    private String csvPath;
    private DbConnect dbConnect;

    public ReadCSV(String csvPath, DbConnect dbConnect) {
        this.csvPath = csvPath;
        this.dbConnect = dbConnect;
    }

    public void readCSV(){
        String str, username, strSplit[];
        String splitter, splitted[], line;
        splitter = ",";
        try{

            File location = new File(csvPath);
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
            Date date = new Date();
//            Charset charset = StandardCharsets.UTF_8;
            for(String f: location.list()) {
                File file = new File(csvPath + ((char) 92) + f);
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(csvPath+((char)92)+f), "UTF8"));


//                BufferedReader bufferedReader = new Files.newBufferedReader(csvPath+((char)92)+f,charset);
                str = bufferedReader.readLine();
                strSplit = str.split(" ");
                username = strSplit[0] + " " + strSplit[1];
                System.out.println(username);
                bufferedReader.readLine();
                bufferedReader.readLine();
                while((line = bufferedReader.readLine()) != null){

                    splitted = line.split(splitter);
                    if(Character.isDigit(splitted[0].charAt(0))) {
//                        System.out.println(line);
                        dbConnect.putToDatabase(splitted, dbConnect.getTableName(username));
                    }

                }

               bufferedReader.close();
                file.delete();
//                file.renameTo(new File(csvPath + ((char)92) + ((char)92) + "processed" + ((char)92) + ((char)92) + f.substring(0,f.length()-4) + dateFormat.format(date) + ".csv" ));
           }
        } catch(IOException e){
                e.printStackTrace();
        }
    }
}
