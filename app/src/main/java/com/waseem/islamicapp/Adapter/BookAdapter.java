package com.waseem.islamicapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.waseem.islamicapp.Activity.DetailActivity;
import com.waseem.islamicapp.Models.Book;
import com.waseem.islamicapp.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BooksHolder> {


    List<Book> list;
    Context activity;

    public BookAdapter(List<Book> list, Context activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BooksHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_books,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BooksHolder holder, int position) {

        Book book = list.get(position);

        holder.book_title.setText(book.getTitle());


        Glide.with(activity)
                .load(book.getCover())
                .skipMemoryCache(true)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.book_cover);

        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtra("title",book.getTitle());
            intent.putExtra("content",book.getContent());
            intent.putExtra("type",book.getType());

            activity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class BooksHolder extends RecyclerView.ViewHolder {

        ImageView book_cover;
        TextView book_title;



        public BooksHolder(@NonNull View itemView) {
            super(itemView);
        book_cover=itemView.findViewById(R.id.book_cover);
        book_title=itemView.findViewById(R.id.book_title);

        }
    }


}
