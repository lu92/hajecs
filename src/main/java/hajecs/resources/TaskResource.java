package hajecs.resources;

import hajecs.model.DTO.SingleTaskDTOInfo;
import hajecs.model.Task.DailyTask;
import hajecs.model.Task.SingleTask;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 24.05.15.
 */
public class TaskResource {

    private long nodeId = 10;
    private long milestoneId = 10;
    private String name = "task's name";
    private String describe = "task's describe";
    private String start = "20/05/2015";
    private String deadline = "22/05/2015";
    private Set<Long> workersId = new HashSet<>();
    private Set<Long> rolesId = new HashSet<>();
    private Set<SingleTaskDTOInfo> singleTaskDTOs = new HashSet<>();

    public static DailyTask getDailyTask() {
        String name = "task's name";
        String describe = "task's describe";
        String start = "20/05/2015";
        DailyTask dailyTask = new DailyTask(name, describe,start);
        dailyTask.getSingleTaskStorage().clear();
        dailyTask.getSingleTaskStorage().add(new SingleTask("08:00-16:00", "praca"));
        return dailyTask;
    }
}
