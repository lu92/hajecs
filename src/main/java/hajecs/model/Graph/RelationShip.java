package hajecs.model.Graph;

/**
 * Created by lucjan on 07.05.15.
 */
public class RelationShip{

    private Long id;

    private AbstractNode beginNode;

    private AbstractNode endNode;

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

    public static RelationShip getReversedRelationShip(RelationShip relationShip) {
        RelationShip reversedRelationShip = new RelationShip(relationShip.getEndNode(), relationShip.getBeginNode());
        return reversedRelationShip;
    }

    public RelationShip getReversedRelationShip() {
        RelationShip reversedRelationShip = new RelationShip(getEndNode(), getBeginNode());
        return reversedRelationShip;
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

    public void show() {
        System.out.println("RelationShip : " + beginNode.getName() + " -> " + endNode.getName());
    }
}
