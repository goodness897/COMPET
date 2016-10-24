package com.mu.compet;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPageFragment extends Fragment {

    FragmentTabHost tabHost;


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

        tabHost = (FragmentTabHost) view.findViewById(R.id.tabHost);

        tabHost.setup(getContext(), getChildFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator(getTabIndicator(getContext(), R.drawable.my_page_tab_selector)), MyPageAllPostFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator(getTabIndicator(getContext(), R.drawable.my_picture_tab_selector)), MyPageOnlyPictureFragment.class, null);

        Button updateProfileButton = (Button) view.findViewById(R.id.btn_update_my_profile);
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateMyProfileActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private View getTabIndicator(Context context, int res) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setBackgroundResource(res);
        return view;
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

}
