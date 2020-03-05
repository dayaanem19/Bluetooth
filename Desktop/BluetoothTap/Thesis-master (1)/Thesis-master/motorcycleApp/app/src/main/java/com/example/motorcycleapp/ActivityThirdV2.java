package com.example.motorcycleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityThirdV2 extends AppCompatActivity {
    private Button submitButton;

    //to disable the functionality of back button in android phones
    @Override
    public void onBackPressed(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On Screen load
        setContentView(R.layout.activity_third_screenv2);

        submitButton = findViewById(R.id.resetBtn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityThird21();
            }
        });
    }

    public void openActivityThird21() {
        Intent intent = new Intent(this, ActivityThird21.class);
        startActivity(intent);
    }
}
