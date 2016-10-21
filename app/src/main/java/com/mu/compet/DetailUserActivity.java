package com.mu.compet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailUserActivity extends AppCompatActivity {

    TextView nickNameView;
    TextView postCountView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        nickNameView = (TextView) findViewById(R.id.text_nickname);
        postCountView = (TextView) findViewById(R.id.text_post_count);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("NickName");

        nickNameView.setText(nickName);

        initToolBar("User ID");


    }

    private void initToolBar(String title) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView titleText = (TextView) findViewById(R.id.toolbar_title);
        titleText.setText(title);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
