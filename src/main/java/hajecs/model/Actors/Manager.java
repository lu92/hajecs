package hajecs.model.Actors;


import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by lucjan on 10.03.15.
 */
@NodeEntity
public class Manager extends Person {



    public Manager() {
    }

    public Manager(String username, String password, String email) {
        super(username, password, email);
    }


}
