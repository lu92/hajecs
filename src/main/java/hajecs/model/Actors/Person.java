package hajecs.model.Actors;


import hajecs.model.personalData.Address;
import hajecs.model.personalData.Personality;
import hajecs.model.personalData.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucjan on 10.03.15.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {

    @Id @GeneratedValue
    private Long person_id;
    private String username;
    private String password;
    private String email;


    @OneToOne(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Personality personality;

    @OneToOne(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roleStorage = new ArrayList<>();

    public Person() {
    }


    public Person(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Person(String username, String password, String email, Personality personality, Address address, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.personality = personality;
        this.address = address;
        this.roleStorage = roles;
    }

    public Person(Long person_id, String username, String password, String email, Personality personality, Address address,  List<Role> roles) {
        this(username, password, email, personality, address, roles);
        this.person_id = person_id;
    }


    public void addRoles(Role ... roles) {
        for (Role role : roles)
            roleStorage.add(role);
    }

//    public enum PersonType {
//        CLIENT, CONSULTANT, MANAGER, STUDENT, WORKER;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (address != null ? !address.equals(person.address) : person.address != null) return false;
        if (!email.equals(person.email)) return false;
        if (!password.equals(person.password)) return false;
        if (person_id != null ? !person_id.equals(person.person_id) : person.person_id != null) return false;
        if (personality != null ? !personality.equals(person.personality) : person.personality != null) return false;
        if (roleStorage != null ? !roleStorage.equals(person.roleStorage) : person.roleStorage != null) return false;
        if (!username.equals(person.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = person_id != null ? person_id.hashCode() : 0;
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (personality != null ? personality.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (roleStorage != null ? roleStorage.hashCode() : 0);
        return result;
    }

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
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

    public List<Role> getRoleStorage() {
        return roleStorage;
    }

    public void setRoleStorage(List<Role> roleStorage) {
        this.roleStorage = roleStorage;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", personality=" + personality +
                ", address=" + address +
                ", roleStorage=" + roleStorage +
                '}';
    }
}
