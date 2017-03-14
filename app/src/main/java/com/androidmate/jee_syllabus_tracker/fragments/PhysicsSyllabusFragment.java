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
import com.androidmate.jee_syllabus_tracker.adapters.PhySyllabusAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;



public class PhysicsSyllabusFragment extends Fragment {

    ListView lv;
    PhySyllabusAdapter adapter;
    AdView mAdView;

    ArrayList<String> syllabus = new ArrayList<>(Arrays.asList("Units and Dimensions","Vectors and Elementary Calculus","Errors & Measurements","Kinematics","Laws of Motion","Circular Motion","Work, Energy and Power","Centre Of Mass, Momentum and Collisions" ,"Rotational Mechanics","Gravitation","Fluids","Mechanical Properties of Solids","SHM and Oscillatory Motion","Wave Motion and Waves on a string","Sound Waves","Geometrical Optics","Wave Optics","Thermal Expansion and Calorimetry","Kinetic Theory of Gases","Thermodynamics","Specific Heat Capacities of Gases","Heat Transfer" ,"Electric Field and Potential","Capacitors","Current and Electricity","Force due to Magnetic Field","Magnetic Field due to current" ,"Electromagnetic Induction","Alternating Current" ,"Dual Nature of Matter and Photoelectric Effect","Bohr’s Model of an Atom","X-rays"));
    ArrayList<String> position = new ArrayList<>(Arrays.asList("1","2","3"));
    boolean[] checkBoxState = new boolean[syllabus.size()];

    public PhysicsSyllabusFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.physics_fragment_syllabus,container,false);
        mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("A9636149FD57C0D52A7FD275CD84433F")
                .build();
        mAdView.loadAd(adRequest);
        SharedPreferences preferences = getContext().getSharedPreferences("Syllabus",getContext().MODE_PRIVATE);
        String state = preferences.getString("phystate",null);

        if(state != null){
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(state.split(",")));
            for(int i=0;i<temp.size();i++){
                checkBoxState[i] = Boolean.parseBoolean(temp.get(i));
            }
        }


        lv = (ListView) v.findViewById(R.id.lvSyllabus);
        adapter = new PhySyllabusAdapter(getContext(),syllabus,checkBoxState);
        lv.setAdapter(adapter);
        return v;
    }
}
