package hajecs.model.Graph;

import hajecs.model.Actors.Person;
import java.util.Date;
import java.util.List;

/**
 * Created by lucjan on 07.05.15.
 */
public class MileStone extends AbstractGraph implements IMileStone{

    private Person manager;

    @Override
    public int getNumberOfWorkers() {
        return 0;
    }

    @Override
    public Date getPredictabeDeadline() {
        return null;
    }

    @Override
    public List<AbstractNode> getTasksForActualWeek() {
        return null;
    }

    @Override
    public List<AbstractNode> getPerformedTasks() {
        return null;
    }

    @Override
    public int getNumberOfPerformedTasks() {
        return 0;
    }

    @Override
    public Person getResponsibleWorkerForNode(AbstractNode node) {
        return null;
    }
}
