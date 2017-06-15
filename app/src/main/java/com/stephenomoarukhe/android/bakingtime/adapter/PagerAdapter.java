package com.stephenomoarukhe.android.bakingtime.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.stephenomoarukhe.android.bakingtime.ui.fragment.IngredientFragment;
import com.stephenomoarukhe.android.bakingtime.ui.fragment.StepFragment;

/**
 * Created by Omoarukhe on 06/13/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                IngredientFragment tab1 = new IngredientFragment();
                return tab1;
            case 1:
                StepFragment tab2 = new StepFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "INGREDIENTS";
            case 1:
                return "STEPS";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
