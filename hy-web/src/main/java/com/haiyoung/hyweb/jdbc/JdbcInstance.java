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
            String sql = "select * from user where user_id = ?";
            ps = con.prepareStatement(sql);//实例化预编译语句
            ps.setString(1, "haiyoung");//查询参数绑定，数字表示参数的索引
            rs = ps.executeQuery();//执行查询
            String name = null;
            String pswd = null;
            if(rs.next()){
                name = rs.getString("user_name");
                pswd = rs.getString("pswd");
            }
            /*释放资源*/
            rs.close();
            ps.close();
            con.close();

            System.out.println(name);
            System.out.println(pswd);
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
