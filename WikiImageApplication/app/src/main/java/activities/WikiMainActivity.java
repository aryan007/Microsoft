package activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import adapter.ImageAdapter;
import model.Page;
import model.WicktionarySearchResponse;
import network.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class WikiMainActivity extends Activity implements RestClient, ImageAdapter.OnItemClickListener {

    EditText mInputEditText;
    RecyclerView mImageListView;
    List<Page> pageList;
    ImageAdapter imageAdapter ;
    ProgressDialog progressBar;
    GridLayoutManager gridLayoutManager;
    android.os.Handler mHandler = new android.os.Handler();
    private final long DELAY = 1000; // in ms
    Call<WicktionarySearchResponse> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_main);
        mInputEditText = (EditText)findViewById(R.id.editTextEntry);
        mImageListView = (RecyclerView)findViewById(R.id.flikrListView);
        progressBar = new ProgressDialog(this);

        mInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before,
                                      int count) {
                new CancelTask().execute(call);
            }

            @Override
            public void afterTextChanged(final Editable s) {
                //avoid triggering event when text is too short
                if (s.length() >= 3) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            createRetrofitService();
                        }
                    }, DELAY);
                }
            }
        });

    }

    private void hideKeyboard(View view)
    {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    //Makes a Network call using Retrofit and fetches the list of Images
    private void createRetrofitService()
    {
        call = mService.listPhotos(mInputEditText.getEditableText().toString().trim());
        progressBar.show();
        call.enqueue(new Callback<WicktionarySearchResponse>() {
            @Override
            public void onResponse(Response<WicktionarySearchResponse> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    if(response!=null && response.body()!=null && response.body().getQuery()!=null && response.body().getQuery().getPages()!=null) {
                        pageList = response.body().getQuery().getPages();
                        initializeRecyclerView();
                    }
                    progressBar.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(WikiMainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressBar.dismiss();
            }
        });
    }

    //Initializes the Recycler View which loads the Images
    private void initializeRecyclerView() {
        mImageListView.setHasFixedSize(true);
        imageAdapter = new ImageAdapter(pageList,this);
        gridLayoutManager = new GridLayoutManager(this,1);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mImageListView.setLayoutManager(gridLayoutManager);
        mImageListView.setAdapter(imageAdapter);
    }

    //Opens the Detail View Of Image
    @Override
    public void onItemClick(int flikrImagePosition,View view) {

    }


    private static class CancelTask extends AsyncTask<Call, Void, Void> {
        @Override
        protected Void doInBackground(Call... params) {
            Call call = params[0];
            if(call!=null)
            call.cancel();
            return null;
        }
    }

}
