package hajecs.model.Task;

import hajecs.model.personalData.Role;
import hajecs.notificationVisitor.Visitor;

import java.util.*;

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

    public DailyTask(String name, String describe, Set<Role> requirements, String taskName) {
        super(name, describe, requirements);
        singleTaskStorage.add(new SingleTask(taskName));
    }

    public DailyTask(String name, String describe, Date day, Set<Role> requirements, String taskName) {
        super(name, describe, day, day, requirements);
        singleTaskStorage.add(new SingleTask(taskName));
    }

    public DailyTask(Long id, String name, String describe, Date day, Set<Role> requirements, String taskName) {
        super(id, name, describe, day, day, requirements);
        singleTaskStorage.add(new SingleTask(taskName));
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

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
