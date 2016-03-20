package com.tam.nytimes.network;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tam.nytimes.models.FilterOptions;

/**
 * Created by toan on 3/20/2016.
 */
public class ArticleClient {
    private static final String API_BASE_URL = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
    private static final String DATE_FORMAT = "yyyymmdd";
    private AsyncHttpClient client;
    public ArticleClient() {
        this.client = new AsyncHttpClient();
    }

    public void getArticles(String query, int page, JsonHttpResponseHandler handler) {
        try {

            RequestParams params = new RequestParams();
            params.put("api-key", "bbbef2f77221f5d1ba77cb2f088ed36a:5:74755766");
            params.put("page", page);
            params.put("q", query);
            client.get(API_BASE_URL, params, handler);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getArticles(String query, int page,
                            FilterOptions filterOptions, JsonHttpResponseHandler handler) {
        try {

            RequestParams params = new RequestParams();

            params.put("page", page);
            String beginDate = filterOptions.getBeginDateFilter();
            params.put("q", query);
            String filter = filterOptions.getArticleTypeFilter();
            if (!filter.equals("new_desk:()")) {
                params.put("fq", filter);
            }

            params.put("begin_date", beginDate);
            params.put("sort", filterOptions.getSortOrder().toString());
            //Toast.makeText(ArticleClient.this, params.toString() , Toast.LENGTH_SHORT).show();
            params.put("api-key", "bbbef2f77221f5d1ba77cb2f088ed36a:5:74755766");
            Log.d("url", params.toString());
            client.get(API_BASE_URL, params, handler);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
