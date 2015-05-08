package IntegratedTests;

import hajecs.HajecsApplication;
import hajecs.model.Task.SingleTask;
import hajecs.repositories.SingleTaskRepository;
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
public class SingleTaskTest {

    @Autowired
    private SingleTaskRepository singleTaskRepository;

    @Test
    public void simpleTest() {
        SingleTask singleTask = new SingleTask("task duration", "task name");
        Assert.assertNotNull(singleTaskRepository);

        singleTaskRepository.save(singleTask);
        Assert.assertEquals(1L, singleTaskRepository.count());

        Assert.assertEquals("task duration", singleTaskRepository.findAll().get(0).getDuration());
        Assert.assertEquals(singleTask, singleTaskRepository.findAll().get(0));
    }
}
