package hajecs.services;

import hajecs.model.personalData.Role;

import java.util.Set;

/**
 * Created by lucjan on 25.05.15.
 */
public interface RoleService  {
    long createRole(Role role);
    void deleteRole(long roleId);
    Role getRole(long roleId) throws IllegalArgumentException;
    Set<Role> getAllRoles();
    boolean addPrivilegeToRole(long roleId, long privilegeId) throws IllegalArgumentException;
}
