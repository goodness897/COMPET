package com.mu.compet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mu.compet.data.Post;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPageFragment extends Fragment {

    ListView listView;
    MyPostAdapter mAdapter;

    public MyPageFragment() {
        // Required empty public constructor
    }

    public static MyPageFragment newInstance() {
        MyPageFragment fragment = new MyPageFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        initToolBar(getString(R.string.app_name), view);
        setHasOptionsMenu(true);
        listView = (ListView) view.findViewById(R.id.listView);
        mAdapter = new MyPostAdapter();
        listView.setAdapter(mAdapter);

        Button updateProfileButton = (Button) view.findViewById(R.id.btn_update_my_profile);
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateMyProfileActivity.class);
                startActivity(intent);
            }
        });

        initData();


        return view;
    }

    int[] resIds = {R.drawable.image_sample_post01, R.drawable.image_sample_post02
            ,R.drawable.image_sample_post03, R.drawable.image_sample_post04};

    String sampleString = "this is sample content ";

    private void initData() {
        StringBuilder sampleStringBuilder = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i; j++) {
                sampleStringBuilder.append(sampleString);
            }

            Post p = new Post();
            p.setReplyCount("+" + r.nextInt(10));
            p.setPostContent(sampleStringBuilder.toString() + i);
            p.setImageCount("+" + r.nextInt(3));
            p.setPostImage(ContextCompat.getDrawable(getContext(), resIds[i % resIds.length]));
            mAdapter.add(p);
        }
    }

    private void initToolBar(String title, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.main_toolbar);
        TextView titleText = (TextView) view.findViewById(R.id.toolbar_title);
        titleText.setText(title);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_my_page_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateMyProfileClicked() {

    }

}
