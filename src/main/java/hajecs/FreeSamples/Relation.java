package hajecs.FreeSamples;

import org.springframework.data.neo4j.annotation.*;

/**
 * Created by lucjan on 15.05.15.
 */
@RelationshipEntity(type = "MEMBER_OF")
public class Relation {

    @GraphId private Long id;
    @Fetch @StartNode private Vertex a;
    @Fetch @EndNode private Vertex b;
    private String name;

    public Relation() {
    }


    public Relation(Vertex a, Vertex b, String role) {
        this.a = a;
        this.b = b;
        this.name = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relation relation = (Relation) o;

        if (!name.equals(relation.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Vertex getA() {
        return a;
    }

    public void setA(Vertex a) {
        this.a = a;
    }

    public Vertex getB() {
        return b;
    }

    public void setB(Vertex b) {
        this.b = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "id=" + id +
                ", a=" + a.getName() +
                ", b=" + b.getName() +
                ", name='" + name + '\'' +
                '}';
    }
}
