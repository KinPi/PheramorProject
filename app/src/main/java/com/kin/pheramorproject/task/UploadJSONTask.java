package com.kin.pheramorproject.task;

import android.os.AsyncTask;
import android.util.Log;

import com.kin.pheramorproject.model.User;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadJSONTask extends AsyncTask<User, Void, Boolean> {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected Boolean doInBackground(User... users) {
        try {
            User user = users[0];
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", user.email);
            jsonObject.put("password", user.password);
            jsonObject.put("firstName", user.firstName);
            jsonObject.put("lastName", user.lastName);
            jsonObject.put("heightFeet", user.heightFeet);
            jsonObject.put("heightInches", user.heightFeet);
            jsonObject.put("zipCode", user.zipCode);
            jsonObject.put("gender", user.gender);
            jsonObject.put("dateOfBirth", user.dateOfBirth);
            jsonObject.put("race", user.race);
            jsonObject.put("religion", user.religion);
            jsonObject.put("interestGender", user.interestGender);
            jsonObject.put("interestMinAge", user.interestMinAge);
            jsonObject.put("interestMaxAge", user.interestMaxAge);

            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, jsonObject.toString());
            Request request = new Request.Builder()
                    .url(Endpoints.endpoint)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
        }

        catch (Exception e) {
            return false;
        }

        return true;
    }
}
