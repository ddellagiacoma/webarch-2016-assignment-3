package beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;

@Stateless
public class DateTime implements ABean {
    @Override
    public String print() {
        String timeStamp = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date());
        return timeStamp;
    }
}