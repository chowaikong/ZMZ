package me.knox.zmz.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by KNOX.
 */

public abstract class BaseBindingActivity<T extends ViewDataBinding> extends AppCompatActivity {

  public T mDataBinding;

  public Context getContext() {
    return this;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    Window window = getWindow();
    if (window != null) {
      window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
          WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
    super.onCreate(savedInstanceState);

    mDataBinding = setDataBindingContentView(savedInstanceState);

    initView();
    initListener();
    initData();
  }

  public void setupToolbar(@NonNull Toolbar toolbar) {
    setSupportActionBar(toolbar);
    if (getSupportActionBar() == null) {
      return;
    }
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  protected abstract T setDataBindingContentView(@Nullable Bundle savedInstanceState);

  protected abstract void initView();

  protected abstract void initData();

  protected abstract void initListener();
}
