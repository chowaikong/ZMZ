package me.knox.zmz.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.knox.zmz.databinding.FragmentTodayBinding;

/**
 * Created by KNOX.
 */

public class TodayFragment extends BaseFragment {

  public static TodayFragment newInstance() {
    Bundle bundle = new Bundle();
    TodayFragment fragment = new TodayFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {




    return FragmentTodayBinding.inflate(inflater, container, false).getRoot();
  }
}
