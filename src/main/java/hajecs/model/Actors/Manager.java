package hajecs.model.Actors;


import hajecs.model.Graph.MileStone;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Created by lucjan on 10.03.15.
 */
@NodeEntity
public class Manager extends Person {

    @Fetch @RelatedTo(type = "MANAGER_MILESTONE", direction = Direction.BOTH)
    private MileStone mileStone;

    public Manager() {
    }

    public Manager(String username, String password, String email) {
        super(username, password, email);
    }


}
