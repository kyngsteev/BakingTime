package com.stephenomoarukhe.android.bakingtime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stephenomoarukhe.android.bakingtime.R;
import com.stephenomoarukhe.android.bakingtime.model.Ingredients;

import java.util.ArrayList;

/**
 * Created by Omoarukhe on 06/14/2017.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>{

    private ArrayList<Ingredients> mIngredients;

    public IngredientAdapter(ArrayList<Ingredients> ingredients) {
        mIngredients = ingredients;
    }

    @Override
    public IngredientAdapter.IngredientViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutIdFromParent = R.layout.ingredient_list_item;
        boolean shouldAttachToParent = false;

        View view = inflater.inflate(layoutIdFromParent, viewGroup, shouldAttachToParent);
        IngredientAdapter.IngredientViewHolder viewHolder = new IngredientAdapter.IngredientViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.IngredientViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mIngredients == null) return 0;
        return mIngredients.size();
    }


    class IngredientViewHolder extends RecyclerView.ViewHolder{

        TextView ingredientTextView;
        TextView measureTextView;
        TextView quantityTextView;


        public IngredientViewHolder(View itemView) {
            super(itemView);

            ingredientTextView = (TextView) itemView.findViewById(R.id.ingredient_text);
            measureTextView = (TextView) itemView.findViewById(R.id.measure_value);
            quantityTextView = (TextView) itemView.findViewById(R.id.quantity_value);
        }

        void onBind(int position) {
            if (!mIngredients.isEmpty()) {
                ingredientTextView.setText(mIngredients.get(position).getIngredient());
                measureTextView.setText(mIngredients.get(position).getMeasure());
                quantityTextView.setText(String.valueOf(mIngredients.get(position).getQuantity()));
            }
        }
    }
}
