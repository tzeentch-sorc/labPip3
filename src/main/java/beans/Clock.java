package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean
@ApplicationScoped
public class Clock implements Serializable {
    SimpleDateFormat dateNTime = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    private Date date = new Date();

    public String getDateNTime() {
        return dateNTime.format(date);
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void update(){
        this.date = new Date(date.getTime()+6000);
    }
}