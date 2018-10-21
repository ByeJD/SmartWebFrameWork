package com.quan.common;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DatabaseHelper {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");

        DRIVER = PropsUtil.getString(conf, "jdbc.driver");
        URL = PropsUtil.getString(conf, "jdbc.url");
        USERNAME = PropsUtil.getString(conf, "jdbc.username");
        PASSWORD = PropsUtil.getString(conf, "jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){

        Connection connection = CONNECTION_THREAD_LOCAL.get();

        if(connection == null){
            try {
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }

        return connection;
    }

    public static void closeConnection(){
        Connection connection =  CONNECTION_THREAD_LOCAL.get();
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                CONNECTION_THREAD_LOCAL.remove();
            }
        }
    }


    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params){
        T entity = null;
        try {
            Connection connection = getConnection();
            entity = QUERY_RUNNER.query(connection,sql,new BeanHandler<>(entityClass),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return entity;
    }


    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params){
        List<T> entityList = null;
        try {
            Connection connection = getConnection();
            entityList = QUERY_RUNNER.query(connection,sql,new BeanListHandler<T>(entityClass),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return entityList;
    }

}
