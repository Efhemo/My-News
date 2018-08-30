package com.example.efhemo.mynews;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.efhemo.mynews.data.NewsContract;

/**
 * Created by EFHEMO on 2/26/2018.
 */
public class  SportFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    RecyclerView recyclerView;
    RCAdapter adapter;
    private static final int LOADER_ID = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        /*TextView textView = (TextView)rootView.findViewById(R.id.helloWorld);
        textView.setText("Sport News");*/

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView= (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);


        adapter = new RCAdapter(getContext(), null, NewsContract.NewsSportEntry.CONTENT_URI_SPORT);
        recyclerView.setAdapter(adapter);

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
                return new android.support.v4.content.CursorLoader(getContext(),
                        NewsContract.NewsSportEntry.CONTENT_URI_SPORT, // provider content URI to query content://com.android.example/pets/
                        projection,
                        null,
                        null,null
                        /*NewsContract.COLUMN_NEWS_DATE+" DESC"*/); //default sort order
            default:
                //this is an invalid id loader
                return null;
        }
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);

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
        adapter.swapCursor(null);

    }

}
