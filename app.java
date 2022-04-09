import java.sql.*;
public class app {
    public void insert(String nm,String c,String lf,String ld,String cat,String onm,String ph)
    {
        try{
           
            
            int i1=0;
            String i2="";
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            Statement smt2=con.createStatement();
            ResultSet r=smt2.executeQuery("select item_id from lost");
            while(r.next())
            if(r.isLast())
            i1=r.getInt("item_id");
            i1=i1+1;
            if(i1<10)
            i2=("000"+i1);
            else if(i1<100)
            i2=("00"+i1);
            else if(i1<1000)
            i2=("0"+i1);
            else
            i2=Integer.toString(i1);
            stmt.executeUpdate("insert into lost values('"+i2+"','"+nm+"',"+Integer.parseInt(c)+",'"+lf+"','"+ld+"','"+cat+"','"+onm+"','"+ph+"')");
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
    public void search(String iname)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("select * from lost where item_nm='"+iname+"'");
            while(r.next())
            System.out.println(r.getString("item_id")+" "+r.getString("item_nm")+" "+r.getString("item_count")+" "+r.getString("last_found")+" "+r.getString("lost_at")+" "+r.getString("category")+" "+r.getString("owner_nm")+" "+r.getString("contact"));
            }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    public void remove(String id)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            stmt.execute("delete from lost where item_id='"+id+"'");
            }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    public void update()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            stmt.execute("update lost set item_id='0004' where item_nm='aasasa'");
            }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    /*public void alter()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            stmt.execute("alter table lost modify column item_id varchar(10)");
            }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }*/
    public static void main(String[] args){
        app a=new app();
        //System.out.println(a.get("item_nm"));
        //a.get();
        //a.search("AirPods");
        //a.update();
        //a.alter();
        a.get();
    }
}