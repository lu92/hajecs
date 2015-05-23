package hajecs.model.Actors;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hajecs.model.Task.AbstractTask;
import hajecs.model.personalData.Address;
import hajecs.model.personalData.Personality;
import hajecs.model.personalData.Role;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 10.03.15.
 */
@NodeEntity
public abstract class Person {

    @GraphId
    private Long id;
    private String username;
    private String password;
    private String email;


    @Fetch @RelatedTo(type = "CONNECTED", direction = Direction.BOTH)
    private Personality personality;

    @Fetch @RelatedTo(type = "CONNECTED2", direction = Direction.BOTH)
    private Address address;

    @Fetch @RelatedTo(type = "CONNECTED3", direction = Direction.BOTH)
    private Set<Role> roleStorage = new HashSet<>();


    @JsonIgnore
    @Fetch @RelatedTo(type = "PERSON_TASK_RELATION", direction = Direction.BOTH)
    private Set<AbstractTask> tasks = new HashSet<>();

    public Person() {
    }


    public Person(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Person(String username, String password, String email, Personality personality, Address address, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.personality = personality;
        this.address = address;
        this.roleStorage = roles;
    }

    public Person(Long person_id, String username, String password, String email, Personality personality, Address address,  Set<Role> roles) {
        this(username, password, email, personality, address, roles);
        this.id = person_id;
    }


    public void addRoles(Role ... roles) {
        for (Role role : roles)
            roleStorage.add(role);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (address != null ? !address.equals(person.address) : person.address != null) return false;
        if (!email.equals(person.email)) return false;
        if (!password.equals(person.password)) return false;
        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (personality != null ? !personality.equals(person.personality) : person.personality != null) return false;
        if (roleStorage != null ? !roleStorage.equals(person.roleStorage) : person.roleStorage != null) return false;
        if (!username.equals(person.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (personality != null ? personality.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (roleStorage != null ? roleStorage.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Personality getPersonality() {
        return personality;
    }

    public void setPersonality(Personality personality) {
        this.personality = personality;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Role> getRoleStorage() {
        return roleStorage;
    }

    public void setRoleStorage(Set<Role> roleStorage) {
        this.roleStorage = roleStorage;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", personality=" + personality +
                ", address=" + address +
                ", roleStorage=" + roleStorage +
                '}';
    }
}
