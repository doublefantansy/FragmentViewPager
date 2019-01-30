package hzkj.cc.my_fragment_viewpager;

import android.support.v4.app.Fragment;
import android.widget.TextView;

public class ScrollItem {
    String name;
    Fragment fragment;
    TextView line;
    public ScrollItem(String name, Fragment fragment) {
        this.name = name;
        this.fragment = fragment;
    }

    public TextView getLine() {
        return line;
    }

    public void setLine(TextView line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
