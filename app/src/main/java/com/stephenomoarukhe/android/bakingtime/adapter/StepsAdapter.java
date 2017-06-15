package com.stephenomoarukhe.android.bakingtime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stephenomoarukhe.android.bakingtime.R;
import com.stephenomoarukhe.android.bakingtime.model.Steps;

import java.util.ArrayList;

/**
 * Created by Omoarukhe on 06/14/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder>{

    private StepsAdapter.ListItemClickListener mOnClickListener;
    private ArrayList<Steps> mSteps;

    public StepsAdapter(StepsAdapter.ListItemClickListener listener, ArrayList<Steps> steps) {
        mOnClickListener = listener;
        mSteps = steps;

    }

    @Override
    public StepsAdapter.StepsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutIdFromParent = R.layout.step_list_item;
        boolean shouldAttachToParent = false;

        View view = inflater.inflate(layoutIdFromParent, viewGroup, shouldAttachToParent);
        StepsAdapter.StepsViewHolder viewHolder = new StepsAdapter.StepsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StepsAdapter.StepsViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mSteps == null) return 0;
        return mSteps.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    class StepsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView stepText;
        TextView stepText_id;
        TextView descriptionText;


        public StepsViewHolder(View itemView) {
            super(itemView);

            stepText = (TextView) itemView.findViewById(R.id.step_text);
            stepText_id = (TextView) itemView.findViewById(R.id.step_id);
            descriptionText = (TextView) itemView.findViewById(R.id.step_description_short);;
            itemView.setOnClickListener(this);
        }

        void onBind(int position) {
            if (!mSteps.isEmpty()) {
                stepText.setText(R.string.step_text);
                stepText_id.setText(String.valueOf(mSteps.get(position).getId() + 1));
                descriptionText.setText(mSteps.get(position).getShortDescription());
            }
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
