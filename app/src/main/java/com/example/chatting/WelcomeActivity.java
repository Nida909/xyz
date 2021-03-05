package com.example.chatting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Thread thread=new Thread()
        {
            @Override
            public void run() {
                try{
                    sleep(4000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(WelcomeActivity.this,MainActivity2.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}