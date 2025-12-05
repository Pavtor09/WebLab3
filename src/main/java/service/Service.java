package service;

import controller.ErrorBean;
import controller.ResultBean;
import repository.DatabaseService;
import repository.RepositoryInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Service implements Serializable, ServiceInterface {

    private boolean hit;
    @Inject
    private ResultBean resultBean;
    @Inject
    private HitChecker checker;
    @Inject
    Validator validator;
    @Inject
    private ErrorBean errorBean;
    @Inject
    private RepositoryInterface DbService;

    public void check(double x, String sy, double r, boolean doRangeValidation) {
        long startTime = System.nanoTime();
        if (validator.isTypeValid(sy)) {

            double y = Double.parseDouble(sy);
            boolean valid = true;
            if (doRangeValidation) {
                valid = validator.isRangeValid(x, y, r);
            }

            if (valid) {
                hit = checker.check(x, y, r);
                LocalTime now = LocalTime.now();
                ResultObject result = new ResultObject(x, y, r, hit, String.format("%02d:%02d", now.getHour(), now.getMinute()), (System.nanoTime() - startTime) / 1000000);
                resultBean.add(result);
                DbService.save(result);

            }

        }
    }

    public void load() {
        resultBean.setResults((List<ResultObject>) DbService.load());
//        System.out.println("inLoad");
    }

    public void clear() throws SQLException {
        resultBean.setResults(new ArrayList<ResultObject>());
        DbService.clear();
    }
}
