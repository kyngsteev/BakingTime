package com.stephenomoarukhe.android.bakingtime.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stephenomoarukhe.android.bakingtime.R;
import com.stephenomoarukhe.android.bakingtime.adapter.StepsAdapter;

import static com.stephenomoarukhe.android.bakingtime.ui.fragment.RecipeFragment.mRecipes;

/**
 * Created by Omoarukhe on 06/13/2017.
 */

public class StepFragment extends Fragment implements StepsAdapter.ListItemClickListener {

    private RecyclerView stepsRecyclerView;
    private StepsAdapter stepsAdapter;
    private int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        index = getActivity().getIntent().getExtras().getInt(getString(R.string.extra));

        stepsRecyclerView = (RecyclerView) inflater.inflate(R.layout.step_fragment, container, false);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stepsRecyclerView.setHasFixedSize(true);
        stepsAdapter = new StepsAdapter(this, mRecipes.get(index).getSteps());
        stepsRecyclerView.setAdapter(stepsAdapter);

        return stepsRecyclerView;

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
