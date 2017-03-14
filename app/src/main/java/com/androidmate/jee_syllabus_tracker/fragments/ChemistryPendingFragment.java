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

public class ChemistryPendingFragment extends Fragment {

    RecyclerView rv;
    LinearLayoutManager llm;
    RecyclerView.Adapter adapter;
    ArrayList<String> syllabus = new ArrayList<>(Arrays.asList("Basic Concepts of Chemistry" ,"Stoichiometry, Redox and Titrations","Atomic Structure","Chemical Bonding","Gaseous and Liquid States","ChemicalThermodynamics","Chemical Kinetics","Nuclear chemistry","Chemical Equilibrium","Ionic Equilibrium","Electrochemistry","Solid State","Solutions and Colligative Properties","Surface Chemistry","IUPAC Nomenclature","Isomerism","General Organic Chemistry","Aromatic Compounds and Electrophilic Aromatic Substitution","Hydrocarbons" ,"Alkyl Halides & Aryl Halides","Alcohols, Phenols & Ethers","Aldehydes and Ketones","Carboxylic Acids and their derivatives" ,"Amines" ,"Biomolecules","Polymers","Practical Organic Chemistry","Trends in Periodic Table","S-Block","P-Block","Transition Elements ( D-block )","Coordination Compounds","Principle of Metallurgy","Qualitative Salt Analysis"));
    ArrayList<String> pending = new ArrayList<>();
    AdView mAdView;

    public ChemistryPendingFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.chemistry_fragment_pending,container,false);
        mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("A9636149FD57C0D52A7FD275CD84433F")
                .build();
        mAdView.loadAd(adRequest);
        SharedPreferences preferences = getContext().getSharedPreferences("Syllabus",getContext().MODE_PRIVATE);
        String state = preferences.getString("chemstate",null);

        if (pending.size() != 0)
            pending.clear();

        if(state != null) {
            ArrayList<String> checkState = new ArrayList<>(Arrays.asList(state.split(",")));


            for (int i = 0; i < checkState.size(); i++) {
                if (!Boolean.parseBoolean(checkState.get(i))) {
                    pending.add(syllabus.get(i));
                }
            }
        }

        rv = (RecyclerView) v.findViewById(R.id.rvChemPending);
        llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        adapter = new CompletedAdapter(getContext(),pending);
        rv.setAdapter(adapter);

        return v;
    }
}
