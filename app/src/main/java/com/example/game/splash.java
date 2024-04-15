package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class splash extends AppCompatActivity {



    Animation bot,t;
    TextView ii,ll;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//       image=findViewById(R.id.pic1);
       ii=findViewById(R.id.text);
       ll=findViewById(R.id.ttt);
       t= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.top);
       bot=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bottom);

//       image.setAnimation(t);
       ii.setAnimation(t);
       ll.setAnimation(bot);

        Intent i=new Intent(splash.this, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent i=new Intent(splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },5000);




    }
}