package hajecs.controllers;

import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.Response;
import hajecs.model.DTO.RoleDTO;
import hajecs.model.DTO.RoleDTOInfo;
import hajecs.model.personalData.Role;
import hajecs.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 25.05.15.
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping(value = "/createRole", method = RequestMethod.POST)
    public Response createRole(@RequestBody RoleDTO roleDTO) {
        roleService.createRole(DTOConverter.toRole(roleDTO));
        return new Response("new Role was created");
    }



    @ResponseBody
    @RequestMapping(value = "/getAllRoles", method = RequestMethod.GET)
    public Set<RoleDTOInfo> getAllRoles() {
        Set<RoleDTOInfo> roleDTOInfos = new HashSet<>();
        for (Role role : roleService.getAllRoles()) {
            roleDTOInfos.add(DTOConverter.toRoleDTOInfo(role));
        }
        return roleDTOInfos;
    }



    @ResponseBody
    @RequestMapping(value = "/addPrivilegeToRole/{roleId}/{privilegeId}", method = RequestMethod.GET)
    public Response addPrivilegeToRole(@PathVariable("roleId") long roleId, @PathVariable("privilegeId") long privilegeId) {
        try {
            roleService.addPrivilegeToRole(roleId, privilegeId);
        } catch (Exception e) {
            return new Response("Invalid Data");
        }

        return new Response("Privilege was added to Role");
    }

}
