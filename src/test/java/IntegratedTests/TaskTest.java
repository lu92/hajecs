package IntegratedTests;

import hajecs.HajecsApplication;
import hajecs.model.Task.AbstractTask;
import hajecs.model.Task.DailyTask;
import hajecs.model.Task.HourlyTask;
import hajecs.model.Task.SingleTask;
import hajecs.model.personalData.Privilege;
import hajecs.model.personalData.Role;
import hajecs.repositories.AbstractTaskRepository;
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
public class TaskTest {

    @Autowired
    private AbstractTaskRepository abstractTaskRepository;

    @Autowired
    private SingleTaskRepository singleTaskRepository;

    @Test
    public void saveDailyTask() {
        AbstractTask dailyTask = new DailyTask("piatek", "description", "12/05/2015", "first task");
        Assert.assertNotNull(abstractTaskRepository);
        Assert.assertNotNull(dailyTask);

        abstractTaskRepository.save(dailyTask);
        Assert.assertEquals(1L, abstractTaskRepository.count());

        Assert.assertNotNull(singleTaskRepository);
        Assert.assertEquals(1L, singleTaskRepository.count());
        Assert.assertEquals("first task", singleTaskRepository.findAll().get(0).getTask());

    }

    @Test
    public void saveHourlyTask() {
        AbstractTask hourlyTask = new HourlyTask("piatek", "description", "12/05/2015");
        hourlyTask.addTasks(
                new SingleTask("12-13", "spotkanie z klientem"),
                new SingleTask("13-14", "obiad z prezesem"),
                new SingleTask("14-15", "odebrac dzieciaka ze szkoly"));

        Assert.assertNotNull(hourlyTask);

        //  dodanie rol


        Role JavaDeveloper = new Role("JavaDeveloper");
        Role CPPDeveloper = new Role("CPPDeveloper");

        JavaDeveloper.addPrivileges(new Privilege("java"),new Privilege("jsp"),new Privilege("tomcat"));
        CPPDeveloper.addPrivileges(new Privilege("unitTest"),new Privilege("jsp"));
        hourlyTask.addRoles(JavaDeveloper, CPPDeveloper);

        //

        abstractTaskRepository.save(hourlyTask);
        Assert.assertEquals(1L, abstractTaskRepository.count());
        Assert.assertEquals(3L, singleTaskRepository.count());
        AbstractTask hourlyTaskDb = abstractTaskRepository.findAll().get(0);
        String [] correctDuration = {"12-13", "13-14", "14-15"};

        int i=0;
        for (SingleTask singleTask : hourlyTaskDb.getSingleTaskStorage()) {
            Assert.assertEquals(correctDuration[i++], singleTask.getDuration());
        }


        Assert.assertEquals(2L, abstractTaskRepository.findAll().get(0).getRoleStorage().size());
    }
}
