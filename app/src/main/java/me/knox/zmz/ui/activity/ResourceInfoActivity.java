package me.knox.zmz.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import me.knox.zmz.R;
import me.knox.zmz.databinding.ActivityResourceInfoBinding;
import me.knox.zmz.entity.Resource;

/**
 * Created by KNOX.
 */

public class ResourceInfoActivity extends BaseBindingActivity<ActivityResourceInfoBinding> {

  private static final String RESOURCE = "resource";

  public static void start(Context context, Resource.Data resource) {
    Intent intent = new Intent(context, ResourceInfoActivity.class);
    intent.putExtra(RESOURCE, resource);
    context.startActivity(intent);
  }

  @Override protected ActivityResourceInfoBinding setDataBindingContentView(
      @Nullable Bundle savedInstanceState) {
    return DataBindingUtil.setContentView(this, R.layout.activity_resource_info);
  }

  @Override protected void initView() {
    setupToolbar(mDataBinding.toolbar);
    Intent intent = getIntent();
    if (intent == null) return;
    Resource.Data data = intent.getParcelableExtra(RESOURCE);
    String title = data.getCnname();
    mDataBinding.toolbar.setTitle(title);
  }

  @Override protected void initData() {

  }

  @Override protected void initListener() {

  }
}
