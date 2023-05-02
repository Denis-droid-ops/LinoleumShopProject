package com.kuznetsov.linoleum.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class ConnectionManager {
    private static DataSource dataSource;
    private final static PropertiesConfiguration config = new PropertiesConfiguration();

    public ConnectionManager(){

    }
    public static Connection getConnection() throws SQLException {
        try {
            Context initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/linoleumShop");
        }catch (NamingException e){
            throw new RuntimeException();
        }
        return dataSource.getConnection();
    }

    public static Connection getConnectionForTests() throws SQLException,ConfigurationException {
        config.load("application.properties");
        Connection connection = DriverManager.getConnection(config.getString("db.url")
            ,config.getString("db.user"),config.getString("db.password"));
        return connection;
    }
}
