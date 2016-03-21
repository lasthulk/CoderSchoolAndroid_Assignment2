package com.tam.nytimes.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tam.nytimes.R;
import com.tam.nytimes.models.Article;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArticleActivity extends AppCompatActivity {

    @Bind(R.id.wvArticle)
    WebView wvArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
//        Article article = (Article) getIntent().getSerializableExtra("article");
        Article article = (Article) Parcels.unwrap(getIntent().getParcelableExtra("article"));
        wvArticle.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvArticle.loadUrl(article.getWebUrl());
    }

}
