package com.example.dogecoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class SupportActivity extends AppCompatActivity {

    MediaPlayer buttonclick1;
    ImageView backimg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_support);
        getSupportActionBar().hide();
        backimg1 = (ImageView) findViewById(R.id.backimg);
        buttonclick1 = MediaPlayer.create(this, R.raw.button_sound1);
    }

    void back (View v){
        if (buttonclick1.isPlaying()) {
            buttonclick1.stop();
            buttonclick1.release();
            buttonclick1 = MediaPlayer.create(SupportActivity.this, R.raw.button_sound1);
        } buttonclick1.start();
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }
}