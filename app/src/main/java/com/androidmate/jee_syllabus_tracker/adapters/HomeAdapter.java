package com.androidmate.jee_syllabus_tracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidmate.jee_syllabus_tracker.R;

import java.util.ArrayList;

/**
 * Created by Diva on 12/28/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Home>{

    Context context;
    ArrayList<String> title;
    ArrayList<String> completion;

    public HomeAdapter(Context context, ArrayList<String> title, ArrayList<String> completion) {
        this.context = context;
        this.title = title;
        this.completion = completion;
    }

    @Override
    public Home onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_home,parent,false);
        Home h = new Home(v);
        return h;
    }

    @Override
    public void onBindViewHolder(Home holder, int position) {

        holder.tvTitle.setText(title.get(position));
        holder.tvCompletion.setText(completion.get(position));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class Home extends RecyclerView.ViewHolder{

        TextView tvTitle,tvCompletion;
        public Home(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTopicName);
            tvCompletion = (TextView) itemView.findViewById(R.id.tvTopicCompletion);
        }

    }
}
