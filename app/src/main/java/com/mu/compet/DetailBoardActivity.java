package com.mu.compet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mu.compet.data.Board;
import com.mu.compet.data.ListData;
import com.mu.compet.data.Reply;
import com.mu.compet.data.ResultMessage;
import com.mu.compet.manager.NetworkManager;
import com.mu.compet.manager.NetworkRequest;
import com.mu.compet.request.AddReplyRequest;
import com.mu.compet.request.DeleteBoardRequest;
import com.mu.compet.request.ListReplyRequest;

import java.util.Arrays;

public class DetailBoardActivity extends AppCompatActivity {

    private String TAG = "DetailBoardActivity";

    ListView listView;
    ReplyAdapter mAdapter;
    EditText replyEdit;
    Button writeButton;

    ImageView profileImage;
    TextView nickNameText;
    TextView postDateText;
    ImageView firstImage;
    ImageView secondImage;
    ImageView thirdImage;
    TextView contentText;

    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        initToolBar(getString(R.string.activity_detail_post));

        Intent intent = getIntent();
        board = (Board) intent.getSerializableExtra("board");


        listView = (ListView) findViewById(R.id.listView);


        replyEdit = (EditText) findViewById(R.id.edit_content);
        writeButton = (Button) findViewById(R.id.btn_write_comment);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String replyContent = replyEdit.getText().toString();
                String boardNum = String.valueOf(board.getBoardNum());
                AddReplyRequest request = new AddReplyRequest(v.getContext(), boardNum, replyContent);
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultMessage>() {
                    @Override
                    public void onSuccess(NetworkRequest<ResultMessage> request, ResultMessage result) {
                        Log.d("DetailBoardActivity", "성공 : " + result.getMessage());
                        listReplyRequest();
                    }

                    @Override
                    public void onFail(NetworkRequest<ResultMessage> request, int errorCode, String errorMessage, Throwable e) {
                        Log.d("DetailBoardActivity", "실패 : " + errorMessage);

                    }
                });

                listView.smoothScrollToPosition(mAdapter.getCount());
//                listView.setSelection(mAdapter.getCount() - 1);
            }
        });


        View headerView = LayoutInflater.from(this).inflate(R.layout.view_detail_header, null, false);
        initHeader(headerView, board);

        listView.addHeaderView(headerView);
        mAdapter = new ReplyAdapter();
        listView.setAdapter(mAdapter);

        initData();


    }

    private void initHeader(View v, Board board) {
        profileImage = (ImageView) v.findViewById(R.id.image_profile);
        nickNameText = (TextView) v.findViewById(R.id.text_nickname);
        postDateText = (TextView) v.findViewById(R.id.text_post_date);
        firstImage = (ImageView) v.findViewById(R.id.image_post_first_image);
        secondImage = (ImageView) v.findViewById(R.id.image_post_second_image);
        thirdImage = (ImageView) v.findViewById(R.id.image_post_third_image);
        contentText = (TextView) v.findViewById(R.id.text_post_content);

        setHeaderView(board);

    }

    private void setHeaderView(Board board) {
        nickNameText.setText(board.getUserNick());
        postDateText.setText(board.getBoardRegDate());
        firstImage.setImageURI(Uri.parse(board.getBoardFirstImg()));
        contentText.setText(board.getBoardContent());
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

            Intent intent = new Intent(DetailBoardActivity.this, UpdatePostActivity.class);
            intent.putExtra("board", board);
            startActivity(intent);


        } else if (id == R.id.action_delete) {
            String boardNum = String.valueOf(board.getBoardNum());
            DeleteBoardRequest request = new DeleteBoardRequest(this, boardNum);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultMessage>() {
                @Override
                public void onSuccess(NetworkRequest<ResultMessage> request, ResultMessage result) {
                    Log.d(TAG, result.getMessage());
                }

                @Override
                public void onFail(NetworkRequest<ResultMessage> request, int errorCode, String errorMessage, Throwable e) {
                    Log.d(TAG, errorMessage);

                }
            });
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

        listReplyRequest();


    }

    private void listReplyRequest() {

        String boardNum = String.valueOf(board.getBoardNum());
        String pageNum = "1";
        String lastReplyNum = "1";
        ListReplyRequest request = new ListReplyRequest(this, boardNum, pageNum, lastReplyNum);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ListData<Reply>>() {
            @Override
            public void onSuccess(NetworkRequest<ListData<Reply>> request, ListData<Reply> result) {

                Log.d("DetailBoardActivity", "성공 : " + result.getMessage());
                Reply[] reply = result.getData();

                mAdapter.addAll(Arrays.asList(reply));

            }

            @Override
            public void onFail(NetworkRequest<ListData<Reply>> request, int errorCode, String errorMessage, Throwable e) {
                Log.d("DetailBoardActivity", "실패 : " + errorMessage);

            }
        });
    }
}
