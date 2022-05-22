package com.example.pasapas.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pasapas.R;
import com.example.pasapas.model.Cours;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Cours cours = (Cours) getIntent().getSerializableExtra("cours");
        setTilte(cours);
        displayVideo(cours);
    }

    void setTilte(Cours cours) {

        TextView propos = findViewById(R.id.coursPropos);
        propos.setText(cours.getNom() + " - Niveau " + cours.getNiveau());

        TextView titre = findViewById(R.id.titreVideo);
        titre.setText(cours.getTitre());
    }

    void displayVideo(Cours cours) {
        CircularProgressIndicator progressIndicator = findViewById(R.id.circularVideo);
        WebView webView = findViewById(R.id.webview);
        String html = "<iframe src=\""+
                cours.getUrl_video() +
                "\" title=\""+ cours.getTitre() +
                "\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen>Chargement ... </iframe>";
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webView.loadData(html, "text/html", "utf-8");
        progressIndicator.hide();
        webView.setVisibility(View.VISIBLE);
    }

}