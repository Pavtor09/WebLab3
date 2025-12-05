package controller;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("point")
@SessionScoped
public class PointBean implements Serializable {
    protected double x;
    private String y;
    private double R = 1;

    public PointBean() {
        System.out.println("point bean made!!");
    }

    public double getR() {
        return R;
    }

    public double getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public void setR(double r) {
        R = r;
    }

    public void setX(double x) {
        this.x = x;
//        System.out.println("X установлен, ура победа");
    }

    public void setY(String y) {
        this.y = y;
    }
}
