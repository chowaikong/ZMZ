package me.knox.zmz.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.genius.groupie.GroupAdapter;
import com.genius.groupie.Section;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
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
import me.knox.zmz.entity.CarouselItem;
import me.knox.zmz.entity.CategoryHeader;
import me.knox.zmz.entity.Hot;
import me.knox.zmz.entity.News;
import me.knox.zmz.entity.NewsListItem;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.entity.ResourcesItem;
import me.knox.zmz.entity.ScheduleUpdate;
import me.knox.zmz.entity.ScheduleUpdatesItem;
import me.knox.zmz.entity.Update;
import me.knox.zmz.entity.UpdatesItem;
import me.knox.zmz.presenter.HotListPresenter;
import me.knox.zmz.presenter.NewsListPresenter;
import me.knox.zmz.presenter.ResourcesPresenter;
import me.knox.zmz.presenter.ScheduleUpdatesPresenter;
import me.knox.zmz.presenter.UpdatesPresenter;
import me.knox.zmz.ui.adapter.HotAdapter;
import me.knox.zmz.ui.adapter.NewsListAdapter;
import me.knox.zmz.ui.adapter.ResourcesAdapter;
import me.knox.zmz.ui.adapter.ScheduleUpdateAdapter;
import me.knox.zmz.ui.adapter.UpdatesAdapter;
import me.knox.zmz.ui.util.Toaster;
import me.knox.zmz.ui.util.ZLog;
import org.joda.time.LocalDate;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding>
    implements HotListContract.View, ScheduleUpdatesContract.View, NewsListContract.View,
    UpdatesContract.View, ResourcesContract.View {

  private static final String TODAY = LocalDate.now().toString();

  private final Section mSectionDrama = new Section(new CategoryHeader("今日美剧更新"));
  private final Section mSectionDownloads = new Section(new CategoryHeader("今日下载更新"));
  private final Section mNewsSection = new Section(new CategoryHeader("新闻资讯"));
  private final Section mResourcesSection = new Section(new CategoryHeader("资源更新"));

  private final GroupAdapter mGroupAdapter = new GroupAdapter();

  private final List<Hot> mHotList = new ArrayList<>();
  private final HotAdapter mHotAdapter = new HotAdapter(mHotList);
  private final List<News> mNewsList = new ArrayList<>();
  private final List<ScheduleUpdate> mScheduleUpdates = new ArrayList<>();
  private final List<Update> mUpdates = new ArrayList<>();
  private final List<Resource.Data> mDataList = new ArrayList<>();
  private final ScheduleUpdateAdapter mScheduleUpdateAdapter = new ScheduleUpdateAdapter(mScheduleUpdates);
  private final NewsListAdapter mNewsListAdapter = new NewsListAdapter(mNewsList);
  private final UpdatesAdapter mUpdatesAdapter = new UpdatesAdapter(mUpdates);
  private final ResourcesAdapter mResourcesAdapter = new ResourcesAdapter(mDataList);

  private final CarouselItem mCarouselItem = new CarouselItem(mHotAdapter);
  private final ScheduleUpdatesItem mScheduleUpdatesItem = new ScheduleUpdatesItem(mScheduleUpdateAdapter);
  private final NewsListItem mNewsListItem = new NewsListItem(mNewsListAdapter);
  private final UpdatesItem mUpdatesItem = new UpdatesItem(mUpdatesAdapter);
  private final ResourcesItem mResourcesItem = new ResourcesItem(mResourcesAdapter);

  @Inject HotListPresenter mHotListPresenter;
  @Inject ScheduleUpdatesPresenter mScheduleUpdatesPresenter;
  @Inject UpdatesPresenter mUpdatesPresenter;
  @Inject NewsListPresenter mNewsListPresenter;
  @Inject ResourcesPresenter mResourcesPresenter;

  private int mPage = 0;

  @Override
  protected ActivityMainBinding setDataBindingContentView(@Nullable Bundle savedInstanceState) {
    return DataBindingUtil.setContentView(this, R.layout.activity_main);
  }

  @Override protected void initView() {
    mDataBinding.rv.rvVertical.setAdapter(mGroupAdapter);
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

    mHotListPresenter.getHot();

  }

  @Override protected void initListener() {


  }

  @Override public void obtainHotListSuccess(List<Hot> hotList) {
    if (isFinishing()) return;
    if (hotList == null || hotList.size() <= 0) return;
    mDataBinding.progress.bar.setVisibility(View.GONE);
    mHotAdapter.setData(hotList);
    mGroupAdapter.add(mCarouselItem);
    if (mCarouselItem.getBinding() != null) {
      mCarouselItem.getBinding().vp.setCurrentItem(0);
    }

    mScheduleUpdatesPresenter.getScheduleUpdates(TODAY, TODAY);
  }

  @Override public void error(String error, Object... objects) {
    if (isFinishing()) return;
    ZLog.e(error);
    Toaster.show(R.string.something_wrong_happened);
  }

  @Override
  public void obtainScheduleUpdatesSucceed(Map<String, List<ScheduleUpdate>> stringListMap) {
    if (isFinishing()) return;
    List<ScheduleUpdate> scheduleUpdates = stringListMap.get(TODAY);
    if (scheduleUpdates.size() <= 0) return;
    mScheduleUpdatesItem.setScheduleUpdates(scheduleUpdates);
    mScheduleUpdateAdapter.setData(scheduleUpdates);
    mSectionDrama.add(mScheduleUpdatesItem);
    mGroupAdapter.add(mSectionDrama);

    mNewsListPresenter.getNewsList(mPage);

  }

  @Override public void obtainNewsListSucceed(List<News> newsList) {
    if (isFinishing()) return;
    if (newsList == null || newsList.size() <= 0) return;
    mGroupAdapter.add(mNewsSection);
    mNewsListAdapter.setData(newsList);
    mGroupAdapter.add(mNewsListItem);

    mUpdatesPresenter.getUpdates();

  }

  @Override public void obtainUpdatesSucceed(List<Update> updates) {
    if (isFinishing()) return;
    if (updates == null || updates.size() <= 0) return;
    mGroupAdapter.add(mSectionDownloads);
    mUpdatesAdapter.setData(updates);
    mSectionDownloads.add(mUpdatesItem);

    mResourcesPresenter.getResources(mPage);

  }

  @Override public void obtainResourcesSucceed(List<Resource.Data> resources) {
    if (isFinishing()) return;
    if (resources == null || resources.size() <= 0) return;
    mGroupAdapter.add(mResourcesSection);
    mResourcesAdapter.setData(resources);
    mResourcesItem.setDataList(resources);
    mGroupAdapter.add(mResourcesItem);
  }
}
