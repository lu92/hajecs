package hajecs.model.personalData;
import hajecs.model.Actors.Person;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;


/**
 * Created by lucjan on 10.03.15.
 */
@NodeEntity
public class Address {

    @GraphId
    private Long id;
    private String country;
    private String city;
    private String zipCode;


    @Fetch
    @RelatedTo(type = "CONNECTED2", direction = Direction.BOTH)
    private Person person;

    public Address() {
    }

    public Address(String country, String city, String zipCode) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Address(Long address_id, String country, String city, String zipCode) {
        this.id = address_id;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!city.equals(address.city)) return false;
        if (!country.equals(address.country)) return false;
        if (!zipCode.equals(address.zipCode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + zipCode.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
