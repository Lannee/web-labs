package db;

import java.io.*;
import java.util.Properties;

public class ConfigurationParser {

    private String dbURL = "jdbc:postgresql://localhost:5432/postgres";
    private String password = "password";
    private String userName = "postgres";


    public ConfigurationParser() {
        Properties props = new Properties();
        try(InputStream resources = ConfigurationParser.class.getResourceAsStream("/config.properties")) {

            if(resources != null) {
                props.load(resources);
                dbURL = props.getProperty("db_url");

                userName = props.getProperty("user_name");
                password = props.getProperty("user_password");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getDbURL() {
        return dbURL;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}