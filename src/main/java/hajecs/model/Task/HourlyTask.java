package hajecs.model.Task;

import hajecs.model.personalData.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lucjan on 07.05.15.
 */

public class HourlyTask extends AbstractTask{

    public HourlyTask() {
    }

    public HourlyTask(String name, String describe) {
        super(name, describe);
    }

    public HourlyTask(String name, String describe, List<Role> requirements) {
        super(name, describe, requirements);
    }

    public HourlyTask(String name, String describe, String day) {
        super(name, describe, day, day);
    }

    public HourlyTask(String name, String describe, Date day, List<Role> requirements) {
        super(name, describe, day, day, requirements);
    }

    public HourlyTask(Long id, String name, String describe, Date day, List<Role> requirements) {
        super(id, name, describe, day, day, requirements);
    }

    @Override
    public void addTasks(SingleTask ... tasks) {
        for (SingleTask singleTask : tasks)
            singleTaskStorage.add(singleTask);
    }

    @Override
    public boolean isExecuted() {
        return getProgress() == 100 ? true : false;
    }

    @Override
    public int getProgress() {  // do test
        return (int) (getNumberOfPerformedTasks() / singleTaskStorage.size()) * 100;
    }

    @Override
    public List<SingleTask> getPerformedTasks() {
        List<SingleTask> executedTasks = new ArrayList<SingleTask>(singleTaskStorage.size());
        for (SingleTask singleTask : singleTaskStorage)
            if (singleTask.isExecuted())
                executedTasks.add(singleTask);
        return executedTasks;
    }

    @Override
    public List<SingleTask> getNotPerformedTasks() {
        List<SingleTask> executedTasks = new ArrayList<SingleTask>(singleTaskStorage.size());
        for (SingleTask singleTask : singleTaskStorage)
            if (!singleTask.isExecuted())
                executedTasks.add(singleTask);
        return executedTasks;
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

    }

    @Override
    public void endTask(String taskName) {

    }
}
