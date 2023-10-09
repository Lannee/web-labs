package model.managers;

import java.time.Duration;
// import java.time.Instant;
import java.time.LocalDateTime;

import data.Point;
import data.Shot;
import jakarta.servlet.http.HttpServletRequest;

public class PointManager {
    
    public static Shot manage(Point point) {
        long startTime = System.nanoTime();

        if(!BoundManager.checkBound(point.xCoord(), point.yCoord(), point.radius())) return null;
        boolean hitFact = HitManager.checkHit(point.xCoord(), point.yCoord(), point.radius());
        
        return new Shot(point.xCoord(), 
        point.yCoord(), 
        point.radius(), 
        hitFact, 
        LocalDateTime.now(), 
        Duration.ofNanos(System.nanoTime() - startTime),
        getRandomColor());
    }

    private static long getRandomColor() {
        return Math.round((Math.random()*16777215));
    }

    public static Double getRequestParam(HttpServletRequest req, String pName) {
        String[] values = req.getParameterValues(pName);
        if(values == null) return null;

        try {
            return Double.parseDouble(values[0]);
        } catch(Exception e) {
            return null;
        }
    }
}
