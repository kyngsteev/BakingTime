package com.stephenomoarukhe.android.bakingtime.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stephenomoarukhe.android.bakingtime.R;
import com.stephenomoarukhe.android.bakingtime.model.Recipe;

import java.util.ArrayList;

/**
 * Created by Omoarukhe on 11/06/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ListItemClickListener mOnClickListener;
    private ArrayList<Recipe> recipes;
    ArrayList<Integer> mImages;

    public RecipeAdapter(ListItemClickListener listener, ArrayList<Recipe> recipes,
                         ArrayList<Integer> images) {
        mOnClickListener = listener;
        mImages = images;
        this.recipes = recipes;

    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutIdFromParent = R.layout.list_item;
        boolean shouldAttachToParent = false;

        View view = inflater.inflate(layoutIdFromParent, viewGroup, shouldAttachToParent);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (recipes == null) return 0;
        return recipes.size();
    }

    public interface ListItemClickListener {
        void onClick(int clickedItemIndex);
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        ImageView recipeImage;
        TextView recipeTitle;
        TextView servings;
        CardView cardView;


        public RecipeViewHolder(View itemView) {
            super(itemView);

            recipeImage = (ImageView) itemView.findViewById(R.id.thumbnail);
            recipeTitle = (TextView) itemView.findViewById(R.id.title);
            servings = (TextView) itemView.findViewById(R.id.count);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            itemView.setOnClickListener(this);
        }

        void onBind(int position) {
            if (!recipes.isEmpty()) {
                Picasso.with(itemView.getContext()).load(mImages.get(position)).into(recipeImage);
                recipeTitle.setText(recipes.get(position).getName());
                servings.setText(itemView.getContext().getString(R.string.servings) + " " + recipes.get(position).getServings());
            }
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onClick(clickedPosition);
        }
    }
}
