package com.tam.nytimes.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by toan on 3/20/2016.
 */
public class ArticleClient {
    private static final String API_BASE_URL = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
    private AsyncHttpClient client;
    public ArticleClient() {
        this.client = new AsyncHttpClient();
    }

    public void getArticles(final String query, final int page, JsonHttpResponseHandler handler) {
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

}
