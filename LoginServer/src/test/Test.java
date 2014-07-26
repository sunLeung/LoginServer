package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	
	public static void main(String[] args) {
		
	
	// 驱动程序名
    String driver = "com.mysql.jdbc.Driver";

    // URL指向要访问的数据库名scutcs
    String url = "jdbc:mysql://127.0.0.1:3306/login_server";

    // MySQL配置时的用户名
    String user = "root"; 

    // MySQL配置时的密码
    String password = "liang";

    try { 
     // 加载驱动程序
     Class.forName(driver);

     // 连续数据库
     Connection conn = DriverManager.getConnection(url, user, password);

     if(!conn.isClosed()) 
      System.out.println("Succeeded connecting to the Database!");

     // statement用来执行SQL语句
     Statement statement = conn.createStatement();

     // 要执行的SQL语句
     String sql = "select * from Player";

     // 结果集
     ResultSet rs = statement.executeQuery(sql);


    } catch(SQLException e) {


     e.printStackTrace();


    } catch(Exception e) {


     e.printStackTrace();


    } 
} 
}
