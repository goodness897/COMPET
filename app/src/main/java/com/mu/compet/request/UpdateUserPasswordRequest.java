package com.mu.compet.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.mu.compet.data.User;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;

/**
 * Created by jeon on 2016-09-04.
 */
public class UpdateUserPasswordRequest extends AbstractRequest<User> {
    Request mRequest;

    MediaType jpeg = MediaType.parse("image/jpeg");
    private final static String PROFILE = "profile";
    private final static String USER_PASS = "userPass";
    private final static String USER_NICKNAME = "userNick";
    private final static String USER_FILE = "userFile";

    public UpdateUserPasswordRequest(Context context, String userPass) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(PROFILE)
                .build();

        MultipartBody.Builder body = new MultipartBody.Builder()
                .addFormDataPart(USER_PASS, userPass);
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
