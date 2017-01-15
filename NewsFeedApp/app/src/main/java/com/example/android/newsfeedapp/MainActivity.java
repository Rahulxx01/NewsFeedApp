package com.example.android.newsfeedapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private TextView mEmptyStateTextView;
    private static final String NEWS_URL_LINK = "https://content.guardianapis.com/search?api-key=9cee3c0d-c7ae-4a44-8aa1-4bb00c7edad7";
    private static final int NEWS_ID = 1;
    public NewsAdapter newsAdapter;
    public ListView newsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check internetConnection//
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network//
        NetworkInfo netInformation = connectivityManager.getActiveNetworkInfo();
        // If there is a network connection, then fetch data//
        if(netInformation!=null && netInformation.isConnected()){
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_ID,null,this).forceLoad();

        }else{
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible//
            View loadingIndicator = (View)findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(GONE);
            // Display empty state with no connection error message
            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            mEmptyStateTextView.setText("No internet Connection");

        }



       // final ArrayList<News> news = new ArrayList<News>();

       /* news.add(new News("Rahul","Yadav","123","1"));
        news.add(new News("Rahul","Yadav","123","1"));
        news.add(new News("Rahul","Yadav","123","1"));
        news.add(new News("Rahul","Yadav","123","1"));
        news.add(new News("Rahul","Yadav","123","1"));
        news.add(new News("Rahul","Yadav","123","1"));*/

        newsListView = (ListView)findViewById(R.id.list);
        newsAdapter = new NewsAdapter(this,new ArrayList<News>());
        newsListView.setAdapter(newsAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current news that was clicked on//
                News currentNews = newsAdapter.getItem(position);
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getmUrl());
                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });


    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        return new NewsLoader(this,NEWS_URL_LINK);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        newsAdapter.clear();
        View loadingIndicator = (View) findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(GONE);
        if(newsAdapter!=null){
            newsAdapter.addAll(news);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
            newsAdapter.clear();
    }
}
