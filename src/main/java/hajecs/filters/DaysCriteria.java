package hajecs.filters;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Created by Radek on 2015-05-24.
 */
public class DaysCriteria {
    public Date begin;
    public Date end;

    public DaysCriteria(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    public DaysCriteria() {
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
