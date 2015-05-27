package hajecs.filters;

import hajecs.Person;

import java.util.Set;

/**
 * Created by Radek on 2015-05-24.
 */
public interface AvailableWorkersFiltr {
    Set<hajecs.model.Actors.Person> getAvaialbeWorkers(Set<hajecs.model.Actors.Person> persons, FiltrCriteria criteria);
}
