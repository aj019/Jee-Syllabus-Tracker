package com.androidmate.jee_syllabus_tracker.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidmate.jee_syllabus_tracker.R;
import com.androidmate.jee_syllabus_tracker.adapters.CompletedAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Diva on 11/30/2016.
 */

public class MathCompletedFragment extends Fragment {

    RecyclerView rv;
    LinearLayoutManager llm;
    RecyclerView.Adapter adapter;

    AdView mAdView;

    ArrayList<String> syllabus = new ArrayList<>(Arrays.asList("Sets and Relations" ,"Logarithms","Complex Numbers","Sequences and Series","Quadratic Equations and Expressions" ,"Permutation and Combination","Binomial Theorem","Matrices & Determinants" ,"Cartesian Coordinate System and Locus" ,"Straight Lines","Circle","Parabola","Ellipse","Hyperbola","Trigonometric Ratios and Identities","Properties and Solutions of Triangles" ,"Trigonometric Equations" ,"Inverse Trigonometric Functions","Functions","Limits","Continuity and Differentiability","Methods of Differentiation","Application of Derivatives" ,"Indefinite Integral","Definite Integral","Area bounded by a region","Differential Equations" ,"Vector Algebra","3-D coordinate Geometry","Probability"));

    ArrayList<String> completed = new ArrayList<>();
    ArrayList<String> checkState;
    public MathCompletedFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.math_fragment_completed,container,false);
        mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("A9636149FD57C0D52A7FD275CD84433F")
                .build();
        mAdView.loadAd(adRequest);
        SharedPreferences preferences = getContext().getSharedPreferences("Syllabus",getContext().MODE_PRIVATE);
        String state = preferences.getString("mathstate",null);

        if (completed.size() != 0)
            completed.clear();

        if(state != null) {
            checkState = new ArrayList<>(Arrays.asList(state.split(",")));

            for (int i = 0; i < checkState.size(); i++) {
                if (Boolean.parseBoolean(checkState.get(i))) {
                    completed.add(syllabus.get(i));
                }
            }
        }
        rv = (RecyclerView) v.findViewById(R.id.rvMathCompleted);
        llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        adapter = new CompletedAdapter(getContext(),completed);
        rv.setAdapter(adapter);


        return v;
    }
}
