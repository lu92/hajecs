package IntegratedTests;

import hajecs.Neo4jTestApplication;
import hajecs.Resource.PersonResource;
import hajecs.Resource.RoleResource;
import hajecs.model.Task.DailyTask;
import hajecs.model.Task.SingleTask;
import hajecs.model.personalData.Role;
import hajecs.repositories.PersonRepository;
import hajecs.repositories.RoleRepository;
import hajecs.repositories.SingleTaskRepository;
import hajecs.repositories.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

/**
 * Created by lucjan on 15.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jTestApplication.class)
@Transactional
public class TaskTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SingleTaskRepository singleTaskRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Rollback(true)
    public void saveTask() {
        Assert.assertNotNull(taskRepository);
        Assert.assertNotNull(singleTaskRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(personRepository);

        DailyTask dailyTask = new DailyTask("name", "describe", "12/04/2015");
        dailyTask.addRoles(
                new Role("defaul Role")
        );

        dailyTask.addWorkers(
                PersonResource.getManagerJanKowalski(),
                PersonResource.getJavaDeveloperAdamWojcik(),
                PersonResource.getJavaDeveloperWojciechSeliga(),
                PersonResource.getUXDesignerMonikaStokrotka()
        );


        Assert.assertEquals(1, dailyTask.getRoleStorage().size());

        taskRepository.save(dailyTask);

//        DailyTask taskDb = (DailyTask) taskRepository.findAll().iterator().next();

//        for (Role role : roleRepository.findAll())
//            taskDb.addRoles(role);

//        taskRepository.save(taskDb);

        Assert.assertEquals(1, taskRepository.count());
        Assert.assertEquals(1, singleTaskRepository.count());

        Assert.assertEquals(singleTaskRepository.findAll().iterator().next().getAbstractTask().getDeadline(), new Date("12/04/2015"));


        taskRepository.findAll().iterator().next().getRoleStorage().remove(RoleResource.getWebDeveloper());
        Assert.assertEquals(5, roleRepository.count());

        Assert.assertEquals(4, taskRepository.findAll().iterator().next().getNumberOfWorkers());
        Assert.assertEquals(4, personRepository.count());

        //  Everything is ok
        Assert.assertEquals(5,roleRepository.count());
        Assert.assertEquals(1, taskRepository.findAll().iterator().next().getRoleStorage().size());
    }
}
