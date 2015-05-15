package IntegratedTests;

import com.fasterxml.jackson.databind.jsontype.impl.AsExternalTypeDeserializer;
import hajecs.Neo4jTestApplication;
import hajecs.model.personalData.Personality;
import hajecs.repositories.PersonalityRepository;
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
public class PersonalityTest {

    @Autowired
    private PersonalityRepository personalityRepository;

    @Test
    @Rollback(true)
    public void personalityTest() {
        Assert.assertNotNull(personalityRepository);

        Personality personality1 = new Personality("Jan", "Kowalski", "01/01/1970", "123456789");
        Personality personality2 = new Personality("Jan", "Nowak", "01/01/1970", "123456789");
        Personality personality3 = new Personality("Joanna", "Skiba", "01/01/1970", "123456789");
        Assert.assertNotNull(personality1);
        Assert.assertNotNull(personality2);
        Assert.assertNotNull(personality3);

        personalityRepository.save(personality1);
        personalityRepository.save(personality2);
        personalityRepository.save(personality3);
        Assert.assertEquals(3, personalityRepository.count());

        Personality JoannaSkibaDb = personalityRepository.findByNameAndLastname("Joanna", "Skiba");
        Assert.assertNotNull(JoannaSkibaDb);

        Assert.assertTrue(JoannaSkibaDb.getName().equals("Joanna") && JoannaSkibaDb.getLastname().equals("Skiba"));
        System.out.println(JoannaSkibaDb);

    }
}
