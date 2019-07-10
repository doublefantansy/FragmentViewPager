package hzkj.cc.fragmentviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hzkj.cc.my_fragment_viewpager.FragmentViewPager;
import hzkj.cc.my_fragment_viewpager.IndicatorBar;
import hzkj.cc.my_fragment_viewpager.OnMovePageListenner;
import hzkj.cc.my_fragment_viewpager.ScrollItem;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentViewPager fragmentViewPager = findViewById(R.id.bar);
        List<ScrollItem> scrollViews = new ArrayList() {
            {
                add(new ScrollItem("详情", new Fragment1()));
                add(new ScrollItem("流程", new Fragment2()));
//                add(new ScrollItem("视频", new Fragment3()));
//                add(new ScrollItem("新闻", new Fragment5()));
//                add(new ScrollItem("笑话", new Fragment5()));
//                add(new ScrollItem("问题", new Fragment5()));
            }
        };
        fragmentViewPager.setListenner(new OnMovePageListenner() {
            @Override
            public void click(int i) {
                Toast.makeText(MainActivity.this, i + "", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        fragmentViewPager.setList(scrollViews, getSupportFragmentManager());
    }
}
