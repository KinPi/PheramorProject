package com.kin.pheramorproject.activities;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kin.pheramorproject.R;
import com.kin.pheramorproject.utility.AnimationHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.logo) ImageView logo;
    @BindView(R.id.sign_up_btn) Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void onStart () {
        super.onStart();
        AnimationHelper.scaleIn(this, logo).start();
    }

    public void clickSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        Pair<View, String> pair1 = Pair.create(logo, "logo");
        Pair<View, String> pair2 = Pair.create(signUpButton, "signUp");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair1, pair2);
        startActivity(intent, options.toBundle());
    }

    public void clickLogIn(View view) {
        Toast.makeText(this, getString(R.string.log_in_message), Toast.LENGTH_LONG).show();
    }

}
