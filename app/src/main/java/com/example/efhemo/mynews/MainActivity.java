package com.example.efhemo.mynews;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.efhemo.mynews.data.NewsContract;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>
{

    //Add your Api key: apikey=newsapikey to all SAMPLE_JSON-RESPONSE
    private static final String SAMPLE_JSON_RESPONSE =
            "https://newsapi.org/v2/top-headlines?country=us&apikey=";

    private static final String SAMPLE_JSON_RESPONSE_CNN =
            "https://newsapi.org/v2/top-headlines?sources=cnn&apikey=";

    private static final String SAMPLE_JSON_RESPONSE_SPORT =
            "https://newsapi.org/v2/top-headlines?country=gb&category=sports&apikey=";

    private static final String SAMPLE_JSON_RESPONSE_NGSPORT =
            "https://newsapi.org/v2/top-headlines?country=ng&category=sports&apikey=";
    private static final String SAMPLE_JSON_RESPONSE_BUSINESS =
            "https://newsapi.org/v2/top-headlines?country=ng&category=business&apikey=";

    private static final String SAMPLE_JSON_RESPONSE_ENTERTAINMENT =
            "https://newsapi.org/v2/top-headlines?country=ng&category=entertainment&apikey=";


    private static String LOG_TAG = MainActivity.class.getSimpleName();
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*if (findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new HomeFragment()).commit();
        }*/

        getSupportFragmentManager().beginTransaction().commit();
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu); //add the navigation drawer button

        final ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);

        NewsFragmentAdapter newsFragmentAdapter = new NewsFragmentAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(newsFragmentAdapter);


        TabLayout tabLayout = (TabLayout)findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        drawerLayout.removeDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id =item.getItemId();
                switch (id){
                    case R.id.navigation_menu_bbc_news:
                        //Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(0);
                        /*if (findViewById(R.id.fragment_container) != null){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container,new HomeFragment()).commit();
                        }*/
                        break;
                    case R.id.navigation_menu_cnn_news:
                        //Toast.makeText(MainActivity.this, "cnn", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(1);
                        /*if (findViewById(R.id.fragment_container) != null){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container,new CNNFragment()).commit();
                        }*/
                        break;
                    case R.id.navigation_menu_sport:
                        //Toast.makeText(MainActivity.this, "sport", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(2);
                        /*if (findViewById(R.id.fragment_container) != null){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container,new SportFragment()).commit();
                        }*/
                        break;
                    case R.id.navigation_menu_nigeria_sport_news:
                        //Toast.makeText(MainActivity.this, "nigeria sport news", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(3);
                        /*if (findViewById(R.id.fragment_container) != null){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container,new NigeriaSportFragment()).commit();
                        }*/
                        break;
                    case R.id.navigation_menu_business_news:
                        //Toast.makeText(MainActivity.this, "business news", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(4);
                        /*if (findViewById(R.id.fragment_container) != null){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container,new BusinessFragment()).commit();
                        }*/
                        break;
                    case R.id.navigation_menu_entertainment:
                        //Toast.makeText(MainActivity.this, "entertainment", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(5);
                        /*if (findViewById(R.id.fragment_container) != null){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container,new Entertainment()).commit();
                        }*/
                        break;
                    default:
                        Log.d(LOG_TAG, "non of the menuitem pressed");
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });


        getLoaderManager().initLoader(0, null, this).forceLoad();
    }

    private void beginConnect(){
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        // If there is a network connection, fetch data
        if ((networkInfo != null && networkInfo.isConnected()) ) {

            //Make sure the Error Msg is gone if there is network
            //mEmptyStateTextView.setVisibility(View.GONE);

            // Get a reference to the LoaderManager, in order to interact with loaders.


            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            getLoaderManager().restartLoader(3, null, this).forceLoad();
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible

            Log.e(LOG_TAG, "problem with connection");

            //building AlertDialog.Builder if no Network
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Connection Problem");
            alert.setMessage("Check your connection settings and refresh");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    beginConnect();
                }
            });
            alert.create().show();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case android.R.id.home: //by default
                drawerLayout.openDrawer(GravityCompat.START);
                return  true;
            case R.id.refresh_button:
                beginConnect();
                return  true;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        new NewsLoader(this, SAMPLE_JSON_RESPONSE, NewsContract.NewsEntry.TABLE_NAME).startLoading();
        new NewsLoader(this, SAMPLE_JSON_RESPONSE_CNN, NewsContract.NewsCNNEntry.TABLE_NAME_CCN).startLoading();
        new NewsLoader(this, SAMPLE_JSON_RESPONSE_SPORT, NewsContract.NewsSportEntry.TABLE_NAME_SPORT).startLoading();
        new NewsLoader(this, SAMPLE_JSON_RESPONSE_NGSPORT, NewsContract.NewsNGSportEntry.TABLE_NAME_NGSPORT).startLoading();
        new NewsLoader(this, SAMPLE_JSON_RESPONSE_BUSINESS, NewsContract.NewsBusinessEntry.TABLE_NAME_BUSINESS).startLoading();
        return new NewsLoader(this, SAMPLE_JSON_RESPONSE_ENTERTAINMENT, NewsContract.NewsEnterEntry.TABLE_NAME_ENTERTAINMENT);

    }


    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        getContentResolver().delete(NewsContract.NewsEntry.CONTENT_URI, null, null);

        getContentResolver().delete(NewsContract.NewsCNNEntry.CONTENT_URI_CCN, null, null);
        getContentResolver().delete(NewsContract.NewsSportEntry.CONTENT_URI_SPORT, null, null);
        getContentResolver().delete(NewsContract.NewsNGSportEntry.CONTENT_URI_NGSPORT, null, null);
        getContentResolver().delete(NewsContract.NewsBusinessEntry.CONTENT_URI_BUSINESS, null, null);
        getContentResolver().delete(NewsContract.NewsEnterEntry.CONTENT_URI_ENTERTAINMENT, null, null);

    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }
}
