package hajecs.model.DTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 24.05.15.
 */
public class AllTaskNodeDTOInfo {
    Set<TaskNodeDTOInfo> allTaskNodeDTOInfos = new HashSet<>();

    public AllTaskNodeDTOInfo() {
    }

    public AllTaskNodeDTOInfo(Set<TaskNodeDTOInfo> taskNodeDTOInfos) {
        this.allTaskNodeDTOInfos = taskNodeDTOInfos;
    }

    public void addTaskNodeDTOInfo(TaskNodeDTOInfo ... taskNodeDTOInfos) {
        for (TaskNodeDTOInfo taskNodeDTOInfo : taskNodeDTOInfos)
            allTaskNodeDTOInfos.add(taskNodeDTOInfo);
    }

    public Set<TaskNodeDTOInfo> getAllTaskNodeDTOInfos() {
        return allTaskNodeDTOInfos;
    }

    public void setAllTaskNodeDTOInfos(Set<TaskNodeDTOInfo> allTaskNodeDTOInfos) {
        this.allTaskNodeDTOInfos = allTaskNodeDTOInfos;
    }
}
