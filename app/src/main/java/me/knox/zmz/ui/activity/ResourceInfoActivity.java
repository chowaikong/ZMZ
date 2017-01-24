package me.knox.zmz.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import javax.inject.Inject;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.knox.zmz.R;
import me.knox.zmz.contract.ResourceInfoContract;
import me.knox.zmz.databinding.ActivityResourceInfoBinding;
import me.knox.zmz.di.component.DaggerResourceInfoComponent;
import me.knox.zmz.di.module.ResourceInfoModule;
import me.knox.zmz.entity.ResourceInfo;
import me.knox.zmz.presenter.ResourceInfoPresenter;
import me.knox.zmz.ui.item.ResourceInfoHeaderItemProvider;
import me.knox.zmz.ui.util.Toaster;
import me.knox.zmz.ui.util.ZLog;

/**
 * Created by KNOX.
 */

public class ResourceInfoActivity extends BaseBindingActivity<ActivityResourceInfoBinding>
    implements ResourceInfoContract.View {

  private static final String ID = "id";
  private static final String POSTER = "poster";
  private int mId;

  private final Items mItems = new Items();
  private final MultiTypeAdapter mMultiTypeAdapter = new MultiTypeAdapter();

  @Inject ResourceInfoPresenter mResourceInfoPresenter;

  public static void start(Context context, int id, String poster) {
    Intent intent = new Intent(context, ResourceInfoActivity.class);
    intent.putExtra(ID, id);
    intent.putExtra(POSTER, poster);
    context.startActivity(intent);
  }

  @Override protected ActivityResourceInfoBinding setDataBindingContentView(
      @Nullable Bundle savedInstanceState) {
    return DataBindingUtil.setContentView(this, R.layout.activity_resource_info);
  }

  @Override protected void initView() {
    Intent intent = getIntent();
    if (intent == null) return;
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
    if (isFinishing()) return;
    String[] strings = {resourceInfo.getCnname(), resourceInfo.getEnname()};
    mItems.add(strings);
    mItems.add(resourceInfo.getPlayStatus());
    mItems.add(resourceInfo.getContent());
    mItems.add(resourceInfo.getRemark());
    mMultiTypeAdapter.setItems(mItems);
    mDataBinding.progress.bar.setVisibility(View.GONE);
  }

  @Override public void error(String error, Object... objects) {
    if (isFinishing()) return;
    ZLog.e(error);
    Toaster.show(R.string.something_wrong_happened);
  }
}
