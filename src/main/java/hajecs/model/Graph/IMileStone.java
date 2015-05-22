package hajecs.model.Graph;



import hajecs.model.Actors.Person;
import hajecs.model.Actors.Worker;
import hajecs.model.Task.AbstractTask;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by lucjan on 07.05.15.
 */
public interface IMileStone {
    int getNumberOfWorkers();
    int getNumberOfWorkersForSelecteNode(String nodeName);
    int getNumberOfWorkersForSelecteNode(TaskNode taskNode);
    Set<Person> getAllWorkers();
    Person getManager();
    Date getPredictableDeadline();
    Set<Person> getResponsibleWorkerForNode(TaskNode node);
    List<AbstractTask> getTasksForActualWeek();
    Set<AbstractTask> getPerformedTasks();
    Set<AbstractTask> getNotPerformedTasks();
    int getNumberOfNotPerformedTasks();
    int getNumberOfPerformedTasks();
    void setStartTaskNode(String startTaskNode);
    void setEndTaskNode(String endTaskNode);

    void setWorkerToTask(String taskName, Worker ... workers);
    void setWorkerToTask(TaskNode taskNode, Worker ... workers);
    void setWorkerToTask(long nodeNumber, Worker ... workers);
    void setWorkerToTask(AbstractTask task, Worker ... workers);

}
