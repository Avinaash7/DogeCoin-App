package com.example.dogecoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer BtnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        BtnClick = MediaPlayer.create(this, R.raw.button_sound1);

      //  getWindow().getDecorView().performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

    }

    public void play(View v){

        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        try {
            if (BtnClick.isPlaying()) {
                BtnClick.stop();
                BtnClick.release();
                BtnClick = MediaPlayer.create(this, R.raw.button_sound1);
            } BtnClick.start();
        } catch(Exception e) { e.printStackTrace(); }
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
    }

    public void support(View v){
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        try {
            if (BtnClick.isPlaying()) {
                BtnClick.stop();
                BtnClick.release();
                BtnClick = MediaPlayer.create(this, R.raw.button_sound1);
            } BtnClick.start();
        } catch(Exception e) { e.printStackTrace(); }
        Intent intent = new Intent(this, SupportActivity.class);
        startActivity(intent);
    }

    public void abtDoge(View v){
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        try {
            if (BtnClick.isPlaying()) {
                BtnClick.stop();
                BtnClick.release();
                BtnClick = MediaPlayer.create(this, R.raw.button_sound1);
            } BtnClick.start();
        } catch(Exception e) { e.printStackTrace(); }
        Intent intent = new Intent(this, AboutDoge.class);
        startActivity(intent);

    }
}