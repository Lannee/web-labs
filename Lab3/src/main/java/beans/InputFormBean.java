package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
@ManagedBean
public class InputFormBean implements Serializable  {

    private double x;
    private double y;
    private double rValue;

    public void setXValue(double x) {
        this.x = x;
    }

    public void setYValue(double y) {
        this.y = y;
    }

    public void setrValue(double r) {
        this.rValue = r;
    }

    public double getXValue() {
        return x;
    }

    public double getYValue() {
        return y;
    }

    public double getrValue() {
        return rValue;
    }
}
