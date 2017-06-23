package com.stephenomoarukhe.android.bakingtime.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stephenomoarukhe.android.bakingtime.R;
import com.stephenomoarukhe.android.bakingtime.adapter.StepsAdapter;
import com.stephenomoarukhe.android.bakingtime.model.Steps;
import com.stephenomoarukhe.android.bakingtime.ui.StepDetailsActivity;

import java.util.ArrayList;

import static com.stephenomoarukhe.android.bakingtime.ui.MainActivity.isTablet;
import static com.stephenomoarukhe.android.bakingtime.ui.fragment.RecipeFragment.mRecipes;

/**
 * Created by Omoarukhe on 06/13/2017.
 */

public class StepFragment extends Fragment implements StepsAdapter.ListItemClickListener {

    private RecyclerView stepsRecyclerView;
    private StepsAdapter stepsAdapter;
    private int index = 0;

    public static ArrayList<Steps> mSteps = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        index = getActivity().getIntent().getExtras().getInt(getString(R.string.extra));
        mSteps = mRecipes.get(index).getSteps();

        stepsRecyclerView = (RecyclerView) inflater.inflate(R.layout.step_fragment, container, false);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stepsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        stepsRecyclerView.setHasFixedSize(true);
        stepsAdapter = new StepsAdapter(this, mSteps);
        stepsRecyclerView.setAdapter(stepsAdapter);

        return stepsRecyclerView;

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (!isTablet) {
            Intent intent = new Intent(getActivity(), StepDetailsActivity.class);
            intent.putExtra("item", clickedItemIndex);
            startActivity(intent);
        } else {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            StepsDetailFragment stepsDetailsFragment = new StepsDetailFragment();
            stepsDetailsFragment.index = clickedItemIndex;
            fragmentManager.beginTransaction()
                    .replace(R.id.stepsdetailsframe, stepsDetailsFragment)
                    .commit();
        }
    }

}
