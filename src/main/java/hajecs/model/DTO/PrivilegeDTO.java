package hajecs.model.DTO;

/**
 * Created by lucjan on 25.05.15.
 */
public class PrivilegeDTO {
    private String name;

    public PrivilegeDTO() {
    }

    public PrivilegeDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
