package com.example.efhemo.mynews;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.AsyncTaskLoader;
import android.util.Log;

import com.example.efhemo.mynews.data.NewsContract;
import com.example.efhemo.mynews.data.NewsDbHelper;

import java.util.List;

//import android.content.Context;

/**
 * Created by EFHEMO on 2/12/2018.
 */
public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private static final String LOG_TAG =NewsLoader.class.getSimpleName() ;
    private String mUrl;
    private Context context;
    private String tableName;

    public NewsLoader(Context context, String url, String tableName) {
        super(context);
        this.context = context;
        mUrl = url;
        this.tableName = tableName;

    }

    /**
     * Subclasses must implement this to take care of loading their data,
     * as per {@link #startLoading()}.  This is not called by clients directly,
     * but as a result of a call to {@link #startLoading()}.
     */
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {

        SQLiteDatabase db;
        NewsDbHelper dbHelper;
        dbHelper = new NewsDbHelper(context);
        db= dbHelper.getReadableDatabase();
        News news;

        if (mUrl == null) {
            return null;
        }

        List<News> newses = QueryUtils.fetchNewsData(mUrl);

        try{
            //Vector<ContentValues> cVVector = new Vector<ContentValues>(newses.size());
            ContentValues contentValues = new ContentValues();

            for(int i = 0; i<newses.size(); i++){
                news= newses.get(i);
                contentValues.put(NewsContract.COLUMN_NEWS_TITLE, news.getTitle());
                contentValues.put(NewsContract.COLUMN_NEWS_IMAGE, news.getmImages());
                contentValues.put(NewsContract.COLUMN_NEWS_URL, news.getLink());
                contentValues.put(NewsContract.COLUMN_NEWS_DATE, news.getDate());
                //getContext().getContentResolver().bulkInsert()
                db.insert(tableName, null, contentValues);

                //cVVector.add(contentValues);
            }
        }catch(Exception e){
            Log.d("NewsLoader", "Empty fetch data exception "+ e);

        }

        return newses;
    }




}
