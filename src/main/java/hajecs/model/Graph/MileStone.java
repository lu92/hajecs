package hajecs.model.Graph;

import hajecs.model.Actors.Person;
import hajecs.model.Actors.Worker;
import hajecs.model.Task.AbstractTask;
import org.springframework.data.annotation.Transient;

import java.util.*;

/**
 * Created by lucjan on 07.05.15.
 */
public class MileStone extends AbstractGraph implements IMileStone{

    @Transient
    private Person manager;

    @Transient
    private TaskNode startTaskNode;

    @Transient
    private TaskNode endTaskNode;

    private long counter = 0L;  // do numerowania nodow w ktorych sa taski, nazwa noda

    public MileStone() {
    }

    public MileStone(String name) {
        super(name);
    }

    public MileStone(String name, String describe) {
        super(name, describe);
    }

    public MileStone(Long id, String name, String describe) {
        super(id, name, describe);
    }

    public MileStone(Person manager) {
        this.manager = manager;
    }

    public MileStone(String name, String describe, Person manager) {
        super(name, describe);
        this.manager = manager;
    }

    public MileStone(Long id, String name, String describe, Person manager) {
        super(id, name, describe);
        this.manager = manager;
    }

    //      METHODS


    public void addTasks(AbstractTask ... tasks) {
        for (AbstractTask task : tasks) {
            this.addNodes(new TaskNode("" + counter++, task));
        }
    }

    public void addRelationShipsBetweenTwoTasks(String beginTask, String endTask) {
        addRelationShips(findNodeByTask(beginTask), findNodeByTask(endTask));
    }

    public TaskNode findNodeByTask(String taskName) throws IllegalArgumentException{
        for (AbstractNode node : getNodeStorage()) {
            if ( ((TaskNode) node).getTask().getName().equals(taskName))
                return (TaskNode) node;
        }
        throw new IllegalArgumentException("task " + taskName + " doesn't exists");
    }


    //      METHODS FROM INTERFACE IMileStone
    @Override
    public int getNumberOfWorkers() {
        int number = 0;
        for (AbstractNode node : this.getNodeStorage()) {
            number += ((TaskNode) node).getTask().getNumberOfWorkers();
        }
        return number;
    }

    @Override
    public int getNumberOfWorkersForSelecteNode(String nodeName) {
        TaskNode taskNode = (TaskNode) findNode(nodeName);
        return taskNode.getTask().getNumberOfWorkers();
    }

    @Override
    public int getNumberOfWorkersForSelecteNode(TaskNode taskNode) {
        return taskNode.getTask().getNumberOfWorkers();
    }

    @Override
    public Set<Person> getAllWorkers() {
        Set<Person> allWorkers = new HashSet<>();
        for (AbstractNode node : this.getNodeStorage()) {
            allWorkers.addAll(((TaskNode) node).getTask().getWorkerStorage());
        }
        return allWorkers;
    }


    @Override
    public Person getManager() {
        return manager;
    }

    @Override
    public Date getPredictableDeadline() {
        return endTaskNode.getTask().getEnd();
    }

    @Override
    public Set<Person> getResponsibleWorkerForNode(TaskNode node) {
        return node.getTask().getWorkerStorage();
    }

    @Override
    public List<AbstractTask> getTasksForActualWeek() {
        return null;
    }

    @Override
    public Set<AbstractTask> getPerformedTasks() {
        Set<AbstractTask> performedTasks = new HashSet<>();
        for (AbstractTask task : getAllTasks())
            if (task.isExecuted())
                performedTasks.add(task);
        return performedTasks;
    }


    public List<AbstractTask> getAllTasks() {
        List<AbstractTask> allTasks = new ArrayList<>();
        for (AbstractNode node : getNodeStorage())
            allTasks.add(((TaskNode)node).getTask());
        return allTasks;
    }

    @Override
    public Set<AbstractTask> getNotPerformedTasks() {
        Set<AbstractTask> notPerformedTasks = new HashSet<>();
        for (AbstractTask task : getAllTasks())
            if (!task.isExecuted())
                notPerformedTasks.add(task);
        return notPerformedTasks;
    }

    @Override
    public int getNumberOfNotPerformedTasks() {
        return getNotPerformedTasks().size();
    }

    @Override
    public int getNumberOfPerformedTasks() {
        return getPerformedTasks().size();
    }

    @Override
    public void setStartTaskNode(String startTaskNode) {
        this.startTaskNode = findNodeByTask(startTaskNode);
    }

    @Override
    public void setEndTaskNode(String endTaskNode) {
        this.endTaskNode = findNodeByTask(endTaskNode);
    }

    @Override
    public void setWorkerToTask(String taskName, Worker... workers) {
        TaskNode taskNode = findNodeByTask(taskName);
        taskNode.getTask().addWorkers(workers);
    }

    @Override
    public void setWorkerToTask(TaskNode taskNode, Worker... workers) {
        taskNode.getTask().addWorkers(workers);
    }

    @Override
    public void setWorkerToTask(long nodeNumber, Worker... workers) {
        TaskNode taskNode = findNodeByTask(String.valueOf(nodeNumber));
        taskNode.getTask().addWorkers(workers);
    }

    @Override
    public void setWorkerToTask(AbstractTask task, Worker... workers) {
        task.addWorkers(workers);
    }

//          SETTERS AND GETTERS

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public TaskNode getStartTaskNode() {
        return startTaskNode;
    }

    public void setStartTaskNode(TaskNode startTaskNode) {
        this.startTaskNode = startTaskNode;
    }

    public TaskNode getEndTaskNode() {
        return endTaskNode;
    }

    public void setEndTaskNode(TaskNode endTaskNode) {
        this.endTaskNode = endTaskNode;
    }


    @Override
    public String toString() {
        return "AbstractGraph{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", nodeStorage=" + nodeStorage +
                ", graphRelationShipStorage=" + graphRelationShipStorage +
                '}'
        +
         "MileStone{" +
                "manager=" + manager +
                ", startTaskNode=" + startTaskNode +
                ", endTaskNode=" + endTaskNode +
                ", counter=" + counter +
                '}';
    }
}
