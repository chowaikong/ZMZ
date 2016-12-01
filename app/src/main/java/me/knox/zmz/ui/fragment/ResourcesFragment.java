package me.knox.zmz.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.knox.zmz.databinding.FragmentResourcesBinding;

/**
 * Created by KNOX.
 */

public class ResourcesFragment extends BaseFragment{

  public static ResourcesFragment newInstance() {
    Bundle bundle = new Bundle();
    ResourcesFragment fragment = new ResourcesFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return FragmentResourcesBinding.inflate(inflater, container, false).getRoot();
  }
}
