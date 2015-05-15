package hajecs;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class PersonOld {

    @GraphId Long id;
    public String name;

    public PersonOld() {}
    public PersonOld(String name) { this.name = name; }

    @RelatedTo(type="TEAMMATE", direction=Direction.BOTH)
    public @Fetch Set<PersonOld> teammates;

    public void worksWith(PersonOld person) {
        if (teammates == null) {
            teammates = new HashSet<PersonOld>();
        }
        teammates.add(person);
    }

    public String toString() {
        String results = name + "'s teammates include\n";
        if (teammates != null) {
            for (PersonOld person : teammates) {
                results += "\t- " + person.name + "\n";
            }
        }
        return results;
    }

    public void showDetails() {
        System.out.println(id + "\t" + name);
    }

}
