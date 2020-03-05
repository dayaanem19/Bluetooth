package com.example.motorcycleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityMainV2 extends AppCompatActivity {

    private Button signUpBtn;
    private TextView forgotPassword;
    private Button loginButton;
    private ImageView backBtn;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On Screen load
        setContentView(R.layout.activity_mainv2);

        signUpBtn = findViewById(R.id.signUpbutton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySecond21();
            }
        });

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           openActivityFirst();
                                       }
                                   });

        forgotPassword = findViewById(R.id.textView2);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySeventh2();
            }
        });

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityFourthV2();
            }
        });

    }

    //to disable the functionality of back button in android phones
    @Override
    public void onBackPressed(){
    }

    public void openActivitySecond21() {
        Intent intent = new Intent(this, ActivitySecond21.class);
        startActivity(intent);

        // NOTE: To success fully connect to screen, declare class name in AndroidManifest.xml
    }

    public void openActivitySeventh2() {
        Intent intent = new Intent(this, ActivitySeventh2.class);
        startActivity(intent);

        // NOTE: To success fully connect to screen, declare class name in AndroidManifest.xml
    }

    public void openActivityFourthV2() {
        Intent intent = new Intent(this, ActivityFourthV2.class);
        startActivity(intent);

        // NOTE: To success fully connect to screen, declare class name in AndroidManifest.xml
    }

    public void openActivityFirst() {
            Intent intent = new Intent(this, ActivityFirst.class);
            startActivity(intent);

            // NOTE: To success fully connect to screen, declare class name in AndroidManifest.xml
        }

}
