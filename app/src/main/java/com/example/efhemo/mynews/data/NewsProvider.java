package com.example.efhemo.mynews.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by EFHEMO on 9/18/2017.
 */
public class NewsProvider extends ContentProvider {

    public static final String LOG_TAG = NewsProvider.class.getSimpleName();

    //Database helper object
    private NewsDbHelper mDbHelper;


    private static final int NEWS = 100;

    private static final int NEWS_CCN = 200;
    private static final int NEWS_SPORT = 300;
    private static final int NEWS_NGSPORT = 400;
    private static final int NEWS_BUSINESS = 500;
    private static final int NEWS_ENTERTAINMENT = 600;

    private static final int NEWS_ID = 101;
    private static final int NEWS_ID_CCN = 102;
    private static final int NEWS_ID_SPORT = 103;
    private static final int NEWS_ID_NGSPORT = 104;
    private static final int NEWS_ID_BUSINESS = 105;
    private static final int NEWS_ID_ENTERTAINMENT = 106;

    private static final UriMatcher sUriMather = new UriMatcher(UriMatcher.NO_MATCH);

    static {

         /*we need to set up if multiple row or single row*/
        //"com.example.efhemo.animals"
        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS, NEWS);

        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS_CCN, NEWS_CCN);
        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS_SPORT, NEWS_SPORT);
        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS_NGSPORT, NEWS_NGSPORT);
        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS_BUSINESS, NEWS_BUSINESS);
        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS_ENTERTAINMENT, NEWS_ENTERTAINMENT);

        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS + "/#", NEWS_ID);// THIS WILL HAVE ITS COLLEGUES LATER ON
        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS_CCN + "/#", NEWS_ID_CCN);

        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS_SPORT + "/#", NEWS_ID_SPORT);
        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS_NGSPORT + "/#", NEWS_ID_NGSPORT);
        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS_BUSINESS + "/#", NEWS_ID_BUSINESS);
        sUriMather.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS_ENTERTAINMENT + "/#", NEWS_ID_ENTERTAINMENT);
    }

    /**
     * Implement this to initialize your content provider on startup.
     * This method is called for all registered content providers on the
     * application main thread at application launch time.  It must not perform
     * lengthy operations, or application startup will be delayed.
     * <p>
     * <p>You should defer nontrivial initialization (such as opening,
     * upgrading, and scanning databases) until the content provider is used
     * (via {@link #query}, {@link #insert}, etc).  Deferred initialization
     * keeps application startup fast, avoids unnecessary work if the provider
     * turns out not to be needed, and stops database errors (such as a full
     * disk) from halting application launch.
     * <p>
     * <p>If you use SQLite, {@link SQLiteOpenHelper}
     * is a helpful utility class that makes it easy to manage databases,
     * and will automatically defer opening until first use.  If you do use
     * SQLiteOpenHelper, make sure to avoid calling
     * {@link SQLiteOpenHelper#getReadableDatabase} or
     * {@link SQLiteOpenHelper#getWritableDatabase}
     * from this method.  (Instead, override
     * {@link SQLiteOpenHelper#onOpen} to initialize the
     * database when it is first opened.)
     *
     * @return true if the provider was successfully loaded, false otherwise
     */
    @Override
    public boolean onCreate() {
        mDbHelper = new NewsDbHelper(getContext());
        return true;
    }


    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;
        //this will check if the uri sent by the client is multiple row or single row
        /** As specify in the static, if it is multiple row, UriMatcher returns = 100,
         *  if it is a single row, the uri matcher returns = 101*/
        int match = sUriMather.match(uri);
        switch(match){
                /**if it is multiple(ALLROWS) row = 100*/
            case NEWS:
                cursor = database.query(
                        NewsContract.NewsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null,
                        sortOrder);
                break;

            case NEWS_CCN:
                cursor = database.query(
                        NewsContract.NewsCNNEntry.TABLE_NAME_CCN, projection, selection, selectionArgs,
                        null, null,
                        sortOrder);
                break;

            case NEWS_SPORT:
                cursor = database.query(
                        NewsContract.NewsSportEntry.TABLE_NAME_SPORT, projection, selection, selectionArgs,
                        null, null,
                        sortOrder);
                break;

            case NEWS_NGSPORT:
                cursor = database.query(
                        NewsContract.NewsNGSportEntry.TABLE_NAME_NGSPORT, projection, selection, selectionArgs,
                        null, null,
                        sortOrder);
                break;

            case NEWS_BUSINESS:
                cursor = database.query(
                        NewsContract.NewsBusinessEntry.TABLE_NAME_BUSINESS, projection, selection, selectionArgs,
                        null, null,
                        sortOrder);
                break;

            case NEWS_ENTERTAINMENT:
                cursor = database.query(
                        NewsContract.NewsEnterEntry.TABLE_NAME_ENTERTAINMENT, projection, selection, selectionArgs,
                        null, null,
                        sortOrder);
                break;
                /**if it is single row =101*/
            case NEWS_ID:
                selection = NewsContract.NewsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))}; //use parseId to extract Id from a Uri
                cursor = database.query(
                        NewsContract.NewsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case NEWS_ID_CCN:
                selection = NewsContract.NewsCNNEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))}; //use parseId to extract Id from a Uri
                cursor = database.query(
                        NewsContract.NewsCNNEntry.TABLE_NAME_CCN, projection, selection, selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case NEWS_ID_SPORT:
                selection = NewsContract.NewsSportEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))}; //use parseId to extract Id from a Uri
                cursor = database.query(
                        NewsContract.NewsSportEntry.TABLE_NAME_SPORT, projection, selection, selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case NEWS_ID_NGSPORT:
                selection = NewsContract.NewsNGSportEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))}; //use parseId to extract Id from a Uri
                cursor = database.query(
                        NewsContract.NewsNGSportEntry.TABLE_NAME_NGSPORT, projection, selection, selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case NEWS_ID_BUSINESS:
                selection = NewsContract.NewsBusinessEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))}; //use parseId to extract Id from a Uri
                cursor = database.query(
                        NewsContract.NewsBusinessEntry.TABLE_NAME_BUSINESS, projection, selection, selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case NEWS_ID_ENTERTAINMENT:
                selection = NewsContract.NewsEnterEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))}; //use parseId to extract Id from a Uri
                cursor = database.query(
                        NewsContract.NewsEnterEntry.TABLE_NAME_ENTERTAINMENT, projection, selection, selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException("cannot resolve uri "+ uri);
        }
        //set notification URI on the cursor
        //if the data at this URI changes, then we know we need to update the cursor
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    /**
     * Implement this to handle requests for the MIME type of the data at the
     * given URI.  The returned MIME type should start with
     * <code>vnd.android.cursor.item</code> for a single record,
     * or <code>vnd.android.cursor.dir/</code> for multiple items.
     * This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     * <p>
     * <p>Note that there are no permissions needed for an application to
     * access this information; if your content provider requires read and/or
     * write permissions, or is not exported, all applications can still call
     * this method regardless of their access permissions.  This allows them
     * to retrieve the MIME type for a URI when dispatching intents.
     *
     * @param uri the URI to query.
     * @return a MIME type string, or {@code null} if there is no type.
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        final int match = sUriMather.match(uri);
        switch (match){
            case NEWS:
                return NewsContract.NewsEntry.CONTENT_LIST_TYPE;
            case NEWS_ID:
                return NewsContract.NewsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("cannot resolve uri " + uri + "with match" + match);
        }
    }


    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        int match  = sUriMather.match(uri);
        switch (match){
            case NEWS:
                return insertPet(uri, values);
                default:
                    new IllegalArgumentException("Insertion is not supported for "+ uri);
        }
        return uri;
    }

    private Uri insertPet(Uri uri, ContentValues values) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        long Rowid = database.insert(NewsContract.NewsEntry.TABLE_NAME,
                null, values);

        if(Rowid == -1){
            Log.e(LOG_TAG, "Failed to insert row for "+ uri);
        }

        //notify all listeners that the data has change for the content URI
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, Rowid);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
             int count = 0;
        final int match  = sUriMather.match(uri);
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        switch (match){
            case NEWS:
                database.execSQL("DELETE FROM "+ NewsContract.NewsEntry.TABLE_NAME+" WHERE "+
                        NewsContract.NewsEntry._ID+" IN (SELECT "+ NewsContract.NewsEntry._ID+ " FROM "
                        +NewsContract.NewsEntry.TABLE_NAME+" ORDER BY "+ NewsContract.COLUMN_NEWS_DATE+" DESC LIMIT -1 OFFSET 50)");
                //count = database.delete(NewsContract.NewsEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case NEWS_CCN:
                database.execSQL("DELETE FROM "+ NewsContract.NewsCNNEntry.TABLE_NAME_CCN+" WHERE "+
                        NewsContract.NewsCNNEntry._ID+" IN (SELECT "+ NewsContract.NewsCNNEntry._ID+ " FROM "
                        +NewsContract.NewsCNNEntry.TABLE_NAME_CCN+" ORDER BY "+ NewsContract.COLUMN_NEWS_DATE+" DESC LIMIT -1 OFFSET 50);");
                //count = database.delete(NewsContract.NewsEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case NEWS_SPORT:
                database.execSQL("DELETE FROM "+ NewsContract.NewsSportEntry.TABLE_NAME_SPORT+" WHERE "+
                        NewsContract.NewsSportEntry._ID+" IN (SELECT "+ NewsContract.NewsSportEntry._ID+ " FROM "
                        +NewsContract.NewsSportEntry.TABLE_NAME_SPORT+" ORDER BY "+ NewsContract.COLUMN_NEWS_DATE+" DESC LIMIT -1 OFFSET 50);");
                //count = database.delete(NewsContract.NewsEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case NEWS_NGSPORT:
                database.execSQL("DELETE FROM "+ NewsContract.NewsNGSportEntry.TABLE_NAME_NGSPORT+" WHERE "+
                        NewsContract.NewsNGSportEntry._ID+" IN (SELECT "+ NewsContract.NewsNGSportEntry._ID+ " FROM "
                        +NewsContract.NewsNGSportEntry.TABLE_NAME_NGSPORT+" ORDER BY "+ NewsContract.COLUMN_NEWS_DATE+" DESC LIMIT -1 OFFSET 50);");
                //count = database.delete(NewsContract.NewsEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case NEWS_BUSINESS:
                database.execSQL("DELETE FROM "+ NewsContract.NewsBusinessEntry.TABLE_NAME_BUSINESS+" WHERE "+
                        NewsContract.NewsBusinessEntry._ID+" IN (SELECT "+ NewsContract.NewsBusinessEntry._ID+ " FROM "
                        +NewsContract.NewsBusinessEntry.TABLE_NAME_BUSINESS+" ORDER BY "+ NewsContract.COLUMN_NEWS_DATE+" DESC LIMIT -1 OFFSET 50);");
                //count = database.delete(NewsContract.NewsEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case NEWS_ENTERTAINMENT:
                database.execSQL("DELETE FROM "+ NewsContract.NewsEnterEntry.TABLE_NAME_ENTERTAINMENT+" WHERE "+
                        NewsContract.NewsEnterEntry._ID+" IN (SELECT "+ NewsContract.NewsEnterEntry._ID+ " FROM "
                        +NewsContract.NewsEnterEntry.TABLE_NAME_ENTERTAINMENT+" ORDER BY "+ NewsContract.COLUMN_NEWS_DATE+" DESC LIMIT -1 OFFSET 50);");
                //count = database.delete(NewsContract.NewsEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case NEWS_ID:
                selection = NewsContract.NewsEntry._ID +"=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return database.delete(NewsContract.NewsEntry.TABLE_NAME, selection, selectionArgs);
            default:
                new IllegalArgumentException("Insertion is not supported for "+ uri);
        }
        return count;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        final int match  = sUriMather.match(uri);
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        switch (match){
            case NEWS:
                count = database.update(NewsContract.NewsEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case NEWS_ID:
                selection = NewsContract.NewsEntry._ID +"=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return database.update(NewsContract.NewsEntry.TABLE_NAME, values, selection, selectionArgs);
            default:
                new IllegalArgumentException("Insertion is not supported for "+ uri);
        }
        return count;
    }
}
