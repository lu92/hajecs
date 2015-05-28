package hajecs.model.DTO;

/**
 * Created by lucjan on 21.05.15.
 */
public class NodeDTO {
    /*
    do tworzenia wierzcholka w milestone lub project
     */
    private long graphId;
    private String name;

    private double x;
    private double y;

    public NodeDTO(long graphId, String name, double x, double y) {
        this.graphId = graphId;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public NodeDTO() {
    }

    public NodeDTO(long graphId, String name) {
        this.graphId = graphId;
        this.name = name;
    }

    public long getGraphId() {
        return graphId;
    }

    public void setGraphId(long graphId) {
        this.graphId = graphId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
