
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScreen extends AppCompatActivity {
    MediaPlayer buttonClick,wrongclick,correctclick;
    int dogecoin_counter=0;
    ImageView img,img0,img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15;
    Random position = new Random();
    TextView scoreupdate;
    int rand_int1;
    int imageId;
    long startTime,endTime,timeElapsed;
    TextView dialog,scorev,highscorev;
    int[] coins = new int[] {R.drawable.bitcoin,R.drawable.dogecoin};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_screen);
        getSupportActionBar().hide();

        buttonClick = MediaPlayer.create(this, R.raw.button_sound1);
        wrongclick = MediaPlayer.create(this, R.raw.button_soundwrong);
        correctclick = MediaPlayer.create(this, R.raw.button_soundright);

        scoreupdate = (TextView) findViewById(R.id.dynamic_score);

        img0=(ImageView) findViewById(R.id.imageView0);
        img1=(ImageView) findViewById(R.id.imageView1);
        img2=(ImageView) findViewById(R.id.imageView2);
        img3=(ImageView) findViewById(R.id.imageView3);
        img4=(ImageView) findViewById(R.id.imageView4);
        img5=(ImageView) findViewById(R.id.imageView5);
        img6=(ImageView) findViewById(R.id.imageView6);
        img7=(ImageView) findViewById(R.id.imageView7);
        img8=(ImageView) findViewById(R.id.imageView8);
        img9=(ImageView) findViewById(R.id.imageView9);
        img10=(ImageView) findViewById(R.id.imageView10);
        img11=(ImageView) findViewById(R.id.imageView11);
        img12=(ImageView) findViewById(R.id.imageView12);
        img13=(ImageView) findViewById(R.id.imageView13);
        img14=(ImageView) findViewById(R.id.imageView14);
        img15=(ImageView) findViewById(R.id.imageView15);


        rand_int1 = position.nextInt(16);
        //dogecoin_counter=0;
        img = (ImageView) findViewById(R.id.framelayout).findViewById(R.id.grid).findViewWithTag(String.valueOf(rand_int1));
        img.setImageResource(coins[1]);
        scoreupdate.setText("Score: "+Integer.toString(dogecoin_counter));

        View.OnClickListener clickListener1 = new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(img.getTag()==v.getTag())
                {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Log.i("gg", String.valueOf(img.getTag()));

                    if (correctclick.isPlaying()) {
                        correctclick.stop();
                        correctclick.release();
                        correctclick = MediaPlayer.create(GameScreen.this, R.raw.button_soundright);
                    }
                    correctclick.start();
                    dogecoin_counter=1;
                    scoreupdate.setText("Score: "+Integer.toString(dogecoin_counter));
                    GenerateNewimg();
                }


            }

        };
        img0.setOnClickListener(clickListener1);
        img1.setOnClickListener(clickListener1);
        img2.setOnClickListener(clickListener1);
        img3.setOnClickListener(clickListener1);
        img4.setOnClickListener(clickListener1);
        img5.setOnClickListener(clickListener1);
        img6.setOnClickListener(clickListener1);
        img7.setOnClickListener(clickListener1);
        img8.setOnClickListener(clickListener1);
        img9.setOnClickListener(clickListener1);
        img10.setOnClickListener(clickListener1);
        img11.setOnClickListener(clickListener1);
        img12.setOnClickListener(clickListener1);
        img13.setOnClickListener(clickListener1);
        img14.setOnClickListener(clickListener1);
        img15.setOnClickListener(clickListener1);



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

    RandomImgSetter();

        if(imageId==1){
            startTime = System.currentTimeMillis();
        }

            new CountDownTimer(1500, 1000) {
                public void onTick(long millisecondsuntilDone) {
                    //Countdown is counting down(every sec)
                    Log.i("Seconds Left", String.valueOf(millisecondsuntilDone / 1000));

                    View.OnClickListener clickListener = new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                            if(v.getTag()==img.getTag() && imageId==1)
                            {

                                cancel();
                                if (correctclick.isPlaying()) {
                            correctclick.stop();
                            correctclick.release();
                            correctclick = MediaPlayer.create(GameScreen.this, R.raw.button_soundright);
                        } correctclick.start();

                        dogecoin_counter=dogecoin_counter+1;
                        scoreupdate.setText("Score: "+Integer.toString(dogecoin_counter));
                        Toast.makeText(GameScreen.this, "You clicked on DogeCoin", Toast.LENGTH_LONG).show();
                        img.setImageBitmap(null);
                        GenerateNewimg();


                        }
                            else{
                                if (wrongclick.isPlaying()) {
                                    wrongclick.stop();
                                    wrongclick.release();
                                    wrongclick = MediaPlayer.create(GameScreen.this, R.raw.button_soundwrong);
                                } wrongclick.start();

                                Toast.makeText(GameScreen.this, "You Lose", Toast.LENGTH_SHORT).show();
                                Log.i("hoo","superrr");
                                Log.i("tag of clicked view",String.valueOf(v.getTag()));
                                Log.i("Tag of img",String.valueOf(img.getTag()));

                                cancel();
                                loseMessage();
                                DisableClickable();


                            }
                        }
                    };
                    img0.setOnClickListener(clickListener);
                    img1.setOnClickListener(clickListener);
                    img2.setOnClickListener(clickListener);
                    img3.setOnClickListener(clickListener);
                    img4.setOnClickListener(clickListener);
                    img5.setOnClickListener(clickListener);
                    img6.setOnClickListener(clickListener);
                    img7.setOnClickListener(clickListener);
                    img8.setOnClickListener(clickListener);
                    img9.setOnClickListener(clickListener);
                    img10.setOnClickListener(clickListener);
                    img11.setOnClickListener(clickListener);
                    img12.setOnClickListener(clickListener);
                    img13.setOnClickListener(clickListener);
                    img14.setOnClickListener(clickListener);
                    img15.setOnClickListener(clickListener);


                }

                public void onFinish() {
                    //happens after countdown is finished
                    Log.i("Done", "Countdown Timer finished");
                    if(imageId!=1){
                        GenerateNewimg();
                    }
                    else{
                        loseMessage();
                        DisableClickable();

                    }

                }
            }.start();

    }

    public void DisableClickable(){
        img0.setClickable(false);
        img1.setClickable(false);
        img2.setClickable(false);
        img3.setClickable(false);
        img4.setClickable(false);
        img5.setClickable(false);
        img6.setClickable(false);
        img7.setClickable(false);
        img8.setClickable(false);
        img9.setClickable(false);
        img10.setClickable(false);
        img11.setClickable(false);
        img12.setClickable(false);
        img13.setClickable(false);
        img14.setClickable(false);
        img15.setClickable(false);
    }

    public void RandomImgSetter(){
        img.setImageBitmap(null);
        rand_int1 = position.nextInt(16);
        img = (ImageView) findViewById(R.id.framelayout).findViewById(R.id.grid).findViewWithTag(String.valueOf(rand_int1));
        imageId = (int)(Math.random() * coins.length);
        img.setImageResource(coins[imageId]);
    }

    public void loseMessage(){
        SharedPreferences prefs = getSharedPreferences("HighS", MODE_PRIVATE);

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
        scoreupdate.setText("Score: "+Integer.toString(dogecoin_counter));
        GenerateNewimg();



    }




}