package hajecs.filters;

/**
 * Created by Radek on 2015-05-24.
 */
import hajecs.model.personalData.Role;
import hajecs.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class FiltrCriteriaImplBuilder {

    private Set<Role> roles;

    private FiltrCriteriaImpl filtrCriteria = new FiltrCriteriaImpl();

    public FiltrCriteriaImplBuilder(CriteriaDTO criteriaDTO,Set<Role> allRoles){
        this.roles = allRoles;
        setDateCriteria(criteriaDTO);
        setRolesCriteria(criteriaDTO);
    }

    public FiltrCriteriaImplBuilder(){

    }

    private void setDateCriteria(CriteriaDTO dto){
        String begiDateString = dto.getBeginTaskDate();
        String endDateString = dto.getEndTaskDate();
        Date begin = new Date(begiDateString);
        Date end = new Date(endDateString);
        this.filtrCriteria.setDaysCriteria(new DaysCriteria(begin,end));
    }

    private void setRolesCriteria(CriteriaDTO criteria){
        Set<Role> roles = new HashSet<>();
        for(Long id : criteria.getRoles()){
            Role r = getRole(id);
            if(r!=null){
                roles.add(r);
            }

        }
        this.filtrCriteria.setRoleCriteria(new RoleCriteria(roles));
    }

    private Role getRole(Long roleId){
        for(Role r: this.roles){
            if(r.getId() == roleId){
                return r;
            }
        }
        return null;
    }



    public FiltrCriteria buldFiltrCriteriaImpl(){
        return this.filtrCriteria;
    }
}
