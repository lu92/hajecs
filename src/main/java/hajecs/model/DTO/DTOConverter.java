package hajecs.model.DTO;

import hajecs.model.Graph.AbstractNode;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.TaskNode;
import hajecs.model.Task.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 22.05.15.
 */
public class DTOConverter {

    public static AbstractNode toNode(NodeDTO nodeDTO) {
        AbstractNode node = new TaskNode(nodeDTO.getName());
        return node;
    }

    public static SingleTask toSingleTask(SingleTaskDTO singleTaskDTO) {
        return new SingleTask(singleTaskDTO.getDuration(),
                singleTaskDTO.getTask());
    }

    public static MileStone toMileStone(MileStoneDTO mileStoneDTO) {
        return new MileStone(mileStoneDTO.getName(), mileStoneDTO.getDescribe());
    }

    public static AbstractTask toTask(TaskDTO taskDTO) {
        
        AbstractTask task = null;

        switch(taskDTO.getTaskType()) {
            case DAILY_TASK:
                task = new DailyTask(
                        taskDTO.getName(), taskDTO.getDescribe(),
                        taskDTO.getStart());
                break;

            case  HOURLY_TASK:
                task = new HourlyTask(
                        taskDTO.getName(), taskDTO.getDescribe(),
                        taskDTO.getStart());
                Set<SingleTask> singleTaskSet = new HashSet<>();
                for (SingleTaskDTO singleTaskDTO : taskDTO.getSingleTasks()) {
                    singleTaskSet.add(toSingleTask(singleTaskDTO));
                }
                task.setSingleTaskStorage(singleTaskSet);
                break;

            case SEVERALDAYS_TASK:
                task = new SeveralDaysTask(
                        taskDTO.getName(), taskDTO.getDescribe(),
                        taskDTO.getStart(), taskDTO.getDeadline());
                Set<SingleTask> singleTaskSet2 = new HashSet<>();
                for (SingleTaskDTO singleTaskDTO : taskDTO.getSingleTasks()) {
                    singleTaskSet2.add(toSingleTask(singleTaskDTO));
                }
                task.setSingleTaskStorage(singleTaskSet2);
                break;
        }
        return task;
    }
}
