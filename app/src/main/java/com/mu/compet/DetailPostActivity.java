package com.mu.compet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mu.compet.data.Reply;

import java.util.Random;

public class DetailPostActivity extends AppCompatActivity {

    ListView listView;
    ReplyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        initToolBar(getString(R.string.activity_detail_post));

        listView = (ListView) findViewById(R.id.listView);
        View headerView = LayoutInflater.from(this).inflate(R.layout.view_detail_header, null, false);
        listView.addHeaderView(headerView);
        mAdapter = new ReplyAdapter();
        listView.setAdapter(mAdapter);

        initData();


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_ud_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_update) {

            Intent intent = new Intent(DetailPostActivity.this, UpdatePostActivity.class);
            startActivity(intent);


        } else if (id == R.id.action_delete) {
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    int[] resIds = {R.drawable.sample_profile01, R.drawable.sample_profile02
            , R.drawable.sample_profile03};

    String sampleString = "this is sample comment ";

    private void initData() {

        Random r = new Random();

        for (int i = 0; i < 3; i++) {

            Reply reply = new Reply();
            reply.setNickName("name" + i);
            reply.setDate((r.nextInt(3) + 1) + "시간 전");
            reply.setContent(sampleString);
            reply.setProfileImage(ContextCompat.getDrawable(this, resIds[i % resIds.length]));
            mAdapter.add(reply);
        }
    }
}
