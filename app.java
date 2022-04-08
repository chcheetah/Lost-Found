import java.sql.*;
public class app {
    public void insert(String id,String nm,String c,String lf,String ld,String cat,String onm,String ph)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into lost values('"+id+"','"+nm+"',"+Integer.parseInt(c)+",'"+lf+"','"+ld+"','"+cat+"','"+onm+"','"+ph+"')");
            }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    public String get(String a)
    {   
        String s="";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("select * from lost");
            while(r.next())
            s=r.getString(a);
            }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return s;
    }
    public void get()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("select * from lost");
            while(r.next())
            System.out.println(r.getString("item_id")+" "+r.getString("item_nm")+" "+r.getString("item_count")+" "+r.getString("last_found")+" "+r.getString("lost_at")+" "+r.getString("category")+" "+r.getString("owner_nm")+" "+r.getString("contact"));
            }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        app a=new app();
        //System.out.println(a.get("item_nm"));
        a.get();
    }
}