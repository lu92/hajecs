package hajecs;

/**
 * Created by lucjan on 14.05.15.
 */
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "hajecs")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {


    public Neo4jConfig() {
        setBasePackage("hajecs");
    }

    /**
     * Start new embebed data base
     * @return
     */
    @Bean
    GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase("cristianNeo4jTest2.db");
    }
}
