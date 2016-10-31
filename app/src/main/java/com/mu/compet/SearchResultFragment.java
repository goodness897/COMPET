package com.mu.compet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mu.compet.data.Board;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultFragment extends Fragment {
    ListView listView;
    PostAdapter mAdapter;
    ArrayList<Board> items;


    public SearchResultFragment() {
        // Required empty public constructor
    }

    public static SearchResultFragment newInstance(ArrayList<Board> items) {

        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putSerializable("board", items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            items = new ArrayList<>();
            items = (ArrayList<Board>) getArguments().getSerializable("board");

        }
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

        initData();
        return view;
    }


    private void initData() {

        mAdapter.addAll(items);

    }

}
