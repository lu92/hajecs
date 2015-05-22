package IntegratedTests;

import hajecs.Neo4jTestApplication;
import hajecs.resources.PersonResource;
import hajecs.model.Actors.Manager;
import hajecs.model.Actors.Person;
import hajecs.model.Actors.Worker;
import hajecs.repositories.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucjan on 14.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jTestApplication.class)
@Transactional
public class PersonTest {

    @Autowired
    private PersonRepository personRepository;

    @Test @Rollback(true)
    public void personTest() {
        Assert.assertNotNull(personRepository);

        personRepository.save(PersonResource.getWebDeveloperDominikNocon());
        personRepository.save(PersonResource.getJavaDeveloperAdamWojcik());
        personRepository.save((Manager) PersonResource.getManagerJanKowalski());
        personRepository.save((Worker) PersonResource.getJavaDeveloperWojciechSeliga());

        Assert.assertEquals(4, personRepository.count());
        for (Person person : personRepository.findAll()) {
            System.out.println(person.getPersonality().getName());
            System.out.println(person.getPersonality().getLastname());
            System.out.println(person.getAddress().getCountry());
            System.out.println(person.getEmail());
            System.out.println(person.getRoleStorage());
            System.out.println(person.getId());
        }

    }

}
