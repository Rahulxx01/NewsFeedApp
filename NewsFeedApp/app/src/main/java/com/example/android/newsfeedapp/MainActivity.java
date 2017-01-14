package com.example.android.newsfeedapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private TextView mEmptyStateTextView;
    private static final String NEWS_URL_LINK = "https://content.guardianapis.com/search?api-key=9cee3c0d-c7ae-4a44-8aa1-4bb00c7edad7";
    private static final int NEWS_ID = 1;
    public NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(NEWS_ID,null,this).forceLoad();
       // final ArrayList<News> news = new ArrayList<News>();

       /* news.add(new News("Rahul","Yadav","123","1"));
        news.add(new News("Rahul","Yadav","123","1"));
        news.add(new News("Rahul","Yadav","123","1"));
        news.add(new News("Rahul","Yadav","123","1"));
        news.add(new News("Rahul","Yadav","123","1"));
        news.add(new News("Rahul","Yadav","123","1"));*/

        ListView newsListView = (ListView)findViewById(R.id.list);
        newsAdapter = new NewsAdapter(this,new ArrayList<News>());
        mEmptyStateTextView = (TextView)findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);
        newsListView.setAdapter(newsAdapter);


    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        return new NewsLoader(this,NEWS_URL_LINK);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        newsAdapter.clear();
        if(newsAdapter!=null){
            newsAdapter.addAll(news);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
            newsAdapter.clear();
    }
}
