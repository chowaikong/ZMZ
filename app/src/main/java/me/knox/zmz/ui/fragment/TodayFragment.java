package me.knox.zmz.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.genius.groupie.GroupAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import me.knox.zmz.contract.HotListContract;
import me.knox.zmz.contract.NewsListContract;
import me.knox.zmz.databinding.FragmentTodayBinding;
import me.knox.zmz.di.component.DaggerTodayComponent;
import me.knox.zmz.di.module.HotListModule;
import me.knox.zmz.di.module.NewsListModule;
import me.knox.zmz.entity.Hot;
import me.knox.zmz.entity.HotListItem;
import me.knox.zmz.entity.News;
import me.knox.zmz.entity.NewsListItem;
import me.knox.zmz.presenter.HotListPresenter;
import me.knox.zmz.presenter.NewsListPresenter;
import me.knox.zmz.ui.adapter.HotListAdapter;
import me.knox.zmz.ui.adapter.NewsListAdapter;
import me.knox.zmz.ui.util.Toaster;

/**
 * Created by KNOX.
 */

public class TodayFragment extends BindingLazyFragment<FragmentTodayBinding>
    implements HotListContract.View, NewsListContract.View {

  @Inject HotListPresenter mHotListPresenter;
  @Inject NewsListPresenter mNewsListPresenter;
  private final List<Hot> mHotList = new ArrayList<>();
  private final List<News> mNewsList = new ArrayList<>();
  private final HotListAdapter mHotListAdapter = new HotListAdapter(mHotList);
  private final NewsListAdapter mNewsListAdapter = new NewsListAdapter(mNewsList);

  private final HotListItem mHotListItem = new HotListItem(mHotListAdapter);
  private final NewsListItem mNewsListItem = new NewsListItem(mNewsListAdapter);
  private final GroupAdapter mTodayGroupAdapter = new GroupAdapter();

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
    mDataBinding.rv.rvVertical.setAdapter(mTodayGroupAdapter);
  }

  @Override protected void initData() {
    DaggerTodayComponent.builder()
        .hotListModule(new HotListModule(this))
        .newsListModule(new NewsListModule(this))
        .build()
        .inject(this);

    if (mHotListPresenter != null) {
      mHotListPresenter.getHot();
    }
    if (mNewsListPresenter != null) {
      mNewsListPresenter.getNewsList(0);
    }
  }

  @Override public void obtainHotListSuccess(List<Hot> hotList) {
    if (isFragmentNotAvailable()) return;
    mHotListAdapter.setData(hotList);
  }

  @Override public void obtainNewsListSucceed(List<News> newsList) {
    if (isFragmentNotAvailable()) return;
    mNewsListAdapter.setData(newsList);
    mTodayGroupAdapter.add(mHotListItem);
    mTodayGroupAdapter.add(mNewsListItem);
  }

  @Override public void error(String error, Object... objects) {
    if (isFragmentNotAvailable()) return;
    Toaster.show(error);
  }
}
