package com.example.efhemo.mynews;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class NewsFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;
    public NewsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {

        if(position==0){
            return new HomeFragment();
        }else if(position==1){
            return new CNNFragment();
        }else if(position ==2){
            return new SportFragment();
        }else if (position ==3){
            return new NigeriaSportFragment();
        }else if(position ==4){
            return new BusinessFragment();
        }else{
            return new Entertainment();
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 6;
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "HOME";
            case 1:
                return "CNN";
            case 2:
                return "SPORT";
            case 3:
                return "NG SPORT";
            case 4:
                return "BUSINESS";
            case 5:
                return "ENTERTAINMENT";
            default:
                return null;
        }
    }
}
