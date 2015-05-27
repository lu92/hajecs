package hajecs.filters;

import hajecs.Person;

import java.util.Set;

/**
 * Created by Radek on 2015-05-24.
 */
public class DayAndRoleFilter implements AvailableWorkersFiltr {

    private AvailableWorkersFiltr daysFiltr = new DaysFiltr();
    private AvailableWorkersFiltr roleFiltr = new RoleFiltr();

    @Override
    public Set<hajecs.model.Actors.Person> getAvaialbeWorkers(Set<hajecs.model.Actors.Person> persons, FiltrCriteria criteria) {
        Set<hajecs.model.Actors.Person> firstSet = daysFiltr.getAvaialbeWorkers(persons,criteria);
        Set<hajecs.model.Actors.Person> result = roleFiltr.getAvaialbeWorkers(firstSet,criteria);
        return result;
    }
}
