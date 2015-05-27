package hajecs.filters;

import hajecs.Person;
import hajecs.model.personalData.Role;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Radek on 2015-05-24.
 */
public class RoleFiltr implements AvailableWorkersFiltr {

    @Override
    public Set<hajecs.model.Actors.Person> getAvaialbeWorkers(Set<hajecs.model.Actors.Person> persons, FiltrCriteria criteria) {
        Set<hajecs.model.Actors.Person> personResult = new HashSet<>();
        RoleCriteria roleCriteria= criteria.getRoleCriteria();
        if(roleCriteria==null ||roleCriteria.getNeedRoles().isEmpty()) return persons;
        Set<Role> needesRole = roleCriteria.getNeedRoles();
        for(hajecs.model.Actors.Person p: persons){
            if(isPersonMeetsCriteria(p,needesRole)){
                personResult.add(p);
            }
        }
        return personResult;
    }

    private boolean isPersonMeetsCriteria(hajecs.model.Actors.Person person, Set<Role> roles){
        for(Role r: roles){
            if(!person.getRoleStorage().contains(r)) return false;
        }
        return true;
    }
}
