package com.example.androidservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ivPlayStop;
    String audioLink="http://searchgurbani.com/audio/sggs/1.mp3";
    boolean musicPlaying=false;
    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPlayStop=findViewById(R.id.ivPlayStop);
        ivPlayStop.setBackgroundResource(R.drawable.music);
        serviceIntent=new Intent(this,MyPlayService.class);

        ivPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!musicPlaying)
                {
                    playAudio();
                    ivPlayStop.setImageResource(R.drawable.stop);
                    musicPlaying=true;
                }
                else
                {
                    stopPlayService();
                    ivPlayStop.setImageResource(R.drawable.music);
                    musicPlaying=false;
                }
            }
        });
    }

    private void stopPlayService() {

        try {
            stopService(serviceIntent);
        }
        catch (SecurityException e)
        {
            Toast.makeText(this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void playAudio() {
        serviceIntent.putExtra("AudioLink",audioLink);

        try {
            startService(serviceIntent);
        }
        catch (SecurityException e)
        {
            Toast.makeText(this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}