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

/**
 * Created by Omoarukhe on 06/14/2017.
 */

public class DetailActivity extends AppCompatActivity implements RecipeAdapter.ListItemClickListener{

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

    @Override
    public void onClick(int clickedItemIndex) {
        if(isTablet){
            FragmentManager fragmentManager = getSupportFragmentManager();
            StepsDetailFragment stepsDetailsFragment = new StepsDetailFragment();
            stepsDetailsFragment.index = clickedItemIndex;
            fragmentManager.beginTransaction()
                    .add(R.id.stepsdetailsframe, stepsDetailsFragment)
                    .commit();
        }
    }
}
