package hajecs.model.test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucjan on 11.05.15.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractObject {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private int age;


    List<AbstractObject> otherObjects = new ArrayList<>();

    public AbstractObject(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public AbstractObject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<AbstractObject> getOtherObjects() {
        return otherObjects;
    }

    public void setOtherObjects(List<AbstractObject> otherObjects) {
        this.otherObjects = otherObjects;
    }
}
