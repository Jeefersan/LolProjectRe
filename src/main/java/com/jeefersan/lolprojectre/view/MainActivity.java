package com.jeefersan.lolprojectre.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.jeefersan.lolprojectre.R;

import com.jeefersan.lolprojectre.view.fragments.BasePlayerFragment;
import com.jeefersan.lolprojectre.view.fragments.ChallengerFragment;
import com.jeefersan.lolprojectre.view.fragments.GrandmasterFragment;
import com.jeefersan.lolprojectre.view.fragments.MasterFragment;
import com.jeefersan.lolprojectre.view.fragments.SearchFragment;
import com.jeefersan.lolprojectre.view.fragments.SearchSummonerFragment;
import com.jeefersan.lolprojectre.viewmodel.ChallengerViewModel;
import com.jeefersan.lolprojectre.viewmodel.GrandmasterViewModel;
import com.jeefersan.lolprojectre.viewmodel.MasterViewModel;


import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;


public class MainActivity extends AppCompatActivity {

    private ChallengerViewModel mChallengerViewModel;
    private GrandmasterViewModel mGrandmasterViewModel;
    private MasterViewModel mMasterViewModel;

    public final static String opGg ="https://euw.op.gg/summoner/champions/userName=";

    public static String apiKey = "RGAPI-d48d8bbf-4c61-4812-b06b-ee0729f0b4d1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChallengerViewModel = new ChallengerViewModel(getApplication());
        mGrandmasterViewModel = new GrandmasterViewModel(getApplication());
        mMasterViewModel = new MasterViewModel(getApplication());


        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(tabsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tablayout);
        tabs.setupWithViewPager(viewPager);


    }


    class TabsPagerAdapter extends FragmentPagerAdapter {
        private String[] tabTitles = {"Challenger", "Grandmaster", "Master", "Search Summoner"};

        public TabsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ChallengerFragment(mChallengerViewModel);
                case 1:
                    return new GrandmasterFragment(mGrandmasterViewModel);
                case 2:
                    return new MasterFragment(mMasterViewModel);
                case 3:
                    return new SearchFragment();
                default:
                    return null;
            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}