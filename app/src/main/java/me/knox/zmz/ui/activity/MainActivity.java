package me.knox.zmz.ui.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.knox.zmz.R;
import me.knox.zmz.contract.HotListContract;
import me.knox.zmz.contract.NewsListContract;
import me.knox.zmz.contract.ResourcesContract;
import me.knox.zmz.contract.ScheduleUpdatesContract;
import me.knox.zmz.contract.UpdatesContract;
import me.knox.zmz.databinding.ActivityMainBinding;
import me.knox.zmz.di.component.DaggerMainComponent;
import me.knox.zmz.di.module.HotListModule;
import me.knox.zmz.di.module.NewsListModule;
import me.knox.zmz.di.module.ResourcesModule;
import me.knox.zmz.di.module.ScheduleUpdatesModule;
import me.knox.zmz.di.module.UpdatesModule;
import me.knox.zmz.entity.Category;
import me.knox.zmz.entity.Hot;
import me.knox.zmz.entity.HotList;
import me.knox.zmz.entity.News;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.entity.ScheduleUpdate;
import me.knox.zmz.entity.ScheduleUpdateList;
import me.knox.zmz.entity.Update;
import me.knox.zmz.presenter.HotListPresenter;
import me.knox.zmz.presenter.NewsListPresenter;
import me.knox.zmz.presenter.ResourcesPresenter;
import me.knox.zmz.presenter.ScheduleUpdatesPresenter;
import me.knox.zmz.presenter.UpdatesPresenter;
import me.knox.zmz.ui.item.CarouselItemProvider;
import me.knox.zmz.ui.item.NewsItemProvider;
import me.knox.zmz.ui.item.ResourcesItemProvider;
import me.knox.zmz.ui.item.ScheduleUpdateItemProvider;
import me.knox.zmz.ui.item.UpdatesItemProvider;
import me.knox.zmz.ui.util.OnScrollingListener;
import me.knox.zmz.ui.util.Toaster;
import me.knox.zmz.ui.util.ZLog;
import me.knox.zmz.ui.widget.VerticalSpaceItemDecoration;
import org.joda.time.LocalDate;

import static me.knox.zmz.misc.Constants.TRANSITION_SEARCH;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding>
    implements HotListContract.View, ScheduleUpdatesContract.View, NewsListContract.View,
    UpdatesContract.View, ResourcesContract.View {

  private static final String TODAY = LocalDate.now().toString();

  private final Items mItems = new Items();
  private final MultiTypeAdapter mMultiTypeAdapter = new MultiTypeAdapter(mItems);
  private int mPage = 0;

  @Inject HotListPresenter mHotListPresenter;
  @Inject ScheduleUpdatesPresenter mScheduleUpdatesPresenter;
  @Inject UpdatesPresenter mUpdatesPresenter;
  @Inject NewsListPresenter mNewsListPresenter;
  @Inject ResourcesPresenter mResourcesPresenter;

  @Override
  protected ActivityMainBinding setDataBindingContentView(@Nullable Bundle savedInstanceState) {
    return DataBindingUtil.setContentView(this, R.layout.activity_main);
  }

  @Override protected void initView() {
    mDataBinding.list.rvVertical.addItemDecoration(new VerticalSpaceItemDecoration(20));
    mDataBinding.list.rvVertical.getLayoutManager().setItemPrefetchEnabled(true);
    mMultiTypeAdapter.applyGlobalMultiTypePool();
    mMultiTypeAdapter.register(HotList.class, new CarouselItemProvider());
    mMultiTypeAdapter.register(ScheduleUpdateList.class, new ScheduleUpdateItemProvider());
    mMultiTypeAdapter.register(News.class, new NewsItemProvider());
    mMultiTypeAdapter.register(Update.class, new UpdatesItemProvider());
    mMultiTypeAdapter.register(Resource.Data.class, new ResourcesItemProvider());
    mDataBinding.list.rvVertical.setAdapter(mMultiTypeAdapter);
  }

  @Override protected void initData() {
    DaggerMainComponent.builder()
        .hotListModule(new HotListModule(this))
        .scheduleUpdatesModule(new ScheduleUpdatesModule(this))
        .newsListModule(new NewsListModule(this))
        .updatesModule(new UpdatesModule(this))
        .resourcesModule(new ResourcesModule(this))
        .build()
        .inject(this);

    loadData(true);
  }

  @Override protected void initListener() {
    mDataBinding.list.rvVertical.addOnScrollListener(new OnScrollingListener() {
      @Override public void loadMore() {
        loadData(false);
      }

      @Override public void stateChanged(int state) {
        if (state == RecyclerView.SCROLL_STATE_DRAGGING
            || state == RecyclerView.SCROLL_STATE_SETTLING) {
          mDataBinding.setIsScrolling(true);
        } else {
          mDataBinding.setIsScrolling(false);
        }
      }
    });

    mDataBinding.searchCard.setOnClickListener(
        v -> SearchActivity.startWithTransition((Activity) getContext(), mDataBinding.searchCard,
            TRANSITION_SEARCH));
  }

  private void loadData(boolean isRefresh) {
    if (isRefresh) {
      mPage = 0;
      mHotListPresenter.getHot();
    } else {
      mPage++;
      mResourcesPresenter.getResources(mPage);
    }
  }

  private void addListToItems(List<?> list) {
    for (int i = 0; i < list.size(); i++) {
      mItems.add(list.get(i));
    }
  }

  @Override public void obtainHotListSuccess(List<Hot> hotList) {
    if (isFinishing()) return;

    mScheduleUpdatesPresenter.getScheduleUpdates(TODAY, TODAY);

    if (hotList == null || hotList.size() <= 0) return;

    HotList list = new HotList(hotList);
    mItems.add(list);
  }

  @Override
  public void obtainScheduleUpdatesSucceed(Map<String, List<ScheduleUpdate>> stringListMap) {
    if (isFinishing()) return;

    mNewsListPresenter.getNewsList(mPage);

    List<ScheduleUpdate> scheduleUpdates = stringListMap.get(TODAY);
    if (scheduleUpdates.size() <= 0) return;

    mItems.add(new Category("今日美剧更新"));
    ScheduleUpdateList list = new ScheduleUpdateList(scheduleUpdates);
    mItems.add(list);
  }

  @Override public void obtainNewsListSucceed(List<News> newsList) {
    if (isFinishing()) return;

    mUpdatesPresenter.getUpdates();

    if (newsList == null || newsList.size() <= 0) return;

    mItems.add(new Category("新闻资讯"));
    addListToItems(newsList);
  }

  @Override public void obtainUpdatesSucceed(List<Update> updates) {
    if (isFinishing()) return;

    mResourcesPresenter.getResources(mPage);

    if (updates == null || updates.size() <= 0) return;

    mItems.add(new Category("今日下载更新"));
    addListToItems(updates);
  }

  @Override public void obtainResourcesSucceed(List<Resource.Data> resources) {
    if (isFinishing()) return;

    if (mPage == 0) {
      mItems.add(new Category("资源更新"));
      addListToItems(resources);
      mMultiTypeAdapter.notifyItemRangeInserted(0, resources.size());
      mDataBinding.progress.bar.setVisibility(View.GONE);
    } else {
      int size = mItems.size();
      addListToItems(resources);
      mMultiTypeAdapter.notifyItemRangeInserted(size, mItems.size());
    }
  }

  @Override public void error(String error, Object... objects) {
    if (isFinishing()) return;

    ZLog.e(error);
    Toaster.show(R.string.something_wrong_happened);
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    // Once onDestroy invoked, dispose all subscribers

    if (mHotListPresenter != null) {
      mHotListPresenter.dispose();
    }

    if (mScheduleUpdatesPresenter != null) {
      mScheduleUpdatesPresenter.dispose();
    }

    if (mNewsListPresenter != null) {
      mNewsListPresenter.dispose();
    }

    if (mUpdatesPresenter != null) {
      mUpdatesPresenter.dispose();
    }

    if (mResourcesPresenter != null) {
      mResourcesPresenter.dispose();
    }
  }
}
