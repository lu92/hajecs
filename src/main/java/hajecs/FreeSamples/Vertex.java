package hajecs.FreeSamples;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 15.05.15.
 */

@NodeEntity
public abstract class Vertex {

    @GraphId private Long id;
    private String name;

    @RelatedToVia(type = "MEMBER_OF", direction = Direction.BOTH)
    private Set<Relation> relations = new HashSet<>();

    public Vertex() {
    }

    public Vertex(String name) {
        this.name = name;
    }

    public void addRelations(Relation ... relationSet) {
        for (Relation relation : relationSet) {
            relations.add(relation);
        }

    }

    public boolean isConnectedWithVertex(String vertexName) {
        for (Relation relation : relations) {
            if (relation.getB().getName().equals(vertexName))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (!name.equals(vertex.name)) return false;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Relation> getRelations() {
        return relations;
    }

    public void setRelations(Set<Relation> relations) {
        this.relations = relations;
    }


}
