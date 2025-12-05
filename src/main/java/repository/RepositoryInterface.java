package repository;

import java.sql.SQLException;

public interface RepositoryInterface {
    Object load();
    void save(Object resultObj);
    void clear();

}
