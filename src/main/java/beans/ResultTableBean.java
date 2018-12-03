package beans;


import lombok.Data;
import model.Point;
import util.Factory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "resTable", eager = true)
@SessionScoped
@Data
public class ResultTableBean {
    private Point newPoint = new Point(1);
    private List<Point> results = null;

    public List<Point> getResults(){
        try {
            return new ArrayList<>(Factory.getInstance().getResRepository().getAllPoints());
        } catch (SQLException e) {
            System.out.println("BAD ON GETTING POINTS LIST");
        }
        return null;
    }

    private void initResults() {
            results = getResults();
    }

    public void addPoint(){
        initResults();
        check(newPoint);
        try {
            Factory.getInstance().getResRepository().addPoint(newPoint);
        }catch (SQLException e){
            System.out.println("BAD ON ADDING POINT");
        }

        newPoint = new Point(newPoint.getR());
    }

    public int getResLength(){
        if(results == null)
            initResults();
        try {
            return results.size();
        }catch (Exception e){
            return 0;
        }
    }

    public void check(Point p){
        int x = p.getX();
        double y = p.getY();
        int r = p.getR();
        if (    ( x >= 0 && x <= (r/2)) && (y >= 0 && y <= r) //прямоугольник
                || (x >= 0 && y < 0) && (y >= x - r/2) //треугольник
                || (x <= 0 && y >= 0) && (x * x + y * y <= r * r))//окружность
        {
            p.setRes(true);
        } else p.setRes(false);


    }

}
