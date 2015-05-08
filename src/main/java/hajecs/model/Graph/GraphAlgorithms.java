package hajecs.model.Graph;

import java.util.Date;

/**
 * Created by lucjan on 07.05.15.
 */
public interface GraphAlgorithms {
    boolean checkAcyclicality(AbstractGraph graph);
    int calculateDeadline(AbstractGraph graph);
    Date getDeadline(AbstractGraph graph);


}