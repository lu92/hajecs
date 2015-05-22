package hajecs.model.personalData;



import com.fasterxml.jackson.annotation.JsonIgnore;
import hajecs.model.Actors.Person;
import hajecs.model.Task.AbstractTask;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 10.03.15.
 */
@NodeEntity
public class Role {

    @GraphId
    private Long id;

    @Indexed(unique = true)
    private String roleName;


    @Fetch
    @RelatedTo(type = "CONNECTED", direction = Direction.BOTH)
    private Set<Privilege> privilegeStorage = new HashSet<>();

    @Fetch
    @RelatedTo(type = "ROLE_TASK_RELATION", direction = Direction.BOTH)
    @JsonIgnore
    private Set<AbstractTask> tasks = new HashSet<>();

    @Fetch
    @RelatedTo(type = "CONNECTED3", direction = Direction.BOTH)
    @JsonIgnore
    private Set<Person> persons = new HashSet<>();

    public Role() {
    }

    public Role(Long role_id, String roleName) {
        this.id = role_id;
        this.roleName = roleName;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(Long role_id, String roleName, Set<Privilege> privileges) {
        this.id = role_id;
        this.roleName = roleName;
        this.privilegeStorage = privileges;
    }

    public Role(String roleName, Set<Privilege> privileges) {
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


    //          END OF METHODS


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (!roleName.equals(role.roleName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Privilege> getPrivilegeStorage() {
        return privilegeStorage;
    }

    public void setPrivilegeStorage(Set<Privilege> privilegeStorage) {
        this.privilegeStorage = privilegeStorage;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", privilegeStorage=" + privilegeStorage +
                '}';
    }
}
