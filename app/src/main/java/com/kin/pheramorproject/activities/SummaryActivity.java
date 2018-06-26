package com.kin.pheramorproject.activities;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kin.pheramorproject.R;
import com.kin.pheramorproject.adapters.SummaryUserDetailsAdapter;
import com.kin.pheramorproject.model.User;
import com.kin.pheramorproject.utility.AnimationHelper;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class SummaryActivity extends AppCompatActivity {

    @BindView(R.id.profile_picture) ImageView profilePictureImageView;
    @BindView(R.id.full_name_textview) TextView fullNameTextView;
    @BindView(R.id.gender_icon_image) ImageView genderIconImage;
    @BindView(R.id.discrete_scroll_view) DiscreteScrollView discreteScrollView;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        ButterKnife.bind(this);

        AnimationHelper.setUpTransition(this);
        user = getIntent().getParcelableExtra(User.USER_PARCEL);
        Glide.with(this)
                .load(user.profilePicture)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(profilePictureImageView);

        fullNameTextView.setText(user.firstName + " " + user.lastName);

        int genderIconId = (user.gender.equals(getString(R.string.male))) ? R.drawable.male : R.drawable.female;
        genderIconImage.setImageDrawable(getResources().getDrawable(genderIconId));

        List<String> userDetails = Arrays.asList(getResources().getStringArray(R.array.user_details));
        TypedArray imgs = getResources().obtainTypedArray(R.array.user_details_icons);
        List<Integer> userDetailsIcon = new ArrayList<>();
        for (int i = 0; i < userDetails.size(); i++) {
            userDetailsIcon.add(imgs.getResourceId(i, -1));
        }

        discreteScrollView.setOrientation(DSVOrientation.HORIZONTAL);
        discreteScrollView.setAdapter(new SummaryUserDetailsAdapter(this, userDetails, userDetailsIcon, user));
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder().
                            setMinScale(0.8F)
                            .build());



    }
}
