package hajecs.model.Actors;

import javax.persistence.Entity;

/**
 * Created by lucjan on 10.03.15.
 */
@Entity
public class Worker extends  Person{

    public Worker(String username, String password, String email) {
        super(username, password, email);
    }

    public Worker() {

    }
}
