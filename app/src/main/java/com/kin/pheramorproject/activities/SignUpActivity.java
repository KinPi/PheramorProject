package com.kin.pheramorproject.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kin.pheramorproject.R;
import com.kin.pheramorproject.utility.Validator;
import com.kin.pheramorproject.utility.AnimationHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.logo) ImageView logo;
    @BindView(R.id.email_edittext) EditText emailEditText;
    @BindView(R.id.password_edittext) EditText passwordEditText;
    @BindView(R.id.confirm_password_edittext) EditText confirmPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    public void clickSignUp(View view) {
        String email = emailEditText.getText().toString();
        boolean isEmailValid = Validator.validateEmailAddress(email);

        if (!isEmailValid) {
            Toast.makeText(this, getString(R.string.invalid_email_error_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(emailEditText);
            return;
        }

        String password = passwordEditText.getText().toString();
        boolean isPasswordValid = Validator.validatePassword(password);

        if (!isPasswordValid) {
            Toast.makeText(this, getString(R.string.invalid_password_error_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(passwordEditText);
            return;
        }

        String confirmPassword = confirmPasswordEditText.getText().toString();
        boolean arePasswordsMatching = Validator.arePasswordsMatching(password, confirmPassword);

        if (!arePasswordsMatching) {
            Toast.makeText(this, getString(R.string.non_matching_passwords_error_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(confirmPasswordEditText);
            return;
        }

        Intent intent = new Intent(this, SignUpUserProfileActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle());
    }
}
