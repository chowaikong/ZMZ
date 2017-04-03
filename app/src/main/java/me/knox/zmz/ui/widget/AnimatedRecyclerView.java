package me.knox.zmz.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.mikepenz.itemanimators.SlideUpAlphaAnimator;

/**
 * Created by KNOX.
 */

public class AnimatedRecyclerView extends RecyclerView {

  public AnimatedRecyclerView(Context context) {
    this(context, null);
  }

  public AnimatedRecyclerView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public AnimatedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    SlideUpAlphaAnimator animator = new SlideUpAlphaAnimator();
    animator.setAddDuration(1500);
    setItemAnimator(animator);
  }
}
