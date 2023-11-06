package data;

public class HitManager {

    public static boolean checkHit(double x, double y, double r) {
        return checkFirstAreaHit(x, y, r) || checkSecondAreaHit(x, y, r) || checkThirdAreaHit(x, y, r);
    }

    public static boolean checkFirstAreaHit(double x, double y, double r) {
        return x <= 0 && y <= 0 && y + 2*x <= -r;  // y >= -2x - r
    }

    public static boolean checkSecondAreaHit(double x, double y, double r) {
        return x <= 0 && y >= 0 && x*x + y*y <= (r*r);
    }

    public static boolean checkThirdAreaHit(double x, double y, double r) {
        return x >= 0 && x <= r && y >= 0 && y <= r/2;
    }

}