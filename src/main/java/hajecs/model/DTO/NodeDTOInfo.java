package hajecs.model.DTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 24.05.15.
 */
public class NodeDTOInfo {
    private Long id;
    private String name;
    private Set<Long> neighbours = new HashSet<>();

    public NodeDTOInfo() {
    }

    public NodeDTOInfo(Long id, String name, Set<Long> neighbours) {
        this.id = id;
        this.name = name;
        this.neighbours = neighbours;
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

    public Set<Long> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<Long> neighbours) {
        this.neighbours = neighbours;
    }
}

