package com.mu.compet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    ImageView imageProfile;
    ImageView imageCamera;

    EditText editId;
    EditText editNickName;
    EditText editPassword;
    EditText editPasswordCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initToolBar(getString(R.string.activity_sign_up));

        imageProfile = (ImageView) findViewById(R.id.image_profile);
        imageCamera = (ImageView) findViewById(R.id.image_camera);

        editId = (EditText) findViewById(R.id.edit_id);
        editNickName = (EditText) findViewById(R.id.edit_nickname);
        editPassword = (EditText) findViewById(R.id.edit_password);
        editPasswordCheck = (EditText) findViewById(R.id.edit_password_check);

        imageCamera.bringToFront();

    }

    private void initToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        TextView titleText = (TextView) findViewById(R.id.toolbar_title);
        titleText.setText(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void loginCompleteSignUp(View view) {

        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void duplicateIdCheckClicked(View view) {
        // id 중복 요청
    }

    public void duplicateNickNameCheckClicked(View view) {
        // nickname 중복 요청
    }
}
