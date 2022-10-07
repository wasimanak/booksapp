package com.waseem.islamicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.waseem.islamicapp.Adapter.TextBookAdapter;
import com.waseem.islamicapp.Models.TextBook;
import com.waseem.islamicapp.R;
import com.waseem.islamicapp.helper.ViewPdf;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    String title,content,type;

    TextBookAdapter adapter;
    List<TextBook> list = new ArrayList<>();
    ViewPager2 viewPager2;
    LinearLayout linearLayout;
    ProgressBar loader;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();

        title = bundle.getString("title");
        content = bundle.getString("content");
        type = bundle.getString("type");


        linearLayout = findViewById(R.id.pdfviewer);
        loader = findViewById(R.id.pdf_loader);
        viewPager2 = findViewById(R.id.text_book);

        if (type.contains("text")){


            linearLayout.setVisibility(View.GONE);
            loader.setVisibility(View.GONE);
            viewPager2.setVisibility(View.VISIBLE);
            initText();

        }else {

            linearLayout.setVisibility(View.VISIBLE);
            loader.setVisibility(View.VISIBLE);
            viewPager2.setVisibility(View.GONE);

            new ViewPdf(content,linearLayout,loader,DetailActivity.this);

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initText() {


        adapter = new TextBookAdapter(list,DetailActivity.this);
        viewPager2.setAdapter(adapter);

        if (content.contains("~~")){
            String[] data = content.split("~~");

            for (String datum : data) {
                String chaptername = datum.split("~")[0];
                String chaptercontent = datum.split("~")[1];

                list.add(new TextBook(chaptername,chaptercontent));
                adapter.notifyDataSetChanged();

            }


        }else {

            if (content.contains("~")){

                String chaptername = content.split("~")[0];
                String chaptercontent = content.split("~")[1];

                list.add(new TextBook(chaptername,chaptercontent));
                adapter.notifyDataSetChanged();

            }


        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (type.contains("pdf")){

            ViewPdf.stopPdf();

        }

    }
}