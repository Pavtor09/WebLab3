package controller;

import service.ResultObject;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("result")  // CDI аннотация - имя для EL выражений #{manageBean}
@SessionScoped
public class ResultBean implements Serializable {
    private List<ResultObject> results = new ArrayList<>();
    public List<ResultObject> getResults()
    {
        return results;
    }
    public void setResults(List<ResultObject> list)
    {
        results = list;
    }
    public void add(ResultObject res)
    {
        results.add(res);
    }


}
