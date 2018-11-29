package beans;


import lombok.Data;
import model.Point;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "resTable", eager = true)
@SessionScoped
@Data
public class ResultTableBean {
    private Point newPoint = new Point();
    private List<Point> results = new ArrayList<>();
    public void addPoint(){
        check(newPoint);
        results.add(newPoint);
        newPoint = new Point();
    }

    public int getResLength(){
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
