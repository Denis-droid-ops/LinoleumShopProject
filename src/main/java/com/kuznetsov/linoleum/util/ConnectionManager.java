package com.kuznetsov.linoleum.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionManager {
    private static DataSource dataSource;
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
}
