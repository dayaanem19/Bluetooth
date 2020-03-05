package com.example.motorcycleapp;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class ActivityFifthV2 extends AppCompatActivity {

    LottieAnimationView animation_view;
    Button changeKeyBtn;
    TextView manualDesc;
    TextView resetDesc;
    TextView autoDesc;
    Button ResetBtn;

    private TextView logoutBtn;

    //to disable the functionality of back button in android phones
    @Override
    public void onBackPressed(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On Screen load
        setContentView(R.layout.activity_fifth_screenv2);


        logoutBtn = findViewById(R.id.textView21);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMainV2();
            }
        });


        animation_view=findViewById(R.id.animation_view);
        animation_view.pauseAnimation(); //this will stop animation to play automatically.
        changeKeyBtn=findViewById(R.id.changeKeyBtn);
        manualDesc=findViewById(R.id.manualDesc);
        ResetBtn=findViewById(R.id.ResetBtn);
        resetDesc=findViewById(R.id.resetDesc);
        autoDesc=findViewById(R.id.autoDesc);

//        //showing the auto check mark
//        ResetBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                animation_view.setAnimation("automatic_check.json");
//                animation_view.playAnimation();
//
//                //showing the textview description of auto change
//                animation_view.addAnimatorListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//                        manualDesc.setVisibility(View.INVISIBLE);
//                        resetDesc.setVisibility(View.INVISIBLE);
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        autoDesc.setVisibility(View.VISIBLE);
//                        if (autoDesc.getVisibility() == View.VISIBLE) {
//                            manualDesc.setVisibility(View.INVISIBLE);
//                            resetDesc.setVisibility(View.INVISIBLE);
//                        }
//                        else {
//                            autoDesc.setVisibility(View.VISIBLE);
//                        }
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//                    }
//                });
//            }
//        });


        //show the manual change of checkmark
        changeKeyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation_view.setAnimation("manual_check.json");
                animation_view.playAnimation();//this will play the animation when clicked

                //showing the textview description of change key button
                animation_view.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        resetDesc.setVisibility(View.INVISIBLE);
                        autoDesc.setVisibility(View.INVISIBLE);
                        manualDesc.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        manualDesc.setVisibility(View.VISIBLE);
                        if (manualDesc.getVisibility() == View.VISIBLE) {
                            resetDesc.setVisibility(View.INVISIBLE);
                            autoDesc.setVisibility(View.INVISIBLE);
                        }
                        else {
                            resetDesc.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
            }
        });


        //showing the reset check mark
        ResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation_view.setAnimation("reset_check.json");
                animation_view.playAnimation();

                //        //showing the textview description of reset key button
                animation_view.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        manualDesc.setVisibility(View.INVISIBLE);
                        autoDesc.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        resetDesc.setVisibility(View.VISIBLE);
                        if (resetDesc.getVisibility() == View.VISIBLE) {
                            manualDesc.setVisibility(View.INVISIBLE);
                            autoDesc.setVisibility(View.INVISIBLE);
                        }
                        else {
                            manualDesc.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
            }
        });




    }

    public void openActivityMainV2() {
        Intent intent = new Intent(this, ActivityMainV2.class);
        startActivity(intent);

        // NOTE: To success fully connect to screen, declare class name in AndroidManifest.xml
    }



}
