package hajecs.model.DTO;

import hajecs.model.Graph.TaskNode;

import java.util.Set;

/**
 * Created by lucjan on 23.05.15.
 */
public class MileStoneDTOInfo {
    private long id;
    private String name;
    private String describe;
    private Set<TaskNode> taskNodeSet;
}
