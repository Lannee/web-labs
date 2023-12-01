package data;

import db.ConfigurationParser;
import db.DBCollectionLoader;
import db.DBConnection;
import db.DBInitializer;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

@Named
@SessionScoped
@ManagedBean
public class UserDataBean implements Serializable {
    private List<Shot> shots;

    private DBConnection dbConnection;

    public UserDataBean() {
        shots = new LinkedList<>();

        try {
            ConfigurationParser conf = new ConfigurationParser();

            dbConnection = new DBConnection(conf.getDbURL(), conf.getUserName(), conf.getPassword());
            DBInitializer.configureDB(dbConnection.getConnection());

            DBCollectionLoader collectionLoader = new DBCollectionLoader(dbConnection);
            shots.addAll(collectionLoader.getCollection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Shot> getShots() {
        return shots;
    }

    public void setShots(List<Shot> shots) {
        this.shots = shots;
    }

    public void addShot(Shot shot) {
        try(PreparedStatement ps = dbConnection.getConnection().prepareStatement(
                "INSERT INTO user_data (x, y, r, hitFact, currTimeMilli, execTimeMilli) values (?, ?, ?, ?, ?, ?)"
        )) {
            ps.setDouble(1, shot.getX());
            ps.setDouble(2, shot.getY());
            ps.setDouble(3, shot.getR());
            ps.setBoolean(4, shot.isHit());
            ps.setLong(5, shot.getCurrTime().toInstant(ZoneOffset.ofHours(3)).toEpochMilli());
            ps.setLong(6, shot.getExecTime());

            ps.execute();
            shots.add(shot);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void newShot(double x, double y, double r) {
        System.out.println("Shot to x = " + x + " y = " + y + " r = " + r);
        long start = System.nanoTime();
        boolean hit = HitManager.checkHit(x, y, r);
        long executionTime = System.nanoTime() - start;

        Shot shot = new Shot();
        shot.setX(x);
        shot.setY(y);
        shot.setR(r);
        shot.setHit(hit);
        shot.setCurrTime(LocalDateTime.now());
        shot.setExecTime(executionTime);

        addShot(shot);
    }

    public void clearShots() {
        try(PreparedStatement ps = dbConnection.getConnection().prepareStatement("DELETE FROM user_data;")) {
            ps.execute();
            shots.clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
