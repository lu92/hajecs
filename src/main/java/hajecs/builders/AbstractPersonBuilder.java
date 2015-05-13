package hajecs.builders;



import hajecs.model.Actors.Person;
import hajecs.model.personalData.Address;
import hajecs.model.personalData.Personality;
import hajecs.model.personalData.Role;

import java.util.Date;

/**
 * Created by lucjan on 30.03.15.
 */
public interface AbstractPersonBuilder {

    public void setPersonality(String name, String lastname, Date birth, String telephoneNumber);
    public void setPersonality(String name, String lastname, String birth, String telephoneNumber);
    public void setPersonality(Personality personality);

    public void setRoles(String... roleNames) throws IllegalArgumentException;
    public void setRoles(Role... roles);

    public void setAddress(String country, String city, String zipCode);
    public void setAddress(Address address);

    public Class getClassPersonType();
    public String getPersonType();

    public Person getBuildResult();
    public void printPerson();
}
