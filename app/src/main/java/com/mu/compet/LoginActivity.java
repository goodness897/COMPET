package com.mu.compet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText inputId;
    EditText inputPassword;
    Button loginButton;
    Button signUpButton;

    String id;
    String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputId = (EditText)findViewById(R.id.edit_id);
        inputPassword = (EditText)findViewById(R.id.edit_password);
        loginButton = (Button)findViewById(R.id.btn_login);
        signUpButton = (Button)findViewById(R.id.btn_sign_up);

        id = inputId.getText().toString();
        passWord = inputPassword.getText().toString();

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
