package data;

import java.time.Duration;
import java.time.LocalDateTime;

import com.google.gson.annotations.SerializedName;

public record Shot(
    @SerializedName("x")
    Double xCoord, 
    @SerializedName("y")
    Double yCoord, 
    @SerializedName("r")
    Double radius, 
    boolean hitFact, 
    LocalDateTime currTime, 
    Duration execTime,
    long color) {}
