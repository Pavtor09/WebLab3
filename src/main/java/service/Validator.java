package service;

import controller.ErrorBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class Validator implements Serializable {
    @Inject
    private ErrorBean error;

    public boolean isTypeValid(String y) {
        try {
            Double.parseDouble(y);
        } catch (Exception e) {
            error.setMessage("Y is unparseable");
            return false;
        }
        if (error.isShown()) {
            error.setShow(false);
        }
        return true;
    }

    public boolean isRangeValid(double x, double y, double r) {
        if (x < -4 || x > 4 || x % 1 != 0) {
            error.setMessage("X is out of range");
            return false;
        }

        if (y <= -3 || y >= 5) {
            error.setMessage("Y is out of range");
            return false;
        }
        if (r < 1 || r > 5 || r % 1 != 0) {
            error.setMessage("R is out of range");
            return false;
        }
        if (error.isShown()) {
            error.setShow(false);
        }
        return true;
    }
}
