package com.kin.pheramorproject.utility;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class CategoryDialogCreator {

    public static void setUpCategory (Activity activity, EditText editText, int layoutId) {
        editText.setOnTouchListener( (v, e) -> {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                setUpDialog(activity, editText, layoutId).show();
            }
            return true;
        });
    }

    public static AlertDialog setUpDialog(Activity activity, EditText genderEditText, int layoutId) {
        ViewGroup alertLayout = (ViewGroup) activity.getLayoutInflater().inflate(layoutId, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
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
}
