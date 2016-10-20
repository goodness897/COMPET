package com.mu.compet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class NewWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_write);
        initToolBar(getString(R.string.activity_new_write));


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

    public void addPictureClicked(View view) {
        // 갤러리를 통한 이미지 가져오기


    }

    public void addCameraClicked(View view) {
        // 카메라를 통한 이미지 가져오기

    }

    public void writeCompleteClicked(View view) {
        // 글 작성 완료
        finish();
    }
}
