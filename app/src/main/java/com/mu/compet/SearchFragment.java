package com.mu.compet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    //    Spinner typeSpinner;
    private EditText keywordInputEditText;
    private Button cancelButton;


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);

//        typeSpinner = (Spinner) view.findViewById(R.id.spinner_search_type);
        keywordInputEditText = (EditText) view.findViewById(R.id.edit_search_keyword);

        keywordInputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String keyword = keywordInputEditText.getText().toString();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Fragment fragment = SearchResultFragment.newInstance(keyword);
                    ft.replace(R.id.container, fragment);
                    ft.commitAllowingStateLoss();
                    return true;
                }
                return false;
            }
        });

        cancelButton = (Button) view.findViewById(R.id.btn_cancel);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keywordInputEditText.setText("");
            }
        });

//        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 1:
//                        if (TextUtils.isEmpty(keywordInputEditText.getText())) {
//                            DialogFragment dateFragment = new PickupDateFragment();
//                            dateFragment.show(getChildFragmentManager(), "pickDate");
//                        }
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        String type = typeSpinner.getSelectedItem().toString();
//        if ("날짜".equals(type)) {
//            keywordInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    DialogFragment dateFragment = new PickupDateFragment();
//                    dateFragment.show(getChildFragmentManager(), "pickDate");
//                }
//            });
//        }

        return view;
    }


    public void receiveText(String text) {

        ((MainActivity) getActivity()).receiveText(text);
    }

    public void setMessage(String text) {

        if (keywordInputEditText != null) {
            keywordInputEditText.setText(text);
        }
    }
}
