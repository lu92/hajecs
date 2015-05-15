package IntegratedTests;

import hajecs.Neo4jTestApplication;
import hajecs.model.Task.SingleTask;
import hajecs.repositories.SingleTaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 15.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jTestApplication.class)
@Transactional
public class SingleTaskTest {

    @Autowired
    private SingleTaskRepository singleTaskRepository;

    @Test
    public void singleTaskTest() {
        Assert.assertNotNull(singleTaskRepository);

        Set<SingleTask> singleTaskSet = new HashSet<>();
        for (int i=0; i<5; i++) {
            SingleTask task = new SingleTask("duration 12-14 hour", "task " + i);
            task.setExecuted(true);
            singleTaskSet.add(task);
        }

        for (SingleTask singleTask : singleTaskSet)
            singleTaskRepository.save(singleTask);


        Assert.assertEquals(5, singleTaskRepository.count());

        int value = 0;
        for (SingleTask task : singleTaskRepository.findAll()) {
            Assert.assertFalse(task.isExecuted());
            for (int i=0; i<5; i++) {
                if (task.getTask().equals("task " + i))
                    value++;
            }
        }

        Assert.assertEquals(5, value);

    }


}
