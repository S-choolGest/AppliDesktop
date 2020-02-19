package IServices.Education;

import Entite.Education.Absence;
import java.sql.SQLException;
import java.util.List;

public interface IServicesAbsences<T> {
    void ajouter(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    List<T> readAll() throws SQLException;

}
