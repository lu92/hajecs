package hajecs.model.Task;

import hajecs.model.personalData.Role;
import hajecs.notificationVisitor.Visitor;

import java.util.*;

/**
 * Created by lucjan on 07.05.15.
 */

public class HourlyTask extends AbstractTask{

    public HourlyTask() {
    }

    public HourlyTask(String name, String describe) {
        super(name, describe);
    }

    public HourlyTask(String name, String describe, Set<Role> requirements) {
        super(name, describe, requirements);
    }

    public HourlyTask(String name, String describe, String day) {
        super(name, describe, day, day);
    }

    public HourlyTask(String name, String describe, Date day, Set<Role> requirements) {
        super(name, describe, day, day, requirements);
    }

    public HourlyTask(Long id, String name, String describe, Date day, Set<Role> requirements) {
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
    public Set<SingleTask> getPerformedTasks() {
        Set<SingleTask> executedTasks = new HashSet<>(singleTaskStorage.size());
        for (SingleTask singleTask : singleTaskStorage)
            if (singleTask.isExecuted())
                executedTasks.add(singleTask);
        return executedTasks;
    }

    @Override
    public Set<SingleTask> getNotPerformedTasks() {
        Set<SingleTask> executedTasks = new HashSet<>(singleTaskStorage.size());
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

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
