package com.kin.pheramorproject.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

    public static final String USER_PARCEL = "User";

    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public int heightFeet;
    public int heightInches;
    public String zipCode;
    public String gender;
    public String dateOfBirth;
    public String race;
    public String religion;
    public String interestGender;
    public int interestMinAge;
    public int interestMaxAge;
    public String profilePicture;

    public User () {}

    public User (Parcel in) {
        email = in.readString();
        password = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        heightFeet = in.readInt();
        heightInches = in.readInt();
        zipCode = in.readString();
        gender = in.readString();
        dateOfBirth = in.readString();
        race = in.readString();
        religion = in.readString();
        interestGender = in.readString();
        interestMinAge = in.readInt();
        interestMaxAge = in.readInt();
        profilePicture = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(heightFeet);
        dest.writeInt(heightInches);
        dest.writeString(zipCode);
        dest.writeString(gender);
        dest.writeString(dateOfBirth);
        dest.writeString(race);
        dest.writeString(religion);
        dest.writeString(interestGender);
        dest.writeInt(interestMinAge);
        dest.writeInt(interestMaxAge);
        dest.writeString(profilePicture);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
