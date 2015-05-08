package hajecs.model.personalData;



import hajecs.model.Actors.Person;
import hajecs.model.Task.AbstractTask;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucjan on 10.03.15.
 */
@Entity
public class Role {

    @Id @GeneratedValue
    private Long role_id;
    private String roleName;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Privilege> privilegeStorage = new ArrayList<>();

    @ManyToMany
    private List<AbstractTask> tasks = new ArrayList<>();

    @ManyToMany
    private List<Person> persons = new ArrayList<>();

    public Role() {
    }

    public Role(Long role_id, String roleName) {
        this.role_id = role_id;
        this.roleName = roleName;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(Long role_id, String roleName, List<Privilege> privileges) {
        this.role_id = role_id;
        this.roleName = roleName;
        this.privilegeStorage = privileges;
    }

    public Role(String roleName, List<Privilege> privileges) {
        this.roleName = roleName;
        this.privilegeStorage = privileges;
    }


    //              METHODS

    public void addPrivilege(Privilege privilege) {
        privilegeStorage.add(privilege);
    }

    public void addPrivileges(String ... args) {
        for (String privilege : args)
            privilegeStorage.add(new Privilege(privilege));
    }

    public void addPrivileges(Privilege ... allprivileges) {
        for (Privilege privilege : allprivileges)
            privilegeStorage.add(privilege);
    }

    public Privilege getPrivilege(int index) {
        return privilegeStorage.get(index);
    }

    //          END OF METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (privilegeStorage != null ? !privilegeStorage.equals(role.privilegeStorage) : role.privilegeStorage != null) return false;
        if (!roleName.equals(role.roleName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleName.hashCode();
        result = 31 * result + (privilegeStorage != null ? privilegeStorage.hashCode() : 0);
        return result;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Privilege> getPrivilegeStorage() {
        return privilegeStorage;
    }

    public void setPrivilegeStorage(List<Privilege> privilegeStorage) {
        this.privilegeStorage = privilegeStorage;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", roleName='" + roleName + '\'' +
                ", privilegeStorage=" + privilegeStorage +
                '}';
    }
}
