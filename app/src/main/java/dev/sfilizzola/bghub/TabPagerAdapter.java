package dev.sfilizzola.bghub;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.content.Context;

/**
 * Created by Samuel on 06/06/2015.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private String[] tabs;
    private Context mContext;


    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        tabs = context.getResources().getStringArray(R.array.tabs);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new HotsFragment();
            case 1:
                return new CollectionFragment();
            case 2:
                return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }


}
