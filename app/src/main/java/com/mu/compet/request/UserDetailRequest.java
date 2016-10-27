package com.mu.compet.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.mu.compet.data.User;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Mu on 2016-10-27.
 */

// userNum(int) 조회할 유저 번호
// /user/{userNum}

public class UserDetailRequest extends AbstractRequest<User> {
    Request mRequest;

    private final static String USER = "user";
    private final static String USER_NUMBER = "userNum";
    private final static String USER_ID = "userId";
    private final static String USER_NICKNAME = "userNick";

    public UserDetailRequest(Context context, String userNum) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(USER)
                .addPathSegment(userNum)
                .build();
        mRequest = new Request.Builder()
                .url(url)
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
