package IntegratedTests;

import hajecs.Neo4jTestApplication;
import hajecs.factories.GraphAndTaskFactory;
import hajecs.factories.GraphStructureType;
import hajecs.model.Actors.Person;
import hajecs.model.Graph.AbstractNode;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.RelationShip;
import hajecs.model.Graph.TaskNode;
import hajecs.model.Task.AbstractTask;
import hajecs.model.Task.DailyTask;
import hajecs.model.Task.SeveralDaysTask;
import hajecs.repositories.DBGraphRepository;
import hajecs.repositories.DBNodeRepository;
import hajecs.repositories.DBRelationShipRepository;
import hajecs.repositories.TaskRepository;
import hajecs.resources.PersonResource;
import hajecs.services.MileStoneServiceImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

    private MileStone mileStone;
    private Person manager;

    @Before
    public void initMileStone() {
//        mileStone = new MileStone("Projekt IT", "zaprojektowanie prostej platformy");
        mileStone = (MileStone) GraphAndTaskFactory.getInstance().createGraphStructure(GraphStructureType.MILESTONE);
        mileStone.setName("Projekt IT");
        mileStone.setDescribe("zaprojektowanie prostej platformy");

        Assert.assertNotNull(mileStone);

        manager = PersonResource.getManagerJanKowalski();

        Assert.assertNotNull(manager);
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


/*

        mileStone.addRelationShipsBetweenTwoTasks("Przygotowanie dokumentacji", "Przygotowanie bazy danych");
        mileStone.addRelationShipsBetweenTwoTasks("Przygotowanie dokumentacji", "Implementacja WEB");
        mileStone.addRelationShipsBetweenTwoTasks("Przygotowanie dokumentacji", "Implementacja logiki aplikacji");

        mileStone.addRelationShipsBetweenTwoTasks("Przygotowanie bazy danych", "bazy danych: funkcje");
        mileStone.addRelationShipsBetweenTwoTasks("Przygotowanie bazy danych", "bazy danych: procedury");
        mileStone.addRelationShipsBetweenTwoTasks("Przygotowanie bazy danych", "bazy danych: triggery");

        mileStone.addRelationShipsBetweenTwoTasks("bazy danych: funkcje", "Testowanie bazy danych");
        mileStone.addRelationShipsBetweenTwoTasks("bazy danych: procedury", "Testowanie bazy danych");
        mileStone.addRelationShipsBetweenTwoTasks("bazy danych: triggery", "Testowanie bazy danych");

        mileStone.addRelationShipsBetweenTwoTasks("Testowanie bazy danych", "Skladanie projektu w calosc");
        mileStone.addRelationShipsBetweenTwoTasks("Implementacja WEB", "Integracja WEB SERVER");
        mileStone.addRelationShipsBetweenTwoTasks("Implementacja logiki aplikacji", "Integracja WEB SERVER");
        mileStone.addRelationShipsBetweenTwoTasks("Integracja WEB SERVER", "Skladanie projektu w calosc");

        org.junit.Assert.assertEquals(10, mileStone.getNumberOfNodes());
        org.junit.Assert.assertEquals(13, mileStone.getNumberOfRelationShips());

        mileStone.setStartTaskNode("Przygotowanie dokumentacji");
        mileStone.setEndTaskNode("Skladanie projektu w calosc");

        mileStone.setWorkerToTask("Przygotowanie dokumentacji",
                (Worker) PersonResource.getJavaDeveloperWojciechSeliga(),
                (Worker) PersonResource.getJavaDeveloperAdamWojcik());

        mileStone.setWorkerToTask("Implementacja WEB",
                (Worker) PersonResource.getWebDeveloperDominikNocon(),
                (Worker) PersonResource.getWebDeveloperPrzemekRoman());

        mileStone.setWorkerToTask("Implementacja logiki aplikacji",
                (Worker) PersonResource.getJavaDeveloperAdamWojcik(),
                (Worker) PersonResource.getJavaDeveloperKamilMilosz());

        mileStone.setWorkerToTask("Integracja WEB SERVER",
                (Worker) PersonResource.getJavaDeveloperPiotrNawalka(),
                (Worker) PersonResource.getJavaDeveloperWojciechSeliga(),
                (Worker) PersonResource.getWebDeveloperMateuszStepala(),
                (Worker) PersonResource.getWebDeveloperDominikNocon());

        mileStone.setWorkerToTask("Skladanie projektu w calosc",
                (Worker) PersonResource.getJavaDeveloperWojciechSeliga(),
                (Worker) PersonResource.getWebDeveloperDominikNocon(),
                (Worker) PersonResource.getDatabaseDeveloperAdrianKrawiec(),
                (Worker) PersonResource.getDatabaseDeveloperLukaszDebinski(),
                (Worker) PersonResource.getUXDesignerMonikaStokrotka(),
                (Worker) PersonResource.getWebDeveloperMateuszStepala());

        mileStone.setWorkerToTask("Przygotowanie bazy danych",
                (Worker) PersonResource.getDatabaseDeveloperAdrianKrawiec());

        mileStone.setWorkerToTask("bazy danych: funkcje",
                (Worker) PersonResource.getDatabaseDeveloperAdrianKrawiec());

        mileStone.setWorkerToTask("bazy danych: procedury",
                (Worker) PersonResource.getDatabaseDeveloperLukaszDebinski());

        mileStone.setWorkerToTask("bazy danych: triggery",
                (Worker) PersonResource.getDatabaseDeveloperAdrianCiecholewski());

        mileStone.setWorkerToTask("Testowanie bazy danych",
                (Worker) PersonResource.getDatabaseDeveloperAdrianKrawiec(),
                (Worker) PersonResource.getDatabaseDeveloperLukaszDebinski());

        org.junit.Assert.assertEquals(2, mileStone.findNodeByTask("Przygotowanie dokumentacji").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(2, mileStone.findNodeByTask("Implementacja logiki aplikacji").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(2, mileStone.findNodeByTask("Implementacja WEB").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(4, mileStone.findNodeByTask("Integracja WEB SERVER").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(1, mileStone.findNodeByTask("Przygotowanie bazy danych").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(1, mileStone.findNodeByTask("bazy danych: funkcje").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(1, mileStone.findNodeByTask("bazy danych: procedury").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(1, mileStone.findNodeByTask("bazy danych: triggery").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(2, mileStone.findNodeByTask("Testowanie bazy danych").getTask().getNumberOfWorkers());
        org.junit.Assert.assertEquals(6, mileStone.findNodeByTask("Skladanie projektu w calosc").getTask().getNumberOfWorkers());
        */
    }

    @Test @Rollback(true)
    public void test() {

        Assert.assertNotNull(dbGraphRepository);
        Assert.assertNotNull(dbNodeRepository);
        Assert.assertNotNull(dbRelationShipRepository);
        Assert.assertNotNull(taskRepository);

        Assert.assertNotNull(mileStoneService);

//        logger.info("przed zapisaniem " +mileStone.toString());

        for (AbstractNode node : mileStone.getNodeStorage()) {
//            logger.info("hashValue = " + ((TaskNode) node).getTask().getHashValue());
        }

        long id = mileStoneService.saveOrUpdateMileStone(mileStone);
        Assert.assertEquals(1, dbGraphRepository.count());

//        logger.info("po zapisaniu " + dbGraphRepository.findOne(id).toString());

        org.junit.Assert.assertEquals(10, dbGraphRepository.findOne(id).getNumberOfNodes());
        org.junit.Assert.assertEquals(10, dbNodeRepository.count());
        Assert.assertEquals(10, taskRepository.count());

        // koniec

        mileStone.addRelationShipsBetweenTwoTasks("Przygotowanie dokumentacji", "Przygotowanie bazy danych");

        org.junit.Assert.assertNotNull(taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId());

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
                );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );

        mileStoneService.addRelationShipBetweenTaskNodes(dbGraphRepository.findByName("Projekt IT").getId(),
                taskRepository.findByName("Przygotowanie dokumentacji").getTaskNode().getId(),
                taskRepository.findByName("Przygotowanie bazy danych").getTaskNode().getId()
        );



//        org.junit.Assert.assertEquals(13, dbGraphRepository.findOne(id).getNumberOfRelationShips());

        for (AbstractNode node : dbNodeRepository.findAll()) {
            logger.info("" + node);
        }

        for (RelationShip relationShip : dbRelationShipRepository.findAll()) {
            logger.info("" + relationShip);
        }
        org.junit.Assert.assertEquals(1, dbRelationShipRepository.count());

//        AbstractTask task = ((MileStone) dbGraphRepository.findOne(id)).getAllTasks().get(0);

//        logger.info("jak wyglada zerowy task " + String.valueOf(task));

//        ((MileStone)dbGraphRepository.findOne(id)).addRelationShipsBetweenTwoTasks("Przygotowanie dokumentacji", "Przygotowanie bazy danych");

//        Assert.assertEquals(1, dbRelationShipRepository.count());

//        long id2 = mileStoneService.saveOrUpdateMileStone(mileStone);
//        Assert.assertEquals(id, id2);

    }
}
