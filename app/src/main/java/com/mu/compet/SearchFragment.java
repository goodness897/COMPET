package com.mu.compet;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    Spinner typeSpinner;
    EditText keywordInputEditText;
    Button searchButton;


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

        typeSpinner = (Spinner)view.findViewById(R.id.spinner_search_type);
        keywordInputEditText = (EditText) view.findViewById(R.id.edit_search_keyword);

        searchButton = (Button) view.findViewById(R.id.btn_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_LONG).show();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = new SearchResultFragment();
                ft.replace(R.id.container, fragment);
                ft.commitAllowingStateLoss();
            }
        });

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 1:
                        DialogFragment dateFragment = new PickupDateFragment();
                        dateFragment.show(getChildFragmentManager(), "pickDate");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String type = typeSpinner.getSelectedItem().toString();
        if("날짜".equals(type)) {
            keywordInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    DialogFragment dateFragment = new PickupDateFragment();
                    dateFragment.show(getChildFragmentManager(), "pickDate");
                }
            });
        }

        return view;
    }

    public void receiveText(String text) {
        ((MainActivity)getActivity()).receiveText(text);
    }

    public void setMessage(String text) {
        if (keywordInputEditText != null) {
            keywordInputEditText.setText(text);
        }
    }


}
