package com.kin.pheramorproject.task;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.kin.pheramorproject.model.User;
import com.kin.pheramorproject.utility.FileChooser;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadProfilePictureTask extends AsyncTask<User, Void, Boolean> {

    private static final MediaType MEDIA_TYPE_IMAGE = MediaType.parse("image/jpeg");
    private Activity activity;

    public UploadProfilePictureTask (Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(User... users) {
        try {
            User user = users[0];
            /* Need to do this to get the absolute path of the file. The path in user.profilePicture
             * is for the content provider uri.
             * e.g. content://com.android.providers.media.documents/document/image%3A20545
             */
            Uri uri = Uri.parse(user.profilePicture);
            File file = new File(FileChooser.getPath(activity, uri));
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image", file.getName(),
                            RequestBody.create(MEDIA_TYPE_IMAGE, file))
                    .build();

            Request request = new Request.Builder()
                    .header("Authorization", "some access token")
                    .url(Endpoints.endpoint)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();

        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
