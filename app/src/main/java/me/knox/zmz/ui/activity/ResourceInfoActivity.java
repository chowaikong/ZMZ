package me.knox.zmz.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.genius.groupie.GroupAdapter;
import javax.inject.Inject;
import me.knox.zmz.R;
import me.knox.zmz.contract.ResourceInfoContract;
import me.knox.zmz.databinding.ActivityResourceInfoBinding;
import me.knox.zmz.di.component.DaggerResourceInfoComponent;
import me.knox.zmz.di.module.ResourceInfoModule;
import me.knox.zmz.entity.ResourceInfo;
import me.knox.zmz.entity.ResourceInfoContentItem;
import me.knox.zmz.entity.ResourceInfoHeaderItem;
import me.knox.zmz.entity.ResourceInfoRemarkItem;
import me.knox.zmz.entity.ResourceInfoStatusItem;
import me.knox.zmz.presenter.ResourceInfoPresenter;
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

  private final GroupAdapter mGroupAdapter = new GroupAdapter();

  @Inject ResourceInfoPresenter mResourceInfoPresenter;

  public static void start(Context context, int id) {
    Intent intent = new Intent(context, ResourceInfoActivity.class);
    intent.putExtra(ID, id);
    //intent.putExtra(POSTER, poster);
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
    mDataBinding.setPoster(poster);
    mDataBinding.rvVertical.setAdapter(mGroupAdapter);
  }

  @Override protected void initData() {
    DaggerResourceInfoComponent.builder()
        .resourceInfoModule(new ResourceInfoModule(this))
        .build()
        .inject(this);

    mResourceInfoPresenter.getResourceInfo(mId, 1);
  }

  @Override protected void initListener() {

  }

  @Override public void obtainResourceInfoSucceed(ResourceInfo resourceInfo) {
    if (isFinishing()) return;
    mDataBinding.setPoster(resourceInfo.getPoster());
    ResourceInfoHeaderItem resourceInfoHeaderItem
        = new ResourceInfoHeaderItem(resourceInfo.getCnname(), resourceInfo.getEnname());
    ResourceInfoStatusItem resourceInfoStatusItem = new ResourceInfoStatusItem(resourceInfo);
    ResourceInfoContentItem resourceInfoContentItem =
        new ResourceInfoContentItem(resourceInfo.getContent());
    ResourceInfoRemarkItem resourceInfoRemarkItem =
        new ResourceInfoRemarkItem(resourceInfo.getRemark());
    mGroupAdapter.add(resourceInfoHeaderItem);
    mGroupAdapter.add(resourceInfoStatusItem);
    mGroupAdapter.add(resourceInfoContentItem);
    mGroupAdapter.add(resourceInfoRemarkItem);
    mDataBinding.progress.bar.setVisibility(View.GONE);
  }

  @Override public void error(String error, Object... objects) {
    if (isFinishing()) return;
    ZLog.e(error);
    Toaster.show(R.string.something_wrong_happened);
  }
}
