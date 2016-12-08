package me.knox.zmz.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import me.knox.zmz.contract.ResourcesContract;
import me.knox.zmz.databinding.FragmentResourcesBinding;
import me.knox.zmz.di.component.DaggerResourcesComponent;
import me.knox.zmz.di.module.ResourcesModule;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.presenter.ResourcesPresenter;
import me.knox.zmz.ui.adapter.ResourcesAdapter;
import me.knox.zmz.ui.util.Toaster;
import me.knox.zmz.ui.widget.OnLoadMoreListener;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by KNOX.
 */

public class ResourcesFragment extends BaseFragment implements ResourcesContract.View {

  @Inject ResourcesPresenter mResourcesPresenter;

  private FragmentResourcesBinding mFragmentResourcesBinding;
  private final List<Resource.Data> mDataList = new ArrayList<>();
  private final ResourcesAdapter mResourcesAdapter = new ResourcesAdapter(mDataList);
  private int mPage = 0;

  public static ResourcesFragment newInstance() {
    Bundle bundle = new Bundle();
    ResourcesFragment fragment = new ResourcesFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    mFragmentResourcesBinding = FragmentResourcesBinding.inflate(inflater, container, false);

    DaggerResourcesComponent.builder()
        .resourcesModule(new ResourcesModule(this))
        .build()
        .inject(this);

    if (mResourcesPresenter != null) {
      mResourcesPresenter.getResources(mPage);
    }

    mFragmentResourcesBinding.resourceList.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
    mFragmentResourcesBinding.resourceList.setAdapter(mResourcesAdapter);
    mFragmentResourcesBinding.resourceList.addOnScrollListener(new OnLoadMoreListener() {
      @Override public void loadMore() {
        mPage ++;
        mResourcesPresenter.getResources(mPage);
      }
    });

    return mFragmentResourcesBinding.getRoot();
  }

  @Override public void obtainResourcesSucceed(List<Resource.Data> resources) {
    if (isFragmentNotAvailable()) return;
    if (mPage == 0) {
      mResourcesAdapter.setData(resources);
    } else {
      mResourcesAdapter.addData(resources);
    }
  }

  @Override public void obtainResourcesFailed(String error) {
    if (isFragmentNotAvailable()) return;
    Toaster.showShortToast(error);
  }
}
