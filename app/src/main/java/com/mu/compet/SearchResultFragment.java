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
 * Use the {@link SearchResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultFragment extends Fragment {
    ListView listView;
    PostAdapter mAdapter;
    String keyWord;


    public SearchResultFragment() {
        // Required empty public constructor
    }

    public static SearchResultFragment newInstance(String keyWord) {

        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString("keyword", keyWord);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String keyword = getArguments().getString("keyword");
            performSearch(keyword);

        }
    }
    private void performSearch(String keyWord) {

        SearchBoardRequest request = new SearchBoardRequest(getContext(), "1", "1", "name", keyWord);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ListData<Board>>() {
            @Override
            public void onSuccess(NetworkRequest<ListData<Board>> request, ListData<Board> result) {
                Log.d("SearchFragment", "성공 : " + result.getMessage());
                Board[] board = result.getData();
                mAdapter.addAll(Arrays.asList(board));

            }

            @Override
            public void onFail(NetworkRequest<ListData<Board>> request, int errorCode, String errorMessage, Throwable e) {
                Log.d("SearchFragment", "실패 : " + errorMessage);

            }
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
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
        return view;
    }


}
