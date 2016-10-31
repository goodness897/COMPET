package com.mu.compet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mu.compet.data.Board;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserAllPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserAllPostFragment extends Fragment {

    ListView listView;
    MyPostAdapter mAdapter;


    public UserAllPostFragment() {
        // Required empty public constructor
    }

    public static UserAllPostFragment newInstance() {
        UserAllPostFragment fragment = new UserAllPostFragment();
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
                Intent intent = new Intent(getContext(), DetailBoardActivity.class);
                startActivity(intent);
            }
        });
        listView.setAdapter(mAdapter);
        initData();

        return view;
    }

    private void initData() {

    }

}
