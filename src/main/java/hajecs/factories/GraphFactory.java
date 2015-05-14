package hajecs.factories;

import hajecs.model.Graph.AbstractGraph;
import hajecs.model.Graph.Graph;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.Project;
import hajecs.model.Task.AbstractTask;

/**
 * Created by lucjan on 13.05.15.
 */
public final class GraphFactory extends AbstractFactory {

    private static final GraphFactory graphFactoryInstance = new GraphFactory();

    private GraphFactory() {
    }

    public static GraphFactory getGraphFactoryInstance() {
        return graphFactoryInstance;
    }

    @Override
    AbstractGraph getGraph(GraphStructureType type) {

        switch (type) {
            case MILESTONE:
                return new MileStone("default milestone", "this milestone was made by Graph Factory");
            case GRAPH:
                return new Graph("default graph", "this graph was made by Graph Factory");
            case PROJECT:
                return new Project("default project", "this project was made by Graph Factory");
        }

        return null;
    }

    @Override
    AbstractTask getTask(TaskType type) {
        return null;
    }
}
