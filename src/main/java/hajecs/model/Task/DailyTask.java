package hajecs.model.Task;

import hajecs.model.personalData.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lucjan on 07.05.15.
 */
public class DailyTask extends AbstractTask{

    public DailyTask() {
    }

    public DailyTask(String name, String describe, String day) {
        super(name, describe, day, day);
        singleTaskStorage.add(new SingleTask(name));
    }

    public DailyTask(String name, String describe, String day, String taskName) {
        super(name, describe, day, day);
        singleTaskStorage.add(new SingleTask(taskName));
    }



    public DailyTask(String name, String describe) {
        super(name, describe);
    }

    public DailyTask(String name, String describe, List<Role> requirements, String taskName) {
        super(name, describe, requirements);
        singleTaskStorage.add(new SingleTask(taskName));
    }

    public DailyTask(String name, String describe, Date day, List<Role> requirements, String taskName) {
        super(name, describe, day, day, requirements);
        singleTaskStorage.add(new SingleTask(taskName));
    }

    public DailyTask(Long id, String name, String describe, Date day, List<Role> requirements, String taskName) {
        super(id, name, describe, day, day, requirements);
        singleTaskStorage.add(new SingleTask(taskName));
    }


    public void changeTaskName(String taskName) {
        singleTaskStorage.get(0).setTask(taskName);
    }

    @Override
    public void addTasks(SingleTask... tasks) {}

    @Override
    public boolean isExecuted() {
        return singleTaskStorage.get(0).isExecuted();
    }

    @Override
    public int getProgress() {
        return isExecuted() ? 100 : 0;
    }

    @Override
    public List<SingleTask> getPerformedTasks() {
        return isExecuted() ? singleTaskStorage : new ArrayList<SingleTask>();
    }

    @Override
    public List<SingleTask> getNotPerformedTasks() {
        return isExecuted() ? new ArrayList<SingleTask>() : singleTaskStorage;
    }

    @Override
    public int getNumberOfPerformedTasks() {
        return getPerformedTasks().size();
    }

    @Override
    public int getNumberOfNotPerformedTasks() {
        return getNotPerformedTasks().size();
    }

    @Override
    public void endTask(int id) {
        singleTaskStorage.get(0).setExecuted(true);
        this.setEnd(new Date());
    }

    @Override
    public void endTask(String taskName) {
        singleTaskStorage.get(0).setExecuted(true);
        this.setEnd(new Date());
    }
}
