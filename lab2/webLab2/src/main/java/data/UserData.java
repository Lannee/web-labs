package data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

// import jakarta.annotation.ManagedBean;

// @ManagedBean(name = "myBean")
public class UserData implements Serializable {
    
    private List<Shot> shots = new LinkedList<>();

    public List<Shot> getShots() {
        return shots;
    }

    public void setShots(List<Shot> shots) {
        this.shots = shots;
    }

    public void addShot(Shot shot) {
        shots.add(shot);
    }

}
