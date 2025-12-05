package service;

import java.sql.SQLException;

public interface ServiceInterface {
    void check(double x, String sy, double r,boolean doRangeValidation);
    void load() throws SQLException;
    void clear() throws SQLException;
}
