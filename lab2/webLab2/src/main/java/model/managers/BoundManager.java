package model.managers;

import java.util.Set;
import java.util.TreeSet;

public class BoundManager {
    private static Set<Double> rValues = new TreeSet<>();

    static {
        rValues.add(1D);
        rValues.add(2D);
        rValues.add(3D);
        rValues.add(4D);
        rValues.add(5D);
    }

    public static boolean checkBound(double x, double y, double r) {
        return checkX(x) && checkY(y) && checkR(r);
    }

    public static boolean checkX(double x) {
        return x >= -3 && x <= 5;
    }

    public static boolean checkY(double y) {
        return y >= -3 && y <= 5;
    }

    public static boolean checkR(double r) {
        return rValues.contains(r);
    }
}
