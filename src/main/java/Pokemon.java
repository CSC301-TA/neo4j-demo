import com.sun.net.httpserver.HttpExchange;
import org.json.JSONException;
import java.io.IOException;
import org.json.*;

public class Pokemon extends Endpoint {

    /**
     * GET /pokemon/:pid
     * @param {String} pid     String representing a unique pokemon id
     * @return 200, 400, 404, 500
     * Returns a Pokemon with the specified pokemon id, if it exists.
     */

    public void handleGet(HttpExchange r) throws IOException, JSONException {
        // TODO: Complete this :)
    }

    /**
     * POST /pokemon/
     * @param_body
     * @return 200, 400, 500
     * Creates a Pokemon with the specified fields
     */

    public void handlePost(HttpExchange r) throws IOException, JSONException {
        String body = Utils.convert(r.getRequestBody());
        try {
            JSONObject deserialized = new JSONObject(body);

            String name, pid, description, type1, type2;

            if (deserialized.length() == 5 && deserialized.has("name") && deserialized.has("pid") &&
                    deserialized.has("description") && deserialized.has("type1") && deserialized.has("type2")) {
                name = deserialized.getString("name");
                pid = deserialized.getString("pid");
                description = deserialized.getString("description");
                type1 = deserialized.getString("type1");
                type2 = deserialized.getString("type2");
            } else {
                r.sendResponseHeaders(400, -1);
                return;
            }

            try {
                this.dao.insertPokemon(name, pid, description, type1, type2);
            } catch (Exception e) {
                r.sendResponseHeaders(500, -1);
                e.printStackTrace();
                return;
            }
            r.sendResponseHeaders(200, -1);
        } catch (Exception e) {
            e.printStackTrace();
            r.sendResponseHeaders(500, -1);
        }
    }
}
