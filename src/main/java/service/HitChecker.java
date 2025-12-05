package service;

import controller.ErrorBean;
import controller.ResultBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalTime;
@SessionScoped
public class HitChecker implements Serializable {

    public boolean check(double x, double y, double r)
    {
        boolean hit = false;
                if (x >= 0 && y >= 0){
                    hit = (x*x + y*y < r*r);
                }
                if (x <= 0 && y <= 0)
                {
                    hit = (x > -r && y > -r/2);
                }
                if (x>=0 && y <= 0)
                {
                    hit = (x-y < r/2);
                }
                return hit;
            }

        }


