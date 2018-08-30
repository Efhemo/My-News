package com.example.efhemo.mynews.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.efhemo.mynews.data.NewsContract.NewsEntry;

/**
 * Created by EFHEMO on 9/11/2017.
 */
public class NewsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "news.db";
    private static final int DATABASE_VERSION = 1;



    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     *                <p>
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public NewsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATE TABLE "table Name" (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, breed TEXT, weight INTEGER);
        //This is our DataBase TABLE
        String SQL_CREATE_NEWS_TABLE = "CREATE TABLE " + NewsEntry.TABLE_NAME + " ("
                + NewsEntry._ID + " INTEGER PRIMARY KEY, "
                + NewsContract.COLUMN_NEWS_TITLE + " TEXT UNIQUE  NOT NULL, "
                + NewsContract.COLUMN_NEWS_IMAGE + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_URL + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_DATE + " INTEGER NOT NULL DEFAULT 0);";

        String SQL_CREATE_NEWS_TABLE_CNN = "CREATE TABLE " + NewsContract.NewsCNNEntry.TABLE_NAME_CCN + " ("
                + NewsContract.NewsCNNEntry._ID + " INTEGER PRIMARY KEY, "
                + NewsContract.COLUMN_NEWS_TITLE + " TEXT UNIQUE  NOT NULL, "
                + NewsContract.COLUMN_NEWS_IMAGE + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_URL + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_DATE + " INTEGER NOT NULL DEFAULT 0);";

        String SQL_CREATE_NEWS_TABLE_SPORT = "CREATE TABLE " + NewsContract.NewsSportEntry.TABLE_NAME_SPORT + " ("
                + NewsContract.NewsSportEntry._ID + " INTEGER PRIMARY KEY, "
                + NewsContract.COLUMN_NEWS_TITLE + " TEXT UNIQUE NOT NULL, "
                + NewsContract.COLUMN_NEWS_IMAGE + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_URL + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_DATE + " INTEGER NOT NULL DEFAULT 0);";

        String SQL_CREATE_NEWS_TABLE_NGSPORT = "CREATE TABLE " + NewsContract.NewsNGSportEntry.TABLE_NAME_NGSPORT + " ("
                + NewsContract.NewsNGSportEntry._ID + " INTEGER PRIMARY KEY, "
                + NewsContract.COLUMN_NEWS_TITLE + " TEXT UNIQUE NOT NULL, "
                + NewsContract.COLUMN_NEWS_IMAGE + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_URL + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_DATE + " INTEGER NOT NULL DEFAULT 0);";

        String SQL_CREATE_NEWS_TABLE_BUSINESS = "CREATE TABLE " + NewsContract.NewsBusinessEntry.TABLE_NAME_BUSINESS + " ("
                + NewsContract.NewsBusinessEntry._ID + " INTEGER PRIMARY KEY, "
                + NewsContract.COLUMN_NEWS_TITLE + " TEXT UNIQUE NOT NULL, "
                + NewsContract.COLUMN_NEWS_IMAGE + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_URL + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_DATE + " INTEGER NOT NULL DEFAULT 0);";

        String SQL_CREATE_NEWS_TABLE_ENTERTAINMENT = "CREATE TABLE " + NewsContract.NewsEnterEntry.TABLE_NAME_ENTERTAINMENT+ " ("
                + NewsContract.NewsEnterEntry._ID + " INTEGER PRIMARY KEY, "
                + NewsContract.COLUMN_NEWS_TITLE + " TEXT UNIQUE NOT NULL, "
                + NewsContract.COLUMN_NEWS_IMAGE + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_URL + " TEXT NOT NULL, "
                + NewsContract.COLUMN_NEWS_DATE + " INTEGER NOT NULL DEFAULT 0);";


        db.execSQL(SQL_CREATE_NEWS_TABLE);

        db.execSQL(SQL_CREATE_NEWS_TABLE_CNN);
        db.execSQL(SQL_CREATE_NEWS_TABLE_SPORT);
        db.execSQL(SQL_CREATE_NEWS_TABLE_NGSPORT);
        db.execSQL(SQL_CREATE_NEWS_TABLE_BUSINESS);
        db.execSQL(SQL_CREATE_NEWS_TABLE_ENTERTAINMENT);

    }


    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ NewsEntry.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS "+ NewsContract.NewsCNNEntry.TABLE_NAME_CCN);
        db.execSQL("DROP TABLE IF EXISTS "+ NewsContract.NewsSportEntry.TABLE_NAME_SPORT);
        db.execSQL("DROP TABLE IF EXISTS "+ NewsContract.NewsNGSportEntry.TABLE_NAME_NGSPORT);
        db.execSQL("DROP TABLE IF EXISTS "+ NewsContract.NewsBusinessEntry.TABLE_NAME_BUSINESS);
        db.execSQL("DROP TABLE IF EXISTS "+ NewsContract.NewsEnterEntry.TABLE_NAME_ENTERTAINMENT);
        onCreate(db);
    }
}
