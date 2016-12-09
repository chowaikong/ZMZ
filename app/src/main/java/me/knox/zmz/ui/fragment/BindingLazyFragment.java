package me.knox.zmz.ui.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by KNOX.
 */

public abstract class BindingLazyFragment<T extends ViewDataBinding> extends Fragment {

  protected T mDataBinding;
  private boolean isViewCreated = false;
  private boolean isVisible = false;
  private boolean isLazyLoaded = true;

  protected abstract T getDataBinding(LayoutInflater inflater, ViewGroup container);

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mDataBinding = getDataBinding(inflater, container);

    return mDataBinding.getRoot();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    isViewCreated = true;
    initView();
    lazyLoad();
  }

  @Override public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);

    isVisible = getUserVisibleHint();
    if (isVisible) lazyLoad();
  }

  @Override public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);

    isVisible = !hidden;
  }

  private void lazyLoad() {
    if (isVisible && isViewCreated && isLazyLoaded) {
      isViewCreated = false;
      isLazyLoaded = false;
      initData();
    }
  }

  protected abstract void initView();

  protected abstract void initData();

  public AppCompatActivity getAppCompatActivity() {
    return (AppCompatActivity) getActivity();
  }

  public boolean isFragmentNotAvailable() {
    return getActivity() == null || getActivity().isFinishing() || isDetached();
  }
}
