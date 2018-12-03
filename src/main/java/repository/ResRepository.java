package repository;

import model.Point;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface ResRepository {
    void addPoint(Point point) throws SQLException;
    List<Point> getAllPoints() throws SQLException;
}
