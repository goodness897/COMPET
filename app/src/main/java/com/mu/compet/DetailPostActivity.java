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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mu.compet.data.Reply;

import java.util.Random;

public class DetailPostActivity extends AppCompatActivity {

    ListView listView;
    ReplyAdapter mAdapter;
    EditText commentEdit;
    Button writeButton;

    ImageView profileImage;
    TextView nickNameText;
    TextView postDateText;
    ImageView firstImage;
    ImageView secondImage;
    ImageView thirdImage;
    TextView contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        initToolBar(getString(R.string.activity_detail_post));

        listView = (ListView) findViewById(R.id.listView);


        commentEdit = (EditText)findViewById(R.id.edit_content);
        writeButton = (Button)findViewById(R.id.btn_write_comment);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random r = new Random();
                String comment = commentEdit.getText().toString();
                commentEdit.setText("");
                Reply reply = new Reply();
                reply.setNickName("name" + r.nextInt(10));
                reply.setDate((r.nextInt(3) + 1) + "시간 전");
                reply.setContent(comment);
                reply.setProfileImage(ContextCompat.getDrawable(DetailPostActivity.this, resIds[1]));
                mAdapter.add(reply);

                listView.smoothScrollToPosition(mAdapter.getCount());
//                listView.setSelection(mAdapter.getCount() - 1);
            }
        });


        View headerView = LayoutInflater.from(this).inflate(R.layout.view_detail_header, null, false);
        initHeader(headerView);
//        setHeaderView(board);

        listView.addHeaderView(headerView);
        mAdapter = new ReplyAdapter();
        listView.setAdapter(mAdapter);

        initData();


    }

    private void initHeader(View v) {
        profileImage = (ImageView)v.findViewById(R.id.image_profile);
        nickNameText = (TextView)v.findViewById(R.id.text_nickname);
        postDateText = (TextView)v.findViewById(R.id.text_post_date);
        firstImage = (ImageView)v.findViewById(R.id.image_post_first_image);
        secondImage = (ImageView)v.findViewById(R.id.image_post_second_image);
        thirdImage = (ImageView)v.findViewById(R.id.image_post_third_image);
        contentText = (TextView)v.findViewById(R.id.text_post_content);

    }

//    private void setHeaderView(Board board) {
//        nickNameText.setText(board.getNickName());
//        postDateText.setText(board.getDate());
//        contentText.setText(board.getPostContent());
//    }

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
