package com.mu.compet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mu.compet.data.Board;
import com.mu.compet.data.ListData;
import com.mu.compet.manager.NetworkManager;
import com.mu.compet.manager.NetworkRequest;
import com.mu.compet.request.SearchBoardRequest;

import java.util.Arrays;


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

    public static UserAllPostFragment newInstance(String userNick) {
        UserAllPostFragment fragment = new UserAllPostFragment();
        Bundle args = new Bundle();
        args.putString("userNick", userNick);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            String userNick = getArguments().getString("userNick");
            initData(userNick);

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

        return view;
    }

    private void initData(String userNick) {

        SearchBoardRequest request = new SearchBoardRequest(getContext(), "1", "1", "name", userNick);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ListData<Board>>() {
            @Override
            public void onSuccess(NetworkRequest<ListData<Board>> request, ListData<Board> result) {

                Log.d("DetailUserActivity", "성공 : " + result.getMessage());

                Board[] board = result.getData();

                mAdapter.addAll(Arrays.asList(board));
            }

            @Override
            public void onFail(NetworkRequest<ListData<Board>> request, int errorCode, String errorMessage, Throwable e) {
                Log.d("DetailUserActivity", "성공 : " + errorMessage);


            }
        });

    }

}
