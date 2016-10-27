package com.mu.compet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mu.compet.data.Board;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPageAllPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPageAllPostFragment extends Fragment {

    ListView listView;
    MyPostAdapter mAdapter;


    public MyPageAllPostFragment() {
        // Required empty public constructor
    }

    public static MyPageAllPostFragment newInstance() {
        MyPageAllPostFragment fragment = new MyPageAllPostFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_page_all_post, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        mAdapter = new MyPostAdapter();
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

    int[] resIds = {R.drawable.image_sample_post01, R.drawable.image_sample_post02
            , R.drawable.image_sample_post03, R.drawable.image_sample_post04};

    String sampleString = "this is sample content ";

    private void initData() {
        StringBuilder sampleStringBuilder = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            sampleStringBuilder.append(sampleString);
            for (int j = 0; j < i; j++) {
                sampleStringBuilder.append(sampleString);
            }
            Board p = new Board();
            p.setReplyCount("+" + r.nextInt(10));
            p.setPostContent(sampleStringBuilder.toString() + i);
            p.setImageCount("+" + r.nextInt(3));
            p.setPostImage(ContextCompat.getDrawable(getContext(), resIds[i % resIds.length]));
            mAdapter.add(p);
        }
    }

}
