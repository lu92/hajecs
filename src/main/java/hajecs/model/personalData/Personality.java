package hajecs.model.personalData;

import hajecs.model.Actors.Person;
import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.*;

import java.util.Date;

/**
 * Created by lucjan on 10.03.15.
 */
@NodeEntity
@TypeAlias("Personality")
public class Personality {

    @GraphId
    private Long personality_id;
    private String name;
    private String lastname;
    private Date birth;
    private String telephoneNumber;


    @Fetch
    @RelatedTo(type = "CONNECTED", direction = Direction.BOTH)
    private Person person;

    public Personality() {
    }

    public Personality(String name, String lastname, Date birth, String telephoneNumber) {
        this.name = name;
        this.lastname = lastname;
        this.birth = birth;
        this.telephoneNumber = telephoneNumber;
    }

    public Personality(String name, String lastname, String birth, String telephoneNumber) {
        this(name, lastname, new Date(birth), telephoneNumber);
    }
    public Personality(Long personality_id, String name, String lastname, String birth, String telephoneNumber) {
        this(name, lastname, birth, telephoneNumber);
        this.personality_id = personality_id;
    }

    public Personality(Long personality_id, String name, String lastname, Date birth, String telephoneNumber) {
        this(name, lastname, birth, telephoneNumber);
        this.personality_id = personality_id;
    }



    //      EQUALS      AND     HASH_CODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personality that = (Personality) o;

        if (!birth.equals(that.birth)) return false;
        if (!lastname.equals(that.lastname)) return false;
        if (!name.equals(that.name)) return false;
        if (!telephoneNumber.equals(that.telephoneNumber)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + birth.hashCode();
        result = 31 * result + telephoneNumber.hashCode();
        return result;
    }


    //            METHODS

    public String getStringBirth() {
        return String.valueOf(birth);
    }

    //          END OF METHODS

    public Long getPersonality_id() {
        return personality_id;
    }

    public void setPersonality_id(Long personality_id) {
        this.personality_id = personality_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "Personality{" +
                "personality_id=" + personality_id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birth=" + birth +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}
