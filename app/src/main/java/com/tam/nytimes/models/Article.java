package com.tam.nytimes.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by toan on 3/20/2016.
 */
@Parcel
public class Article {
    String webUrl;
    String headLine;
    String thumbNail;

    public String getHeadLine() {
        return headLine;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public Article() {}

    public  Article(JSONObject jsonObject) {
        try {
            this.webUrl = jsonObject.getString("web_url");
            this.headLine = jsonObject.getJSONObject("headline").getString("main");
            JSONArray arrMultimedia = jsonObject.getJSONArray("multimedia");
            if (arrMultimedia.length() > 0) {
                JSONObject multimedia = arrMultimedia.getJSONObject(0);
                this.thumbNail = "http://www.nytimes.com/" + multimedia.getString("url");
            } else {
                this.thumbNail = "";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Article> fromJSONArray(JSONArray array) {
        ArrayList<Article> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Article(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
