package JUnitTest;

import hajecs.factories.GraphAndTaskFactory;
import hajecs.factories.GraphStructureType;
import hajecs.factories.TaskType;
import hajecs.model.Graph.Graph;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.Project;
import hajecs.model.Task.DailyTask;
import hajecs.model.Task.HourlyTask;
import hajecs.model.Task.SeveralDaysTask;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lucjan on 13.05.15.
 */
public class GraphFactoryTest {

    @Test
    public void createAllKindOfFactories() {
        GraphAndTaskFactory graphAndTaskFactory = GraphAndTaskFactory.getInstance();
        Assert.assertNotNull(graphAndTaskFactory);

        Assert.assertTrue(graphAndTaskFactory.createGraphStructure(GraphStructureType.GRAPH) instanceof Graph);
        Assert.assertTrue(graphAndTaskFactory.createGraphStructure(GraphStructureType.MILESTONE) instanceof MileStone);
        Assert.assertTrue(graphAndTaskFactory.createGraphStructure(GraphStructureType.PROJECT) instanceof Project);

        Assert.assertTrue(graphAndTaskFactory.createTask(TaskType.DAILY_TASK) instanceof DailyTask);
        Assert.assertTrue(graphAndTaskFactory.createTask(TaskType.HOURLY_TASK) instanceof HourlyTask);
        Assert.assertTrue(graphAndTaskFactory.createTask(TaskType.SEVERALDAYS_TASK) instanceof SeveralDaysTask);
    }
}
