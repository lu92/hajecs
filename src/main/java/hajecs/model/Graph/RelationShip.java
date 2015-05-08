package hajecs.model.Graph;
import javax.persistence.*;

/**
 * Created by lucjan on 07.05.15.
 */
@Entity
public class RelationShip{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private AbstractNode beginNode;

    @ManyToOne
    private AbstractNode endNode;

    @Transient
    private boolean visited = false; // for DFS

    public RelationShip() {
    }

    public RelationShip(AbstractNode beginNode, AbstractNode endNode) {
        this.beginNode = beginNode;
        this.endNode = endNode;
    }

    public RelationShip(Long id, AbstractNode beginNode, AbstractNode endNode) {
        this.id = id;
        this.beginNode = beginNode;
        this.endNode = endNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationShip that = (RelationShip) o;

        if (!beginNode.equals(that.beginNode)) return false;
        if (!endNode.equals(that.endNode)) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + beginNode.hashCode();
        result = 31 * result + endNode.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbstractNode getBeginNode() {
        return beginNode;
    }

    public void setBeginNode(AbstractNode beginNode) {
        this.beginNode = beginNode;
    }

    public AbstractNode getEndNode() {
        return endNode;
    }

    public void setEndNode(AbstractNode endNode) {
        this.endNode = endNode;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
