package ru.lannee.web.managers;

import ru.lannee.web.data.Shot;
import ru.lannee.web.managers.exeptions.OutOfCoordinatesBoundsException;

import java.util.List;

public class BoundManager {

    private final static Double[] xValues = new Double[]{-5d, -4d, -3d, -2d, -1d, 0d, 1d, 2d, 3d};
    private final static Double[] rValues = new Double[]{1d, 2d, 3d, 4d, 5d};

    public static void checkBounds(Shot shot) throws OutOfCoordinatesBoundsException {
        checkX(shot.getX());
        checkY(shot.getY());
        checkR(shot.getR());
    }

    public static void checkX(double x) throws OutOfCoordinatesBoundsException {
        if(!List.of(xValues).contains(x)) throw new OutOfCoordinatesBoundsException("x", x);
    }

    public static void checkY(double y) throws OutOfCoordinatesBoundsException {
        if(!(y >= -3 && y <= 3)) throw new OutOfCoordinatesBoundsException("y", y);
    }

    public static void checkR(double r) throws OutOfCoordinatesBoundsException {
        if(!List.of(rValues).contains(r)) throw new OutOfCoordinatesBoundsException("r", r);
    }
}
