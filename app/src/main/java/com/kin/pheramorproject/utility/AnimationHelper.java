package com.kin.pheramorproject.utility;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kin.pheramorproject.R;

public class AnimationHelper {

    public static void shake (View view) {
        YoYo.with(Techniques.Shake)
                .duration(500)
                .playOn(view);
    }

    public static AnimatorSet scaleIn (Context context, View view) {
        view.setScaleX(0);
        view.setScaleY(0);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.scale_in);
        set.setTarget(view);
        return set;
    }

    public static Animator moveUp (View view) {
        int top = view.getTop();
        int bottom = view.getBottom();
        ObjectAnimator animator = ObjectAnimator.ofInt(view, "bottom", bottom, top);
        animator.setDuration(500);
        return animator;
    }

    public static Animator moveDown (View view) {
        int top = view.getTop();
        int bottom = view.getBottom();
        view.setBottom(top);
        ObjectAnimator animator = ObjectAnimator.ofInt(view, "bottom", top, bottom);
        animator.setDuration(500);
        return animator;
    }

    public static void setUpTransition (Activity activity) {
        Slide slide = new Slide(Gravity.TOP);
        slide.setDuration(700);
        slide.excludeTarget(android.R.id.statusBarBackground, true);
        activity.getWindow().setEnterTransition(slide);
    }

}
