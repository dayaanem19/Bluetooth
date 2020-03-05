package com.example.motorcycleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityFourthV2 extends AppCompatActivity {

    private Button enterBtn;
    private TextView textView;

    //to disable the functionality of back button in android phones
    @Override
    public void onBackPressed(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On Screen load
        setContentView(R.layout.activity_fourth_screenv2);

        enterBtn = findViewById(R.id.enterButton);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityFifthV2();
            }
        });

        textView = findViewById(R.id.textView11);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySixthV2();
            }
        });

    }

    public void openActivityFifthV2() {
        Intent intent = new Intent(this, ActivityFifthV2.class);
        startActivity(intent);
    }

    public void openActivitySixthV2() {
        Intent intent = new Intent(this, ActivitySixthV2.class);
        startActivity(intent);
    }
}
