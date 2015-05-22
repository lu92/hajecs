package hajecs.model.DTO;

import hajecs.model.personalData.Address;
import hajecs.model.personalData.Personality;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 21.05.15.
 */
public class PersonFormDTO {
    /*
    do tworzenia nowej osoby
     */
    private String username;
    private String password;
    private String email;
    private Personality personality;
    private Address address;
    private Set<Long> roleStorage = new HashSet<>();

    public PersonFormDTO() {
    }

    public PersonFormDTO(String username, String password, String email, Personality personality, Address address, Set<Long> roleStorage) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.personality = personality;
        this.address = address;
        this.roleStorage = roleStorage;
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

    public Set<Long> getRoleStorage() {
        return roleStorage;
    }

    public void setRoleStorage(Set<Long> roleStorage) {
        this.roleStorage = roleStorage;
    }


    @Override
    public String toString() {
        return "PersonFormDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", personality=" + personality +
                ", address=" + address +
                ", roleStorage=" + roleStorage +
                '}';
    }
}
