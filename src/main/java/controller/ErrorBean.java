package controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("errorBean")
@SessionScoped
public class ErrorBean implements Serializable {
    private String message;
    private boolean show;
    private boolean shown;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        show = true;
        shown = false;
    }


    public boolean isShow() {
        shown = true;
        return show;

    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }
}
