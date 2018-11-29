package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean
@ApplicationScoped
public class Clock implements Serializable {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    private Date date = new Date();

    public String getDateFormat() {
        return simpleDateFormat.format(date);
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void update(){
        date = new Date(date.getTime()+6000);
    }
}