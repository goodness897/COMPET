package com.mu.compet.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.mu.compet.data.ResultMessage;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-29.
 */
//

//        userId: 유저의 아이디  (필수)
//        userPass: 유저의 비밀번호 (필수)
//        userNick : 유저의 이름
//        userFile: 유저의 프로파일 이미지  파일(image/*)
//
//        예시) /user
public class SignUpRequest extends AbstractRequest<ResultMessage> {

    Request mRequest;

    MediaType jpeg = MediaType.parse("image/jpeg");

    final static String USER = "user";
    final static String USER_ID = "userId";
    final static String USER_PASSWORD = "userPass";
    final static String USER_NICKNAME = "userNick";
    final static String USER_FILE = "userFile";

    public SignUpRequest(Context context, String userId, String userPass, String userNick, File userFile) {

        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(USER)
                .build();

        MultipartBody.Builder body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(USER_ID, userId)
                .addFormDataPart(USER_PASSWORD, userPass)
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
                .post(requestBody)
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
        return new TypeToken<ResultMessage>() {
        }.getType();
    }
}
