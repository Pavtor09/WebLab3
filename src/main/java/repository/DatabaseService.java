package repository;

import service.ResultObject;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class DatabaseService implements Serializable {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "postgres";

    private static final String saveQuery =
            "INSERT INTO results (x, y, r, hit, reqTime, exTime) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
    private final String loadQuery = "SELECT x, y, r, hit, reqTime, exTime FROM results";
    private final String clearQuery = "Delete from results;";

    public void saveToDatabase(ResultObject result) {

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(saveQuery)) {

            stmt.setDouble(1, result.getX());
            stmt.setDouble(2, result.getY());
            stmt.setDouble(3, result.getR());
            stmt.setBoolean(4, result.isHit());
            stmt.setString(5, result.getCurrentTime());
            stmt.setLong(6, result.getExecutionTime()); // INTERVAL как строка

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("ошибка загрузки в бд", e);
        }
    }

    public List<ResultObject> loadFromDatabase() {

        List<ResultObject> results = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(loadQuery);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                double r = rs.getDouble("r");
                boolean hit = rs.getBoolean("hit");
                String reqTime = rs.getString("reqTime");
                long exTime = rs.getLong("exTime"); // Assuming interval stored as string

                ResultObject qr = new ResultObject(x, y, r, hit, reqTime, exTime);
                results.add(qr);
            }
        } catch (SQLException e) {
            throw new RuntimeException("ошибка чтения с бд", e);
        }

        return results;

    }

    public void clearDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(clearQuery)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("ошибка очистки бд", e);
        }
    }
}
