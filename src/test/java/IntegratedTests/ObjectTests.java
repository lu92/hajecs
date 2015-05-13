package IntegratedTests;

import hajecs.HajecsApplication;
import hajecs.model.test.AbstractObject;
import hajecs.model.test.ObjectImpl;
import hajecs.repositories.ObjectRepository;
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
 * Created by lucjan on 11.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HajecsApplication.class)
@WebAppConfiguration
@Transactional  //  IMPORTANT
@TransactionConfiguration(defaultRollback = true)
public class ObjectTests {

    @Autowired
    private ObjectRepository objectRepository;

    @Test
    public void test() {
        Assert.assertNotNull(objectRepository);

        AbstractObject aga = new ObjectImpl("Aga", 22);
        Assert.assertNotNull(aga);

        aga.getOtherObjects().add(new ObjectImpl("Czesio", 1));
        aga.getOtherObjects().add(new ObjectImpl("Miesio", 2));
        aga.getOtherObjects().add(new ObjectImpl("Kasia", 3));

        Assert.assertEquals(3, aga.getOtherObjects().size());


        objectRepository.save(aga);
        Assert.assertEquals(4, objectRepository.count());


    }
}
