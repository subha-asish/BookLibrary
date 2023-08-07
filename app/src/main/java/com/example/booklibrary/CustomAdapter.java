package com.example.booklibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    // 1- Variable
    private Context context;
    private ArrayList<String> book_id,book_title,book_author,book_pages;
    private Activity Activity;


    // 2- ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView book_id_txt,book_title_txt,book_author_txt,book_pages_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.book_id_txt = itemView.findViewById(R.id.book_id_txt);
            this.book_title_txt = itemView.findViewById(R.id.book_title_txt);
            this.book_author_txt = itemView.findViewById(R.id.book_author_txt);
            this.book_pages_txt = itemView.findViewById(R.id.book_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

    public CustomAdapter(Activity Activity,Context context,
                         ArrayList<String> book_id,
                         ArrayList<String> book_title,
                         ArrayList<String> book_author,
                         ArrayList<String> book_pages
                        ){
        this.Activity= Activity;
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.my_row,parent,false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int positions) {

        holder.book_id_txt.setText(String.valueOf(book_id.get(positions)));
        holder.book_title_txt.setText(String.valueOf(book_title.get(positions)));
        holder.book_author_txt.setText(String.valueOf(book_author.get(positions)));
        holder.book_pages_txt.setText(String.valueOf(book_pages.get(positions)));

        //This is for update
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id" ,String.valueOf(book_id.get(positions)));
                intent.putExtra("title" ,String.valueOf(book_title.get(positions)));
                intent.putExtra("author" ,String.valueOf(book_author.get(positions)));
                intent.putExtra("pages" ,String.valueOf(book_pages.get(positions)));
                Activity.startActivityForResult(intent,1);
            }
        });

    }


    @Override
    public int getItemCount() {
        return book_id.size();
    }

//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
}
