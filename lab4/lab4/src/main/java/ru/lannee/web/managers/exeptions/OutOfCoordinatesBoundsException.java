package ru.lannee.web.managers.exeptions;

public class OutOfCoordinatesBoundsException extends Exception {
    public OutOfCoordinatesBoundsException(String coordinateName, double coordinateValue) {
        super(
                String.format(
                        "Coordinate %s = %.1f is out of bounds",
                        coordinateName, coordinateValue
                )
        );
    }
}
