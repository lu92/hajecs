package IntegratedTests;

import hajecs.FreeSamples.*;
import hajecs.Neo4jTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

/**
 * Created by lucjan on 15.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jTestApplication.class)
@Transactional
public class VertexTest {

    private Logger logger = Logger.getLogger(String.valueOf(this));

    @Autowired
    private VertexRepository vertexRepository;

    @Autowired
    private RelationRepository relationRepository;

    @Test
    public void test() {
        Assert.assertNotNull(vertexRepository);
        Assert.assertNotNull(relationRepository);

        Vertex a = new A("A");
        Vertex b = new B("B");

        Assert.assertNotNull(a);
        Assert.assertNotNull(b);


        // trzeba najpierw zapisac wierzcholki
        vertexRepository.save(a);
        vertexRepository.save(b);
        Assert.assertEquals(2, vertexRepository.count());


        //  dopasowanie dziala na podstawie funkcji haszujacej
        Relation relation = new Relation(a, b, "relation a <-> b");
        Assert.assertNotNull(relation);
        a.addRelations(relation);

        Assert.assertNotNull(relation);
        relationRepository.save(relation);




        //  powinna byc jedna relacja i dwa wierzcholki
        Assert.assertEquals(1, relationRepository.count());
        Assert.assertEquals(2, vertexRepository.count());

        Assert.assertTrue("czy A ma relacje z B", vertexRepository.findByName("A").iterator().next().isConnectedWithVertex("B"));

        // nie ponieważ nie ustawilismy takiej relacji
        Assert.assertFalse("czy B ma relacje z A", vertexRepository.findByName("B").iterator().next().isConnectedWithVertex("A"));

        for (Relation relation1 : relationRepository.findAll())
            logger.info("" + relation1);

    }
}
