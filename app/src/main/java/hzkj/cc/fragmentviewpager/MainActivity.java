package hzkj.cc.fragmentviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import hzkj.cc.my_fragment_viewpager.FragmentViewPager;
import hzkj.cc.my_fragment_viewpager.IndicatorBar;
import hzkj.cc.my_fragment_viewpager.ScrollItem;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentViewPager fragmentViewPager = findViewById(R.id.bar);
        List<ScrollItem> scrollViews = new ArrayList() {
            {
                add(new ScrollItem("关注", new Fragment1()));
                add(new ScrollItem("推荐", new Fragment2()));
                add(new ScrollItem("视频", new Fragment3()));
                add(new ScrollItem("新闻", new Fragment5()));
                add(new ScrollItem("笑话", new Fragment5()));
                add(new ScrollItem("问题", new Fragment5()));
            }
        };
        fragmentViewPager.setList(scrollViews, getSupportFragmentManager());
    }
}
