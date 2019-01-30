package hzkj.cc.my_fragment_viewpager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class IndicatorBar extends LinearLayout {
    List<String> items;
    List<TextView> textViews = new ArrayList<>();
    int itemWidth;
    HorizontalScrollView horizontalScrollView;
    TextView indecatorLine;
    int displayItemsCount = 4;
    int leftM;
    int defalutSize = 0;
    OnMovePageListenner onMovePageListenner;

    public void setOnMovePageListenner(OnMovePageListenner onMovePageListenner) {
        this.onMovePageListenner = onMovePageListenner;
    }

    public IndicatorBar(Context context) {
        super(context);
    }

    public IndicatorBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setItems(List<String> items) {
        this.items = items;
        init();
    }

    public void init() {
        if (items.size() < displayItemsCount) {
            itemWidth = getResources().getDisplayMetrics().widthPixels / items.size();
            displayItemsCount = items.size();
        } else {
            defalutSize = displayItemsCount - (items.size() % displayItemsCount);
            if (items.size() % displayItemsCount != 0) {
                for (int i = 0; i < defalutSize; i++) {
                    items.add("");
                }
            }
            itemWidth = getResources().getDisplayMetrics().widthPixels / displayItemsCount;
        }
        this.setOrientation(VERTICAL);
        this.setBackgroundColor(getResources().getColor(R.color.indicatorBar_white));
        indecatorLine = new TextView(getContext());
        LinearLayout.LayoutParams lineLayoutParams = new LinearLayout.LayoutParams(itemWidth, Util.dipTopx(getContext(), 3));
        indecatorLine.setBackgroundColor(getResources().getColor(R.color.indicatorBar_myBlue));
        indecatorLine.setLayoutParams(lineLayoutParams);
        horizontalScrollView = new HorizontalScrollView(getContext());
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        LinearLayout.LayoutParams scrollLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollLayoutParams.weight = 1;
        horizontalScrollView.setLayoutParams(scrollLayoutParams);
        horizontalScrollView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        LinearLayout linearLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams superLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(superLayoutParams);
        for (int i = 0; i < items.size(); i++) {
            View view = LayoutInflater.from(getContext())
                    .inflate(R.layout.indicatoritem, null);
            RelativeLayout layout = view.findViewById(R.id.layout);
            TextView text = view.findViewById(R.id.text);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(itemWidth, ViewGroup.LayoutParams.MATCH_PARENT);
            layout.setLayoutParams(layoutParams);
            text.setText(items.get(i));
            textViews.add(text);
            text.setTextColor(getResources().getColor(R.color.indicatorBar_gray));
            linearLayout.addView(layout);
            final int finalI = i;
            layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickMove(finalI);
                }
            });
        }
        horizontalScrollView.addView(linearLayout);
        this.addView(horizontalScrollView);
        this.addView(indecatorLine);
        changeTextColor(0);
    }

    public void clickMove(int p) {
        changeTextColor(p);
        onMovePageListenner.click(p);
    }

    public void move(int p, float distance, boolean isRight) {
        LinearLayout.LayoutParams layoutParams = (LayoutParams) indecatorLine.getLayoutParams();
        if (p % displayItemsCount == displayItemsCount - 1) {
            if (isRight) {
                horizontalScrollView.scrollTo(itemWidth * (p + 1), 0);
                leftM = 0;
            } else {
                horizontalScrollView.scrollTo(((p + 1) / displayItemsCount - 1) * 4 * itemWidth, 0);
                leftM = 3 * itemWidth;
            }
        } else {
            leftM = (int) (p % displayItemsCount * itemWidth + distance / displayItemsCount);
        }
        layoutParams.leftMargin = leftM;
        indecatorLine.setLayoutParams(layoutParams);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void changeTextColor(int i) {
        for (TextView textView : textViews) {
            textView
                    .setTextColor(getResources().getColor(R.color.indicatorBar_gray));
        }
        for (int j = 0; j < textViews.size(); j++) {
            if (j == i) {
                textViews.get(i)
                        .setTextColor(getResources().getColor(R.color.indicatorBar_myBlue));
                break;
            }
        }
    }
}
