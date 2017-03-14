package com.androidmate.jee_syllabus_tracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidmate.jee_syllabus_tracker.R;

import java.util.ArrayList;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.Books>{

    ArrayList<String> booktitle;
    ArrayList<Integer> image;
    ArrayList<String> link;
    Context context;

    public BookAdapter(ArrayList<String> booktitle, ArrayList<Integer> image, ArrayList<String> link, Context context) {
        this.booktitle = booktitle;
        this.image = image;
        this.link = link;
        this.context = context;
    }

    @Override
    public Books onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_books,parent,false);
        Books b = new Books(v);
        return b;
    }

    @Override
    public void onBindViewHolder(Books holder, final int position) {
        holder.tvBook.setText(booktitle.get(position));
        holder.iv.setImageResource(Integer.valueOf(image.get(position)));
        holder.btBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link.get(position)));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return booktitle.size();
    }

    public class Books extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvBook;
        Button btBuy;
        public Books(View itemView) {
            super(itemView);

            iv = (ImageView) itemView.findViewById(R.id.ivBook);
            tvBook = (TextView) itemView.findViewById(R.id.tvBookName);
            btBuy = (Button) itemView.findViewById(R.id.btBuyNow);
        }
    }
}
