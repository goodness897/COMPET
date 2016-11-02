package com.mu.compet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost tabHost;
    private long backKeyPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (FragmentTabHost) findViewById(R.id.tabHost);

        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator(getTabIndicator(this, R.drawable.home_tab_selector)), HomeFragment.newInstance().getClass(), null);
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator(getTabIndicator(this, R.drawable.search_tab_selector)), SearchFragment.newInstance().getClass(), null);
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator(getTabIndicator(this, R.drawable.write_tab_selector)), WriteFragment.newInstance().getClass(), null);
        tabHost.addTab(tabHost.newTabSpec("tab4")
                .setIndicator(getTabIndicator(this, R.drawable.my_page_tab_selector)), MyPageFragment.newInstance().getClass(), null);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            String preTab = "tab1";

            @Override
            public void onTabChanged(String tabId) {
                if ("tab3".equals(tabId)) {
                    tabHost.setCurrentTabByTag(preTab);
                    Intent intent = new Intent(MainActivity.this, NewWriteActivity.class);
                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                } else {
                    preTab = tabHost.getCurrentTabTag();
                }
            }
        });
    }

    private View getTabIndicator(Context context, int res) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setBackgroundResource(res);
        return view;
    }

    public String getSavedMessage() {
        return savedMessage;
    }

    String savedMessage = null;

    public void receiveText(String text) {
        Fragment f = getSupportFragmentManager()
                .findFragmentByTag("tab2");
        if (f != null) {
            ((SearchFragment)f).setMessage(text);
        } else {
            savedMessage = text;
        }
        tabHost.setCurrentTabByTag("tab2");
    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "뒤로 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
        }
    }
}
