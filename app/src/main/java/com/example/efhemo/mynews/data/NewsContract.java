package com.example.efhemo.mynews.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by EFHEMO on 9/11/2017.
 */
public final class NewsContract {

    private NewsContract() {
    }

    /*All these are needed for content provider*/
    public static final String CONTENT_AUTHORITY= "com.example.efhemo.mynews";
    public static final  String PATH_NEWS = "news"; //usually the table name

    public static final  String PATH_NEWS_CCN = "ccn";
    public static final  String PATH_NEWS_SPORT = "sport";
    public static final  String PATH_NEWS_NGSPORT = "ngsport";
    public static final  String PATH_NEWS_BUSINESS = "business";
    public static final  String PATH_NEWS_ENTERTAINMENT = "entertainment";

    public final static String COLUMN_NEWS_TITLE = "name";
    public final static String COLUMN_NEWS_IMAGE = "image";
    public final static String COLUMN_NEWS_URL ="link";
    public final static String COLUMN_NEWS_DATE = "date";


    public static final Uri BASE_CONTENT_URI= Uri.parse("content://" + CONTENT_AUTHORITY); //(Parse) Creates Uri
     //THIS IS JUST LIKE SPECIFYING THE TABLE YOU WANT


    //or public static final Uri OUR_URI = Uri.parse("content://com.example.efhemo.animals/pets");

    public static final class NewsEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NEWS);


        public final static String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +CONTENT_AUTHORITY + "/"+ PATH_NEWS;
        public final static String CONTENT_ITEM_TYPE= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY +"/" + PATH_NEWS;

        public final static String TABLE_NAME = "news";
        public final static String _ID = BaseColumns._ID;


    }

    public static final class NewsCNNEntry implements BaseColumns{
        public static final Uri CONTENT_URI_CCN = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NEWS_CCN);

        public final static String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +CONTENT_AUTHORITY + "/"+ PATH_NEWS;
        public final static String CONTENT_ITEM_TYPE= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY +"/" + PATH_NEWS;


        public final static String TABLE_NAME_CCN = "ccn";

        public final static String _ID = BaseColumns._ID;
        /*public final static String COLUMN_NEWS_TITLE = "name";
        public final static String COLUMN_NEWS_IMAGE = "image";
        public final static String COLUMN_NEWS_URL ="link";
        public final static String COLUMN_NEWS_DATE = "date";*/
    }

    public static final class NewsSportEntry implements BaseColumns{
        //TODO: wont you call .buid on the uri?
        public static final Uri CONTENT_URI_SPORT = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NEWS_SPORT);

        public final static String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +CONTENT_AUTHORITY + "/"+ PATH_NEWS;
        public final static String CONTENT_ITEM_TYPE= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY +"/" + PATH_NEWS;


        public final static String TABLE_NAME_SPORT = "sport";

        public final static String _ID = BaseColumns._ID;
        /*public final static String COLUMN_NEWS_TITLE = "name";
        public final static String COLUMN_NEWS_IMAGE = "image";
        public final static String COLUMN_NEWS_URL ="link";
        public final static String COLUMN_NEWS_DATE = "date";*/
    }

    public static final class NewsNGSportEntry implements BaseColumns{

        //TODO: wont you call .buid on the uri?
        public static final Uri CONTENT_URI_NGSPORT = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NEWS_NGSPORT);

        public final static String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +CONTENT_AUTHORITY + "/"+ PATH_NEWS;
        public final static String CONTENT_ITEM_TYPE= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY +"/" + PATH_NEWS;

        public final static String TABLE_NAME_NGSPORT = "ngsport";

        public final static String _ID = BaseColumns._ID;
        /*public final static String COLUMN_NEWS_TITLE = "name";
        public final static String COLUMN_NEWS_IMAGE = "image";
        public final static String COLUMN_NEWS_URL ="link";
        public final static String COLUMN_NEWS_DATE = "date";*/
    }

    public static final class NewsBusinessEntry implements BaseColumns{

        public static final Uri CONTENT_URI_BUSINESS = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NEWS_BUSINESS);
        public final static String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +CONTENT_AUTHORITY + "/"+ PATH_NEWS;
        public final static String CONTENT_ITEM_TYPE= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY +"/" + PATH_NEWS;

        public final static String TABLE_NAME_BUSINESS = "business";

        public final static String _ID = BaseColumns._ID;
        /*public final static String COLUMN_NEWS_TITLE = "name";
        public final static String COLUMN_NEWS_IMAGE = "image";
        public final static String COLUMN_NEWS_URL ="link";
        public final static String COLUMN_NEWS_DATE = "date";*/

    }

    public static final class NewsEnterEntry implements BaseColumns{
        public static final Uri CONTENT_URI_ENTERTAINMENT = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NEWS_ENTERTAINMENT);

        public final static String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +CONTENT_AUTHORITY + "/"+ PATH_NEWS;
        public final static String CONTENT_ITEM_TYPE= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY +"/" + PATH_NEWS;

       /* public final static String TABLE_NAME = "news";

        public final static String TABLE_NAME_CCN = "ccn";
        public final static String TABLE_NAME_SPORT = "sport";
        public final static String TABLE_NAME_NGSPORT = "ngsport";
        public final static String TABLE_NAME_BUSINESS = "business";*/
        public final static String TABLE_NAME_ENTERTAINMENT = "entertainment";

        public final static String _ID = BaseColumns._ID;
        /*public final static String COLUMN_NEWS_TITLE = "name";
        public final static String COLUMN_NEWS_IMAGE = "image";
        public final static String COLUMN_NEWS_URL ="link";
        public final static String COLUMN_NEWS_DATE = "date";*/
    }
}
