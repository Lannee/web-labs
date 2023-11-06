package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
@ManagedBean
public class XBean implements Serializable {

    private double x;

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }
}
