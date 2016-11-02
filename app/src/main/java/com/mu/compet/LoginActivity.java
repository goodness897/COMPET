package com.mu.compet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mu.compet.data.ResultMessage;
import com.mu.compet.manager.NetworkManager;
import com.mu.compet.manager.NetworkRequest;
import com.mu.compet.request.LoginRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    EditText inputId;
    EditText inputPassword;
    Button loginButton;
    Button signUpButton;
    TextView passwordVisibleView;

    String id;
    String passWord;
    boolean isShowPass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputId = (EditText) findViewById(R.id.edit_id);
        inputPassword = (EditText) findViewById(R.id.edit_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        signUpButton = (Button) findViewById(R.id.btn_sign_up);
        passwordVisibleView = (TextView) findViewById(R.id.text_view_password);

        inputPassword.addTextChangedListener(setTextWatcher());
        passwordVisibleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPassword();
            }
        });

        id = inputId.getText().toString();
        passWord = inputPassword.getText().toString();

        exampleDate();

    }

    private void exampleDate() {

        Date date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd kk:mm", Locale.KOREA);
        Date startDate = null;
        try {
            startDate = dateFormat.parse("2016.10.29 18:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = dateFormat.parse("2016.10.31 23:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long duration = (endDate.getTime() - startDate.getTime()) / 1000;
        if(duration / (60 * 60 * 24) == 0) {
            duration = duration / (60 * 60);
            Log.d("LoginActivity", duration + "시간 전");
        } else {
            duration = duration / (60 * 60 * 24);
            Log.d("LoginActivity", duration + "일 전");

        }
    }

    private void showPassword() {

        if (isShowPass) { // 비밀번호 형식 보인다면
            inputPassword.setInputType(129);
            passwordVisibleView.setText(R.string.Look);
            isShowPass = false;
        } else {
            inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordVisibleView.setText(R.string.password_hide);
            isShowPass = true;

        }
    }

    private TextWatcher setTextWatcher() {

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String edit = s.toString();
                if (edit.length() > 0 && inputId.getText().toString().length() > 0) {
                    loginButton.setBackgroundColor(getResources().getColor(R.color.mainRedColor));
                    loginButton.setEnabled(true);

                } else {
                    loginButton.setBackgroundColor(getResources().getColor(R.color.gray));
                    loginButton.setEnabled(false);
                }


            }
        };
        return textWatcher;
    }

    public void loginButtonClicked(View view) {

        loginRequest();

    }

    public void signUpButtonClicked(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void loginRequest() {
        String userId = inputId.getText().toString();
        String userPass = inputPassword.getText().toString();

        LoginRequest request = new LoginRequest(this, userId, userPass);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultMessage>() {
            @Override
            public void onSuccess(NetworkRequest<ResultMessage> request, ResultMessage result) {
                Log.d("LoginActivity", "성공 : " + result.getMessage());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onFail(NetworkRequest<ResultMessage> request, int errorCode, String errorMessage, Throwable e) {
                Log.d("LoginActivity", "실패 : " + errorMessage);

            }
        });
    }
}
