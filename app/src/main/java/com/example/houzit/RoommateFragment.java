package com.example.houzit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

public class RoommateFragment extends Fragment {


    private static final String[] colleges = new String[]{
            "BMSCE", "RVCE", "MSRIT", "PESU main campus", "PESU ECity", "SJCE", "NIE", "DSCE", "CMRIT", "AIT", "UVCE", "BMSIT","New Horizon" , "RVITM", "SJBIT", "RNSIT", "SIT", "CHRIST"
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_roommate, container, false);

        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autocompleteHouse);
        ImageView imageView = view.findViewById(R.id.smallTriangle);
        Button searchBtn = view.findViewById(R.id.searchBtnHouse);
        Button ownerBtn = view.findViewById(R.id.ownerBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, colleges);
        autoCompleteTextView.setAdapter(adapter);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoCompleteTextView.showDropDown();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), ListItems_Recyclerview.class);
                Intent intent = new Intent(getActivity(), RecyclerViewRoommate.class);
                intent.putExtra("college", autoCompleteTextView.getText().toString());
                startActivity(intent);
            }
        });

        ownerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostPropertyRoommate.class);
                startActivity(intent);
            }
        });

        return view;
    }
}