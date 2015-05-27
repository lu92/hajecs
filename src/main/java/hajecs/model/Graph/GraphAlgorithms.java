package hajecs.model.Graph;

import java.util.Date;
import java.util.List;

/**
 * Created by lucjan on 07.05.15.
 */
public interface GraphAlgorithms {
    boolean checkAcyclicality(AbstractGraph graph);
    AbstractGraph getUndirectedGraph(AbstractGraph graph);
    boolean checkDirected(AbstractGraph graph);
    int calculateDeadline(AbstractGraph graph);
    List<AbstractNode> calculatePathBetweenTwoNodes(AbstractGraph graph, AbstractNode beginNode, AbstractNode endNode);
    boolean CheckCohesion(AbstractGraph graph);
    Date getDeadline(AbstractGraph graph);
    AbstractGraph topologicSort(AbstractGraph graph);
    public void doDFS(AbstractGraph graph, AbstractNode startNode);
    void doBFS(AbstractGraph graph);
}
