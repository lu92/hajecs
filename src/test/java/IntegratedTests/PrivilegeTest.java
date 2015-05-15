package IntegratedTests;

import hajecs.Neo4jTestApplication;
import hajecs.model.personalData.Privilege;
import hajecs.repositories.PrivilegeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucjan on 14.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jTestApplication.class)
@Transactional

public class PrivilegeTest {

    @Autowired
    private GraphDatabase graphDatabase;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Test
    @Rollback(true)
    public void privilegeTest() {

        Assert.assertNotNull(graphDatabase);
        Assert.assertNotNull(privilegeRepository);

        String [] privilegeStrings = {"testowanie", "kodowanie", "pomoc"};
        List<Privilege> privilegeList = new ArrayList<>();
        for (int i=0; i<privilegeStrings.length; i++)
            privilegeList.add(new Privilege(privilegeStrings[i]));


        for (Privilege privilege : privilegeList)
            privilegeRepository.save(privilege);

        Assert.assertEquals(privilegeList.size(), privilegeRepository.count());

        Assert.assertNotNull(privilegeRepository.findByPrivilegeName("testowanie"));
        Assert.assertNotNull(privilegeRepository.findByPrivilegeName("kodowanie"));
        Assert.assertNotNull(privilegeRepository.findByPrivilegeName("pomoc"));



    }

}
