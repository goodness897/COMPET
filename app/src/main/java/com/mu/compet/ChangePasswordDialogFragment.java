package com.mu.compet;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangePasswordDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePasswordDialogFragment extends DialogFragment {

    TextView cancelText;
    TextView completeText;


    public ChangePasswordDialogFragment() {
        // Required empty public constructor
    }
    public static ChangePasswordDialogFragment newInstance() {
        ChangePasswordDialogFragment fragment = new ChangePasswordDialogFragment();
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
        View view = inflater.inflate(R.layout.fragment_change_password_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        cancelText = (TextView)view.findViewById(R.id.text_cancel);
        completeText = (TextView)view.findViewById(R.id.text_complete);


        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        completeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }


}
