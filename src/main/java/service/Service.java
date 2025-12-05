package service;

import controller.ErrorBean;
import controller.ResultBean;
import repository.DatabaseService;

import javax.ws.rs.PUT;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
//Добавить интерфейс
//Переделать в бины
public class Service {
    Validator validator;
    private boolean hit;
    private final ResultBean resultBean;
    private final HitChecker checker;
    private DatabaseService DbService;
    public Service(ErrorBean errorBean, ResultBean result)
    {
        validator = new Validator(errorBean);
        this.resultBean = result;
        checker = new HitChecker();
        DbService = new DatabaseService();

    }
    public void check(double x, String sy, double r,boolean doRangeValidation)
    {
        long startTime = System.nanoTime();
        if(validator.isTypeValid(sy))
        {

            double y = Double.parseDouble(sy);
            boolean valid = true;
            if (doRangeValidation)
            {
                valid = validator.isRangeValid(x,y,r);
            }

            if (valid)
            {
                hit = checker.check(x,y,r);
                LocalTime now = LocalTime.now();
                ResultObject result = new ResultObject(x,y,r,hit,String.format("%02d:%02d", now.getHour(), now.getMinute()),(System.nanoTime()-startTime)/1000000);
                resultBean.add(result);
                DbService.saveToDatabase(result);

            }

        }
    }
    public void load()
    {
        resultBean.setResults(DbService.loadFromDatabase());
        System.out.println("inLoad");
    }
    public void clear() throws SQLException {
        resultBean.setResults(new ArrayList<ResultObject>());
        DbService.clearDatabase();
    }
}
