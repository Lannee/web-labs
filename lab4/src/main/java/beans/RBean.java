package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

@Named
@SessionScoped
@ManagedBean
public class RBean implements Serializable {

//    private boolean[] checks = new boolean[] {true, false, false, false, false};
//    private byte checks = 0b00010000;  // b1, b15, b2, b25, b3
    private int selected = 5;
    private final static Map<Integer, Double> radiuses = new TreeMap<>();

    static {
        radiuses.put(1, 1d);
        radiuses.put(2, 1.5d);
        radiuses.put(3, 2d);
        radiuses.put(4, 2.5d);
        radiuses.put(5, 3d);
    }
    public boolean isB1() {
        return selected == 1;
    }

    public boolean isB15() {
        return selected == 2;
    }

    public boolean isB2() {
        return selected == 3;
    }

    public boolean isB25() {
        return selected == 4;
    }

    public boolean isB3() {
        return selected == 5;
    }

    public void setB1(boolean b1) {
        if(b1) {
            selected = 1;
        }
    }

    public void setB15(boolean b15) {
        if(b15) {
            selected = 2;
        }
    }

    public void setB2(boolean b2) {
        if(b2) {
            selected = 3;
        }
    }

    public void setB25(boolean b25) {
        if(b25) {
            selected = 4;
        }
    }

    public void setB3(boolean b3) {
        if(b3) {
            selected = 5;
        }
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getSelected() {
        return selected;
    }

    public double getR() {
        System.out.println(selected);
        return radiuses.get(selected);
    }

    public void validateR(FacesContext facesContext,
                          UIComponent uiComponent, Object o) {
        if (o == null) {
            FacesMessage message = new FacesMessage("Введите значение R");
            throw new ValidatorException(message);
        }
    }
}
