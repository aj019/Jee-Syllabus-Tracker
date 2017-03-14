package com.androidmate.jee_syllabus_tracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidmate.jee_syllabus_tracker.adapters.BookAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class BooksActivity extends AppCompatActivity {

    RecyclerView rv;
    RecyclerView.Adapter adapter;
    LinearLayoutManager llm ;
    ArrayList<String> title= new ArrayList<String>(Arrays.asList("Chemistry by Raymond Chang","Concepts of Physics - Vol. 2 by  H.C. Verma ","\n" +
            "Concepts of Physics Vol. 1 by H.C. Verma","\n" +
            "Advanced Problems in Organic Chemistry for JEE by Himanshu Pandey","A Textbook of INORGANIC CHEMISTRY by Dr. R.K. Gupta","38 Years Solved IIT JEE Mathematics Papers by Amit M. Agarwal"));
    ArrayList<String> link = new ArrayList<String>(Arrays.asList("http://amzn.to/2iOnvL2","http://amzn.to/2hRbk1J","http://amzn.to/2hRl5g4","http://amzn.to/2i9fLTd","http://amzn.to/2i9fLTd","http://amzn.to/2iOADjd"));;
    ArrayList<Integer> image = new ArrayList<Integer>(Arrays.asList(R.mipmap.chem1,R.mipmap.phy1,R.mipmap.phy2,R.mipmap.book4,R.mipmap.book5,R.mipmap.book6));;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        rv = (RecyclerView) findViewById(R.id.rvBooks);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        adapter = new BookAdapter(title,image,link,this);
        rv.setAdapter(adapter);

    }
}
