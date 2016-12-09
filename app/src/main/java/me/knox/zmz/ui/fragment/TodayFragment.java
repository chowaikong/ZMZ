package me.knox.zmz.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import me.knox.zmz.databinding.FragmentTodayBinding;

/**
 * Created by KNOX.
 */

public class TodayFragment extends BindingLazyFragment<FragmentTodayBinding> {

  public static TodayFragment newInstance() {
    Bundle bundle = new Bundle();
    TodayFragment fragment = new TodayFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  protected FragmentTodayBinding getDataBinding(LayoutInflater inflater, ViewGroup container) {
    return FragmentTodayBinding.inflate(inflater, container, false);
  }

  @Override protected void initView() {

  }

  @Override protected void initData() {

  }
}
