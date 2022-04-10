package com.example.lostfound;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
class remove extends AsyncTask<String,Void, Integer>{
    @Override
    protected Integer doInBackground(String ... strings) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            stmt.execute("delete from lost where item_id='"+strings+"'");
            return 0;
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
}
class founde extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... strings) {
        try{

            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(strings[0]);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
class get_found extends AsyncTask<Void,Void,String[]>{
    ArrayList<String> temp = new ArrayList<>();
    @Override
    protected String[] doInBackground(Void... voids) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con= DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("select * from found_item");
            while(r.next())
                temp.add(r.getString("item_nm")+"\t"+r.getString("found_on")+"\t"+r.getString("found_at")+"\t"+r.getString("founder_nm")+"\t"+r.getString("founder_contact"));
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            temp.add("error, unable to connect to server");
        }
        finally{
            String[] o = temp.toArray(new String[0]);
            return o;
        }
    }
    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
    }
}
class insert extends AsyncTask<String, Void, Void> {
    @Override
    public Void doInBackground(String... strings) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(strings[0]);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
class gete extends AsyncTask<Void,Void,String[]>{
    ArrayList<String> temp = new ArrayList<>();
    public String[] getu() {
        return temp.toArray(new String[0]);
    }
    @Override
    protected String[] doInBackground(Void... voids) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con= DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("select * from lost");
            while(r.next())
                temp.add(r.getString("item_id")+"\t"+r.getString("item_nm")+"\t"+r.getString("item_count")+"\t"+r.getString("last_found")+"\t"+r.getString("lost_at")+"\t"+r.getString("category")+"\t"+r.getString("owner_nm")+"\t"+r.getString("contact"));
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            temp.add("error, unable to connect to server");
        }
        finally{
            String[] o = temp.toArray(new String[0]);
            return o;
        }
    }
    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
    }
}
public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "samples.flutter.dev/battery";
    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {
                            // Note: this method is invoked on the main thread.
                            if(call.method.contains("insert")){
                                StringTokenizer d = new StringTokenizer(call.method.replace("insert",""),"\t");
                                String[] x = new String[8];
                                int i = 0;
                                while (d.hasMoreTokens()){
                                    x[i] = d.nextToken();
                                    i++;
                                }
                                String rewq =  "insert into lost values('"+x[0]+"','"+x[1]+"',"+Integer.parseInt(x[2])+",'"+x[3]+"','"+x[4]+"','"+x[5]+"','"+x[6]+"','"+x[7]+"')";
                                new insert().execute(rewq);
                                result.success(1);
                            }
                            if(call.method.contains("found")){
                                StringTokenizer d = new StringTokenizer(call.method.replace("found",""),"\t");
                                String[] x = new String[5];
                                int i = 0;
                                while (d.hasMoreTokens()){
                                    x[i] = d.nextToken();
                                    i++;
                                }
                                String rewq =  "insert into found_item values('"+x[0]+"','"+x[1]+"','"+x[2]+"','"+x[3]+"','"+x[4]+"')";
                                new founde().execute(rewq);
                                result.success(1);
                            }
                            if(call.method.equals("getf")){
                                String[] q = new String[0];
                                try {
                                    q = new get_found().execute().get();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                String th = "";
                                for(String u: q){
                                    th+=u;
                                    th+="^#";
                                }
                                result.success(th);
                            }
                            if(call.method.equals("get")){
                                String[] q = new String[0];
                                try {
                                    q = new gete().execute().get();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                String th = "";
                                for(String u: q){
                                    th+=u;
                                    th+="^#";
                                }
                                result.success(th);
                            }
                            if(call.method.contains("delete")){
                                remove eee = new remove();
                                String f = call.method.replace("delete","");
                                 new remove().execute(f);
                                result.success(0);
                                }
                            // future improvements : add a search function, potentially based on attribute type !
                        }
                );

    }
}
