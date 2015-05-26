package JUnitTest;

import hajecs.factories.TaskType;
import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.SingleTaskDTO;
import hajecs.model.DTO.SingleTaskDTOInfo;
import hajecs.model.DTO.TaskDTOInfo;
import hajecs.model.Task.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 23.05.15.
 */
public class TaskDTOTest {


    private long nodeId = 10;
    private long milestoneId = 10;
    private String name = "task's name";
    private String describe = "task's describe";
    private String start = "20/05/2015";
    private String deadline = "22/05/2015";
    private Set<Long> workersId = new HashSet<>();
    private Set<Long> rolesId = new HashSet<>();
    private Set<SingleTaskDTO> singleTaskDTOs = new HashSet<>();

    @Before
    public void initVariables() {
        workersId.add(3L);
        workersId.add(7L);
        workersId.add(34L);

        rolesId.add(1L);
        rolesId.add(2L);

        singleTaskDTOs.add(new SingleTaskDTO("13:00-13:30", "zrobic kawe"));
        singleTaskDTOs.add(new SingleTaskDTO("13:30-15:00", "odebrac dziecko ze szkoly"));
        singleTaskDTOs.add(new SingleTaskDTO("15:00-17:00", "zrobic obiad"));
    }

    @Test
    public void DailyTasktest() {
/*
        TaskDTOInfo DailyTaskDTO = new TaskDTOInfo(TaskType.DAILY_TASK, nodeId,
                milestoneId, name, describe, start, deadline,
                workersId, rolesId, singleTaskDTOs );

        Assert.assertNotNull(DailyTaskDTO);


        DailyTask dailyTask = (DailyTask) DTOConverter.toTask(DailyTaskDTO);

        Assert.assertNotNull(dailyTask);


        Assert.assertEquals(name, dailyTask.getName());
        Assert.assertEquals(describe, dailyTask.getDescribe());
        Assert.assertEquals(new Date(start), dailyTask.getStart());
        Assert.assertEquals(new Date(start), dailyTask.getDeadline());

        Assert.assertEquals(1, dailyTask.getSingleTaskStorage().size());
        Assert.assertEquals(name, dailyTask.getSingleTaskStorage().iterator().next().getTask());
*/
    }

    @Test
    public void HourlyTasktest() {
        /*
        TaskDTOInfo HourlyTaskDTO = new TaskDTOInfo(TaskType.HOURLY_TASK, nodeId,
                milestoneId, name, describe, start, deadline,
                workersId, rolesId, singleTaskDTOs );

        Assert.assertNotNull(HourlyTaskDTO);


        HourlyTask hourlyTask = (HourlyTask) DTOConverter.toTask(HourlyTaskDTO);

        Assert.assertNotNull(hourlyTask);


        Assert.assertEquals(name, hourlyTask.getName());
        Assert.assertEquals(describe, hourlyTask.getDescribe());
        Assert.assertEquals(new Date(start), hourlyTask.getStart());
        Assert.assertEquals(new Date(start), hourlyTask.getDeadline());

        Assert.assertEquals(3, hourlyTask.getSingleTaskStorage().size());

        int value = 0;
        for (SingleTask singleTask : hourlyTask.getSingleTaskStorage()) {
            for (SingleTaskDTOInfo singleTaskDTO : singleTaskDTOs) {
                if (singleTask.getTask().equals(singleTaskDTO.getTask()))
                    value++;
            }
        }
        Assert.assertEquals(3, value);
        */
    }

    @Test
    public void SeveralDaysTasktest() {
        /*
        TaskDTOInfo SeveralDaysTaskDTO = new TaskDTOInfo(TaskType.SEVERALDAYS_TASK, nodeId,
                milestoneId, name, describe, start, deadline,
                workersId, rolesId, singleTaskDTOs );

        Assert.assertNotNull(SeveralDaysTaskDTO);


        SeveralDaysTask severalDaysTask = (SeveralDaysTask) DTOConverter.toTask(SeveralDaysTaskDTO);

        Assert.assertNotNull(severalDaysTask);


        Assert.assertEquals(name, severalDaysTask.getName());
        Assert.assertEquals(describe, severalDaysTask.getDescribe());
        Assert.assertEquals(new Date(start), severalDaysTask.getStart());
        Assert.assertEquals(new Date(deadline), severalDaysTask.getDeadline());

        Assert.assertEquals(3, severalDaysTask.getSingleTaskStorage().size());

        int value = 0;
        for (SingleTask singleTask : severalDaysTask.getSingleTaskStorage()) {
            for (SingleTaskDTOInfo singleTaskDTO : singleTaskDTOs) {
                if (singleTask.getTask().equals(singleTaskDTO.getTask()))
                    value++;
            }
        }
        Assert.assertEquals(3, value);
        */
    }
}
