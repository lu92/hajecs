package hajecs;

/**
 * Created by lucjan on 14.05.15.
 */
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Person {

    @GraphId Long id;
    public String name;

    public Person() {}
    public Person(String name) { this.name = name; }

    @RelatedTo(type="TEAMMATE", direction=Direction.BOTH)
    public @Fetch Set<Person> teammates;





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
    public Set<Person> getTeammates() {
        return teammates;
    }
    public void setTeammates(Set<Person> teammates) {
        this.teammates = teammates;
    }
    public void worksWith(Person person) {
        if (teammates == null) {
            teammates = new HashSet<Person>();
        }
        teammates.add(person);
    }

    public String toString() {
        String results = name + "'s teammates include\n";
        if (teammates != null) {
            for (Person person : teammates) {
                results += "\t- " + person.name + "\n";
            }
        }
        return results;
    }

    public void showDetails() {
        System.out.println(id + "\t" + name);
    }
}
