package JUnitTest;


import hajecs.model.Task.AbstractTask;
import hajecs.model.Task.DailyTask;
import hajecs.model.personalData.Privilege;
import hajecs.model.personalData.Role;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lucjan on 07.05.15.
 */
public class TaskTest {

    @Test
    public void createDailyTask() {
        Role JavaDeveloperRole = new Role(1L, "programista Java");
        JavaDeveloperRole.addPrivileges(
                new Privilege(1L, "Java"),
                new Privilege(2L, "JSP"),
                new Privilege(3L, "Oracle DB"));

        AbstractTask dailyTask = new DailyTask("poniedzialek 13-05-2015", "nikt nie lubi programowac noca");
        dailyTask.addRoles(JavaDeveloperRole);
        Assert.assertNotNull(dailyTask);
    }
}
