package com.kin.pheramorproject.activities;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kin.pheramorproject.R;
import com.kin.pheramorproject.utility.AnimationHelper;
import com.kin.pheramorproject.utility.CategoryDialogCreator;
import com.kin.pheramorproject.utility.Validator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpUserInterestActivity extends AppCompatActivity {

    @BindView(R.id.interest_gender_edittext) EditText interestGenderEditText;
    @BindView(R.id.interest_age_min_edittext) EditText interestAgeMinEditText;
    @BindView(R.id.interest_age_max_edittext) EditText interestAgeMaxEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user_interest);
        AnimationHelper.setUpTransition(this);
        ButterKnife.bind(this);

        CategoryDialogCreator.setUpCategory(this, interestGenderEditText, R.layout.dialog_interest_gender);
    }


    public void clickNext(View view) {
        String interestGender = interestGenderEditText.getText().toString();
        if (interestGender.isEmpty()) {
            Toast.makeText(this, getString(R.string.empty_interest_gender_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(interestGenderEditText);
            return;
        }

        int minAge, maxAge;
        try {
            minAge = Integer.parseInt(interestAgeMinEditText.getText().toString());
            maxAge = Integer.parseInt(interestAgeMaxEditText.getText().toString());
            if (!Validator.validateInterestAges(minAge, maxAge)) {
                Toast.makeText(this, getString(R.string.bad_interest_age_message), Toast.LENGTH_LONG).show();
                AnimationHelper.shake(interestAgeMinEditText);
                AnimationHelper.shake(interestAgeMaxEditText);
                return;
            }
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.empty_interest_age_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(interestAgeMinEditText);
            AnimationHelper.shake(interestAgeMaxEditText);
            return;
        }


        Intent intent = new Intent(this, UploadPictureActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle());
    }
}
