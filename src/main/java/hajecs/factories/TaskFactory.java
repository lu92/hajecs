package hajecs.factories;

import hajecs.model.Graph.AbstractGraph;
import hajecs.model.Task.AbstractTask;
import hajecs.model.Task.DailyTask;
import hajecs.model.Task.HourlyTask;
import hajecs.model.Task.SeveralDaysTask;

/**
 * Created by lucjan on 13.05.15.
 */
public final class TaskFactory  extends  AbstractFactory {

    private static final TaskFactory taskFactoryInstance = new TaskFactory();

    private TaskFactory() {
    }

    public static TaskFactory getTaskFactoryInstance() {
        return taskFactoryInstance;
    }

    @Override
    AbstractGraph getGraph(GraphStructureType type) {
        return null;
    }

    @Override
    AbstractTask getTask(TaskType type) {
        switch (type) {

            case DAILY_TASK:
                return new DailyTask("default daily task", "this daily task was made by Task Factory");

            case HOURLY_TASK:
                return new HourlyTask("default daily task", "this hourly task was made by Task Factory");

            case SEVERALDAYS_TASK:
                return new SeveralDaysTask("default daily task", "this several days task was made by Task Factory");
        }
        return null;
    }
}
