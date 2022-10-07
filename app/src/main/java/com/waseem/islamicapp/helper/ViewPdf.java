package com.waseem.islamicapp.helper;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class ViewPdf  {

   static  PDFPagerAdapter pagerAdapter;
    RemotePDFViewPager remotePDFViewPager;


    public ViewPdf(String url, LinearLayout pdfLayout, ProgressBar loader, Activity activity) {

        DownloadFile.Listener listener = new DownloadFile.Listener() {
            @Override
            public void onSuccess(String url, String destinationPath) {

                pagerAdapter = new PDFPagerAdapter(activity, FileUtil.extractFileNameFromURL(url));
                remotePDFViewPager.setAdapter(pagerAdapter);
                refreshLayout(pdfLayout);
                loader.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onProgressUpdate(int progress, int total) {

            }
        };
        remotePDFViewPager = new RemotePDFViewPager(activity,url,listener);


    }

    public static void stopPdf(){
        if (pagerAdapter !=null){

            pagerAdapter.close();

        }


    }

    private void refreshLayout(LinearLayout pdfLayout) {

        pdfLayout.addView(remotePDFViewPager,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

    }
}
