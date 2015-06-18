package dev.sfilizzola.bghub;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

/**
 * Created by Samuel on 06/06/2015.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private String[] tabs;
    private Context mContext;
    private int icons[];


    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        tabs = context.getResources().getStringArray(R.array.tabs);
        icons = new int[]{R.drawable.ic_tab_hots,R.drawable.ic_tab_collection, R.drawable.ic_tab_profile};
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

        Drawable image = mContext.getResources().getDrawable(icons[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }


}
