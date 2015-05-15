package hajecs.repositories;

import hajecs.model.personalData.Address;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.Set;

/**
 * Created by lucjan on 14.05.15.
 */
public interface AddressRepository extends GraphRepository<Address> {
    Set<Address> findByCountry(String country);
    Set<Address> findByCity(String city);
    Set<Address> findByCountryAndCity(String country, String city);
}
