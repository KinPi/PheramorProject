package com.kin.pheramorproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;

import com.kin.pheramorproject.R;

public class SignUpUserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user_profile);

        Slide slide = new Slide(Gravity.TOP);
        slide.setDuration(700);
        slide.excludeTarget(android.R.id.statusBarBackground, true);
        getWindow().setEnterTransition(slide);

    }
}
