package hzkj.cc.my_fragment_viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyFragmentAdaper extends FragmentPagerAdapter {
    private Context mContext;
    private List<ScrollItem> list;

    public MyFragmentAdaper(FragmentManager fm, List<ScrollItem> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i).fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
