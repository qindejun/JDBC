package dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import util.Dbutil;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class UserDao {
    public void AddClient() throws Exception {
        Dbutil dbUtil = new Dbutil();
        Scanner sc = new Scanner(System.in);
        //接收页面提交的数据
        System.out.println("请输入相关内容");
        System.out.print("请输入姓名:");
        String name = sc.next();
        System.out.print("请输入性别:");
        String sex= sc.next();
        System.out.print("请输入年龄:");
        int age = sc.nextInt();
        System.out.print("请输入工资:");
        int salary = sc.nextInt();
        System.out.print("请输入电话:");
        int phone = sc.nextInt();
        /*
             添加数据
               1.SQL:insert into account(name,sex,age,salary,phone)values(?,?,?,?,?);
               2.参数:需要除id之外的所有参数值
               2.结果:boolean
         */
        //获取数据库连接Connection
        Connection conn = dbUtil.getCon();

        //2.定义SQL语句
        String sql = "insert into account(name,sex,age,salary,phone)values(?,?,?,?,?);";

        //3.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数
        pstmt.setString(1, name);
        pstmt.setString(2, sex);
        pstmt.setInt(3, age);
        pstmt.setInt(4, salary);
        pstmt.setInt(5, phone);

        //5.执行SQL
        int count = pstmt.executeUpdate();//影响的行数

        //处理结果
        if (count > 0) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败！");
        }
        //7.释放资源
        pstmt.close();
        conn.close();
    }

    public void AddUser() throws Exception {
        Dbutil dbUtil = new Dbutil();
        Scanner sc = new Scanner(System.in);
        //接收页面提交的数据
        System.out.println("请输入相关内容");
        System.out.print("请输入用户名:");
        String username = sc.next();
        System.out.print("请输入密码:");
        String password = sc.next();
        /*
             添加数据
               1.SQL:insert into user(username,password)values(?,?);
               2.参数:需要除id之外的所有参数值
               2.结果:boolean
         */
        //获取数据库连接Connection
        Connection conn = dbUtil.getCon();

        //2.定义SQL语句
        String sql = "insert ignore into user(username,password)values(?,?)";

        //3.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数
        pstmt.setString(1, username);
        pstmt.setString(2, password);

        //5.执行SQL
        int count = pstmt.executeUpdate();//影响的行数

        //处理结果
        if (count > 0) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败！");
        }
        //7.释放资源
        pstmt.close();
        conn.close();
    }

    public void Delete() throws Exception {
        Dbutil dbUtil = new Dbutil();
        /*
             删除数据
               1.SQL:delete from account where id = ?;
               2.参数:需要id
               2.结果:boolean
        */
        Scanner sc = new Scanner(System.in);
        //接收页面提交的数据
        System.out.println("请输入您要删除的ID:");
        int ID = sc.nextInt();

        //获取数据库连接Connection
        Connection conn = dbUtil.getCon();

        //2.定义SQL语句
        String sql = "delete from account where id = ?;";
        String sql2 = "ALTER TABLE account AUTO_INCREMENT = 1;";

        //3.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
        //4.设置参数
        pstmt.setInt(1, ID);

        //5.执行SQL
        int count = pstmt.executeUpdate();//影响的行数
        pstmt2.executeUpdate();
        //处理结果
        if (count > 0) {
            System.out.println("执行成功！");
        }

        //7.释放资源
        pstmt.close();
        conn.close();
    }
    public void Update() throws Exception {
        Dbutil dbUtil = new Dbutil();
        /*
             修改数据
               1.SQL:update account set
                     name = ?,
                     sex = ?,
                     age = ?,
                     salary = ?,
                     phone = ?
                     where id = ?;
               2.参数:需要所有参数
               2.结果:boolean
        */
        //接收页面提交的数据
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改ID:");
        int ID = sc.nextInt();
        System.out.println("姓名:");
        String name = sc.next();
        System.out.println("性别:");
        String sex = sc.next();
        System.out.println("年龄:");
        int age = sc.nextInt();
        System.out.println("工资:");
        int salary = sc.nextInt();
        System.out.println("电话:");
        int phone = sc.nextInt();
        System.out.println();

        //获取数据库连接Connection
        Connection conn = dbUtil.getCon();

        //2.定义SQL语句
        String sql = "update account set\n" +
                "name = ?,\n" +
                "sex = ?,\n" +
                "age = ?,\n" +
                "salary = ?,\n" +
                "phone = ?\n" +
                "where id = ?;";

        //3.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数
        pstmt.setString(1,name);
        pstmt.setString(2,sex);
        pstmt.setInt(3,age);
        pstmt.setInt(4,salary);
        pstmt.setInt(5,phone);
        pstmt.setInt(6,ID);

        //5.执行SQL
        int count = pstmt.executeUpdate();//影响的行数

        //处理结果
        if(count>0){
            System.out.println("执行成功！");
        }else{
            System.out.println("执行失败！");
        }

        //7.释放资源
        pstmt.close();
        conn.close();
    }

    public void Select() throws Exception {
        Dbutil dbUtil = new Dbutil();
    /*
        查询所有数据
        1.SQL:select * from account;
        2.参数:不需要
        2.结果:List<Brand>
     */

        //获取数据库连接Connection
        Connection conn = dbUtil.getCon();

        //2.定义SQL语句
        String sql = "select * from account";

        //3.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数

        //5.执行SQL
        ResultSet rs = pstmt.executeQuery();
        System.out.println("ID"+"\t"+"姓名"+"\t"+"性别"+"\t"+"年龄"+"\t"+"工资"+"\t"+"电话");
        //6.处理结果
        while (rs.next()) {
            //获取对象
            int ID = rs.getInt("ID");
            String name = rs.getString("name");
            String sex = rs.getString("sex");
            int age = rs.getInt("age");
            int salary = rs.getInt("salary");
            int phone = rs.getInt("phone");
            System.out.println(ID+"\t"+name+"\t"+sex+"\t"+age+"\t"+salary+"\t"+phone);
        }
        //7.释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }
}
