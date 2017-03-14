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
import com.androidmate.jee_syllabus_tracker.adapters.ChemSyllabusAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Diva on 11/30/2016.
 */

public class ChemistrySyllabusFragment extends Fragment {

    ListView lv;
    ChemSyllabusAdapter adapter;
    AdView mAdView;
    ArrayList<String> syllabus = new ArrayList<>(Arrays.asList("Basic Concepts of Chemistry" ,"Stoichiometry, Redox and Titrations","Atomic Structure","Chemical Bonding","Gaseous and Liquid States","ChemicalThermodynamics","Chemical Kinetics","Nuclear chemistry","Chemical Equilibrium","Ionic Equilibrium","Electrochemistry","Solid State","Solutions and Colligative Properties","Surface Chemistry","IUPAC Nomenclature","Isomerism","General Organic Chemistry","Aromatic Compounds and Electrophilic Aromatic Substitution","Hydrocarbons" ,"Alkyl Halides & Aryl Halides","Alcohols, Phenols & Ethers","Aldehydes and Ketones","Carboxylic Acids and their derivatives" ,"Amines" ,"Biomolecules","Polymers","Practical Organic Chemistry","Trends in Periodic Table","S-Block","P-Block","Transition Elements ( D-block )","Coordination Compounds","Principle of Metallurgy","Qualitative Salt Analysis"));
    ArrayList<String> position = new ArrayList<>(Arrays.asList("1","2","3"));
    boolean[] checkBoxState = new boolean[syllabus.size()];



    public ChemistrySyllabusFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.chemistry_fragment_syllabus,container,false);
        mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("A9636149FD57C0D52A7FD275CD84433F")
                .build();
        mAdView.loadAd(adRequest);
        SharedPreferences preferences = getContext().getSharedPreferences("Syllabus",getContext().MODE_PRIVATE);
        String state = preferences.getString("chemstate",null);

        if(state != null){
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(state.split(",")));
            for(int i=0;i<temp.size();i++){
                checkBoxState[i] = Boolean.parseBoolean(temp.get(i));
            }
        }

        lv = (ListView) v.findViewById(R.id.lvChemSyllabus);
        adapter = new ChemSyllabusAdapter(getContext(),syllabus,checkBoxState);
        lv.setAdapter(adapter);
        return v;
    }
}
