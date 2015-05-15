package hajecs.model.personalData;



import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 10.03.15.
 */
@NodeEntity
@TypeAlias("Privilege")
public class Privilege {

    @GraphId
    private Long id;

    @Indexed(unique = true)
    private String privilegeName;

    @Fetch
    @RelatedTo(type = "CONNECTED", direction = Direction.BOTH)
    private Set<Role> roleStorage = new HashSet<>();



    public Privilege() {
    }

    public Privilege(Long privilege_id, String privilege) {
        this.id = privilege_id;
        this.privilegeName = privilege;
    }

    public Privilege(String privilege) {
        this.privilegeName = privilege;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Privilege privilege1 = (Privilege) o;

        if (!privilegeName.equals(privilege1.privilegeName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return privilegeName.hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public Set<Role> getRoleStorage() {
        return roleStorage;
    }

    public void setRoleStorage(Set<Role> roleStorage) {
        this.roleStorage = roleStorage;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", privilegeName='" + privilegeName + '\'' +
                '}';
    }
}
