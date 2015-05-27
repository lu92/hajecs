package hajecs.filters;

import hajecs.Person;
import hajecs.model.Task.AbstractTask;
import org.springframework.messaging.tcp.reactor.Reactor11TcpClient;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by Radek on 2015-05-24.
 */
public class DaysFiltr implements AvailableWorkersFiltr {

    Logger logger = Logger.getLogger(String.valueOf(this));

    @Override
    public Set<hajecs.model.Actors.Person> getAvaialbeWorkers(Set<hajecs.model.Actors.Person> persons, FiltrCriteria criteria) {
        Set<hajecs.model.Actors.Person> personResult = new HashSet<>();
        DaysCriteria daysCriteria = criteria.getDayCriteria();
        if(daysCriteria == null) return persons;
        Date begin = daysCriteria.getBegin();
        Date end = daysCriteria.getEnd();
        for(hajecs.model.Actors.Person p: persons){
            if(isPersonOk(p,begin,end)){
                personResult.add(p);
            }
        }
        return personResult;
    }

    private boolean isPersonOk(hajecs.model.Actors.Person person, Date begin, Date end){
        logger.config("date begin " + begin+ " date end "+ end);
        for(AbstractTask task: person.getTasks()){
            if(!task.isExecuted()){
                logger.config("task info "+ task.getDeadline());
                if(begin.before(task.getDeadline())){
                    return false;
                }
                if(end.after(task.getStart())){
                    return false;
                }
                if(begin.after(task.getStart())&&end.before(task.getDeadline())){
                    return false;
                }
                if(task.getStart().after(begin)&&task.getEnd().before(end)){
                    return false;
                }

            }
        }
        return true;
    }
}
