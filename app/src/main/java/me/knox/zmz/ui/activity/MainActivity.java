package me.knox.zmz.ui.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import me.drakeet.multitype.GlobalMultiTypePool;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.knox.zmz.R;
import me.knox.zmz.databinding.ActivityMainBinding;
import me.knox.zmz.di.component.DaggerMainComponent;
import me.knox.zmz.di.module.HotListModule;
import me.knox.zmz.di.module.NewsListModule;
import me.knox.zmz.di.module.ResourcesModule;
import me.knox.zmz.di.module.ScheduleUpdatesModule;
import me.knox.zmz.di.module.SubtitleListModule;
import me.knox.zmz.entity.Category;
import me.knox.zmz.entity.Hot;
import me.knox.zmz.entity.HotList;
import me.knox.zmz.entity.News;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.entity.ScheduleUpdate;
import me.knox.zmz.entity.ScheduleUpdateList;
import me.knox.zmz.entity.Subtitle;
import me.knox.zmz.entity.SubtitleList;
import me.knox.zmz.mvp.contract.HotListContract;
import me.knox.zmz.mvp.contract.NewsListContract;
import me.knox.zmz.mvp.contract.ResourcesContract;
import me.knox.zmz.mvp.contract.ScheduleUpdatesContract;
import me.knox.zmz.mvp.contract.SubtitleListContract;
import me.knox.zmz.mvp.presenter.HotListPresenter;
import me.knox.zmz.mvp.presenter.NewsListPresenter;
import me.knox.zmz.mvp.presenter.ResourcesPresenter;
import me.knox.zmz.mvp.presenter.ScheduleUpdatesPresenter;
import me.knox.zmz.mvp.presenter.SubtitleListPresenter;
import me.knox.zmz.ui.item.CarouselItemProvider;
import me.knox.zmz.ui.item.CategoryItemProvider;
import me.knox.zmz.ui.item.NewsItemProvider;
import me.knox.zmz.ui.item.ResourcesItemProvider;
import me.knox.zmz.ui.item.ScheduleUpdateItemProvider;
import me.knox.zmz.ui.item.SingleTextItemProvider;
import me.knox.zmz.ui.item.SubtitleItemProvider;
import me.knox.zmz.ui.util.Toaster;
import me.knox.zmz.ui.widget.VerticalSpaceItemDecoration;
import org.joda.time.LocalDate;

import static me.knox.zmz.misc.Constants.TRANSITION_SEARCH;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding>
    implements HotListContract.View, ScheduleUpdatesContract.View, NewsListContract.View,
    ResourcesContract.View, SubtitleListContract.View {

  private static final String TAG = "MainActivity";
  private static final String TODAY = LocalDate.now().toString();

  private final Items mItems = new Items();
  private final MultiTypeAdapter mMultiTypeAdapter = new MultiTypeAdapter(mItems);

  @Inject HotListPresenter mHotListPresenter;
  @Inject ScheduleUpdatesPresenter mScheduleUpdatesPresenter;
  @Inject NewsListPresenter mNewsListPresenter;
  @Inject ResourcesPresenter mResourcesPresenter;
  @Inject SubtitleListPresenter mSubtitleListPresenter;

  @Override
  protected ActivityMainBinding setDataBindingContentView(@Nullable Bundle savedInstanceState) {
    return DataBindingUtil.setContentView(this, R.layout.activity_main);
  }

  @Override protected void initView() {
    mDataBinding.list.rvVertical.addItemDecoration(new VerticalSpaceItemDecoration(20));
    mDataBinding.list.rvVertical.getLayoutManager().setItemPrefetchEnabled(true);
    GlobalMultiTypePool.register(Category.class, new CategoryItemProvider());
    GlobalMultiTypePool.register(String.class, new SingleTextItemProvider());
    mMultiTypeAdapter.applyGlobalMultiTypePool();
    mMultiTypeAdapter.register(HotList.class, new CarouselItemProvider());
    mMultiTypeAdapter.register(ScheduleUpdateList.class, new ScheduleUpdateItemProvider());
    mMultiTypeAdapter.register(News.class, new NewsItemProvider());
    mMultiTypeAdapter.register(Resource.Data.class, new ResourcesItemProvider());
    mMultiTypeAdapter.register(Subtitle.class, new SubtitleItemProvider());
    mDataBinding.list.rvVertical.setAdapter(mMultiTypeAdapter);
  }

  @Override protected void initData() {
    DaggerMainComponent.builder()
        .hotListModule(new HotListModule(this))
        .scheduleUpdatesModule(new ScheduleUpdatesModule(this))
        .newsListModule(new NewsListModule(this))
        .resourcesModule(new ResourcesModule(this))
        .subtitleListModule(new SubtitleListModule(this))
        .build()
        .inject(this);

    loadData();
  }

  @Override protected void initListener() {

    mDataBinding.list.rvVertical.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (newState == RecyclerView.SCROLL_STATE_DRAGGING
            || newState == RecyclerView.SCROLL_STATE_SETTLING) {
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

  private void loadData() {
    mHotListPresenter.getHot();
  }

  @Override public void obtainHotListSuccess(List<Hot> hotList) {
    if (isFinishing()) {
      return;
    }

    mScheduleUpdatesPresenter.getScheduleUpdates(TODAY, TODAY);

    if (hotList == null || hotList.isEmpty()) {
      return;
    }

    HotList list = new HotList(hotList);
    mItems.add(list);
  }

  @Override
  public void obtainScheduleUpdatesSucceed(Map<String, List<ScheduleUpdate>> stringListMap) {
    if (isFinishing()) {
      return;
    }

    mNewsListPresenter.getNewsList();

    List<ScheduleUpdate> scheduleUpdates = stringListMap.get(TODAY);
    if (scheduleUpdates.isEmpty()) {
      return;
    }

    mItems.add(new Category("今日美剧更新"));
    ScheduleUpdateList list = new ScheduleUpdateList(scheduleUpdates);
    mItems.add(list);
  }

  @Override public void obtainNewsListSucceed(List<News> newsList) {
    if (isFinishing()) {
      return;
    }

    mResourcesPresenter.getResources();

    if (newsList == null || newsList.isEmpty()) {
      return;
    }

    mItems.add(new Category("新闻资讯"));
    mItems.addAll(newsList);
  }

  @Override public void obtainResourcesSucceed(List<Resource.Data> resources) {
    if (isFinishing()) {
      return;
    }

    mSubtitleListPresenter.getSubtitleList();

    mItems.add(new Category("资源更新"));
    mItems.addAll(resources);
  }

  @Override public void obtainSubtitleListSuccess(SubtitleList subtitleList) {
    if (isFinishing()) {
      return;
    }

    mItems.add(new Category("最新字幕"));
    mItems.addAll(subtitleList.getSubtitleList());
    mMultiTypeAdapter.notifyItemRangeChanged(0, mItems.size());
    mDataBinding.progress.bar.setVisibility(View.GONE);
  }

  @Override public void error(String error, Object... objects) {
    if (isFinishing()) {
      return;
    }

    Log.e(TAG, error);
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

    if (mResourcesPresenter != null) {
      mResourcesPresenter.dispose();
    }
  }
}
