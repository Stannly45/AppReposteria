package com.example.reposteriaapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.reposteriaapp.R;

public class IntroActivity extends AppCompatActivity {
    private ConstraintLayout btnStart;
    private VideoView viewIntro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        playVideo();
    }

    private void playVideo(){
        viewIntro = findViewById(R.id.viewIntro);
        viewIntro.setVideoURI(Uri.parse("android.resource://"+getPackageName() + "/" + R.raw.intro));
        viewIntro.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        viewIntro.start();
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        playVideo();
    }
}