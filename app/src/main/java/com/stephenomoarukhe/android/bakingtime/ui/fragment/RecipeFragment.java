package com.stephenomoarukhe.android.bakingtime.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stephenomoarukhe.android.bakingtime.R;
import com.stephenomoarukhe.android.bakingtime.adapter.RecipeAdapter;
import com.stephenomoarukhe.android.bakingtime.model.Recipe;
import com.stephenomoarukhe.android.bakingtime.ui.DetailActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.stephenomoarukhe.android.bakingtime.ui.MainActivity.isTablet;

/**
 * Created by Omoarukhe on 11/06/2017.
 */

public class RecipeFragment extends Fragment implements RecipeAdapter.ListItemClickListener {

    public static final String TAG = RecipeFragment.class.getSimpleName();
    public static ArrayList<Recipe> mRecipes;
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private ArrayList<Integer> mImages;
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView =(RecyclerView) inflater.inflate(R.layout.recipe_fragment, container, false);

        dialog = new ProgressDialog(getContext());

        mImages = new ArrayList<>();
        mImages.add(R.drawable.nutella_pie);
        mImages.add(R.drawable.brownies);
        mImages.add(R.drawable.cheesecake);
        mImages.add(R.drawable.yellow_cake);

        if (isNetworkAvailable(getContext())) {

            dialog.setMessage(getString(R.string.dialog));
            dialog.show();

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(getResources().getString(R.string.bakingUrl))
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }
                    });

                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            mRecipes = getBakingRecipe(jsonData);
                            if (getActivity() == null) return;
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loadData();
                                }
                            });
                        }else {
                            alertUserOfError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: " + e.getMessage(), e);
                    } catch (JSONException e) {
                        Log.e(TAG, "Exception caught: " + e.getMessage(), e);
                    }
                }
            });
        }else {
            alertUserOfError();
        }

        return recyclerView;
    }

    private void loadData() {
        if (isTablet) {
            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
            }
            else{
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3));
            }
        } else {
            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1));
            }
            else{
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
            }
        }
        adapter = new RecipeAdapter(this, mRecipes, mImages);
        recyclerView.setAdapter(adapter);

    }

    private ArrayList<Recipe> getBakingRecipe(String jsonData) throws JSONException {
        JSONArray bakeArray = new JSONArray(jsonData);
        mRecipes = new ArrayList<>();
        for (int i = 0; i < bakeArray.length(); i++) {
            mRecipes.add(new Recipe(bakeArray.getJSONObject(i)));
        }

        return mRecipes;
    }

    @Override
    public void onClick(int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("item", clickedItemIndex);
        startActivity(intent);
    }


    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserOfError(){
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getActivity().getFragmentManager(), "error_dialog");
    }
}
