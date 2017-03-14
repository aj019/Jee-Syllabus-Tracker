package com.androidmate.jee_syllabus_tracker.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.androidmate.jee_syllabus_tracker.R;

import java.util.ArrayList;

/**
 * Created by Diva on 11/30/2016.
 */

public class ChemSyllabusAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> syllabus;
    ArrayList<String> positions;
    boolean[] checkBoxState;

    private static LayoutInflater inflater=null;


    public ChemSyllabusAdapter(Context context, ArrayList<String> syllabus, boolean[] checkBoxState) {
        this.context = context;
        this.syllabus = syllabus;
        this.checkBoxState = checkBoxState;

    }

    @Override
    public int getCount() {
        return syllabus.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.from(context).inflate(R.layout.card_sylaabus, null);

        TextView title = (TextView)vi.findViewById(R.id.tvTopicName); // title
        CheckBox cb = (CheckBox) vi.findViewById(R.id.cbSyllabus);
        // Setting all values in listview
        title.setText(syllabus.get(i));
        cb.setChecked(checkBoxState[i]);

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    checkBoxState[i]=true;

                    String state=String.valueOf(checkBoxState[0]);
                    for(int i=1;i<checkBoxState.length;i++){
                        state = state + "," + String.valueOf(checkBoxState[i]);
                    }
                    SharedPreferences prefs = context.getSharedPreferences("Syllabus",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("chemstate",state);
                    editor.commit();
                }

                else{
                    checkBoxState[i]=false;
                    String state=String.valueOf(checkBoxState[0]);
                    for(int i=1;i<checkBoxState.length;i++){
                        state = state + "," + String.valueOf(checkBoxState[i]);
                    }
                    SharedPreferences prefs = context.getSharedPreferences("Syllabus",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("chemstate",state);
                    editor.commit();
                }

            }
        });

        return vi;
    }
}

