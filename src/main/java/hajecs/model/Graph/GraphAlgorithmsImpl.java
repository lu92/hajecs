package hajecs.model.Graph;

import java.util.Date;
import java.util.List;

/**
 * Created by lucjan on 13.05.15.
 */
public class GraphAlgorithmsImpl implements GraphAlgorithms {
    @Override
    public boolean checkAcyclicality(AbstractGraph graph) {
        return false;
    }

    @Override
    public AbstractGraph getUndirectedGraph(AbstractGraph graph) {
        AbstractGraph undirectedGraph = chooseAndGetProperInstanceOfGraph(graph);
        for (RelationShip relationShip : graph.getGraphRelationShipStorage()) {
            undirectedGraph.addRelationShips(relationShip.getBeginNode(), relationShip.getEndNode());
            undirectedGraph.addRelationShips(relationShip.getEndNode(), relationShip.getBeginNode());
        }
        return undirectedGraph;
    }

    private AbstractGraph chooseAndGetProperInstanceOfGraph(AbstractGraph graph) {
        AbstractGraph instanceOfSelectedGraphType = null;
        if (graph instanceof MileStone) {
            instanceOfSelectedGraphType = new MileStone(graph.getName(), graph.getDescribe());
        }

        if (graph instanceof Project) {
            instanceOfSelectedGraphType = new Project(graph.getName(), graph.getDescribe());
        }

        if (graph instanceof Graph) {
            instanceOfSelectedGraphType = new Graph(graph.getName(), graph.getDescribe());
        }
        return instanceOfSelectedGraphType;
    }

    @Override
    public boolean checkDirected(AbstractGraph graph) {
        if (graph != null) {
            for (RelationShip relationShip : graph.getGraphRelationShipStorage()) {
                if (!graph.getGraphRelationShipStorage().contains(RelationShip.getReversedRelationShip(relationShip)))
                    return true;
            }
        }
        return false;
    }

    @Override
    public int calculateDeadline(AbstractGraph graph) {
        return 0;
    }

    @Override
    public List<AbstractNode> calculatePathBetweenTwoNodes(AbstractGraph graph, AbstractNode beginNode, AbstractNode endNode) {
        return null;
    }

    @Override
    public boolean CheckCohesion(AbstractGraph graph) {
        return false;
    }

    @Override
    public Date getDeadline(AbstractGraph graph) {
        return null;
    }

    @Override
    public AbstractGraph topologicSort(AbstractGraph graph) {
        return null;
    }

    @Override
    public void doDFS(AbstractGraph graph, AbstractNode startNode) {
//        AbstractNode startNode = ((MileStone) graph).
    }

    @Override
    public void doBFS(AbstractGraph graph) {

    }
}
