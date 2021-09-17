import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import org.json.JSONException;

public abstract class Endpoint implements HttpHandler {

    public Neo4jDAO dao;

    public Endpoint() {
        this.dao = new Neo4jDAO();
    }

    public void handle(HttpExchange r) {
        try {
            switch (r.getRequestMethod()) {
                case "GET":
                    this.handleGet(r);
                    break;
                case "POST":
                    this.handlePost(r);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void handleGet(HttpExchange r) throws IOException, JSONException;
    public abstract void handlePost(HttpExchange r) throws IOException, JSONException;

}
