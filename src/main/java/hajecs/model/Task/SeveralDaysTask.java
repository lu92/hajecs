package hajecs.model.Task;

import hajecs.model.personalData.Role;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lucjan on 08.05.15.
 */

@Entity
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

    public SeveralDaysTask(String name, String describe, Date start, Date deadline, List<Role> requirements) {
        super(name, describe, start, deadline, requirements);
        getSingleTaskStorage().add(new SingleTask(name));
    }

    public SeveralDaysTask(Long id, String name, String describe, Date start, Date deadline, List<Role> requirements) {
        super(id, name, describe, start, deadline, requirements);
        getSingleTaskStorage().add(new SingleTask(name));
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
