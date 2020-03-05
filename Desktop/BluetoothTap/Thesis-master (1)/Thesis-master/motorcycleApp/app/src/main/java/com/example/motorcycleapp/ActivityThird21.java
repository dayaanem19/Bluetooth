package com.example.motorcycleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityThird21 extends AppCompatActivity {
    private Button submitButton;

    //to disable the functionality of back button in android phones
    @Override
    public void onBackPressed(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On Screen load
        setContentView(R.layout.activity_third_screenv21);

        submitButton = findViewById(R.id.resetBtn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensuccessfulSignUp();
            }
        });

    }

    public void opensuccessfulSignUp() {
        Intent intent = new Intent(this, successfulSignUp.class);
        startActivity(intent);
    }
}
