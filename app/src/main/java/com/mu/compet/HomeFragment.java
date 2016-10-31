package com.mu.compet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mu.compet.data.Board;
import com.mu.compet.manager.NetworkManager;
import com.mu.compet.manager.NetworkRequest;
import com.mu.compet.request.ListBoardRequest;

import java.util.Arrays;


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

                Intent intent = new Intent(getContext(), DetailUserActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);

            }
        });
        mAdapter.setOnPostClickListener(new PostAdapter.OnPostClickListener() {
            @Override
            public void onPostClick(View view, Board board) {
                Intent intent = new Intent(getContext(), DetailBoardActivity.class);
                intent.putExtra("board", board);
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

    private void initData() {

        ListBoardRequest request = new ListBoardRequest(getContext(), "1", "1");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<Board[]>() {
            @Override
            public void onSuccess(NetworkRequest<Board[]> request, Board[] result) {
                mAdapter.addAll(Arrays.asList(result));

            }

            @Override
            public void onFail(NetworkRequest<Board[]> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

    }

}
