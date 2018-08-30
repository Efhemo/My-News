package com.example.efhemo.mynews;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class webActivity extends AppCompatActivity {

    private Uri mCurrentPetUri;

    ProgressBar progressBar;
    WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_web);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String getTile = getIntent().getExtras().getString("title");
        String getURL = getIntent().getExtras().getString("url");

        setTitle(getTile);

        progressBar = (ProgressBar)findViewById(R.id.webProgress);
        webView = (WebView)findViewById(R.id.webView);
        progressBar.setVisibility(View.GONE);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            /**
             * Notify the host application that the WebView will load the resource
             * specified by the given url.
             *
             * @param view The WebView that is initiating the callback.
             * @param url  The url of the resource the WebView will load.
             */
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                progressBar.incrementProgressBy(10);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getURL);

    }

}
