package hajecs.model.Task;


import hajecs.model.Actors.Person;
import hajecs.model.personalData.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lucjan on 07.05.15.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractTask {

    @Id @GeneratedValue
    protected Long id;
    protected String name;
    protected String describe;
    protected Date start;
    protected Date end;
    protected Date deadline;

    @Transient
    protected List<Person> workerStorage = new ArrayList<Person>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected List<Role> roleStorage = new ArrayList<Role>();

    @OneToMany(mappedBy = "abstractTask", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected List<SingleTask> singleTaskStorage = new ArrayList<SingleTask>();

    public AbstractTask() {
    }

    public AbstractTask(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    public AbstractTask(String name, String describe, String start, String deadline) {
        this.name = name;
        this.describe = describe;
        this.start = new Date(start);
        this.deadline = new Date(deadline);
    }

    //  bez dat i id
    public AbstractTask(String name, String describe, List<Role> roleStorage) {
        this.name = name;
        this.describe = describe;
        this.roleStorage = roleStorage;
    }

    public AbstractTask(String name, String describe, Date start, Date deadline, List<Role> roleStorage) {
        this(name, describe, roleStorage);
        this.start = start;
        this.deadline = deadline;
    }


    public AbstractTask(Long id, String name, String describe, Date start, Date deadline, List<Role> roleStorage) {
        this(name, describe, start, deadline, roleStorage);
        this.id = id;
    }

    public void addWorkers(Person ... workers) {
        for (Person person : workers)
            workerStorage.add(person);
    }

    public void addRoles(Role ... roles) {
        for (Role role : roles)
            roleStorage.add(role);
    }

    public void deleteWorkers() {
    }

    public int getDistanceTimeInDays() {
        return 0;
    }

    public abstract void addTasks(SingleTask ... tasks);
    public abstract boolean isExecuted();
    public abstract int getProgress();
    public abstract List<SingleTask> getPerformedTasks();
    public abstract List<SingleTask> getNotPerformedTasks();
    public abstract int getNumberOfPerformedTasks();
    public abstract int getNumberOfNotPerformedTasks();
    public abstract void endTask(int id);
    public abstract void endTask(String taskName);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<Person> getWorkerStorage() {
        return workerStorage;
    }

    public void setWorkerStorage(List<Person> workerStorage) {
        this.workerStorage = workerStorage;
    }

    public List<Role> getRoleStorage() {
        return roleStorage;
    }

    public void setRoleStorage(List<Role> roleStorage) {
        this.roleStorage = roleStorage;
    }

    public List<SingleTask> getSingleTaskStorage() {
        return singleTaskStorage;
    }

    public void setSingleTaskStorage(List<SingleTask> singleTaskStorage) {
        this.singleTaskStorage = singleTaskStorage;
    }
}
