package hajecs.model.DTO;

import hajecs.model.Actors.PersonType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 25.05.15.
 */
public class PersonDTOInfo {

    private Long id;
    private PersonType personType;
    private String username;
    private String password;
    private String email;

    private String name;
    private String lastName;
    private String birth;
    private String telephoneNumber;

    private String country;
    private String city;
    private String zipCode;
    private Set<Long> roles = new HashSet<>();

    public PersonDTOInfo() {
    }

    public PersonDTOInfo(Long id, PersonType personType, String username,
                         String password, String email, String name, String lastName,
                         String birth, String telephoneNumber, String country, String city,
                         String zipCode, Set<Long> roles) {
        this.id = id;
        this.personType = personType;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.birth = birth;
        this.telephoneNumber = telephoneNumber;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Set<Long> getRoles() {
        return roles;
    }

    public void setRoles(Set<Long> roles) {
        this.roles = roles;
    }
}
