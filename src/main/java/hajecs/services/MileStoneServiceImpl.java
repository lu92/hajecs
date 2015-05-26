package hajecs.services;

import hajecs.model.DTO.*;
import hajecs.model.Graph.AbstractGraph;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.RelationShip;
import hajecs.model.Graph.TaskNode;
import hajecs.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scala.collection.parallel.ParIterableLike;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by lucjan on 21.05.15.
 */

@Service
@Transactional
public class MileStoneServiceImpl implements MileStoneService {
    private Logger logger = Logger.getLogger(String.valueOf(this));

    @Autowired
    private DBGraphRepository dbGraphRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DBTaskNodeRepository dbTaskNodeRepository;

    @Autowired
    private DBNodeRepository dbNodeRepository;

    @Autowired
    private PersonRepository personRepository;


    @Override
    public long createMileStone(MileStone mileStone) {
        return dbGraphRepository.save(mileStone).getId();
    }

    @Override
    public void deleteMileStone(long milestoneId) {
        dbGraphRepository.delete(milestoneId);
    }

    @Override
    public MileStone getMileStone(long milestoneId) {
        return (MileStone) dbGraphRepository.findOne(milestoneId);
    }

    @Override
    public MileStone findMileStoneByName(String milestoneName) {
        return (MileStone) dbGraphRepository.findByName(milestoneName);
    }

    @Override
    public Set<MileStone> getAllMileStones() {
        Set<MileStone> mileStoneSet = new HashSet<>();
        for (AbstractGraph mileStone : dbGraphRepository.findAll())
            mileStoneSet.add((MileStone) mileStone);
        return mileStoneSet;
    }

    @Override
    public Set<MileStoneDTOInfo> getAllMileStoneDtoInfos() {
        Set<MileStoneDTOInfo> mileStoneDTOInfos = new HashSet<>();
        for (MileStone mileStone : getAllMileStones())
            mileStoneDTOInfos.add(DTOConverter.toMileStoneDTOInfo(mileStone));
        return mileStoneDTOInfos;
    }

    @Override
    public boolean addTaskNodeToMileStone(long milestoneId, long nodeId) throws IllegalArgumentException{

        MileStone mileStone = null;
        TaskNode taskNode = null;

        try {
            mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        } catch (Exception e) {
            throw new IllegalArgumentException("MileStone with id = " + milestoneId + " doesn't exists");
        }

        try {
            taskNode = dbTaskNodeRepository.findOne(nodeId);
        } catch (Exception e) {
            throw new IllegalArgumentException("TaskNode with id = " + nodeId + " doesn't exists");
        }

        mileStone.addNodes(taskNode);
        dbGraphRepository.save(mileStone);
        return true;
    }

    @Override
    public void removeTaskNodeFromMileStone(long milestoneId, long nodeId) throws IllegalArgumentException {

    }

    @Override
    public Set<TaskNodeDTOInfo> getAllTaskNode(long milestoneId) {
        return null;
    }

    @Override
    public void addRelationShipBetweenTaskNodes(long mileStoneId, long beginTaskNodeId, long endTaskNodeId) throws IllegalArgumentException {
        MileStone mileStone = null;
        TaskNode beginTaskNode = null;
        TaskNode endTaskNode = null;

        try {
            mileStone = (MileStone) dbGraphRepository.findOne(mileStoneId);
        } catch (Exception e) {
            throw new IllegalArgumentException("mileStone with id " + mileStone + " doesn't exists");
        }

        try {
            beginTaskNode = dbTaskNodeRepository.findOne(beginTaskNodeId);
        } catch (Exception e) {
            throw new IllegalArgumentException("tasknode with id " + beginTaskNodeId + " doesn't exists");
        }
        
        try {
            endTaskNode = dbTaskNodeRepository.findOne(endTaskNodeId);
        } catch (Exception e) {
            throw new IllegalArgumentException("tasknode with id " + endTaskNodeId + " doesn't exists");
        }

        mileStone.addRelationShips(beginTaskNode, endTaskNode);
        dbGraphRepository.save(mileStone);
    }

    @Override
    public Set<RelationShip> getAllRelationShips(long milestoneId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        return mileStone.getNodesRelationShip();
    }

    @Override
    public Set<RelationShipDTOInfo> getAllRelationShipDtoInfos(long milestoneId) {
        Set<RelationShipDTOInfo> relationShipDTOInfos = new HashSet<>();
        for (RelationShip relationShip : getAllRelationShips(milestoneId))
            relationShipDTOInfos.add(DTOConverter.toRelationShipDTOInfo(relationShip));
        return relationShipDTOInfos;
    }

    @Override
    public void setStartTaskNode(long milestoneId, long nodeId) throws IllegalArgumentException {

    }

    @Override
    public void setEndTaskNode(long milestoneId, long nodeId) throws IllegalArgumentException {

    }

    @Override
    public Set<TaskDTOInfo> getExecutedTasks(long milestoneId) {
        return null;
    }

    @Override
    public Set<TaskDTOInfo> getNotExecutedTasks(long milestoneId) {
        return null;
    }

    @Override
    public Set<PersonDTOInfo> getAllWorkers(long milestoneId) {
        return null;
    }

    @Override
    public boolean setManager(long milestoneId, long managerId) throws IllegalArgumentException {
        return false;
    }

    @Override
    public PersonDTOInfo getManager(long milestoneId) {
        return null;
    }


}
