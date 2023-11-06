package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
@ManagedBean
public class YBean implements Serializable {

    private double y;

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void validateY(FacesContext facesContext,
                          UIComponent uiComponent, Object o) {
        if (o == null) {
            FacesMessage message = new FacesMessage("Введите значение Y");
            throw new ValidatorException(message);
        }
    }
}
