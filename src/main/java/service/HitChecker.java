package service;

import controller.ErrorBean;
import controller.ResultBean;

import java.time.LocalTime;

public class HitChecker {

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


