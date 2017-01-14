package com.example.android.newsfeedapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RAHUL YADAV on 13-01-2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context,0,news);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item,parent,false);
        }
        News currentNews = getItem(position);
        //Get textview of the Title section//
        TextView titleTextView = (TextView)listItemView.findViewById(R.id.webTitle);
        //set the titleTextview//
        titleTextView.setText(currentNews.getmWebTitle());
        //Get textView of Section name//
        TextView sectionTextView = (TextView)listItemView.findViewById(R.id.sectionName);
        //set sectionTextView//
        sectionTextView.setText(currentNews.getmSectionName());
        //get textView for submission date//
        TextView webPubDateTextView = (TextView)listItemView.findViewById(R.id.webPublicationDate);
        //set WebPubDateTextview//
        webPubDateTextView.setText(currentNews.getmWebPublicationDate());

        return listItemView;

    }
}
