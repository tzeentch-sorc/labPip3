package impl;

import model.Point;
import org.hibernate.Session;
import repository.ResRepository;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResRepositoryImpl implements ResRepository {
    @Override
    public void addPoint(Point point) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(point);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    @Override
    public List<Point> getAllPoints() throws SQLException {
            Session session = null;
            List points = new ArrayList<Point>();
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                points = session.createCriteria(Point.class).list();
                return points;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
            return null;
        }

}
