package com.biubiu.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DbcpUtil 连接池版本的数据库连接管理工具类
 *
 * @author biubiu
 */
public class DbcpUtil {

    private static BasicDataSource dataSource;

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl("");
            dataSource.setDriverClassName("");
            dataSource.setUsername("");
            dataSource.setPassword("");
        }
        return dataSource.getConnection();
    }

    public static void insert(String sql) {
        try (Connection conn = getConnection(); Statement stat = conn.createStatement()) {
            stat.execute(sql);
        } catch (SQLException e) {
            System.out.println("【当前时间】：" + DateUtil.getCurrentFormatDateStr() + "【message】:" + e.getMessage());
        }
    }
}
