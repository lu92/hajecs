package hajecs.services;

import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.PrivilegeDTOInfo;
import hajecs.model.personalData.Privilege;
import hajecs.repositories.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 25.05.15.
 */
@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService{

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public long createPrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege).getId();
    }

    @Override
    public void deletePrivilege(long privilegeId) {
        privilegeRepository.delete(privilegeId);
    }

    @Override
    public Privilege getPrivilege(long privilegeId) throws IllegalArgumentException {
        return privilegeRepository.findOne(privilegeId);
    }

    @Override
    public Set<Privilege> getAllPrivileges() {
        Set<Privilege> privilegeSet = new HashSet<>();
        for (Privilege privilege : privilegeRepository.findAll())
            privilegeSet.add(privilege);
        return privilegeSet;
    }

    @Override
    public Set<PrivilegeDTOInfo> getAllPrivilegeDtoInfos() {
        Set<PrivilegeDTOInfo> privilegeDTOInfos = new HashSet<>();
        for (Privilege privilege : getAllPrivileges()) {
            privilegeDTOInfos.add(DTOConverter.toPrivilegeDTOInfo(privilege));
        }
        return privilegeDTOInfos;
    }
}
