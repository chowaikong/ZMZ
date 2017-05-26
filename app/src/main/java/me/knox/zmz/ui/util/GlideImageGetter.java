package me.knox.zmz.ui.util;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import java.util.HashSet;
import java.util.Set;
import me.knox.zmz.R;

/**
 * Created by KNOX.
 */

public final class GlideImageGetter implements Html.ImageGetter, Drawable.Callback {

  private static final String TAG = "GlideImageGetter";

  private final Context mContext;

  private final TextView mTextView;

  private final Set<ImageGetterViewTarget> mTargets;

  public static GlideImageGetter get(View view) {
    return (GlideImageGetter)view.getTag(R.id.drawable_callback_tag);
  }

  public void clear() {
    GlideImageGetter prev = get(mTextView);
    if (prev == null) {
      return;
    }

    prev.mTargets.forEach(Glide::clear);
  }

  public GlideImageGetter(Context context, TextView textView) {
    this.mContext = context;
    this.mTextView = textView;

    clear();
    mTargets = new HashSet<>();
    mTextView.setTag(R.id.drawable_callback_tag, this);
  }

  @Override
  public Drawable getDrawable(String url) {
    Log.i(TAG, "Downloading from: " + url);
    final UrlDrawable urlDrawable = new UrlDrawable();

    Glide.with(mContext)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(new ImageGetterViewTarget(mTextView, urlDrawable));


    return urlDrawable;

  }

  @Override
  public void invalidateDrawable(@NonNull Drawable who) {
    mTextView.invalidate();
  }

  @Override
  public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {

  }

  @Override
  public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {

  }

  private class ImageGetterViewTarget extends ViewTarget<TextView, GlideDrawable> {

    private final UrlDrawable mDrawable;

    private ImageGetterViewTarget(TextView view, UrlDrawable drawable) {
      super(view);
      mTargets.add(this);
      this.mDrawable = drawable;
    }

    @Override
    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
      Rect rect;
      if (resource.getIntrinsicWidth() > 100) {
        float width;
        float height;
        Log.i(TAG, "Image width is " + resource.getIntrinsicWidth());
        Log.i(TAG, "View width is " + view.getWidth());
        if (resource.getIntrinsicWidth() >= getView().getWidth()) {
          float downScale = (float) resource.getIntrinsicWidth() / getView().getWidth();
          width = (float) resource.getIntrinsicWidth() / downScale;
          height = (float) resource.getIntrinsicHeight() / downScale;
        } else {
          float multiplier = (float) getView().getWidth() / resource.getIntrinsicWidth();
          width = (float) resource.getIntrinsicWidth() * multiplier;
          height = (float) resource.getIntrinsicHeight() * multiplier;
        }
        Log.i(TAG, "New Image width is " + width);


        rect = new Rect(0, 0, Math.round(width), Math.round(height));
      } else {
        rect = new Rect(0, 0, resource.getIntrinsicWidth() * 2, resource.getIntrinsicHeight() * 2);
      }
      resource.setBounds(rect);

      mDrawable.setBounds(rect);
      mDrawable.setDrawable(resource);


      if (resource.isAnimated()) {
        mDrawable.setCallback(get(getView()));
        resource.setLoopCount(GlideDrawable.LOOP_FOREVER);
        resource.start();
      }

      getView().setText(getView().getText());
      getView().invalidate();
    }

    private Request request;
    @Override
    public Request getRequest() {
      return request;
    }

    @Override
    public void setRequest(Request request) {
      this.request = request;
    }
  }
}
