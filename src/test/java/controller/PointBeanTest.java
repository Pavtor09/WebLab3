package controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointBeanTest {
    @Test
    public void testR() {
        PointBean point = new PointBean();
        point.setR(10.4);
        assertEquals(10.4,point.getR());
    }
    @Test
    public void testX() {
        PointBean point = new PointBean();
        point.setX(1.4);
        assertEquals(1.4,point.getX());
    }


//        public double getX() {
//            return x;
//        }
//
//        public String getY() {
//            return y;
//        }
//
//        public void setR(double r) {
//            R = r;
//        }
//
//        public void setX(double x) {
//            this.x = x;
////        System.out.println("X установлен, ура победа");
//        }
//
//        public void setY(String y) {
//            this.y = y;
//        }
}
