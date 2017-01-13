package me.knox.zmz.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
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
import me.knox.zmz.ui.activity.ResourceInfoActivity;
import me.knox.zmz.ui.adapter.ResourcesAdapter;
import me.knox.zmz.ui.util.RecyclerItemClickListener;
import me.knox.zmz.ui.util.Toaster;
import me.knox.zmz.ui.widget.OnLoadMoreListener;
import me.knox.zmz.ui.widget.VerticalSpaceItemDecoration;

/**
 * Created by KNOX.
 */

public class ResourcesFragment extends BindingLazyFragment<FragmentResourcesBinding> implements ResourcesContract.View {

  @Inject ResourcesPresenter mResourcesPresenter;

  private final List<Resource.Data> mDataList = new ArrayList<>();
  private final ResourcesAdapter mResourcesAdapter = new ResourcesAdapter(mDataList);
  private int mPage = 0;

  public static ResourcesFragment newInstance() {
    Bundle bundle = new Bundle();
    ResourcesFragment fragment = new ResourcesFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override protected FragmentResourcesBinding getDataBinding(LayoutInflater inflater, ViewGroup container) {
    return FragmentResourcesBinding.inflate(inflater, container, false);
  }

  @Override protected void initView() {
    mDataBinding.rv.rvVertical.setClipToPadding(false);
    mDataBinding.rv.rvVertical.setPadding(0, 40, 0, 0);
    mDataBinding.rv.rvVertical.addItemDecoration(new VerticalSpaceItemDecoration(30));
    mDataBinding.rv.rvVertical.setAdapter(mResourcesAdapter);
    mDataBinding.rv.rvVertical.addOnScrollListener(new OnLoadMoreListener() {
      @Override public void loadMore() {
        mPage ++;
        mResourcesPresenter.getResources(mPage);
      }
    });
    mDataBinding.rv.rvVertical.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
        (view, position) -> ResourceInfoActivity.start(getContext(), mDataList.get(position))));
  }

  @Override protected void initData() {
    DaggerResourcesComponent.builder()
        .resourcesModule(new ResourcesModule(this))
        .build()
        .inject(this);

    if (mResourcesPresenter != null) {
      mResourcesPresenter.getResources(mPage);
    }
  }

  @Override public void obtainResourcesSucceed(List<Resource.Data> resources) {
    if (isFragmentNotAvailable()) return;
    if (mPage == 0) {
      mResourcesAdapter.setData(resources);
    } else {
      mResourcesAdapter.addData(resources);
    }
    mDataList.addAll(resources);
  }

  @Override public void error(String error, Object... objects) {
    if (isFragmentNotAvailable()) return;
    Toaster.show(error);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();

    if (mResourcesPresenter != null) {
      mResourcesPresenter.dispose();
    }
  }
}
