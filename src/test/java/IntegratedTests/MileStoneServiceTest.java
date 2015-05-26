package IntegratedTests;

import hajecs.Neo4jTestApplication;
import hajecs.factories.GraphAndTaskFactory;
import hajecs.factories.GraphStructureType;
import hajecs.model.Actors.Person;
import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.MileStoneDTO;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.TaskNode;
import hajecs.model.Task.AbstractTask;
import hajecs.model.Task.DailyTask;
import hajecs.model.Task.SeveralDaysTask;
import hajecs.repositories.*;
import hajecs.resources.PersonResource;
import hajecs.services.MileStoneServiceImpl;
import hajecs.services.NodeService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by lucjan on 22.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jTestApplication.class)
@Transactional
public class MileStoneServiceTest {

    private Logger logger = Logger.getLogger(String.valueOf(this));

    @Autowired
    private MileStoneServiceImpl mileStoneService;

    @Autowired
    private DBGraphRepository dbGraphRepository;

    @Autowired
    private DBNodeRepository dbNodeRepository;

    @Autowired
    private DBRelationShipRepository dbRelationShipRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private NodeService nodeService;

    private MileStone mileStone;
    private Person manager;

    @Before
    public void initMileStone() {
        mileStone = (MileStone) GraphAndTaskFactory.getInstance().createGraphStructure(GraphStructureType.MILESTONE);
        mileStone.setName("Projekt IT");
        mileStone.setDescribe("zaprojektowanie prostej platformy");

        Assert.assertNotNull(mileStone);


        mileStone.addTasks(
                new DailyTask("Przygotowanie dokumentacji", "", "10/05/2015"),
                new DailyTask("Przygotowanie bazy danych", "", "11/05/2015"),
                new SeveralDaysTask("bazy danych: funkcje", "", "12/05/2015", "14/05/2015"),
                new SeveralDaysTask("bazy danych: procedury", "", "12/05/2015", "14/05/2015"),
                new SeveralDaysTask("bazy danych: triggery", "", "12/05/2015", "15/05/2015"),
                new SeveralDaysTask("Implementacja logiki aplikacji", "", "11/05/2015", "13/05/2015"),
                new SeveralDaysTask("Implementacja WEB", "", "11/05/2015", "13/05/2015"),
                new SeveralDaysTask("Integracja WEB SERVER", "", "13/05/2015", "16/05/2015"),
                new DailyTask("Testowanie bazy danych", "", "16/05/2015"),
                new SeveralDaysTask("Skladanie projektu w calosc", "", "16/05/2015", "20/05/2015")
        );

    }

    @Test
    @Rollback(true)
    public void test() {
/*
        dbGraphRepository.deleteAll();
        dbNodeRepository.deleteAll();

        Assert.assertNotNull(dbGraphRepository);
        Assert.assertNotNull(dbNodeRepository);
        Assert.assertNotNull(dbRelationShipRepository);
        Assert.assertNotNull(taskRepository);
        Assert.assertNotNull(personRepository);
        Assert.assertNotNull(mileStoneService);

        long id = mileStoneService.createMileStone(mileStone);
        Assert.assertEquals(1, dbGraphRepository.count());


        Assert.assertEquals(10, dbGraphRepository.findOne(id).getNumberOfNodes());
        Assert.assertEquals(10, dbNodeRepository.count());
        Assert.assertEquals(10, taskRepository.count());


        long projektIT_id = dbGraphRepository.findByName("Projekt IT").getId();

        saveAllTasksToSet(); // pobiera taski do pamieci zamiast korzystac z bazy

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                getTaskByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                getTaskByName("Implementacja WEB").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                getTaskByName("Implementacja logiki aplikacji").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("Przygotowanie bazy danych").getTaskNode().getId(),
                getTaskByName("bazy danych: funkcje").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("Przygotowanie bazy danych").getTaskNode().getId(),
                getTaskByName("bazy danych: procedury").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("Przygotowanie bazy danych").getTaskNode().getId(),
                getTaskByName("bazy danych: triggery").getTaskNode().getId()
        );


        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("bazy danych: funkcje").getTaskNode().getId(),
                getTaskByName("Testowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("bazy danych: procedury").getTaskNode().getId(),
                getTaskByName("Testowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("bazy danych: triggery").getTaskNode().getId(),
                getTaskByName("Testowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("Testowanie bazy danych").getTaskNode().getId(),
                getTaskByName("Skladanie projektu w calosc").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("Implementacja WEB").getTaskNode().getId(),
                getTaskByName("Integracja WEB SERVER").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("Implementacja logiki aplikacji").getTaskNode().getId(),
                getTaskByName("Integracja WEB SERVER").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(projektIT_id,
                getTaskByName("Integracja WEB SERVER").getTaskNode().getId(),
                getTaskByName("Skladanie projektu w calosc").getTaskNode().getId()
        );


        personRepository.save(PersonResource.getManagerJanKowalski());
        mileStoneService.setManager(
                projektIT_id,
                personRepository.findByUsernameAndPassword(PersonResource.getManagerJanKowalski().getUsername(),
                        PersonResource.getManagerJanKowalski().getPassword()).getId()
        );

        org.junit.Assert.assertEquals(PersonResource.getManagerJanKowalski().getUsername(), mileStoneService.getManager(mileStoneService.findMileStoneByName("Projekt IT").getId()).getUsername());


        Assert.assertEquals(10, dbGraphRepository.findByName("Projekt IT").getNumberOfNodes());
        Assert.assertEquals(10, dbNodeRepository.count());

        org.junit.Assert.assertEquals(13, dbGraphRepository.findByName("Projekt IT").getNodesRelationShip().size());
        org.junit.Assert.assertEquals(13, dbRelationShipRepository.count());


        mileStoneService.setStartTaskNode(projektIT_id,
                getTaskByName("Przygotowanie dokumentacji").getTaskNode().getId()
        );

        mileStoneService.setEndTaskNode(projektIT_id,
                getTaskByName("Skladanie projektu w calosc").getTaskNode().getId()
        );

        MileStone mileStoneDb = (MileStone) dbGraphRepository.findByName("Projekt IT");


        org.junit.Assert.assertNotNull(mileStoneDb.getStartTaskNode());
        org.junit.Assert.assertNotNull(mileStoneDb.getEndTaskNode());

        Assert.assertEquals(taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getName(),
                mileStoneDb.getStartTaskNode().getName());

        Assert.assertEquals(taskRepository.findByName("Skladanie projektu w calosc").getTaskNode().getName(),
                mileStoneDb.getEndTaskNode().getName());


        Assert.assertEquals(3, mileStoneDb.findNodeByTask("Przygotowanie dokumentacji").getNumberOfNeighbours());
        Assert.assertEquals(2, mileStoneDb.findNodeByTask("Implementacja WEB").getNumberOfNeighbours());
        Assert.assertEquals(2, mileStoneDb.findNodeByTask("Implementacja logiki aplikacji").getNumberOfNeighbours());
        Assert.assertEquals(3, mileStoneDb.findNodeByTask("Integracja WEB SERVER").getNumberOfNeighbours());
        Assert.assertEquals(2, mileStoneDb.findNodeByTask("Skladanie projektu w calosc").getNumberOfNeighbours());
        Assert.assertEquals(4, mileStoneDb.findNodeByTask("Przygotowanie bazy danych").getNumberOfNeighbours());
        Assert.assertEquals(2, mileStoneDb.findNodeByTask("bazy danych: funkcje").getNumberOfNeighbours());
        Assert.assertEquals(2, mileStoneDb.findNodeByTask("bazy danych: procedury").getNumberOfNeighbours());
        Assert.assertEquals(2, mileStoneDb.findNodeByTask("bazy danych: triggery").getNumberOfNeighbours());
        Assert.assertEquals(4, mileStoneDb.findNodeByTask("Testowanie bazy danych").getNumberOfNeighbours());
    }


        populatePeople();

        //  11 person + 1 manager
        Assert.assertEquals(12, personRepository.count());



        mileStoneService.addPersonToTask(
                projektIT_id,
                getTaskByName("Przygotowanie dokumentacji").getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getJavaDeveloperWojciechSeliga().getUsername(),
                        PersonResource.getJavaDeveloperWojciechSeliga().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getJavaDeveloperAdamWojcik().getUsername(),
                        PersonResource.getJavaDeveloperAdamWojcik().getPassword()).getId());

        Assert.assertEquals(2, ((MileStone) dbGraphRepository.findByName("Projekt IT")).findNodeByTask("Przygotowanie dokumentacji").getTask().getNumberOfWorkers());



        mileStoneService.addPersonToTask(
                projektIT_id,
                getTaskByName("Implementacja WEB").getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getWebDeveloperDominikNocon().getUsername(),
                        PersonResource.getWebDeveloperDominikNocon().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getWebDeveloperPrzemekRoman().getUsername(),
                        PersonResource.getWebDeveloperPrzemekRoman().getPassword()).getId());

        Assert.assertEquals(2, ((MileStone) dbGraphRepository.findByName("Projekt IT")).findNodeByTask("Implementacja WEB").getTask().getNumberOfWorkers());


        mileStoneService.addPersonToTask(
                projektIT_id,
                getTaskByName("Implementacja logiki aplikacji").getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getJavaDeveloperAdamWojcik().getUsername(),
                        PersonResource.getJavaDeveloperAdamWojcik().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getJavaDeveloperKamilMilosz().getUsername(),
                        PersonResource.getJavaDeveloperKamilMilosz().getPassword()).getId());

        Assert.assertEquals(2, ((MileStone) dbGraphRepository.findByName("Projekt IT")).findNodeByTask("Implementacja logiki aplikacji").getTask().getNumberOfWorkers());


        mileStoneService.addPersonToTask(
                projektIT_id,
                getTaskByName("Integracja WEB SERVER").getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getJavaDeveloperPiotrNawalka().getUsername(),
                        PersonResource.getJavaDeveloperPiotrNawalka().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getJavaDeveloperWojciechSeliga().getUsername(),
                        PersonResource.getJavaDeveloperWojciechSeliga().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getWebDeveloperMateuszStepala().getUsername(),
                        PersonResource.getWebDeveloperMateuszStepala().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getWebDeveloperDominikNocon().getUsername(),
                        PersonResource.getWebDeveloperDominikNocon().getPassword()).getId());

        Assert.assertEquals(4, ((MileStone) dbGraphRepository.findByName("Projekt IT")).findNodeByTask("Integracja WEB SERVER").getTask().getNumberOfWorkers());




        mileStoneService.addPersonToTask(
                projektIT_id,
                getTaskByName("Skladanie projektu w calosc").getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getJavaDeveloperWojciechSeliga().getUsername(),
                        PersonResource.getJavaDeveloperWojciechSeliga().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getWebDeveloperDominikNocon().getUsername(),
                        PersonResource.getWebDeveloperDominikNocon().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getDatabaseDeveloperAdrianKrawiec().getUsername(),
                        PersonResource.getDatabaseDeveloperAdrianKrawiec().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getDatabaseDeveloperLukaszDebinski().getUsername(),
                        PersonResource.getDatabaseDeveloperLukaszDebinski().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getUXDesignerMonikaStokrotka().getUsername(),
                        PersonResource.getUXDesignerMonikaStokrotka().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getWebDeveloperMateuszStepala().getUsername(),
                        PersonResource.getWebDeveloperMateuszStepala().getPassword()).getId()
        );

        Assert.assertEquals(6, ((MileStone) dbGraphRepository.findByName("Projekt IT")).findNodeByTask("Skladanie projektu w calosc").getTask().getNumberOfWorkers());


        mileStoneService.addPersonToTask(
                projektIT_id,
                getTaskByName("Przygotowanie bazy danych").getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getDatabaseDeveloperAdrianKrawiec().getUsername(),
                        PersonResource.getDatabaseDeveloperAdrianKrawiec().getPassword()).getId());

        Assert.assertEquals(1, ((MileStone) dbGraphRepository.findByName("Projekt IT")).findNodeByTask("Przygotowanie bazy danych").getTask().getNumberOfWorkers());


        mileStoneService.addPersonToTask(
                projektIT_id,
                getTaskByName("bazy danych: funkcje").getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getDatabaseDeveloperAdrianKrawiec().getUsername(),
                        PersonResource.getDatabaseDeveloperAdrianKrawiec().getPassword()).getId());

        Assert.assertEquals(1, ((MileStone) dbGraphRepository.findByName("Projekt IT")).findNodeByTask("bazy danych: funkcje").getTask().getNumberOfWorkers());



        mileStoneService.addPersonToTask(
                projektIT_id,
                getTaskByName("bazy danych: procedury").getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getDatabaseDeveloperLukaszDebinski().getUsername(),
                        PersonResource.getDatabaseDeveloperLukaszDebinski().getPassword()).getId());

        Assert.assertEquals(1, ((MileStone) dbGraphRepository.findByName("Projekt IT")).findNodeByTask("bazy danych: procedury").getTask().getNumberOfWorkers());


        mileStoneService.addPersonToTask(
                projektIT_id,
                getTaskByName("bazy danych: triggery").getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getDatabaseDeveloperAdrianCiecholewski().getUsername(),
                        PersonResource.getDatabaseDeveloperAdrianCiecholewski().getPassword()).getId());

        Assert.assertEquals(1, ((MileStone) dbGraphRepository.findByName("Projekt IT")).findNodeByTask("bazy danych: triggery").getTask().getNumberOfWorkers());


        mileStoneService.addPersonToTask(
                projektIT_id,
                getTaskByName("Testowanie bazy danych").getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getDatabaseDeveloperAdrianKrawiec().getUsername(),
                        PersonResource.getDatabaseDeveloperAdrianKrawiec().getPassword()).getId(),
                personRepository.findByUsernameAndPassword(
                        PersonResource.getDatabaseDeveloperLukaszDebinski().getUsername(),
                        PersonResource.getDatabaseDeveloperLukaszDebinski().getPassword()).getId());

        Assert.assertEquals(2, ((MileStone) dbGraphRepository.findByName("Projekt IT")).findNodeByTask("Testowanie bazy danych").getTask().getNumberOfWorkers());

        mileStoneDb = (MileStone) dbGraphRepository.findByName("Projekt IT");

        org.junit.Assert.assertEquals(2, mileStoneDb.findNodeByTask("Przygotowanie dokumentacji").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(2, mileStoneDb.findNodeByTask("Implementacja logiki aplikacji").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(2, mileStoneDb.findNodeByTask("Implementacja WEB").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(4, mileStoneDb.findNodeByTask("Integracja WEB SERVER").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(1, mileStoneDb.findNodeByTask("Przygotowanie bazy danych").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(1, mileStoneDb.findNodeByTask("bazy danych: funkcje").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(1, mileStoneDb.findNodeByTask("bazy danych: procedury").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(1, mileStoneDb.findNodeByTask("bazy danych: triggery").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(2, mileStoneDb.findNodeByTask("Testowanie bazy danych").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(6, mileStoneDb.findNodeByTask("Skladanie projektu w calosc").getTask().getNumberOfWorkers());


//        mileStoneService.deleteRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
//                taskRepository.findByName("Implementacja WEB").getTaskNode().getId(),
//                taskRepository.findByName("Integracja WEB SERVER").getTaskNode().getId()
//        );
//
//        mileStoneService.deleteRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
//                taskRepository.findByName("Implementacja logiki aplikacji").getTaskNode().getId(),
//                taskRepository.findByName("Integracja WEB SERVER").getTaskNode().getId()
//        );
//
//        mileStoneService.deleteRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
//                taskRepository.findByName("Integracja WEB SERVER").getTaskNode().getId(),
//                taskRepository.findByName("Skladanie projektu w calosc").getTaskNode().getId()
//        );
//
//        //  ponowne usuniecie nie powoduje bledu
//        mileStoneService.deleteRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
//                taskRepository.findByName("Testowanie bazy danych").getTaskNode().getId(),
//                taskRepository.findByName("Skladanie projektu w calosc").getTaskNode().getId()
//        );
//
//
//        org.junit.Assert.assertEquals(9, dbRelationShipRepository.count());
//        org.junit.Assert.assertEquals(10, dbNodeRepository.count());
//

        try {
            //  usuniecie ostatniego wierzcholka
            mileStoneService.deleteNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                    taskRepository.findByName("Skladanie projektu w calosc").getTaskNode().getId());
        } catch (IllegalArgumentException e) {
            logger.info(e.getMessage());
        }
//
//        org.junit.Assert.assertEquals(9, dbNodeRepository.count());
//        Assert.assertEquals(9, dbGraphRepository.findByName("Projekt IT").getNumberOfNodes());


    }

    @Test
    public void saveMileStone() {
        MileStoneDTO mileStoneDTO = new MileStoneDTO("test1", "test2");
        mileStoneService.saveOrUpdateMileStone(DTOConverter.toMileStone(mileStoneDTO));
        Assert.assertEquals(mileStoneDTO.getName(), dbGraphRepository.findByName(mileStoneDTO.getName()).getName());

    }


    private void populatePeople() {
        personRepository.save(PersonResource.getJavaDeveloperWojciechSeliga());
        personRepository.save(PersonResource.getWebDeveloperDominikNocon());
        personRepository.save(PersonResource.getDatabaseDeveloperAdrianKrawiec());
        personRepository.save(PersonResource.getDatabaseDeveloperLukaszDebinski());
        personRepository.save(PersonResource.getUXDesignerMonikaStokrotka());
        personRepository.save(PersonResource.getWebDeveloperMateuszStepala());
        personRepository.save(PersonResource.getDatabaseDeveloperAdrianCiecholewski());
        personRepository.save(PersonResource.getJavaDeveloperPiotrNawalka());
        personRepository.save(PersonResource.getJavaDeveloperKamilMilosz());
        personRepository.save(PersonResource.getWebDeveloperPrzemekRoman());
        personRepository.save(PersonResource.getJavaDeveloperAdamWojcik());
    }

    private static Set<AbstractTask> allTasks = new HashSet<>();

    private void saveAllTasksToSet() {
        for (AbstractTask task : taskRepository.findAll()) {
            allTasks.add(task);
        }
    }

    private AbstractTask getTaskByName(String name) {
        for (AbstractTask task : allTasks) {
            if (task.getName().equals(name))
                return task;
        }
        return null;
    }


    public void fastTest() {

        dbNodeRepository.deleteAll();
        dbGraphRepository.deleteAll();
        dbRelationShipRepository.deleteAll();

        MileStone newMileStone = new MileStone("fast test", "fast test");
        long milestoneId = mileStoneService.saveOrUpdateMileStone(newMileStone);

        long aId = dbNodeRepository.save(new TaskNode("A")).getId();
        long bId = dbNodeRepository.save(new TaskNode("B")).getId();

        mileStoneService.addRelationShipBetweenTaskNodes(milestoneId, aId, bId);

        Assert.assertEquals(1, dbRelationShipRepository.count());
    }
    */
    }
}
