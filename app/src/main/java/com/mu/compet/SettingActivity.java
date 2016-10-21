package com.mu.compet;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initToolBar(getString(R.string.activity_setting));

        builder = new AlertDialog.Builder(this);
        Button btn = (Button)findViewById(R.id.btn_change_password);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new ChangePasswordDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "change password");

            }
        });

        btn = (Button)findViewById(R.id.btn_logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                builder.setMessage("로그아웃 하시겠습니까?")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 로그아웃 요청
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        btn = (Button)findViewById(R.id.btn_leave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("탈퇴 하시겠습니까?")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 로그아웃 요청
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    private void initToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        TextView titleText = (TextView) findViewById(R.id.toolbar_title);
        titleText.setText(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
