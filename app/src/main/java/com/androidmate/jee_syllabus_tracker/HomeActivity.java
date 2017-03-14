package com.androidmate.jee_syllabus_tracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.androidmate.jee_syllabus_tracker.adapters.HomeAdapter;
import com.androidmate.jee_syllabus_tracker.helpers.RecyclerItemClickListener;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG="Tag";
    AdView mAdView;
    Button btPhy,btMat,btChem,btMoreApp,btBooks;
    RecyclerView rv;
    RecyclerView.Adapter adapter;
    LinearLayoutManager llm;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> completions = new ArrayList<>(Arrays.asList("0%","0%","0%"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btMoreApp = (Button) findViewById(R.id.btMoreApps);
        btMoreApp.setOnClickListener(this);
        btBooks = (Button) findViewById(R.id.btBooks);
        btBooks.setOnClickListener(this);


//        Toast.makeText(this,"total= "+totalmath+"completed = "+countmath,Toast.LENGTH_SHORT).show();
        rv = (RecyclerView) findViewById(R.id.rvHome);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);


        titles.add("Physics");
        titles.add("Chemistry");
        titles.add("Maths");

        initialize();

        adapter = new HomeAdapter(this,titles,completions);
        rv.setAdapter(adapter);
        rv.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View adapterView, int position) {
                if(position == 0){
                    startActivity(new Intent(HomeActivity.this,PhysicsActivity.class));
                }
                else if(position == 1){
                    startActivity(new Intent(HomeActivity.this,ChemistryActivity.class));
                }
                else if(position == 2){
                    startActivity(new Intent(HomeActivity.this,MathActivity.class));
                }
            }
        }));


    }

    @Override
    protected void onResume() {
        super.onResume();

        initialize();
        adapter.notifyDataSetChanged();
    }

    private void initialize() {

        SharedPreferences preferences = getSharedPreferences("Syllabus",this.MODE_PRIVATE);
        String chemstate = preferences.getString("chemstate",null);
        String phystate = preferences.getString("phystate",null);
        String mathstate = preferences.getString("mathstate",null);
        if(phystate != null){
            ArrayList<String> temp1 = new ArrayList<>(Arrays.asList(phystate.split(",")));
           int totalphy = temp1.size();
            int countphy = 0;
            for(int i=0;i<temp1.size();i++){
                if(Boolean.parseBoolean(temp1.get(i))){
                    countphy++;
                }
            }
            int comp = (countphy*100)/totalphy;
            completions.set(0,String.valueOf(comp)+"%");
        }else{
            completions.set(0,"0%");
        }

        if(chemstate != null){
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(chemstate.split(",")));
            int totalchem = temp.size();
            int countchem =0;
            for(int i=0;i<temp.size();i++){
                if(Boolean.parseBoolean(temp.get(i))){
                    countchem++;
                }
            }

            int comp = (countchem*100)/totalchem;
            completions.set(1,String.valueOf(comp)+"%");
        }else {
            completions.set(1,"0%");
        }

        if(mathstate !=null){
            ArrayList<String> temp2 = new ArrayList<>(Arrays.asList(mathstate.split(",")));

            int totalmath = temp2.size();
            int countmath = 0;
            for(int i=0;i<temp2.size();i++){
                if(Boolean.parseBoolean(temp2.get(i))){
                    countmath++;
                }
            }

            int comp = (countmath*100)/totalmath;
            completions.set(2,String.valueOf(comp)+"%");
        }else{
            completions.set(2,"0%");
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btMoreApps:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Androidmate")));
                break;

            case R.id.btBooks:
                startActivity(new Intent(HomeActivity.this,BooksActivity.class));
                break;
        }


    }
}
