package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.WicktionarySearchResponse;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import utils.DictionaryResponseDeserializer;

/**
 * Created by thukralp on 27/02/16.
 */
public interface RestClient {

    String baseUrl = "https://en.wikipedia.org/w/" ;
    static Gson gson= new GsonBuilder().registerTypeAdapter(WicktionarySearchResponse.class,new DictionaryResponseDeserializer()).create();

    Retrofit client = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    WikiImageNetwork mService = client.create(WikiImageNetwork.class);



}
