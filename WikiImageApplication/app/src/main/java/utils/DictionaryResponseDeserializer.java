package utils;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Page;
import model.QueryResults;
import model.WicktionarySearchResponse;

/**
 * Created by thukralp on 09/06/16.
 */
public class DictionaryResponseDeserializer implements JsonDeserializer<WicktionarySearchResponse> {

    @Override
    public WicktionarySearchResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement value = null;
        WicktionarySearchResponse response = new WicktionarySearchResponse();
        if(json!=null) {
            value = json.getAsJsonObject().get("query").getAsJsonObject().get("pages");
            if (value != null) {
                Iterable<Map.Entry<String, JsonElement>> entries = value.getAsJsonObject().entrySet();
                QueryResults query = new QueryResults();
                List<Page> resultPages = new ArrayList<Page>();
                for (Map.Entry<String, JsonElement> entry : entries) {
                    resultPages.add(new Gson().fromJson(entry.getValue(), Page.class));
                }
                query.setPages(resultPages);
                response.setQuery(query);
            }
        }
        return response;
    }
}
