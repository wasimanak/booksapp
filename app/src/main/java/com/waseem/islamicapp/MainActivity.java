package com.waseem.islamicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.waseem.islamicapp.Adapter.BookAdapter;
import com.waseem.islamicapp.Models.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Book> list = new ArrayList<>();
    RecyclerView recyclerView;
    BookAdapter adapter;
    ProgressBar loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        loader = findViewById(R.id.loader);

        recyclerView = findViewById(R.id.recycler_books);
        adapter = new BookAdapter(list,MainActivity.this);
        recyclerView.setAdapter(adapter);

        loader.setVisibility(View.VISIBLE);

        loadData();




    }

    private void loadData() {
        FirebaseDatabase.getInstance().getReference().child("Books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                loader.setVisibility(View.GONE);
                list.clear();
                adapter.notifyDataSetChanged();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    String title = dataSnapshot.child("title").getValue().toString();
                    String content = dataSnapshot.child("content").getValue().toString();
                    String cover = dataSnapshot.child("cover").getValue().toString();
                    String type = dataSnapshot.child("type").getValue().toString();

                    list.add(new Book(title,cover,content,type));
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}