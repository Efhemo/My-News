package com.example.efhemo.mynews;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.efhemo.mynews.data.NewsContract;

//import android.app.LoaderManager.LoaderCallbacks;


/**
 * Created by EFHEMO on 2/25/2018.
 */
public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    RecyclerView recyclerView;
    RCAdapter madapter;
    private static final int LOADER_ID = 1;
    SwipeRefreshLayout sw;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView= (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        madapter = new RCAdapter(getContext(), null, NewsContract.NewsEntry.CONTENT_URI);
        recyclerView.setAdapter(madapter);

        return rootView;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }


    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        /*This are gooig to be done after research if cursorload can do all this
        * check stackOverflow and https://www.androiddesignpatterns.com/2012/08/implementing-loaders.html for some hint*/
        //TODO: OVERRIDE onStartLoading and call forceLoad on it and remove the on from the initialise
        //TODO: see if you should also call takeContentChanged
        //TODO: overide onStopLoading and call cancelLoad on it
        //TODO: also take care of deliverResult method


        switch (id){
            case LOADER_ID:
                String[] projection = {NewsContract.NewsEntry._ID,
                        NewsContract.COLUMN_NEWS_TITLE,
                        NewsContract.COLUMN_NEWS_IMAGE,
                        NewsContract.COLUMN_NEWS_DATE};

                /*This loader will execute the Content Provider QUERY method in a background thread
                *Cursor loader is a loader that QUERIES the ContentResolver and returns a CURSOR
                * The CONTENT_URI uses the Authority Uri to access the content provider
                * The CONTENT_URI is a URI that identifies data in a provider*/
                return new CursorLoader(getContext(),
                        NewsContract.NewsEntry.CONTENT_URI, // provider content URI to query content://com.android.example/pets/
                        projection,
                        null,
                        null, null /*NewsContract.COLUMN_NEWS_DATE+" DESC"*/); //default sort order
            default:
                //this is an invalid id loader
                return null;
        }
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        madapter.swapCursor(data);
        madapter.notifyDataSetChanged();

    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        madapter.swapCursor(null);

    }

    /*public void sendIntent(int position) {
        Intent intent = new Intent(getContext(), webActivity.class);
        Uri contentUri = ContentUris.withAppendedId(NewsContract.CONTENT_URI, position+1);
        cursor = getContext().getContentResolver().query(contentUri, new String[]{NewsContract.NewsEntry._ID,
                NewsContract.NewsEntry.COLUMN_NEWS_TITLE,
                NewsContract.NewsEntry.COLUMN_NEWS_URL}, null, null, null);

        if (cursor == null || cursor.getCount() < 1) {
            //Log.d("RCAdapter","cursor "+cursor.getCount());
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {
            // Find the columns of pet attributes that we're interested in
            int nameColumnIndex = cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NEWS_TITLE);
            int urlColumnIndex = cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NEWS_URL);

            // Extract out the value from the Cursor for the given column index
            String title = cursor.getString(nameColumnIndex);
            String url = cursor.getString(urlColumnIndex);

            intent.putExtra("title", title);
            intent.putExtra("url", url);

            startActivity(intent);
        }
    }*/

}
