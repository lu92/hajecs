package IntegratedTests;

import hajecs.Neo4jTestApplication;
import hajecs.Resource.RoleResource;
import hajecs.model.personalData.Role;
import hajecs.repositories.PrivilegeRepository;
import hajecs.repositories.RoleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucjan on 14.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jTestApplication.class)
@Transactional
public class RoleTest {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Test
    @Rollback(true)
    public void RoleTest() {
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(privilegeRepository);

        roleRepository.save(RoleResource.getDataBaseDeveloper());
        roleRepository.save(RoleResource.getJAVADeveloper());

        Assert.assertEquals(2, roleRepository.count());
        Assert.assertEquals(7, privilegeRepository.count());


        Result<Role> roleResult = roleRepository.findAll();

        for (Role role : roleResult)
            System.out.println(role);

        System.out.println(roleRepository.findByRoleName("Java Developer Role"));
        for (Role role : privilegeRepository.findByPrivilegeName("Java").getRoleStorage()) {
            System.out.println(role.getRoleName());
        }
    }

}
