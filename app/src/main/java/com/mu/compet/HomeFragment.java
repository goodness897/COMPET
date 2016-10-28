package com.mu.compet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mu.compet.data.Board;

import java.util.Random;


public class HomeFragment extends Fragment {

    ListView listView;
    PostAdapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initToolBar(getString(R.string.app_name), view);
        listView = (ListView) view.findViewById(R.id.listView);
        mAdapter = new PostAdapter();
        mAdapter.setOnUserClickListener(new PostAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(View view, Board board) {
                String nickName = board.getNickName();
                Intent intent = new Intent(getContext(), DetailUserActivity.class);
                intent.putExtra("NickName", nickName);
                startActivity(intent);

            }
        });
        mAdapter.setOnPostClickListener(new PostAdapter.OnPostClickListener() {
            @Override
            public void onPostClick(View view, Board board) {
                Intent intent = new Intent(getContext(), DetailPostActivity.class);
                startActivity(intent);
            }
        });
        listView.setAdapter(mAdapter);

        initData();
        return view;
    }

    private void initToolBar(String title, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.main_toolbar);
        TextView titleText = (TextView) view.findViewById(R.id.toolbar_title);
        titleText.setText(title);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    int[] resIds = {R.drawable.image_sample_post01, R.drawable.image_sample_post02
            ,R.drawable.image_sample_post03, R.drawable.image_sample_post04};

    String sampleString = "this is sample content ";

    private void initData() {


//        ListBoardRequest request = new ListBoardRequest(getContext(), "", "");
//        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<Board[]>() {
//            @Override
//            public void onSuccess(NetworkRequest<Board[]> request, Board[] result) {
//
//            }
//
//            @Override
//            public void onFail(NetworkRequest<Board[]> request, int errorCode, String errorMessage, Throwable e) {
//
//            }
//        });

        StringBuilder sampleStringBuilder = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            sampleStringBuilder.append(sampleString);
            for (int j = 0; j < i; j++) {
                sampleStringBuilder.append(sampleString);
            }
            Board p = new Board();
            p.setReplyCount("+" + r.nextInt(10));
            p.setDate("2016년 " + "10월 " + r.nextInt(30) + "일");
            p.setProfileImage(ContextCompat.getDrawable(getContext(), R.drawable.image_default_profile));
            p.setNickName("name " + i);
            p.setPostContent(sampleStringBuilder.toString() + i);
            p.setImageCount("+" + r.nextInt(3));
            p.setPostImage(ContextCompat.getDrawable(getContext(), resIds[i % resIds.length]));
            mAdapter.add(p);
        }
    }

}
