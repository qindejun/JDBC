package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbutil {
    //获取连接
    private String url = "jdbc:mysql://localhost:3306/db1?&useSSL=false&useServerPrepStmts=true";
    private String username = "root";//用户名
    private String password = "123456";//密码
    private String jdbcName = "com.mysql.cj.jdbc.Driver";

    /*
        获取数据库连接
     */
    public Connection getCon() throws Exception{
        Class.forName(jdbcName);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    /*
       关闭数据库连接
     */
    public void closeCon(Connection con) throws Exception{
        if(con!=null){
            con.close();
        }
    }

    public static void main(String[] args) {
        Dbutil dbUtil = new Dbutil();
        try {
            Connection con = dbUtil.getCon();
            System.out.println("数据库连接成功！");
            dbUtil.closeCon(con);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
    }
}
