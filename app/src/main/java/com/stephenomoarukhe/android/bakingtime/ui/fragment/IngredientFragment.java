package com.stephenomoarukhe.android.bakingtime.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stephenomoarukhe.android.bakingtime.R;
import com.stephenomoarukhe.android.bakingtime.adapter.IngredientAdapter;

import static com.stephenomoarukhe.android.bakingtime.ui.fragment.RecipeFragment.mRecipes;

/**
 * Created by Omoarukhe on 06/13/2017.
 */

public class IngredientFragment extends Fragment {

    private RecyclerView recyclerView;
    private IngredientAdapter adapter;
    private int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        index = getActivity().getIntent().getExtras().getInt(getString(R.string.extra));

        getActivity().setTitle(mRecipes.get(index).getName());

        recyclerView = (RecyclerView) inflater.inflate(R.layout.ingredient_fragment, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new IngredientAdapter(mRecipes.get(index).getIngredients());
        recyclerView.setAdapter(adapter);


        return recyclerView;
    }
}
