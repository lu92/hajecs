package hajecs.model.Actors;


import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by lucjan on 10.03.15.
 */
@NodeEntity
public class Student extends Person {

    public Student(String username, String password, String email) {
        super(username, password, email);
    }

    public Student() {

    }
}
