package me.knox.zmz.ui.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;

/**
 * Created by KNOX.
 */

public class ExNestedScrollView extends NestedScrollView {

  private int downY = 0;
  private int mTouchSlop;

  public ExNestedScrollView(Context context) {
    this(context, null);
  }

  public ExNestedScrollView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ExNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
  }

  @Override public void requestChildFocus(View child, View focused) {
    //super.requestChildFocus(child, focused);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {

    switch (ev.getAction()) {
      case ACTION_DOWN:
        downY = (int) ev.getRawY();
        break;
      case ACTION_MOVE:
        int moveY = (int) ev.getRawY();
        if (Math.abs(moveY - downY) > mTouchSlop) {
          return true;
        }
    }
    return super.onInterceptTouchEvent(ev);
  }
}
