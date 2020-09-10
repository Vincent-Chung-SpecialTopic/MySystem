package com.example.myrehabilitaion.FragmentHomePage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myrehabilitaion.BarChartFragment;
import com.example.myrehabilitaion.PieChartFragment;
import com.example.myrehabilitaion.R;
import com.example.myrehabilitaion.RadarChartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Statistics extends Fragment {


    public Statistics() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_statistics, container, false);

        BottomNavigationView bottomNav = root.findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getChildFragmentManager().beginTransaction().replace(R.id.fragment_container,new PieChartFragment()).commit();


        return root;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch(item.getItemId()){
                case R.id.nav_pie:
                    selectedFragment = new PieChartFragment();
                    break;
                case R.id.nav_bar:
                    selectedFragment = new BarChartFragment();
                    break;
                case R.id.nav_line:
                    selectedFragment = new RadarChartFragment();
                    break;
            }
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };

}
