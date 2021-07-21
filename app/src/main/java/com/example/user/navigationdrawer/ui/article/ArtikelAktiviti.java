package com.example.user.navigationdrawer.ui.article;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.user.navigationdrawer.R;


public class ArtikelAktiviti extends AppCompatActivity {

    private static final String ARTICLE_URL = "ARTICLE_URL";

    public static void launch(Context context, String articleUrl) {
        Intent intent = new Intent(context, ArtikelAktiviti.class);
        intent.putExtra(ARTICLE_URL, articleUrl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        // Pridobimo url artikla iz parametrov intenta
        String url = getIntent().getStringExtra(ARTICLE_URL);

        // Nalozimo url v nas WevView
        WebView webView = (WebView) findViewById(R.id.webview_article);
        webView.loadUrl(url);
    }
}
