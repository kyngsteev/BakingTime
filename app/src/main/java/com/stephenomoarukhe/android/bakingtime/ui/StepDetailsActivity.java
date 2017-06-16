package com.stephenomoarukhe.android.bakingtime.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.stephenomoarukhe.android.bakingtime.R;
import com.stephenomoarukhe.android.bakingtime.ui.fragment.StepsDetailFragment;

/**
 * Created by Omoarukhe on 06/15/2017.
 */

public class StepDetailsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            StepsDetailFragment stepsDetailsFragment = new StepsDetailFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.stepsdetailsframe, stepsDetailsFragment)
                    .commit();
        }
    }
}
