package com.mu.compet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
        ToastUtil.show(NewWriteActivity.this, "갤러리");

    }

    public void addCameraClicked(View view) {
        // 카메라를 통한 이미지 가져오기
        ToastUtil.show(NewWriteActivity.this, "카메라");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_new_write_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_complete) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
