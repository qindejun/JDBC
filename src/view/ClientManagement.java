package view;

import dao.*;
import util.Dbutil;

import java.sql.*;
import java.util.Scanner;

public class ClientManagement {
    public static void UserLogin(Connection conn) throws Exception {
        UserDao userDao = new UserDao();
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名:");
        String name = sc.next();
        System.out.print("请输入密码:");
        String pwd = sc.next();
        //定义sql
        String sql = "select * from user where username = ? and password = ?";

        //获取PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //设置？的值
        pstmt.setString(1, name);
        pstmt.setString(2, pwd);

        //执行sql
        ResultSet rs = pstmt.executeQuery();

        //判断登录是否成功
        if (rs.next()) {
            System.out.println("登录成功！");
            while(true) {
                userDao.Select();
                System.out.println("----------------------------------------------------------");
                System.out.println("1.添加客户 2.修改客户 3.删除客户 4.客户列表 5.退出  请选择(1-5):");
                int n = sc.nextInt();
                if(n==5) break;
                switch (n) {
                    case 1:
                        userDao.AddClient();
                        break;
                    case 2:
                        userDao.Update();
                        break;
                    case 3:
                        userDao.Delete();
                        break;
                    case 4:
                        userDao.Select();
                        break;
                }
            }
        }else {
            System.out.println("用户名或密码错误！");
        }
        //释放资源
        pstmt.close();
        conn.close();
    }


    public static void main() throws Exception {
        UserDao userDao = new UserDao();
        Dbutil dbutil = new Dbutil();
        Connection conn = dbutil.getCon();
        //接收用户输入的用户名和密码
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("-------------------------客户管理系统-------------------------");
            System.out.println("欢迎使用客户管理系统，请问您要进行什么操作：");
            System.out.println("1.会员登录   2.会员注册");
            int i = sc.nextInt();
            if (i == 1) {
                UserLogin(conn);
                break;
            } else {
               userDao.AddUser();
            }
        }
        //释放资源
        conn.close();
    }
}
