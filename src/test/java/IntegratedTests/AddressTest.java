package IntegratedTests;

import hajecs.Neo4jTestApplication;
import hajecs.model.personalData.Address;
import hajecs.repositories.AddressRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucjan on 14.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jTestApplication.class)
@Transactional
public class AddressTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void addressTest() {
        Assert.assertNotNull(addressRepository);

        Address address1 = new Address("Poland", "Warsaw", "30-890");
        Address address2 = new Address("Poland", "Lublin", "12-290");
        Address address3 = new Address("Usa", "Washington", "23-234");

        Assert.assertNotNull(address1);
        Assert.assertNotNull(address2);
        Assert.assertNotNull(address3);

        addressRepository.save(address1);
        addressRepository.save(address2);
        addressRepository.save(address3);

        Assert.assertEquals(3, addressRepository.count());
        Assert.assertEquals(2, addressRepository.findByCountry("Poland").size());
        Assert.assertEquals(1, addressRepository.findByCountryAndCity("Usa", "Washington").size());
    }

}

