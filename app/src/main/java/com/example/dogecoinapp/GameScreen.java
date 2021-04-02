
package com.example.dogecoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameScreen extends AppCompatActivity {
    MediaPlayer buttonClick,wrongclick,correctclick;
    int dogecoin_counter=0;
    ImageView img;
    Random position = new Random();
    int rand_int1;
    int imageId;
    long startTime,endTime,timeElapsed;
    TextView dialog,scorev,highscorev;
    int[] coins = new int[] {R.drawable.bitcoin, R.drawable.ethereum, R.drawable.dogecoin};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_screen);
        getSupportActionBar().hide();

        buttonClick = MediaPlayer.create(this, R.raw.button_sound1);
        wrongclick = MediaPlayer.create(this, R.raw.button_soundwrong);
        correctclick = MediaPlayer.create(this, R.raw.button_soundright);

        //Log.i("hh",String.valueOf(rand_int1));
        rand_int1 = position.nextInt(16);
        //dogecoin_counter=0;
        img = (ImageView) findViewById(R.id.framelayout).findViewById(R.id.grid).findViewWithTag(String.valueOf(rand_int1));
        img.setImageResource(coins[2]);
//        img.setClickable(true);
//        img.setEnabled(true);
        img.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
//                img.setClickable(false);
//                img.setEnabled(false);

                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Log.i("gg", String.valueOf(img.getTag()));
                if (img.getTag() != v.getTag()) {
                    loseMessage();
                } else {
                    if (correctclick.isPlaying()) {
                        correctclick.stop();
                        correctclick.release();
                        correctclick = MediaPlayer.create(GameScreen.this, R.raw.button_soundright);
                    }
                    correctclick.start();
                    img.setImageBitmap(null);
                    GenerateNewimg();
                }
            }
        });


    }


    public void OnClickingBack(View v){
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        if (buttonClick.isPlaying()) {
            buttonClick.stop();
            buttonClick.release();
            buttonClick = MediaPlayer.create(GameScreen.this, R.raw.button_sound1);
        } buttonClick.start();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void GenerateNewimg(){

        rand_int1 = position.nextInt(16);
        img = (ImageView) findViewById(R.id.framelayout).findViewById(R.id.grid).findViewWithTag(String.valueOf(rand_int1));
        imageId = (int)(Math.random() * coins.length);
        img.setImageResource(coins[imageId]);

        if(imageId==2){
            startTime = System.currentTimeMillis();
        }

        if(imageId!=2){


            new CountDownTimer(1000, 1000) {
                public void onTick(long millisecondsuntilDone) {
                    //Countdown is counting down(every sec)
                    Log.i("Seconds Left", String.valueOf(millisecondsuntilDone / 1000));

                    img.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                            if(img.getTag()!=v.getTag()){
                                loseMessage();
                            }
                            else {
//                            if (wrongclick.isPlaying()) {
//                                wrongclick.stop();
//                                wrongclick.release();
//                                wrongclick = MediaPlayer.create(GameScreen.this, R.raw.button_soundwrong);
//                            }
                                wrongclick.start();
                                wrongclick.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer wrongclick) {
                                        //code
                                        wrongclick.release();
                                    }
                                });
                                Toast.makeText(GameScreen.this, "You Lose", Toast.LENGTH_SHORT).show();
                                cancel();
                                loseMessage();
                            }
                        }
                    });

                }

                public void onFinish() {
                    //happens after countdown is finished
                    Log.i("Done", "Countdown Timer finished");
                    img.setImageBitmap(null);
                    GenerateNewimg();
                }
            }.start();
        }
        //Log.i("hh",String.valueOf(imageId));
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                if (correctclick.isPlaying()) {
                    correctclick.stop();
                    correctclick.release();
                    correctclick = MediaPlayer.create(GameScreen.this, R.raw.button_soundright);
                } correctclick.start();

                if(v==img && imageId==2){
                    endTime = System.currentTimeMillis();

                    timeElapsed = endTime - startTime;
                    if(timeElapsed>=1000){

                        Toast.makeText(GameScreen.this, "You Took More than 1 Sec", Toast.LENGTH_LONG).show();
                        loseMessage();

                    }
                else{

                    dogecoin_counter=dogecoin_counter+1;
                    Toast.makeText(GameScreen.this, "You clicked on DogeCoin", Toast.LENGTH_LONG).show();
                img.setImageBitmap(null);
                GenerateNewimg();}
                }
//                else if(v==img && imageId!=2) {
//                    Toast.makeText(GameScreen.this, "You Lost", Toast.LENGTH_LONG).show();
//                    dialog = (TextView) findViewById(R.id.messagetext);
//                    dialog.setText("You Failed to Reach The Moon");
//                    scorev=(TextView) findViewById(R.id.ScoreView);
//                    scorev.setText("Score: "+Integer.toString(dogecoin_counter));
//                    LinearLayout layout = (LinearLayout) findViewById(R.id.messagelayout);
//                    layout.setVisibility(View.VISIBLE);
//                }


            }
        });

    }


    public void loseMessage(){
        SharedPreferences prefs = getSharedPreferences("HighS",
                MODE_PRIVATE);

        int finalhighscore = prefs.getInt("Highv",dogecoin_counter);

        if(finalhighscore<=dogecoin_counter){
            finalhighscore=dogecoin_counter;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("Highv",finalhighscore);
            editor.commit();

        }



        dialog = (TextView) findViewById(R.id.messagetext);
        dialog.setText("You Failed to Reach The Moon");
        scorev=(TextView) findViewById(R.id.ScoreView);
        highscorev=(TextView) findViewById(R.id.HighScoreView);
        scorev.setText("Score: "+Integer.toString(dogecoin_counter));
        highscorev.setText("HighScore: "+Integer.toString(finalhighscore));
        LinearLayout layout = (LinearLayout) findViewById(R.id.messagelayout);
        layout.setVisibility(View.VISIBLE);
        FrameLayout bTnlayout = (FrameLayout) findViewById(R.id.buttonslayout);
        bTnlayout.setVisibility(View.VISIBLE);

    }

    public void playAgain(View v){
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        if (buttonClick.isPlaying()) {
            buttonClick.stop();
            buttonClick.release();
            buttonClick = MediaPlayer.create(GameScreen.this, R.raw.button_sound1);
        } buttonClick.start();
        LinearLayout layout = (LinearLayout) findViewById(R.id.messagelayout);
        layout.setVisibility(View.INVISIBLE);
        FrameLayout bTnlayout = (FrameLayout) findViewById(R.id.buttonslayout);
        bTnlayout.setVisibility(View.INVISIBLE);
        img.setImageBitmap(null);
        dogecoin_counter=0;
        GenerateNewimg();



    }




}