package db;

import data.Shot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

public class DBCollectionLoader{

    private static final String initializationQuery = """
        SELECT id, x, y, r, hitFact, currTimeMilli, execTimeMilli
        FROM user_data
        ORDER BY currTimeMilli;
    """;

    private final DBConnection connection;

    public DBCollectionLoader(DBConnection connection) {
        this.connection = connection;
    }

    public List<Shot> getCollection() {
        List<Shot> collection = new LinkedList<>();

        try (Statement statement = connection.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(initializationQuery);
            while (rs.next()) {
                Shot shot = new Shot(
                        rs.getLong("id"),
                        rs.getDouble("x"),
                        rs.getDouble("y"),
                        rs.getDouble("r"),
                        rs.getBoolean("hitFact"),
                        LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(
                                        rs.getLong("currTimeMilli")),
                                ZoneId.systemDefault()),
                        rs.getLong("execTimeMilli")
                );

                collection.add(shot);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return collection;
    }
}