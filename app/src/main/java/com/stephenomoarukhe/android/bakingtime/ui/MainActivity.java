package com.stephenomoarukhe.android.bakingtime.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stephenomoarukhe.android.bakingtime.R;
import com.stephenomoarukhe.android.bakingtime.model.Recipe;
import com.stephenomoarukhe.android.bakingtime.ui.fragment.RecipeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static boolean isTablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            if (findViewById(R.id.bakesGrid) != null) {
                isTablet = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                RecipeFragment recipeFragment = new RecipeFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.bakesGrid, recipeFragment)
                        .commit();
            } else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                RecipeFragment recipeFragment = new RecipeFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.bakersFrame, recipeFragment)
                        .commit();
            }
        }

    }
}
