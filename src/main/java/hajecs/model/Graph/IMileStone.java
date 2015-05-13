package hajecs.model.Graph;



import hajecs.model.Actors.Person;
import hajecs.model.Actors.Worker;
import hajecs.model.Task.AbstractTask;

import java.util.Date;
import java.util.List;

/**
 * Created by lucjan on 07.05.15.
 */
public interface IMileStone {
    int getNumberOfWorkers();
    int getNumberOfWorkersForSelecteNode(String nodeName);
    int getNumberOfWorkersForSelecteNode(TaskNode taskNode);
    Person getManager();
    Date getPredictableDeadline();
    List<Person> getResponsibleWorkerForNode(TaskNode node);
    List<AbstractTask> getTasksForActualWeek();
    List<AbstractTask> getPerformedTasks();
    List<AbstractTask> getNotPerformedTasks();
    int getNumberOfNotPerformedTasks();
    int getNumberOfPerformedTasks();
    void setStartTaskNode(String startTaskNode);
    void setEndTaskNode(String endTaskNode);

    void setWorkerToTask(String taskName, Worker ... workers);
    void setWorkerToTask(TaskNode taskNode, Worker ... workers);
    void setWorkerToTask(long nodeNumber, Worker ... workers);
    void setWorkerToTask(AbstractTask task, Worker ... workers);

}
