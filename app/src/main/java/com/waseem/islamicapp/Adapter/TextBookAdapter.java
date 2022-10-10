package com.waseem.islamicapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waseem.islamicapp.Models.TextBook;
import com.waseem.islamicapp.R;

import java.util.List;

public class TextBookAdapter extends RecyclerView.Adapter<TextBookAdapter.TextBookHolder> {


    List<TextBook> list;
    Activity activity;

    public TextBookAdapter(List<TextBook> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TextBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextBookHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_textbooks,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextBookHolder holder, int position) {

        TextBook textBook = list .get(position);

        holder.chapter.setText(textBook.getChapter());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TextBookHolder extends RecyclerView.ViewHolder {

        TextView chaptername,chapter;

        public TextBookHolder(@NonNull View itemView) {
            super(itemView);


            chapter = itemView.findViewById(R.id.book_title);
        }
    }

}
