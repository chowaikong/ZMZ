package me.knox.zmz.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.animation.AnticipateOvershootInterpolator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

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

    LandingAnimator animator = new LandingAnimator();
    animator.setAddDuration(1200);
    animator.setInterpolator(new AnticipateOvershootInterpolator());
    setItemAnimator(animator);
  }
}
