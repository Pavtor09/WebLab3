package service;

public class ResultObject {
    private double x;
    private double y;
    private double r;
    private boolean hit;
    private String currentTime;
    private long executionTime;

    public ResultObject(double X, double Y, double R, boolean Hit, String CurrentTime, long ExecutionTime) {
        x = X;
        y = Y;
        r = R;
        hit = Hit;
        currentTime = CurrentTime;
        executionTime = ExecutionTime;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public double getR() {
        return r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public String getStringHit() {
        return hit ? "true" : "false";
    }
}
