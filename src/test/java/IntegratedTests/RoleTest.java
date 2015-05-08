package IntegratedTests;

import hajecs.HajecsApplication;
import hajecs.model.personalData.Privilege;
import hajecs.model.personalData.Role;
import hajecs.repositories.PrivilegeRepository;
import hajecs.repositories.RoleRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucjan on 08.05.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HajecsApplication.class)
@WebAppConfiguration
@Transactional  //  IMPORTANT
@TransactionConfiguration(defaultRollback = true)
public class RoleTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Test
    public void RoleTest() {
        Role JavaDeveloper = new Role("JavaDeveloper");
        Role CPPDeveloper = new Role("CPPDeveloper");

        JavaDeveloper.addPrivileges(new Privilege("java"),new Privilege("jsp"),new Privilege("tomcat"));
        CPPDeveloper.addPrivileges(new Privilege("unitTest"),new Privilege("jsp"));

        Assert.assertNotNull(roleRepository);

        roleRepository.save(JavaDeveloper);
        roleRepository.save(CPPDeveloper);


        Role dbJavaDeveloper = roleRepository.findAll().get(0);
        Role dbCPPDeveloper = roleRepository.findAll().get(1);


        Assert.assertEquals(JavaDeveloper,dbJavaDeveloper);
        Assert.assertEquals(CPPDeveloper,dbCPPDeveloper);

        Assert.assertEquals(3L, roleRepository.findAll().get(0).getPrivilegeStorage().size());
        Assert.assertEquals(2L, roleRepository.findAll().get(1).getPrivilegeStorage().size());

        Assert.assertNotNull(privilegeRepository);
        for (Role role : privilegeRepository.findAll().get(0).getRoleStorage())
            System.out.println(role.getRoleName());


        Assert.assertEquals(5L, privilegeRepository.count());

    }
}
