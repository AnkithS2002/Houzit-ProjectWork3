package com.example.houzit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.houzit.databinding.FragmentHouseBinding;

public class HomeFragment extends Fragment {

    HouseFragment houseFragment = new HouseFragment();
    PGFragment pgFragment = new PGFragment();
    RoommateFragment roommateFragment = new RoommateFragment();

    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button houseBtn = view.findViewById(R.id.Radio_home_btn);
        Button PGBtn = view.findViewById(R.id.Radio_PG_btn);
        Button RoommateBtn = view.findViewById(R.id.Radio_Roommate_btn);

        houseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            getChildFragmentManager().beginTransaction().replace(R.id.frame_layout_small, houseFragment).commit();
            }
        });
        PGBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChildFragmentManager().beginTransaction().replace(R.id.frame_layout_small, pgFragment).commit();
            }
        });
        RoommateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChildFragmentManager().beginTransaction().replace(R.id.frame_layout_small, roommateFragment).commit();
            }
        });

        return view;
    }
}