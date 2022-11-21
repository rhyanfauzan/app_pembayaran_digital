package com.ash.mobile.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.ash.mobile.R;
import com.ash.mobile.adapter.SectionPagerAdapter;

public class LainnyaFragment extends Fragment {

    View myFragment;

    ViewPager viewPager;
    TabLayout tabLayout;

    public LainnyaFragment() { }
    public static LainnyaFragment getInstance()    {
        return new LainnyaFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       myFragment = inflater.inflate(R.layout.fragment_lainnya, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        viewPager = myFragment.findViewById(R.id.viewPager_lainnya);
        tabLayout = myFragment.findViewById(R.id.tabLayout_laiinya);

        return myFragment;


    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AllMenuFragment(), "All");
        adapter.addFragment(new PembayaranMenu(), "Pembayaran");
        adapter.addFragment(new GameMenu(), "Game");

        viewPager.setAdapter(adapter);
    }
}