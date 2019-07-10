package hzkj.cc.my_fragment_viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewPager extends LinearLayout {
    List<ScrollItem> list;
    FragmentManager manager;
    IndicatorBar indicatorBar;
    ViewPager viewPager;
    private float lastValue = -1;
    private boolean isLeft;
    OnMovePageListenner listenner;

    public void setListenner(OnMovePageListenner listenner) {
        this.listenner = listenner;
    }

    public FragmentViewPager(Context context) {
        super(context);
    }

    public FragmentViewPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setList(List<ScrollItem> list, FragmentManager manager) {
        this.list = list;
        this.manager = manager;
        init();
    }

    public void init() {
        final View view = LayoutInflater.from(getContext())
                .inflate(R.layout.myviewpager, null);
        indicatorBar = view.findViewById(R.id.indicatorBar);
        viewPager = view.findViewById(R.id.viewPager);
        this.addView(view);
        List<String> names = new ArrayList<>();
        for (ScrollItem scrollItem : list) {
            names.add(scrollItem.name);
        }
        indicatorBar.setItems(names);
        MyFragmentAdaper myFragmentAdaper = new MyFragmentAdaper(manager, list);
        viewPager.setAdapter(myFragmentAdaper);
        viewPager.setCurrentItem(0);
        indicatorBar.setOnMovePageListenner(new OnMovePageListenner() {
            @Override
            public void click(int i) {
                viewPager.setCurrentItem(i);
//                listenner.click(i);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (v > 0) {
                    if (lastValue > v) {
                        isLeft = false;
                    } else if (lastValue < v) {
                        isLeft = true;
                    }
                    indicatorBar.move(i, i1, isLeft);
                    lastValue = v;
                }
            }

            @Override
            public void onPageSelected(int i) {
                indicatorBar.changeTextColor(i);
                listenner.click(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
}
