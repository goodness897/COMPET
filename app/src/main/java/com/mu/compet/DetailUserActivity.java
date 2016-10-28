package com.mu.compet;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mu.compet.data.User;
import com.mu.compet.manager.NetworkManager;
import com.mu.compet.manager.NetworkRequest;
import com.mu.compet.request.DetailUserRequest;

import static com.mu.compet.MyApplication.getContext;

public class DetailUserActivity extends AppCompatActivity {

    TextView nickNameView;
    TextView postCountView;
    ImageView profileImageView;
    private String userId;
    FragmentTabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        nickNameView = (TextView) findViewById(R.id.text_nickname);
        postCountView = (TextView) findViewById(R.id.text_post_count);
        profileImageView = (ImageView) findViewById(R.id.image_profile);
        tabHost = (FragmentTabHost) findViewById(R.id.tabHost);

        tabHost.setup(getContext(), getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1")
                        .setIndicator(getTabIndicator(getContext(), R.drawable.my_list_tab_selector))
                , UserAllPostFragment.newInstance().getClass(), null);
        tabHost.addTab(tabHost.newTabSpec("tab2")
                        .setIndicator(getTabIndicator(getContext(), R.drawable.my_picture_tab_selector))
                , UserOnlyPictureFragment.newInstance().getClass(), null);

        initData();

        initToolBar(userId);

    }

    private View getTabIndicator(Context context, int res) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setBackgroundResource(res);
        return view;
    }

    private void initData() {
        DetailUserRequest request = new DetailUserRequest(this, "1");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<User>() {
            @Override
            public void onSuccess(NetworkRequest<User> request, User result) {

                Log.i("DetailUserActivity", "성공 : " + result.getUserId());
                Log.i("DetailUserActivity", "성공 : " + result.getUserNick());
                Log.i("DetailUserActivity", "성공 : " + result.getUserFile());
                nickNameView.setText(result.getUserNick());
                userId = result.getUserId();
                Glide.with(profileImageView.getContext()).load("http://" + result.getUserFile()).into(profileImageView);
                initToolBar(userId);

            }

            @Override
            public void onFail(NetworkRequest<User> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("DetailUserActivity", "실패 : " + errorCode);

            }

            });
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
