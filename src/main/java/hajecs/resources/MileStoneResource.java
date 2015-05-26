package hajecs.resources;

import hajecs.factories.GraphAndTaskFactory;
import hajecs.factories.GraphStructureType;
import hajecs.model.Graph.MileStone;
import hajecs.model.Task.DailyTask;
import hajecs.model.Task.SeveralDaysTask;

/**
 * Created by lucjan on 24.05.15.
 */
public class MileStoneResource {

    public static MileStone createProjectITMileStone() {
        MileStone mileStone = (MileStone) GraphAndTaskFactory.getInstance().createGraphStructure(GraphStructureType.MILESTONE);
        mileStone.setId(new Long(1));
        mileStone.setName("Projekt IT");
        mileStone.setDescribe("zaprojektowanie prostej platformy");
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
        return mileStone;
    }

}
