package com.androidmate.jee_syllabus_tracker.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidmate.jee_syllabus_tracker.R;
import com.androidmate.jee_syllabus_tracker.adapters.MathSyllabusAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Diva on 11/30/2016.
 */
public class MathSyllabusFragment extends Fragment {

    ListView lv;
    MathSyllabusAdapter adapter;

    ArrayList<String> syllabus = new ArrayList<>(Arrays.asList("Sets and Relations" ,"Logarithms","Complex Numbers","Sequences and Series","Quadratic Equations and Expressions" ,"Permutation and Combination","Binomial Theorem","Matrices & Determinants" ,"Cartesian Coordinate System and Locus" ,"Straight Lines","Circle","Parabola","Ellipse","Hyperbola","Trigonometric Ratios and Identities","Properties and Solutions of Triangles" ,"Trigonometric Equations" ,"Inverse Trigonometric Functions","Functions","Limits","Continuity and Differentiability","Methods of Differentiation","Application of Derivatives" ,"Indefinite Integral","Definite Integral","Area bounded by a region","Differential Equations" ,"Vector Algebra","3-D coordinate Geometry","Probability"));
    ArrayList<String> position = new ArrayList<>(Arrays.asList("1","2","3"));
    boolean[] checkBoxState = new boolean[syllabus.size()];
    AdView mAdView;
    public MathSyllabusFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.math_fragment_syllabus,container,false);

        mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("A9636149FD57C0D52A7FD275CD84433F")
                .build();
        mAdView.loadAd(adRequest);

        SharedPreferences preferences = getContext().getSharedPreferences("Syllabus",getContext().MODE_PRIVATE);
        String state = preferences.getString("mathstate",null);

        if(state != null){
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(state.split(",")));
            for(int i=0;i<temp.size();i++){
                checkBoxState[i] = Boolean.parseBoolean(temp.get(i));
            }
        }


        lv = (ListView) v.findViewById(R.id.lvMathSyllabus);
        adapter = new MathSyllabusAdapter(getContext(),syllabus,checkBoxState);
        lv.setAdapter(adapter);
        return v;
    }
}
