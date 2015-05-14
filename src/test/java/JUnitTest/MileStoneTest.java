package JUnitTest;

import hajecs.Resource.PersonResource;
import hajecs.model.Actors.Person;
import hajecs.model.Actors.Worker;
import hajecs.model.Graph.MileStone;
import hajecs.model.Task.DailyTask;
import hajecs.model.Task.SeveralDaysTask;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lucjan on 13.05.15.
 */

public class MileStoneTest {

    private MileStone mileStone;
    private Person manager;


    @Before
    public void initMileStone() {
        mileStone = new MileStone("Projekt IT", "zaprojektowanie prostej platformy");
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

        Assert.assertEquals(10, mileStone.getNumberOfNodes());
        Assert.assertEquals(13, mileStone.getNumberOfRelationShips());

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

        Assert.assertEquals(2, mileStone.findNodeByTask("Przygotowanie dokumentacji").getTask().getNumberOfWorkers());
        Assert.assertEquals(2, mileStone.findNodeByTask("Implementacja logiki aplikacji").getTask().getNumberOfWorkers());
        Assert.assertEquals(2, mileStone.findNodeByTask("Implementacja WEB").getTask().getNumberOfWorkers());
        Assert.assertEquals(4, mileStone.findNodeByTask("Integracja WEB SERVER").getTask().getNumberOfWorkers());
        Assert.assertEquals(1, mileStone.findNodeByTask("Przygotowanie bazy danych").getTask().getNumberOfWorkers());
        Assert.assertEquals(1, mileStone.findNodeByTask("bazy danych: funkcje").getTask().getNumberOfWorkers());
        Assert.assertEquals(1, mileStone.findNodeByTask("bazy danych: procedury").getTask().getNumberOfWorkers());
        Assert.assertEquals(1, mileStone.findNodeByTask("bazy danych: triggery").getTask().getNumberOfWorkers());
        Assert.assertEquals(2, mileStone.findNodeByTask("Testowanie bazy danych").getTask().getNumberOfWorkers());
        Assert.assertEquals(6, mileStone.findNodeByTask("Skladanie projektu w calosc").getTask().getNumberOfWorkers());
    }

    @Test
    public void testRelationShipsBetweenDefaultMileStone() {
        Assert.assertEquals(0, mileStone.findNode("0").getNumberOfInCommingRelationShips());
        Assert.assertEquals(3, mileStone.findNode("0").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(3, mileStone.findNode("0").getNumberOfNeighbours());

        Assert.assertEquals(1, mileStone.findNode("1").getNumberOfInCommingRelationShips());
        Assert.assertEquals(3, mileStone.findNode("1").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(4, mileStone.findNode("1").getNumberOfNeighbours());

        Assert.assertEquals(1, mileStone.findNode("2").getNumberOfInCommingRelationShips());
        Assert.assertEquals(1, mileStone.findNode("2").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(2, mileStone.findNode("2").getNumberOfNeighbours());

        Assert.assertEquals(1, mileStone.findNode("3").getNumberOfInCommingRelationShips());
        Assert.assertEquals(1, mileStone.findNode("3").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(2, mileStone.findNode("3").getNumberOfNeighbours());

        Assert.assertEquals(1, mileStone.findNode("4").getNumberOfInCommingRelationShips());
        Assert.assertEquals(1, mileStone.findNode("4").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(2, mileStone.findNode("4").getNumberOfNeighbours());

        Assert.assertEquals(1, mileStone.findNode("5").getNumberOfInCommingRelationShips());
        Assert.assertEquals(1, mileStone.findNode("5").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(2, mileStone.findNode("5").getNumberOfNeighbours());

        Assert.assertEquals(1, mileStone.findNode("6").getNumberOfInCommingRelationShips());
        Assert.assertEquals(1, mileStone.findNode("6").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(2, mileStone.findNode("6").getNumberOfNeighbours());

        Assert.assertEquals(2, mileStone.findNode("7").getNumberOfInCommingRelationShips());
        Assert.assertEquals(1, mileStone.findNode("7").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(3, mileStone.findNode("7").getNumberOfNeighbours());


        Assert.assertEquals(3, mileStone.findNode("8").getNumberOfInCommingRelationShips());
        Assert.assertEquals(1, mileStone.findNode("8").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(4, mileStone.findNode("8").getNumberOfNeighbours());

        Assert.assertEquals(2, mileStone.findNode("9").getNumberOfInCommingRelationShips());
        Assert.assertEquals(0, mileStone.findNode("9").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(2, mileStone.findNode("9").getNumberOfNeighbours());
    }

    @Test
    public void createMileStoneTest() {
        MileStone simpleMileStone = new MileStone();
        Assert.assertNotNull(simpleMileStone);
    }

    @Test
    public void calculateStats() {

    }
}
