package IntegratedTests;

import hajecs.HajecsApplication;
import hajecs.model.Actors.Worker;
import hajecs.model.personalData.Address;
import hajecs.model.personalData.Personality;
import hajecs.model.personalData.Privilege;
import hajecs.model.personalData.Role;
import hajecs.repositories.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucjan on 08.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HajecsApplication.class)
@WebAppConfiguration
@Transactional  //  IMPORTANT
@TransactionConfiguration(defaultRollback = true)
public class PersonTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void createSimplePerson() {
        Assert.assertNotNull(personRepository);
        Worker worker = new Worker("user", "pass", "user@gmail.com");
        Assert.assertNotNull(worker);

        Personality personality = new Personality("agnieszka", "sloma", "05/01/1992", "661031041");
        Assert.assertNotNull(personality);

        Address address = new Address("Poland", "Warsaw", "30-112");
        Assert.assertNotNull(address);

        worker.setPersonality(personality);
        worker.setAddress(address);

        Role JavaDeveloper = new Role("JavaDeveloper");
        Role CPPDeveloper = new Role("CPPDeveloper");

        JavaDeveloper.addPrivileges(new Privilege("java"),new Privilege("jsp"),new Privilege("tomcat"));
        CPPDeveloper.addPrivileges(new Privilege("unitTest"),new Privilege("jsp"));

        worker.addRoles(CPPDeveloper, JavaDeveloper);

        personRepository.save(worker);
        Assert.assertEquals(1L, personRepository.count());
        Assert.assertEquals(personality, personRepository.findAll().get(0).getPersonality());
        Assert.assertEquals(address, personRepository.findAll().get(0).getAddress());
        Assert.assertEquals(2L, personRepository.findAll().get(0).getRoleStorage().size());
    }
}
