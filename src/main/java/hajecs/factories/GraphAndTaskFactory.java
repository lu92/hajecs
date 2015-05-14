package hajecs.factories;

import hajecs.model.Graph.AbstractGraph;
import hajecs.model.Task.AbstractTask;

/**
 * Created by lucjan on 13.05.15.
 */
public final class GraphAndTaskFactory {
    private static final GraphFactory graphFactory = GraphFactory.getGraphFactoryInstance();
    private static final TaskFactory taskFactory = TaskFactory.getTaskFactoryInstance();

    private final static GraphAndTaskFactory graphAndTaskFactoryInstance = new GraphAndTaskFactory();


    private GraphAndTaskFactory() {
    }

    public static GraphAndTaskFactory getInstance() {
        return graphAndTaskFactoryInstance;
    }

    public static GraphFactory getGraphFactory() {
        return graphFactory;
    }

    public static TaskFactory getTaskFactory() {
        return taskFactory;
    }

    public AbstractGraph createGraphStructure(GraphStructureType type) {
        return graphFactory.getGraph(type);
    }

    public AbstractTask createTask(TaskType type) {
        return taskFactory.getTask(type);
    }
}
