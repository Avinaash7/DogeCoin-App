package com.example.dogecoinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class AboutDoge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about_doge);
        getSupportActionBar().hide();

        TextView Line1 = findViewById(R.id.line1);
        TextView Line3 = findViewById(R.id.line3);
        String text = "1.Get a DogeCoin Wallet (Ledger,Exodus)";
        String text3 = "3.Find a Doge exchange (Binance, BitPanda)";
        SpannableString ss = new SpannableString(text);
        SpannableString ss3 = new SpannableString(text3);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

                Uri uri = Uri.parse("https://shop.ledger.com/pages/ledger-nano-x"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


            }
        };

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

                Uri uri = Uri.parse("https://www.exodus.com/download/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


            }
        };
        ClickableSpan clickableSpan3 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

                Uri uri = Uri.parse("https://www.binance.com/en/register?ref=OLAP3NBD");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


            }
        };

        ClickableSpan clickableSpan4 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

                Uri uri = Uri.parse("https://www.bitpanda.com/en");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


            }
        };

        ss.setSpan(clickableSpan,25,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss3.setSpan(clickableSpan3,24,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan2,32,38,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss3.setSpan(clickableSpan4,32,41,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Line1.setText(ss);
       Line3.setText(ss3);
        Line1.setMovementMethod(LinkMovementMethod.getInstance());
        Line3.setMovementMethod(LinkMovementMethod.getInstance());
    }
}