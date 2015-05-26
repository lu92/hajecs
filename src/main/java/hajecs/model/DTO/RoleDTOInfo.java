package hajecs.model.DTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 25.05.15.
 */
public class RoleDTOInfo {
    private Long id;
    private String name;
    private Set<PrivilegeDTOInfo> privilegeDTOInfos = new HashSet<>();


    public RoleDTOInfo() {
    }

    public RoleDTOInfo(Long id, String name, Set<PrivilegeDTOInfo> privilegeDTOInfos) {
        this.id = id;
        this.name = name;
        this.privilegeDTOInfos = privilegeDTOInfos;
    }

    public void addPrivilegeDTOInfo(PrivilegeDTOInfo ... dtoInfos) {
        for (PrivilegeDTOInfo privilegeDTOInfo : dtoInfos)
            privilegeDTOInfos.add(privilegeDTOInfo);
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

    public Set<PrivilegeDTOInfo> getPrivilegeDTOInfos() {
        return privilegeDTOInfos;
    }

    public void setPrivilegeDTOInfos(Set<PrivilegeDTOInfo> privilegeDTOInfos) {
        this.privilegeDTOInfos = privilegeDTOInfos;
    }
}
