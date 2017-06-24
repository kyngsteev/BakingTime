package com.stephenomoarukhe.android.bakingtime.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.stephenomoarukhe.android.bakingtime.R;
import com.stephenomoarukhe.android.bakingtime.adapter.PagerAdapter;
import com.stephenomoarukhe.android.bakingtime.adapter.RecipeAdapter;
import com.stephenomoarukhe.android.bakingtime.adapter.StepsAdapter;
import com.stephenomoarukhe.android.bakingtime.ui.fragment.StepsDetailFragment;

import static com.stephenomoarukhe.android.bakingtime.ui.MainActivity.isTablet;
import static com.stephenomoarukhe.android.bakingtime.ui.fragment.RecipeFragment.mRecipes;
import static com.stephenomoarukhe.android.bakingtime.ui.fragment.StepFragment.mSteps;
import static com.stephenomoarukhe.android.bakingtime.ui.fragment.StepsDetailFragment.index;

/**
 * Created by Omoarukhe on 06/14/2017.
 */

public class DetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

}
