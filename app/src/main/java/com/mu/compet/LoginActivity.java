package com.mu.compet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    }

    private void showPassword() {

        if (isShowPass) { // 비밀번호 형식보인다면
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
                if (edit.length() > 0) {
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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void signUpButtonClicked(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
