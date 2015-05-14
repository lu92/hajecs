package hajecs.model.Graph;

import java.util.Date;

/**
 * Created by lucjan on 13.05.15.
 */
public class GraphAlgorithmsImpl implements GraphAlgorithms {
    @Override
    public boolean checkAcyclicality(AbstractGraph graph) {
        return false;
    }

    @Override
    public boolean checkDirected(AbstractGraph graph) {
        return false;
    }

    @Override
    public int calculateDeadline(AbstractGraph graph) {
        return 0;
    }

    @Override
    public Date getDeadline(AbstractGraph graph) {
        return null;
    }

    @Override
    public void doDFS() {

    }

    @Override
    public void doBFS() {

    }
}
