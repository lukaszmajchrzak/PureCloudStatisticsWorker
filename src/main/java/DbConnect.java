import java.nio.file.FileSystems;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DbConnect {
    protected Connection con;
    private ConnectionReader connectionReader = new ConnectionReader();
    private ArrayList<User> users = new ArrayList<>();



    /**
     * <p> Method connects to database using connection string typed in connectionString.xml file
     * <p>
     * to read the file method runs</p>
     */

    public void connect() {
        try {
            connectionReader.setFilePath(FileSystems.getDefault().getPath("C:\\Users\\lukmaj\\Desktop\\dbConnect.xml"));
            String connectionString = connectionReader.getConnectionURL();
            String username = connectionReader.getUsername();
            String password = connectionReader.getPassword();
            this.con = DriverManager.getConnection(connectionString,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void AddUsers(){
        this.connect();
        try(Statement stmt = this.con.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM PureCloud.Config");
            while(rs.next()){
                this.users.add(new User(rs.getString(1),rs.getString(2)));
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public ArrayList<User> getUsers(){
        AddUsers();
        return new ArrayList<>(this.users);
    }
    public HashMap<String,String> readResources(String reportType, HashMap<String,String> resources) {
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PureCloud.Resources");
            while (rs.next()) {
                if (rs.getString(1).equals(reportType)) {
                    if (resources.containsKey(rs.getString(2))) {
                        resources.replace(rs.getString(2), rs.getString(1));
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return resources;
    }
    public String getTableName(String username){
        AddUsers();
            for(int i=0;i<users.size();i++){
                if(this.users.get(i).getFullName().equals(username)){
                    return this.users.get(i).getTableName();
                }
            }
        return null;
    }
    private String[] getColumnNames(String[] query){
        this.connect();
        query[0] = "";
        try (Statement stmt = this.con.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE table_name='LukaszBlaszczyk'");
            int i = 0;
            while(rs.next()){
                if(i != 0)
                    query[0] += ",";
                query[0] += rs.getString(1);
                i++;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return query;
    }
    private String[] buildQueryForAgentMetrics(String[] line){
        String query[] = new String[2];
        query = getColumnNames(query);
        int i=0;
        query[1] = "";
        for(String s: line) {
            if (!s.isEmpty()) {
                if(s.contains("%"))
                    s = s.substring(0,s.length()-1);
                if(s.contains("N/A"))
                    s="0";
                if (i != 0)
                    query[1] += ",";
                if(i==0){
                    String split[], splitter;
                    splitter="/";
                    split = s.split(splitter);
                    s = split[2] + "20" + "/" + split[0] + "/" +split[1];
                }
                query[1] += "'" + s + "'";
                i++;
//                System.out.println(query[1]);
            }
        }
        return query;
    }

    public void putToDatabase(String[] line, String tableName){
        this.connect();
        try{
            Statement stmt = this.con.createStatement();
//            this.connect();
            String[] query = buildQueryForAgentMetrics(line);

           System.out.println("INSERT INTO PureCloud." +  tableName + " (" + query[0] + ") VALUES(" + query[1] + ");");

            stmt.executeUpdate("INSERT INTO PureCloud." +  tableName + " (" + query[0] + ") VALUES(" + query[1] + ");");
        } catch (SQLException e){
            e.getNextException();
        }
    }

    /**
     * <p> Method closes connection to database</p>
     */
    public void close(){
        try{
            con.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
