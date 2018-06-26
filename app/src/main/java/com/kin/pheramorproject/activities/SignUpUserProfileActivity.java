package com.kin.pheramorproject.activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kin.pheramorproject.R;
import com.kin.pheramorproject.utility.AnimationHelper;
import com.kin.pheramorproject.utility.Validator;

import org.apache.commons.text.WordUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpUserProfileActivity extends AppCompatActivity {

    @BindView(R.id.first_name_edittext) EditText firstNameEditText;
    @BindView(R.id.last_name_edittext) EditText lastNameEditText;
    @BindView(R.id.height_feet_edittext) EditText heightFeetEditText;
    @BindView(R.id.height_inches_edittext) EditText heightInchesEditText;
    @BindView(R.id.zip_code_edittext) EditText zipCodeEditText;
    @BindView(R.id.gender_edittext) EditText genderEditText;
    @BindView(R.id.dob_edittext) EditText dobEditText;
    @BindView(R.id.race_edittext) EditText raceEditText;
    @BindView(R.id.religion_edittext) EditText religionEditText;

    private int month;
    private int day;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user_profile);
        setUpTransition();
        ButterKnife.bind(this);

        setUpGender();
        setUpDateOfBirth();
        setUpRace();
        setUpReligion();
    }

    private void setUpTransition () {
        Slide slide = new Slide(Gravity.TOP);
        slide.setDuration(700);
        slide.excludeTarget(android.R.id.statusBarBackground, true);
        getWindow().setEnterTransition(slide);
    }


    private void setUpCategory (EditText editText, int layoutId) {
        editText.setOnTouchListener( (v, e) -> {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                setUpDialog(editText, layoutId).show();
            }
            return true;
        });
    }

    private AlertDialog setUpDialog(EditText genderEditText, int layoutId) {
        ViewGroup alertLayout = (ViewGroup) getLayoutInflater().inflate(layoutId, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(alertLayout);
        AlertDialog dialog = builder.create();

        for (int i = 0; i < alertLayout.getChildCount(); i++) {
            TextView textView = (TextView) alertLayout.getChildAt(i);
            textView.setOnClickListener(v -> {
                genderEditText.setText(textView.getText().toString());
                dialog.dismiss();
            });
        }
        return dialog;
    }

    private void setUpGender () {
        setUpCategory(genderEditText, R.layout.dialog_gender);
    }

    private void setUpRace() {
        setUpCategory(raceEditText, R.layout.dialog_race);
    }

    private void setUpReligion() {
        setUpCategory(religionEditText, R.layout.dialog_religion);
    }

    private void setUpDateOfBirth() {
        final DatePickerDialog.OnDateSetListener mDateSetListener =  (datePicker, year, month, day) -> {
            month = month + 1;
            String date = month + "/" + day + "/" + year;
            this.day = day;
            this.month = month;
            this.year = year;
            dobEditText.setText(date);
        };

        dobEditText.setOnTouchListener((v, e) -> {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignUpUserProfileActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                return true;
            }
            return true;
        });
    }

    public void clickNext(View view) {
        String firstName = firstNameEditText.getText().toString();
        firstName = WordUtils.capitalizeFully(firstName);
        if (!Validator.validateName(firstName)) {
            Toast.makeText(this, getString(R.string.first_name_error_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(firstNameEditText);
            return;
        }

        String lastName = lastNameEditText.getText().toString();
        lastName = WordUtils.capitalize(lastName);
        if (!Validator.validateName(lastName)) {
            Toast.makeText(this, getString(R.string.last_name_error_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(lastNameEditText);
            return;
        }

        int feet, inches;
        try {
            feet = Integer.parseInt(heightFeetEditText.getText().toString());
            inches = Integer.parseInt(heightInchesEditText.getText().toString());
            if (!Validator.validateHeight(feet, inches)) {
                Toast.makeText(this, getString(R.string.bad_height_error_message), Toast.LENGTH_LONG).show();
                AnimationHelper.shake(heightFeetEditText);
                AnimationHelper.shake(heightInchesEditText);
                return;
            }
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.empty_height_error_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(heightFeetEditText);
            AnimationHelper.shake(heightInchesEditText);
            return;
        }

        String zipCode = zipCodeEditText.getText().toString();
        if (!Validator.validateZipCode(zipCode)) {
            Toast.makeText(this, getString(R.string.zip_code_error_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(zipCodeEditText);
            return;
        }

        String gender = genderEditText.getText().toString();
        if (gender.isEmpty()) {
            Toast.makeText(this, getString(R.string.empty_gender_error_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(genderEditText);
            return;
        }

        if (year == 0 || month == 0 || day == 0) {
            Toast.makeText(this, getString(R.string.empty_date_of_birth_error_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(dobEditText);
            return;
        }

        if (!Validator.validateDateOfBirth(year, month, day)) {
            Toast.makeText(this, getString(R.string.date_of_birth_error_message), Toast.LENGTH_LONG).show();
            AnimationHelper.shake(dobEditText);
            return;
        }
    }
}
