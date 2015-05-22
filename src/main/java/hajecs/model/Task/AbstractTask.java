package hajecs.model.Task;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hajecs.model.Actors.Person;
import hajecs.model.Graph.TaskNode;
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

    @JsonIgnore
    protected long hashValue;

    @Fetch
    @RelatedTo(type = "PERSON_TASK_RELATION", direction = Direction.BOTH)
    protected Set<Person> workerStorage = new HashSet<>();

    @Fetch
    @RelatedTo(type = "ROLE_TASK_RELATION", direction = Direction.BOTH)
    protected Set<Role> roleStorage = new HashSet<>();

    @Fetch
    @RelatedTo(type = "SINGLE_TASK_RELATION", direction = Direction.BOTH)
    protected Set<SingleTask> singleTaskStorage = new HashSet<>();


    @Fetch
    @RelatedTo(type = "TASKNODE_ABSTRACTTASK_RELATION", direction = Direction.BOTH)
    protected TaskNode taskNode;

    public AbstractTask() {
        hashValue = generateHashValue();
    }

    public AbstractTask(String name, String describe) {
        this.name = name;
        this.describe = describe;
        hashValue = generateHashValue();
    }

    public AbstractTask(String name, String describe, String start, String deadline) {
        this.name = name;
        this.describe = describe;
        this.start = new Date(start);
        this.deadline = new Date(deadline);
        hashValue = generateHashValue();
    }

    //  bez dat i id
    public AbstractTask(String name, String describe, Set<Role> roleStorage) {
        this.name = name;
        this.describe = describe;
        this.roleStorage = roleStorage;
        hashValue = generateHashValue();
    }

    public AbstractTask(String name, String describe, Date start, Date deadline, Set<Role> roleStorage) {
        this(name, describe, roleStorage);
        this.start = start;
        this.deadline = deadline;
        hashValue = generateHashValue();
    }


    public AbstractTask(Long id, String name, String describe, Date start, Date deadline, Set<Role> roleStorage) {
        this(name, describe, start, deadline, roleStorage);
        this.id = id;
        hashValue = generateHashValue();
    }

    private long generateHashValue() {
        Random random = new Random(System.currentTimeMillis());
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextLong();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractTask task = (AbstractTask) o;

        if (hashValue != task.hashValue) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (hashValue ^ (hashValue >>> 32));
    }

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

    public void setSingleTaskStorage(Set<SingleTask> singleTaskStorage)
    {
        this.singleTaskStorage = singleTaskStorage;
    }

    public long getHashValue() {
        return hashValue;
    }

    public void setHashValue(long hashValue) {
        this.hashValue = hashValue;
    }

    public TaskNode getTaskNode() {
        return taskNode;
    }

    public void setTaskNode(TaskNode taskNode) {
        this.taskNode = taskNode;
    }

    @Override
    public String toString() {
        return "AbstractTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", deadline=" + deadline +
                ", hashValue=" + hashValue +
                ", singleTaskStorage=" + singleTaskStorage +
                '}';
    }
}
