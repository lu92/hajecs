package hajecs.model.DTO;

import hajecs.model.personalData.Privilege;

/**
 * Created by lucjan on 25.05.15.
 */
public class RoleDTO {
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
