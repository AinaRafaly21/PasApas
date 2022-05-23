package com.example.pasapas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pasapas.R;
import com.example.pasapas.model.Cours;
import com.example.pasapas.tools.ParamQuiz;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class VideoActivity extends AppCompatActivity {
    CircularProgressIndicator progressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Cours cours = (Cours) getIntent().getSerializableExtra("cours");
        progressIndicator = findViewById(R.id.circularVideo);
        setTilte(cours);
        displayVideo(cours);
        Button button= findViewById(R.id.passToQuiz);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirect(cours);
            }
        });
    }

    void setTilte(Cours cours) {

        TextView propos = findViewById(R.id.coursPropos);
        propos.setText(cours.getNom() + " - Niveau " + cours.getNiveau());

        TextView titre = findViewById(R.id.titreVideo);
        titre.setText(cours.getTitre());
    }

    void displayVideo(Cours cours) {
        YouTubePlayerView youTubePlayerView = findViewById(R.id.playerView);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                youTubePlayer.loadVideo(cours.getUrl_video(), 0);
                progressIndicator.hide();
                youTubePlayerView.setVisibility(View.VISIBLE);
            }
        });
    }

    void redirect(Cours cours) {
        Intent intent = new Intent(VideoActivity.this, QuizActivity.class);
        ParamQuiz paramQuiz = new ParamQuiz(cours.getNom(), cours.getCategorie(), cours.getNiveau());
        intent.putExtra("paramQuiz", paramQuiz);
        startActivity(intent);
    }

}