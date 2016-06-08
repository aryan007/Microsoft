package network;

import model.WicktionarySearchResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by thukralp on 27/02/16.
 */
public interface WikiImageNetwork {

    int IMAGE_SIZE = 1000;
    @GET("api.php?action=query&prop=pageimages&format=json&piprop=thumbnail&pithumbsize="+IMAGE_SIZE+"&pilimit=50&generator=prefixsearch")
    Call<WicktionarySearchResponse> listPhotos(@Query("gpssearch") String inputText);


}
