package com.example.animationproject;

import android.animation.Animator;
import android.animation.TimeAnimator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.music);
        mp.start();

        ImageView dancers1 = findViewById(R.id.dancers1);
        ImageView dancers2 = findViewById(R.id.dancers2);
        ImageView dancers3 = findViewById(R.id.dancers3);

        ViewPropertyAnimator vpa = dancers1.animate();
        vpa.setStartDelay(5500).alpha(1F);

        //dancers2.animate().setStartDelay(34000).alpha(1F);
        dancers3.animate().setStartDelay(40000).alpha(1F);

        ((AnimationDrawable) dancers1.getDrawable()).start();
        ((AnimationDrawable) dancers2.getDrawable()).start();
        ((AnimationDrawable) dancers3.getDrawable()).start();

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(34000);
                }catch (InterruptedException e){
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dancers2.setVisibility(View.VISIBLE);
                    }
                });
            }

        };
        thread.start();
    }

}