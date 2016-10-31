package com.mu.compet.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.mu.compet.data.ListData;
import com.mu.compet.data.Reply;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Mu on 2016-10-28.
 */

public class ListReplyRequest extends AbstractRequest<ListData<Reply>> {

    Request mRequest;

    private static final String BOARD = "board";
    private static final String REPLYS = "replys";


    public ListReplyRequest(Context context, String boardNum, String pageNum, String lastReplyNum) {

        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(BOARD)
                .addPathSegment(boardNum)
                .addPathSegment(REPLYS)
                .addPathSegment(pageNum)
                .addPathSegment(lastReplyNum)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<ListData<Reply>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
