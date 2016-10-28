package com.mu.compet.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.mu.compet.data.User;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by jeon on 2016-09-04.
 */
public class UpdateUserRequest extends AbstractRequest<User> {
    Request mRequest;

    MediaType jpeg = MediaType.parse("image/jpeg");
    private final static String PROFILE = "profile";
    private final static String USER_PASS = "userPass";
    private final static String USER_NICKNAME = "userNick";
    private final static String USER_FILE = "userFile";

    public UpdateUserRequest(Context context, String userPass, String userNick, File userFile) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(PROFILE)
                .build();

        MultipartBody.Builder body = new MultipartBody.Builder()
                .addFormDataPart(USER_PASS, userPass)
                .addFormDataPart(USER_NICKNAME, userNick);
        if (userFile != null) {
            body.addFormDataPart(USER_FILE, userFile.getName(),
                    RequestBody.create(jpeg, userFile));

        } else {
            body.addFormDataPart(USER_FILE, "");
        }

        MultipartBody requestBody = body.build();

        mRequest = new Request.Builder()
                .url(url)
                .put(requestBody)
                .tag(context)
                .build();

        Log.i("url", mRequest.url().toString());

    }

    @Override
    protected Type getType() {
        return new TypeToken<User>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
