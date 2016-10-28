package com.mu.compet.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.mu.compet.data.Board;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-29.
 */
//

//        게시물 검색(GET)
//        pagNum(int) 총가져올 게시글의 개수 lastBoardNum 마지막으로 받은 게시글의 고유번호
//
//        예시) /boards/{pageNum},{lastBoardNum}

public class SearchBoardRequest extends AbstractRequest<Board> {

    Request mRequest;
    private final static String BOARDS = "boards";
    private final static String SEARCH_TYPE = "searchType";
    private final static String KEYWORD = "keyWord";

    public SearchBoardRequest(Context context, String searchType, String keyWord) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(BOARDS)
                .addQueryParameter(SEARCH_TYPE, searchType)
                .addQueryParameter(KEYWORD, keyWord)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
        Log.i("url", mRequest.url().toString());
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }

    @Override
    protected Type getType() {
        return new TypeToken<Board>() {
        }.getType();
    }
}
