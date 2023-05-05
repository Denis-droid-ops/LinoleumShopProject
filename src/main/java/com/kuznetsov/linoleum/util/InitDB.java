package com.kuznetsov.linoleum.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public final class InitDB {

    private static String scriptLoader(String scriptName) throws URISyntaxException, IOException {
        URL url = InitDB.class.getClassLoader().getResource(scriptName);
        List<String> sqlStrings = Files.readAllLines(Paths.get(url.toURI()));
        return sqlStrings.stream().collect(Collectors.joining());

    }

    public static void init(){
        try(Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement()) {
            statement.addBatch(scriptLoader("initH2DB.sql"));
            statement.addBatch(scriptLoader("populateH2DB.sql"));
            statement.executeBatch();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
