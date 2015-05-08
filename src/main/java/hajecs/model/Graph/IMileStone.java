package hajecs.model.Graph;



import hajecs.model.Actors.Person;

import java.util.Date;
import java.util.List;

/**
 * Created by lucjan on 07.05.15.
 */
public interface IMileStone {
    int getNumberOfWorkers();
    Date getPredictabeDeadline();
    Person getResponsibleWorkerForNode(AbstractNode node);
    List<AbstractNode> getTasksForActualWeek();
    List<AbstractNode> getPerformedTasks();
    int getNumberOfPerformedTasks();
}
