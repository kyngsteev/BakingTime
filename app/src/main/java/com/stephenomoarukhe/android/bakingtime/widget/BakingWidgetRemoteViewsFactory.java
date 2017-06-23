package com.stephenomoarukhe.android.bakingtime.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.stephenomoarukhe.android.bakingtime.R;
import com.stephenomoarukhe.android.bakingtime.model.Recipe;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.stephenomoarukhe.android.bakingtime.ui.fragment.RecipeFragment.mRecipes;

/**
 * Created by Omoarukhe on 06/21/2017.
 */

public class BakingWidgetRemoteViewsFactory implements RemoteViewsFactory {

    private Context mContext;

    public BakingWidgetRemoteViewsFactory(Context context){
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        try {
            new FetchBakesTask().execute().get();
        } catch (InterruptedException | ExecutionException e) {
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (mRecipes == null)return 0;
        return mRecipes.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.baking_ingredient_list_item);
        rv.setTextViewText(R.id.widget_recipe_text, mRecipes.get(position).getName());
        for (int i=0;i<mRecipes.get(position).getIngredients().size();i++){
            RemoteViews  ing= new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
            ing.setTextViewText(R.id.ingredient_text, mRecipes.get(position).getIngredients().get(i).getIngredient());
            ing.setTextViewText(R.id.measure_value, mRecipes.get(position).getIngredients().get(i).getMeasure());
            ing.setTextViewText(R.id.quantity_value, mRecipes.get(position).getIngredients().get(i).getQuantity()+"");
            rv.addView(R.id.ingerdient_list,ing);
        }

        Intent intent = new Intent();
        intent.putExtra("item", position);
        rv.setOnClickFillInIntent(R.id.bacckground, intent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public class FetchBakesTask extends AsyncTask<Void, Void, ArrayList<Recipe>> {

        @Override
        protected ArrayList<Recipe> doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                Uri builtUri = Uri.parse(mContext.getString(R.string.bakingUrl))
                        .buildUpon()
                        .build();

                URL url = new URL(builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(7000);
                urlConnection.setRequestMethod(mContext.getString(R.string.GET));
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                JSONArray bakingArray = new JSONArray(buffer.toString());
                mRecipes = new ArrayList<>();
                for (int i = 0; i < bakingArray.length(); i++) {
                    mRecipes.add(new Recipe(bakingArray.getJSONObject(i)));
                    Log.e("name: ", mRecipes.get(i).getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        reader.close();
                    }
                } catch (Exception e) {
                }
                return mRecipes;
            }
        }
    }
}
