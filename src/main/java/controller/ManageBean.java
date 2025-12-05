package controller;

import service.Service;
import service.ServiceInterface;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.sql.SQLException;
//управляет вызовами из методов из сервиса
@Named("manager")
@ApplicationScoped
public class ManageBean implements Serializable {
    private boolean fromGraphic;

    private boolean doRangeValidation;

    @Inject
    private ErrorBean error;
    @Inject
    private ResultBean result;
    @Inject
    private ServiceInterface service;

//    @PostConstruct
//    public void init() {
//
//        service = new Service();
//    }


    public void check(PointBean point) {
        service.check(point.getX(), point.getY(), point.getR(), doRangeValidation);
    }

    public void load() throws SQLException {
        service.load();
    }

    public void clear() throws SQLException {
        service.clear();
    }

    public void setDoRangeValidation(boolean doRangeValidation) {
        this.doRangeValidation = doRangeValidation;
    }

    public boolean isDoRangeValidation() {
        return doRangeValidation;
    }

    public boolean isFromGraphic() {
        return fromGraphic;
    }

    public void setFromGraphic(boolean fromGraphic) {
        this.fromGraphic = fromGraphic;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public ErrorBean getError() {
        return error;
    }
}

