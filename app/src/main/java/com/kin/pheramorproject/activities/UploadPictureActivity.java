package com.kin.pheramorproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kin.pheramorproject.R;
import com.kin.pheramorproject.model.User;
import com.kin.pheramorproject.utility.AnimationHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class UploadPictureActivity extends AppCompatActivity {

    @BindView(R.id.profile_picture_layout) RelativeLayout profilePictureLayout;
    @BindView(R.id.profile_picture) ImageView profilePictureImageView;
    @BindView(R.id.upload_profile_picture_btn) Button uploadProfilePictureButton;

    private static final int PICK_IMAGE = 1113;
    private boolean isPictureSelected = false;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_picture);
        ButterKnife.bind(this);

        AnimationHelper.setUpTransition(this);

        Glide.with(this)
             .load(R.drawable.default_profile_picture)
             .bitmapTransform(new CropCircleTransformation(this))
             .into(profilePictureImageView);


        user = getIntent().getParcelableExtra(User.USER_PARCEL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            Glide.with(this)
                    .load(selectedImage)
                    .bitmapTransform(new CropCircleTransformation(this))
                    .into(profilePictureImageView);
            isPictureSelected = true;
            user.profilePicture = selectedImage.toString();
        }
    }

    public void clickUploadProfilePicture(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    public void clickNext(View view) {
        if (!isPictureSelected) {
            Toast.makeText(this, getString(R.string.profile_picture_not_selected_message), Toast.LENGTH_LONG).show();;
            AnimationHelper.shake(uploadProfilePictureButton);
            return;
        }

        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra(User.USER_PARCEL, user);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle());
    }
}
