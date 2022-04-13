package com.example.btlon_movie.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.btlon_movie.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.VideoController);
        getLifecycle().addObserver(youTubePlayerView);
        Intent intent=getIntent();
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                String[] videoId = intent.getStringExtra("IdVideo").split("=");
                if(videoId.length>2)
                {
                    Toast.makeText(VideoActivity.this, "Video không thể phát", Toast.LENGTH_SHORT).show();
                }else{
                    youTubePlayer.loadVideo(videoId[1].trim(), 0);
                    youTubePlayer.play();
                }

            }
        });
    }
}