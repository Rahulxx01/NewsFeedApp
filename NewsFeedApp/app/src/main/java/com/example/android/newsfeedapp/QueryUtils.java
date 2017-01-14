package com.example.android.newsfeedapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.example.android.newsfeedapp.QueryUtils.makeHttpRequest;

/**
 * Created by RAHUL YADAV on 13-01-2017.
 */

public final class QueryUtils {
    public static final String LOG_TAG = "QueryUtils";
    private QueryUtils(){
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
        }catch(MalformedURLException e){
            Log.v(LOG_TAG, String.valueOf(e));
            return null;
        }
        return url;
    }
    public static List<News> fetchNewsData(String requestUrl){
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest(url);
        }catch(IOException e){
            Log.v(LOG_TAG, String.valueOf(e));
        }
        List<News> news = extractNews(jsonResponse);
        return news;
    }
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(5000);
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if(responseCode==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else{
                Log.v(LOG_TAG, String.valueOf(responseCode));
            }
        }catch(IOException e){
            Log.v(LOG_TAG,"problem in retreiving data");
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output =  new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line!=null){
                output.append(line);
                line = reader.readLine();
            }

        }
        return  output.toString();
    }
    private static List<News> extractNews(String newsJson){
        if(TextUtils.isEmpty(newsJson)){
            return null;
        }
        List<News> news = new ArrayList<News>();
        try{
            JSONObject root = new JSONObject(newsJson);
            JSONObject response = root.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");
            for(int i=0;i<results.length();i++){
                JSONObject newsData = results.getJSONObject(i);
                String webTitle = newsData.getString("webTitle");
                String sectionName = newsData.getString("sectionName");
                String webPublicationDate = newsData.getString("webPublicationDate");
                String url = newsData.getString("webUrl");
                news.add(new News(webTitle,sectionName,webPublicationDate,url));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return news;
    }

}
