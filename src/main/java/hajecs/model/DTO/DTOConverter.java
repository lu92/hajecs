package hajecs.model.DTO;

import hajecs.builders.PersonBuilder;
import hajecs.factories.TaskType;
import hajecs.model.Actors.*;
import hajecs.model.Graph.AbstractNode;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.RelationShip;
import hajecs.model.Graph.TaskNode;
import hajecs.model.Task.*;
import hajecs.model.personalData.Privilege;
import hajecs.model.personalData.Role;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 22.05.15.
 */
public class DTOConverter {

    private static Format formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static AbstractNode toNode(NodeDTO nodeDTO) {
        AbstractNode node = new hajecs.model.Graph.TaskNode(nodeDTO.getName());
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

    public static SingleTaskDTOInfo toSingleTaskDTOInfo(SingleTask singleTask) {
        SingleTaskDTOInfo singleTaskDTOInfo = new SingleTaskDTOInfo();
        singleTaskDTOInfo.setId(singleTask.getId());
        singleTaskDTOInfo.setDuration(singleTask.getDuration());
        singleTaskDTOInfo.setTask(singleTask.getTask());
        singleTaskDTOInfo.setExecuted(singleTask.isExecuted());
        return singleTaskDTOInfo;
    }

    public static NodeDTOInfo toNodeDTOInfo(AbstractNode abstractNode) {
        NodeDTOInfo nodeDTOInfo = new NodeDTOInfo();
        nodeDTOInfo.setId(abstractNode.getId());
        nodeDTOInfo.setName(abstractNode.getName());
        for (AbstractNode abstractNeighbourNode : abstractNode.getNeighbourNodeStorage()) {
            nodeDTOInfo.getNeighbours().add(abstractNeighbourNode.getId());
        }
        return nodeDTOInfo;
    }

    public static RelationShipDTOInfo toRelationShipDTOInfo(RelationShip relationShip) {
        RelationShipDTOInfo relationShipDTOInfo = new RelationShipDTOInfo(
                relationShip.getId(),
                relationShip.getBeginNode().getId(),
                relationShip.getEndNode().getId());
        return relationShipDTOInfo;
    }

    public static MileStoneDTOInfo toMileStoneDTOInfo(MileStone mileStone) {
        MileStoneDTOInfo mileStoneDTOInfo = new MileStoneDTOInfo();
        if (mileStone != null) {
            mileStoneDTOInfo.setId(mileStone.getId());
            mileStoneDTOInfo.setName(mileStone.getName());
            mileStoneDTOInfo.setDescribe(mileStone.getDescribe());
            for (AbstractNode abstractNode : mileStone.getNodeStorage()) {
                mileStoneDTOInfo.addNodeDTOInfo(toNodeDTOInfo(abstractNode));
            }

            for (RelationShip relationShip : mileStone.getNodesRelationShip()) {
                mileStoneDTOInfo.addRelationShipDTOInfo(toRelationShipDTOInfo(relationShip));
            }
        }
        return mileStoneDTOInfo;
    }

    public static TaskNode toTaskNode(TaskNodeDTO taskNodeDTO) {
        TaskNode taskNode = new TaskNode(taskNodeDTO.getName());
        return taskNode;
    }

    public static TaskNodeDTOInfo toTaskNodeDTOInfo(TaskNode taskNode) {
        TaskNodeDTOInfo taskNodeDTOInfo = new TaskNodeDTOInfo();
        if (taskNode != null){
            taskNodeDTOInfo.setId(taskNode.getId());
            taskNodeDTOInfo.setName(taskNode.getName());
            for (AbstractNode neighbourNode : taskNode.getNeighbourNodeStorage())
                taskNodeDTOInfo.getNeighbours().add(neighbourNode.getId());
            if (taskNode.getTask() != null)
                taskNodeDTOInfo.setTaskId(taskNode.getTask().getId());
        }
        return taskNodeDTOInfo;
    }


    public static TaskDTOInfo toTaskDTOInfo(AbstractTask abstractTask) {
        TaskDTOInfo taskDTOInfo = new TaskDTOInfo();
        taskDTOInfo.setId(abstractTask.getId());
        taskDTOInfo.setName(abstractTask.getName());
        taskDTOInfo.setDescribe(abstractTask.getDescribe());
        taskDTOInfo.setStart(formatter.format(abstractTask.getStart()));
        taskDTOInfo.setDeadline(formatter.format(abstractTask.getDeadline()));
        for (Person person : abstractTask.getWorkerStorage())
            taskDTOInfo.addWorker(person.getId());

        if (abstractTask instanceof DailyTask) {
            taskDTOInfo.setTaskType(TaskType.DAILY_TASK);
        }

        if (abstractTask instanceof HourlyTask) {
            taskDTOInfo.setTaskType(TaskType.HOURLY_TASK);
        }

        if (abstractTask instanceof SeveralDaysTask) {
            taskDTOInfo.setTaskType(TaskType.SEVERALDAYS_TASK);
        }

        for (Person person : abstractTask.getWorkerStorage())
            taskDTOInfo.getWorkersId().add(person.getId());

        for (Role role : abstractTask.getRoleStorage())
            taskDTOInfo.getRolesId().add(role.getId());

        for (SingleTask singleTask : abstractTask.getSingleTaskStorage()) {
            taskDTOInfo.addSingleTasksId(DTOConverter.toSingleTaskDTOInfo(singleTask));
        }
        return taskDTOInfo;
    }


    public static Privilege toPrivilege(PrivilegeDTO privilegeDTO) {
        return new Privilege(privilegeDTO.getName());
    }

    public static PrivilegeDTOInfo toPrivilegeDTOInfo(Privilege privilege) {
        PrivilegeDTOInfo privilegeDTOInfo = new PrivilegeDTOInfo();
        privilegeDTOInfo.setId(privilege.getId());
        privilegeDTOInfo.setName(privilege.getPrivilegeName());
        return privilegeDTOInfo;
    }

    public static Role toRole(RoleDTO roleDTO) {
        return new Role(roleDTO.getName());
    }
    public static RoleDTOInfo toRoleDTOInfo(Role role) {
        RoleDTOInfo roleDTOInfo = new RoleDTOInfo();
        if (role != null) {
            roleDTOInfo.setId(role.getId());
            roleDTOInfo.setName(role.getRoleName());
            for (Privilege privilege : role.getPrivilegeStorage()) {
                roleDTOInfo.addPrivilegeDTOInfo(DTOConverter.toPrivilegeDTOInfo(privilege));
            }
        }
        return roleDTOInfo;
    }


    public static Person toPerson(PersonFormDTO personFormDTO) {
        PersonBuilder personBuilder = new PersonBuilder(
                personFormDTO.getUsername(), personFormDTO.getPassword(),
                personFormDTO.getEmail(), personFormDTO.getPersonType().toString());
        personBuilder.setPersonality(personFormDTO.getName(), personFormDTO.getLastName(),
                personFormDTO.getBirth(), personFormDTO.getTelephoneNumber());
        personBuilder.setAddress(personFormDTO.getCountry(),
                personFormDTO.getCity(), personFormDTO.getZipCode());
        return personBuilder.getBuildResult();
    }

    public static PersonDTOInfo toPersonDTOInfo(Person person) {
        PersonDTOInfo personDTOInfo = new PersonDTOInfo();
        if (person != null) {
            personDTOInfo.setId(person.getId());
            if (person instanceof Manager)
                personDTOInfo.setPersonType(PersonType.MANAGER);

            if (person instanceof Worker)
                personDTOInfo.setPersonType(PersonType.WORKER);

            if (person instanceof Student)
                personDTOInfo.setPersonType(PersonType.STUDENT);

            personDTOInfo.setUsername(person.getUsername());
            personDTOInfo.setPassword(person.getPassword());
            personDTOInfo.setEmail(person.getEmail());

            personDTOInfo.setName(person.getPersonality().getName());
            personDTOInfo.setLastName(person.getPersonality().getLastname());
            personDTOInfo.setBirth(formatter.format(person.getPersonality().getBirth()));
            personDTOInfo.setTelephoneNumber(person.getPersonality().getTelephoneNumber());
            personDTOInfo.setCountry(person.getAddress().getCountry());
            personDTOInfo.setCity(person.getAddress().getCity());
            personDTOInfo.setZipCode(person.getAddress().getZipCode());

            for (Role role : person.getRoleStorage())
                personDTOInfo.getRoles().add(role.getId());
        }
        return personDTOInfo;
    }
}
