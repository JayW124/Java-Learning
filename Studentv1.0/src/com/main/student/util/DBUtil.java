/**
 * 项目名：Student
 * 修改历史：
 * 作者： WJ
 * 创建时间： 2019年4月13日
 */

package com.main.student.util;

import com.main.student.AppConstants;

import java.sql.*;

/**
 * 数据库工具类
 */
public class DBUtil {
    private static DBUtil db;

    private Connection conn;
    //通过调用connection.preparedStatement(sql)方法执行sql语句并获得PreparedStatment对象
    // 数据库系统会对sql语句进行预编译处理，预处理语句将被预先编译好在将来的查询中重用，
    // 通过setInt(),setString()，setObject()替换sql语句中的？
    //也可以用connection.createStatement，需要直接输入完整的sql语句，查询和删除较慢
    private PreparedStatement ps;
    private ResultSet rs;  //用ResultSet类存放获取的结果集

    private DBUtil() {}

    public static DBUtil getDb() {
        if (db == null) {
            db = new DBUtil();
        }
        return db;
    }

    public int executeUpdate(String sql) {
        int result = -1;
        if (getConn() == null) {
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int executeUpdate(String sql, Object[] obj) {
        int result = -1;
        if (getConn() == null) {
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            result = ps.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public ResultSet executeQuery(String sql) {
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet executeQuery(String sql, Object[] obj) {
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public boolean exeute(String sql) {
        if (getConn() == null) {
            return false;
        }
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
			e.printStackTrace();
            return false;
        }
    }
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                //加载数据库驱动程序
                //Class.forName(className)会初始化加载的类，ClassLoader.loadClass(className)不会初始化
                Class.forName(AppConstants.JDBC_DRIVER);
                //getConnection方法连接数据库，输入参数为数据库连接的URL、账号和密码
                conn = DriverManager.getConnection(AppConstants.JDBC_URL, AppConstants.JDBC_USERNAME,
                        AppConstants.JDBC_PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver is not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
