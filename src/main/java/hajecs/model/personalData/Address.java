package hajecs.model.personalData;
import hajecs.model.Actors.Person;

import javax.persistence.*;

/**
 * Created by lucjan on 10.03.15.
 */
@Entity
public class Address {

    @Id @GeneratedValue
    private Long address_id;
    private String country;
    private String city;
    private String zipCode;


    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;

    public Address() {
    }

    public Address(String country, String city, String zipCode) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Address(Long address_id, String country, String city, String zipCode) {
        this.address_id = address_id;
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

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
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
                "address_id=" + address_id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
