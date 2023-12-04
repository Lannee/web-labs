package ru.lannee.web.managers;

public class HitManager {

    public static boolean checkHit(double x, double y, double r) {
        return checkFirstAreaHit(x, y, r) || checkSecondAreaHit(x, y, r) || checkThirdAreaHit(x, y, r);
    }

    public static boolean checkFirstAreaHit(double x, double y, double r) {
        return x >= 0 && y <= 0 && y >= x - r/2;
    }

    public static boolean checkSecondAreaHit(double x, double y, double r) {
        return x <= 0 && y >= 0 && x*x + y*y <= (r*r)/4;
    }

    public static boolean checkThirdAreaHit(double x, double y, double r) {
        return x <= 0 && x >= r && y <= 0 && y >= r/2;
    }

}