package com.haiyoung.hyweb.jdbc;

import java.sql.*;

public class JdbcInstance {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/haiyoung?useSSL=true&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String passwd = "123456";

    //获取连接
    public static Connection getConnection() throws SQLException{
/*        try {
            Class.forName(JDBC_DRIVER);
            在java 6中，引入了service provider的概念，即可以在配置文件中配置service（可能是一个interface或者abstract class）的provider（即service的实现类）。配置路径是：/META-INF/services/下面。详细信息见：http://docs.oracle.com/javase/6/docs/technotes/guides/jar/jar.html#Service%20Provider
            而java.sql.DriverManager也添加了对此的支持，因此，在JDK6中，DriverManager的查找Driver的范围为：
            1）system property “jdbc.drivers” 中配置的Driver值；
            2）用户调用Class.forName()注册的Driver
            3）service provider配置文件java.sql.Driver中配置的Driver值。
            jdk_1.6之后，是可以不用调用Class.forName来加载mysql驱动的，
            因为mysql的驱动程序jar包中已经包含了java.sql.Driver配置文件，并在文件中添加了com.mysql.jdbc.Driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        return DriverManager.getConnection(DB_URL, userName, passwd);
    }

    public static void main(String[] args){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();//创建一个数据库连接
            String sql = "select * from date_test where id = ?";
            ps = con.prepareStatement(sql);//实例化预编译语句
            ps.setString(1, "yy");//查询参数绑定，数字表示参数的索引
            rs = ps.executeQuery();//执行查询
            String name = null;
            String pswd = null;
            if(rs.next()){
//                name = rs.getString("user_name");
//                pswd = rs.getString("pswd");
                System.out.println(rs.getString("id"));
                System.out.println(rs.getDate("create_date"));
                System.out.println(rs.getDate("lock_date"));
            }
            /*释放资源*/
            rs.close();
            ps.close();
            con.close();

//            System.out.println(name);
//            System.out.println(pswd);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {//最后检查资源是否彻底释放
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(con != null){
                    con.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
