package com.mu.compet.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Mu on 2016-10-28.
 */

public class ListFileRequest extends AbstractRequest<File> {

    Request mRequest;

    private static final String BOARD = "board";
    private static final String FILES = "files";


    public ListFileRequest(Context context, String boardNum) {

        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(BOARD)
                .addPathSegment(boardNum)
                .addPathSegment(FILES)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();




    }

    @Override
    protected Type getType() {
        return new TypeToken<File>() {}.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
