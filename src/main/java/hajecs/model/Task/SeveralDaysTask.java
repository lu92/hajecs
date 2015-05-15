package hajecs.model.Task;

import hajecs.model.personalData.Role;

import java.util.*;

/**
 * Created by lucjan on 08.05.15.
 */

public class SeveralDaysTask extends AbstractTask {

    public SeveralDaysTask() {
    }

    public SeveralDaysTask(String name, String describe) {
        super(name, describe);
        getSingleTaskStorage().add(new SingleTask(name));
    }

    public SeveralDaysTask(String name, String describe, String start, String deadline) {
        super(name, describe, start, deadline);
        getSingleTaskStorage().add(new SingleTask(name));
    }

    public SeveralDaysTask(String name, String describe, Date start, Date deadline, Set<Role> requirements) {
        super(name, describe, start, deadline, requirements);
        getSingleTaskStorage().add(new SingleTask(name));
    }

    public SeveralDaysTask(Long id, String name, String describe, Date start, Date deadline, Set<Role> requirements) {
        super(id, name, describe, start, deadline, requirements);
        getSingleTaskStorage().add(new SingleTask(name));
    }

    public void changeTaskName(String taskName) {
        singleTaskStorage.iterator().next().setTask(taskName);
    }

    @Override
    public void addTasks(SingleTask... tasks) {}

    @Override
    public boolean isExecuted() {
        return singleTaskStorage.iterator().next().isExecuted();
    }

    @Override
    public int getProgress() {
        return isExecuted() ? 100 : 0;
    }

    @Override
    public Set<SingleTask> getPerformedTasks() {
        return isExecuted() ? singleTaskStorage : new HashSet<>();
    }

    @Override
    public Set<SingleTask> getNotPerformedTasks() {
        return isExecuted() ? new HashSet<>() : singleTaskStorage;
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
        singleTaskStorage.iterator().next().setExecuted(true);
        this.setEnd(new Date());
    }

    @Override
    public void endTask(String taskName) {
        singleTaskStorage.iterator().next().setExecuted(true);
        this.setEnd(new Date());
    }
}
