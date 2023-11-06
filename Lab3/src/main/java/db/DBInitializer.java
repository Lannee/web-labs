package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {

    private static final String initalizeScript = """         
            create table if not exists user_data (
                                    id serial primary key,
                                    x numeric(1000, 4) not null,
                                    y numeric(1000, 4) not null,
                                    r numeric(1000, 4) not null,
                                    hitFact boolean not null,
                                    currTimeMilli bigint not null,
                                    execTimeMilli bigint not null
            );
            """;

    public static void configureDB(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(initalizeScript);
    }
}