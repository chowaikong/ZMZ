package me.knox.zmz.ui.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import javax.inject.Inject;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.knox.zmz.R;
import me.knox.zmz.databinding.ActivityResourceInfoBinding;
import me.knox.zmz.di.component.DaggerResourceInfoComponent;
import me.knox.zmz.di.module.ResourceInfoModule;
import me.knox.zmz.entity.ResourceInfo;
import me.knox.zmz.mvp.contract.ResourceInfoContract;
import me.knox.zmz.mvp.presenter.ResourceInfoPresenter;
import me.knox.zmz.ui.item.ResourceInfoHeaderItemProvider;
import me.knox.zmz.ui.util.Toaster;

import static me.knox.zmz.misc.Constants.TRANSITION_POSTER;
import static me.knox.zmz.misc.Constants.TRANSITION_TITLE;

/**
 * Created by KNOX.
 */

public class ResourceInfoActivity extends BaseBindingActivity<ActivityResourceInfoBinding>
    implements ResourceInfoContract.View {

  private static final String TAG = "ResourceInfoActivity";
  private static final String ID = "id";
  private static final String POSTER = "poster";
  private int mId;

  private final Items mItems = new Items();
  private final MultiTypeAdapter mMultiTypeAdapter = new MultiTypeAdapter(mItems);

  @Inject ResourceInfoPresenter mResourceInfoPresenter;

  public static void startWithTransition(Activity activity, int id, String poster, View shareElement,
      String transitionName) {
    Intent intent = new Intent(activity, ResourceInfoActivity.class);
    intent.putExtra(ID, id);
    intent.putExtra(POSTER, poster);
    ActivityOptions options =
        ActivityOptions.makeSceneTransitionAnimation(activity, shareElement, transitionName);
    activity.startActivity(intent, options.toBundle());
  }

  public static void startWithoutTransition(Context context, int id) {
    Intent intent = new Intent(context, ResourceInfoActivity.class);
    intent.putExtra(ID, id);
    context.startActivity(intent);
  }

  public static void startWithPairTransition(Activity activity, int id, String poster,
      ImageView image, TextView title) {
    Intent intent = new Intent(activity, ResourceInfoActivity.class);
    intent.putExtra(ID, id);
    intent.putExtra(POSTER, poster);
    Pair posterPair = Pair.create(image, TRANSITION_POSTER);
    Pair titlePair = Pair.create(title, TRANSITION_TITLE);
    ActivityOptions options =
        ActivityOptions.makeSceneTransitionAnimation(activity, posterPair, titlePair);
    activity.startActivity(intent, options.toBundle());
  }

  @Override protected ActivityResourceInfoBinding setDataBindingContentView(
      @Nullable Bundle savedInstanceState) {
    return DataBindingUtil.setContentView(this, R.layout.activity_resource_info);
  }

  @Override protected void initView() {
    Intent intent = getIntent();
    if (intent == null) {
      return;
    }
    mId = intent.getIntExtra(ID, 0);
    String poster = intent.getStringExtra(POSTER);
    if (poster != null && !poster.isEmpty()) {
      mDataBinding.setPoster(poster);
    }

    mDataBinding.rvVertical.setAdapter(mMultiTypeAdapter);
  }

  @Override protected void initData() {
    DaggerResourceInfoComponent.builder()
        .resourceInfoModule(new ResourceInfoModule(this))
        .build()
        .inject(this);

    mMultiTypeAdapter.applyGlobalMultiTypePool();
    mMultiTypeAdapter.register(String[].class, new ResourceInfoHeaderItemProvider());

    mResourceInfoPresenter.getResourceInfo(mId, 1);
  }

  @Override protected void initListener() {
  }

  @Override public void obtainResourceInfoSucceed(ResourceInfo resourceInfo) {
    if (isFinishing()) {
      return;
    }
    //if (mPoster == null) {
    //  mPoster = resourceInfo.getPoster();
    //}
    //mDataBinding.setPoster(mPoster);
    String[] strings = { resourceInfo.getCnname(), resourceInfo.getEnname() };
    mItems.add(strings);
    mItems.add(resourceInfo.getPlayStatus());
    mItems.add(resourceInfo.getContent());
    if(!TextUtils.isEmpty(resourceInfo.getRemark())) {
      mItems.add(resourceInfo.getRemark());
    }
    mMultiTypeAdapter.notifyItemRangeInserted(0, mItems.size());
    mDataBinding.progress.bar.setVisibility(View.GONE);
  }

  @Override public void error(String error, Object... objects) {
    if (isFinishing()) {
      return;
    }
    Log.e(TAG, error);
    Toaster.show(R.string.something_wrong_happened);
  }
}
