 package com.example.moviex.activities;

 import android.os.Bundle;
 import androidx.recyclerview.widget.RecyclerView;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.appcompat.app.AppCompatActivity;
 import com.android.volley.RequestQueue;
 import com.android.volley.Response;
 import com.android.volley.VolleyError;
 import com.android.volley.toolbox.JsonArrayRequest;
 import com.android.volley.toolbox.Volley;
 import com.example.moviex.R;
 import com.example.moviex.adapters.RecyclerViewAdapter;
 import com.example.moviex.models.Movies;

 import org.json.JSONArray;
 import org.json.JSONException;
 import org.json.JSONObject;

 import java.util.ArrayList;
 import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "https://ybsciler.com/Json/Mobile/filmler.json" ; // verileri çekeceğimiz json data
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Movies> lstMovies;
    private RecyclerView recyclerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstMovies = new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
    }

    private void jsonrequest() { // Json veri okuma işlemleri

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {

                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Movies movies = new Movies() ;
                        movies.setName(jsonObject.getString("name"));
                        movies.setDescription(jsonObject.getString("description"));
                        movies.setRating(jsonObject.getString("Rating"));
                        movies.setCategorie(jsonObject.getString("categorie"));
                        movies.setNb_episode(jsonObject.getInt("year"));
                        movies.setStudio(jsonObject.getString("yonetmen"));
                        //movies.setImage_url(jsonObject.getString("img"));
                        movies.setUrl(jsonObject.getString("url"));
                        lstMovies.add(movies);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setuprecyclerview(lstMovies);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request) ;


    }

    private void setuprecyclerview(List<Movies> lstMovies) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, lstMovies) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }


}