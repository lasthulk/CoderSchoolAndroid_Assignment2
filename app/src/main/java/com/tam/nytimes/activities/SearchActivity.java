package com.tam.nytimes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.tam.nytimes.R;
import com.tam.nytimes.adapters.ArticleAdapter;
import com.tam.nytimes.models.Article;
import com.tam.nytimes.models.FilterOptions;
import com.tam.nytimes.network.ArticleClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private static final int REQUEST_CODE = 10;

    @Bind(R.id.etSearch)
    EditText etSearch;

    @Bind(R.id.btnSearch)
    Button btnSearch;

    @Bind(R.id.gvResults)
    GridView gvResults;

    private ArrayList<Article> articles;
    private ArticleAdapter adapter;
    static FilterOptions filterOptions;
    private void setupViews() {
        filterOptions = new FilterOptions();
        articles = new ArrayList<>();
        adapter = new ArticleAdapter(this, articles);
        gvResults.setAdapter(adapter);
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article selectedArticle = articles.get(position);
                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                intent.putExtra("article", selectedArticle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setupViews();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            this.filterOptions = (FilterOptions) data.getSerializableExtra("filterOptions");
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Toast.makeText(SearchActivity.this, "setting", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SettingsActivity.class);
            //intent.putExtra("filterOptions", filterOptions);
            startActivity(intent);
            //startActivityForResult(intent, REQUEST_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onArticleSearch(View view) {
//        Toast.makeText(SearchActivity.this, "search", Toast.LENGTH_SHORT).show();
        String query = etSearch.getText().toString();
        if (query.isEmpty()) {
            Toast.makeText(SearchActivity.this, "Please input some text to search", Toast.LENGTH_SHORT).show();
            etSearch.requestFocus();
        } else {
            ArticleClient client = new ArticleClient();
            client.getArticles(query, 0, filterOptions, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    JSONArray articlesJsonArray = null;
                    try {
                        adapter.clear();
                        articlesJsonArray = response.getJSONObject("response").getJSONArray("docs");
//                        if (articlesJsonArray.length() == 0) {
//                            Toast.makeText(SearchActivity.this, "There is no record", Toast.LENGTH_SHORT).show();
//                        } else {
//                        articles.addAll(Article.fromJSONArray(articlesJsonArray));
                            adapter.addAll(Article.fromJSONArray(articlesJsonArray));
                        //}
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //                @Override
//                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                    JSONArray articlesJsonArray = null;
//                    try {
//                        adapter.clear();
//                        articlesJsonArray = response.getJSONObject("response").getJSONArray("docs");
////                        articles.addAll(Article.fromJSONArray(articlesJsonArray));
//                        adapter.addAll(Article.fromJSONArray(articlesJsonArray));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }
            });

            Log.d(TAG, filterOptions.getSortOrder().toString());
        }
    }
}
