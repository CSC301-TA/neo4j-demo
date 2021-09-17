import org.neo4j.driver.*;

public class Neo4jDAO {

    private final Session session;
    private final Driver driver;

    private final String uriDb = "bolt://localhost:7687";
    private final String username = "neo4j";
    private final String password = "123456";


    public Neo4jDAO() {
        this.driver = GraphDatabase.driver(this.uriDb, AuthTokens.basic(this.username, this.password));
        this.session = this.driver.session();
    }

    public void insertPokemon(String name, String pid, String description, String type1, String type2) {
        String query;
        query = "CREATE (n:pokemon {name: \"%s\", pid: \"%s\", description: \"%s\", type1: \"%s\", type2: \"%s\"})";
        query = String.format(query, name, pid, description, type1, type2);
        this.session.run(query);
        return;
    }

}
