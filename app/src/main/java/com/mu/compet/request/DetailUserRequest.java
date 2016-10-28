package com.mu.compet.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mu.compet.data.User;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by Mu on 2016-10-27.
 */

// userNum(int) 조회할 유저 번호
// /user/{userNum}

public class DetailUserRequest extends AbstractRequest<User> {
    Request mRequest;

    private final static String USER = "user";

    public DetailUserRequest(Context context, String userNum) {
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
    protected User parse(ResponseBody body) throws IOException {
        String text = body.string();
        Gson gson = new Gson();
        User temp = gson.fromJson(text, getType());
        Log.i("result", text);
        return temp;
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
