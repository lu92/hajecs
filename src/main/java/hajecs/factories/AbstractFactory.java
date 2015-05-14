package hajecs.factories;


import hajecs.model.Graph.AbstractGraph;
import hajecs.model.Task.AbstractTask;

/**
 * Created by lucjan on 13.05.15.
 */
public abstract class AbstractFactory {
    abstract AbstractGraph getGraph(GraphStructureType type);
    abstract AbstractTask getTask(TaskType type);
}
