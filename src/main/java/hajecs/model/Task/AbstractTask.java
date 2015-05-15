package hajecs.model.Task;


import hajecs.model.Actors.Person;
import hajecs.model.personalData.Role;
import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lucjan on 07.05.15.
 */
@NodeEntity
public abstract class AbstractTask {

    @GraphId
    protected Long id;
    protected String name;
    protected String describe;
    protected Date start;
    protected Date end;
    protected Date deadline;

    @Fetch
    @RelatedTo(type = "PERSON_TASK_RELATION", direction = Direction.BOTH)
    protected Set<Person> workerStorage = new HashSet<>();

    @Fetch
    @RelatedTo(type = "ROLE_TASK_RELATION", direction = Direction.BOTH)
    protected Set<Role> roleStorage = new HashSet<>();

    @Fetch
    @RelatedTo(type = "SINGLE_TASK_RELATION", direction = Direction.BOTH)
    protected Set<SingleTask> singleTaskStorage = new HashSet<>();

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
    public AbstractTask(String name, String describe, Set<Role> roleStorage) {
        this.name = name;
        this.describe = describe;
        this.roleStorage = roleStorage;
    }

    public AbstractTask(String name, String describe, Date start, Date deadline, Set<Role> roleStorage) {
        this(name, describe, roleStorage);
        this.start = start;
        this.deadline = deadline;
    }


    public AbstractTask(Long id, String name, String describe, Date start, Date deadline, Set<Role> roleStorage) {
        this(name, describe, start, deadline, roleStorage);
        this.id = id;
    }

    public int getNumberOfWorkers() {
        return getWorkerStorage().size();
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
    public abstract Set<SingleTask> getPerformedTasks();
    public abstract Set<SingleTask> getNotPerformedTasks();
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

    public Set<Person> getWorkerStorage() {
        return workerStorage;
    }

    public void setWorkerStorage(Set<Person> workerStorage) {
        this.workerStorage = workerStorage;
    }

    public Set<Role> getRoleStorage() {
        return roleStorage;
    }

    public void setRoleStorage(Set<Role> roleStorage) {
        this.roleStorage = roleStorage;
    }

    public Set<SingleTask> getSingleTaskStorage() {
        return singleTaskStorage;
    }

    public void setSingleTaskStorage(Set<SingleTask> singleTaskStorage) {
        this.singleTaskStorage = singleTaskStorage;
    }
}
