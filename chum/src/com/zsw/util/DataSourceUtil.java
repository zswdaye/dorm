package com.zsw.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.PooledDataSource;

public class DataSourceUtil {
	public static volatile DataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource("chumdata");
    }

    public static Connection getConnection() {
        Connection c = null;
        try {
            c = dataSource.getConnection();
            PooledDataSource pds = (PooledDataSource) dataSource;
            System.out.println();
            System.out.println(dataSource.toString());
            System.out.println("c3p0连接池中[总共]连接数量：" + pds.getNumConnectionsDefaultUser());
            System.out.println("c3p0连接池中[在忙]连接数量：" + pds.getNumBusyConnectionsDefaultUser());
            System.out.println("c3p0连接池中[空闲]连接数量：" + pds.getNumIdleConnectionsDefaultUser());
            System.out.println("c3p0连接池中[未关]连接数量：" + pds.getNumUnclosedOrphanedConnectionsAllUsers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("数据库连接获取失败");
        }
        return c;
    }

    public static void closeAll(ResultSet rs, Statement stmt, Connection connection) {
        try {
            if(rs!=null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ResultSet关闭失败");
        }
        try {
            if(stmt!=null) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Statement关闭失败");
        }
        try {
            if(connection!=null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection关闭失败");
        }
    }
}
