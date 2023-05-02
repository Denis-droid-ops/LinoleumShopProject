package com.kuznetsov.linoleum.testData;

import com.kuznetsov.linoleum.util.ConnectionManager;
import org.postgresql.core.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class InitDB {
    public static void init(){
        try(Connection connection = ConnectionManager.getConnectionForTests();
            Statement statement = connection.prepareStatement("")) {

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
