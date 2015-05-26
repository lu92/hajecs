package hajecs.controllers;

import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.PrivilegeDTO;
import hajecs.model.DTO.PrivilegeDTOInfo;
import hajecs.model.DTO.Response;
import hajecs.services.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by lucjan on 25.05.15.
 */
@RestController
@RequestMapping(value = "/privilege")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;


    @ResponseBody
    @RequestMapping(value = "/createPrivilege", method = RequestMethod.POST)
    public Response createPrivilege(@RequestBody PrivilegeDTO privilegeDTO) {
        privilegeService.createPrivilege(DTOConverter.toPrivilege(privilegeDTO));
        return new Response("created new Privilege");
    }

    @ResponseBody
    @RequestMapping(value = "/deletePrivilege/{privilegeId}")
    public Response deletePrivilege(@PathVariable("privilegeId") long privilegeId) {
        privilegeService.deletePrivilege(privilegeId);
        return new Response("Privilege wad deleted");
    }


    @ResponseBody
    @RequestMapping(value = "/getAllPrivileges", method = RequestMethod.GET)
    public Set<PrivilegeDTOInfo> getAllPrivileges() {
        return privilegeService.getAllPrivilegeDtoInfos();
    }

}
