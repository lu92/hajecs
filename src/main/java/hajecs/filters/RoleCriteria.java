package hajecs.filters;

import hajecs.model.personalData.Role;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Radek on 2015-05-24.
 */
public class RoleCriteria {
    private Set<Role> needRoles = new HashSet<>();

    public RoleCriteria(Set<Role> needRoles) {
        this.needRoles = needRoles;
    }

    public RoleCriteria() {
    }

    public Set<Role> getNeedRoles() {
        return needRoles;
    }

    public void setNeedRoles(Set<Role> needRoles) {
        this.needRoles = needRoles;
    }

    public void addRoles(Role... roles) {
        for (Role role : roles) {
            this.needRoles.add(role);
        }
    }
}
