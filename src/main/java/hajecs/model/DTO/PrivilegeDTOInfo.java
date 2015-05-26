package hajecs.model.DTO;

/**
 * Created by lucjan on 25.05.15.
 */
public class PrivilegeDTOInfo {
    private Long id;
    private String name;

    public PrivilegeDTOInfo() {
    }

    public PrivilegeDTOInfo(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
