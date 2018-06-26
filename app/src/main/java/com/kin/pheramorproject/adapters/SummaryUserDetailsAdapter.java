package com.kin.pheramorproject.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kin.pheramorproject.R;
import com.kin.pheramorproject.model.User;

import java.util.List;

public class SummaryUserDetailsAdapter extends RecyclerView.Adapter<SummaryUserDetailsAdapter.ViewHolder> {

    private Activity activity;
    private List<String> userDetails;
    private List<Integer> userDetailsIcon;
    private User user;

    public SummaryUserDetailsAdapter (Activity activity, List<String> userDetails, List<Integer> userDetailsIcon, User user) {
        this.activity = activity;
        this.userDetails = userDetails;
        this.userDetailsIcon = userDetailsIcon;
        this.user = user;
    }

    @NonNull
    @Override
    public SummaryUserDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.summary_card_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryUserDetailsAdapter.ViewHolder holder, int position) {
        TextView titleTextView = holder.titleTextView;
        ImageView titleImageIcon = holder.titleImageIcon;
        TextView userAnswersTextView = holder.userAnswersTextView;

        String category = userDetails.get(position);
        titleTextView.setText(category);
        titleImageIcon.setImageDrawable(activity.getResources().getDrawable(userDetailsIcon.get(position)));

        String answer;
        switch (category) {
            case "Height":
                            answer = String.format("%d\" %d'", user.heightFeet, user.heightInches);
                            break;
            case "Date Of Birth":
                            answer = user.dateOfBirth;
                            break;
            case "Zip Code":
                            answer = user.zipCode;
                            break;
            case "Race":
                            answer = user.race;
                            break;
            case "Religion":
                            answer = user.religion;
                            break;
            case "Preference":
                            String unformattedString = "%s Gender(s)\nBetween the age of %d and %d.";
                            answer = String.format(unformattedString, user.interestGender, user.interestMinAge, user.interestMaxAge);
                            break;
            default:
                            answer = "";
                            break;
        }
        if (answer.length() > 20) {
            userAnswersTextView.setTextSize(18F);
        }
        else if (userAnswersTextView.getTextSize() != 24F) {
            userAnswersTextView.setTextSize(24F);
        }
        userAnswersTextView.setText(answer);
    }

    @Override
    public int getItemCount() {
        return userDetails.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView titleImageIcon;
        TextView userAnswersTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_textview);
            titleImageIcon = (ImageView) itemView.findViewById(R.id.title_image_icon);
            userAnswersTextView = (TextView) itemView.findViewById(R.id.user_answers_textview);
        }
    }
}
