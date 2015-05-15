package hajecs.model.Actors;


import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by lucjan on 10.03.15.
 */
@NodeEntity
public class Worker extends  Person{

    public Worker(String username, String password, String email) {
        super(username, password, email);
    }

    public Worker() {

    }
}
