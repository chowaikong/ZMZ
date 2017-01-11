package me.knox.zmz.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.genius.groupie.GroupAdapter;
import com.genius.groupie.Section;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import me.knox.zmz.contract.ScheduleUpdatesContract;
import me.knox.zmz.contract.UpdatesContract;
import me.knox.zmz.databinding.FragmentUpdatesBinding;
import me.knox.zmz.di.component.DaggerUpdatesComponent;
import me.knox.zmz.di.module.ScheduleUpdatesModule;
import me.knox.zmz.di.module.UpdatesModule;
import me.knox.zmz.entity.CarouselItem;
import me.knox.zmz.entity.CategoryHeader;
import me.knox.zmz.entity.ScheduleUpdate;
import me.knox.zmz.entity.Update;
import me.knox.zmz.entity.UpdatesItem;
import me.knox.zmz.presenter.ScheduleUpdatesPresenter;
import me.knox.zmz.presenter.UpdatesPresenter;
import me.knox.zmz.ui.adapter.ScheduleUpdateAdapter;
import me.knox.zmz.ui.adapter.UpdatesAdapter;
import me.knox.zmz.ui.util.Toaster;
import org.joda.time.LocalDate;

/**
 * Created by KNOX.
 */

public class UpdatesFragment extends BindingLazyFragment<FragmentUpdatesBinding> implements UpdatesContract.View,
    ScheduleUpdatesContract.View {

  private static final String TODAY = LocalDate.now().toString();

  private final GroupAdapter mUpdatesGroupAdapter = new GroupAdapter();

  private final Section mSectionDrama = new Section(new CategoryHeader("今日美剧更新"));
  private final Section mSectionDownloads = new Section(new CategoryHeader("今日下载更新"));

  @Inject UpdatesPresenter mUpdatesPresenter;
  @Inject ScheduleUpdatesPresenter mScheduleUpdatesPresenter;

  public static UpdatesFragment newInstance() {
    Bundle bundle = new Bundle();
    UpdatesFragment fragment = new UpdatesFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  protected FragmentUpdatesBinding getDataBinding(LayoutInflater inflater, ViewGroup container) {
    return FragmentUpdatesBinding.inflate(inflater, container, false);
  }

  @Override protected void initView() {
    mDataBinding.rv.setAdapter(mUpdatesGroupAdapter);
  }

  @Override protected void initData() {
    DaggerUpdatesComponent.builder()
        .updatesModule(new UpdatesModule(this))
        .scheduleUpdatesModule(new ScheduleUpdatesModule(this))
        .build()
        .inject(this);

    mScheduleUpdatesPresenter.getScheduleUpdates(TODAY, TODAY); // 今日美剧更新
    mUpdatesPresenter.getUpdates();                             // 今日下载更新
  }

  @Override public void obtainUpdatesSucceed(List<Update> updates) {
    if (isFragmentNotAvailable()) return;
    if (updates == null || updates.size() <= 0) return;
    UpdatesItem updatesItem = new UpdatesItem(new UpdatesAdapter(new ArrayList<>()));
    updatesItem.getUpdatesAdapter().setData(updates);
    mSectionDownloads.add(updatesItem);
    mUpdatesGroupAdapter.add(mSectionDownloads);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();

    if (mUpdatesPresenter != null) {
      mUpdatesPresenter.dispose();
    }

    if (mScheduleUpdatesPresenter != null) {
      mScheduleUpdatesPresenter.dispose();
    }
  }

  @Override
  public void obtainScheduleUpdatesSucceed(Map<String, List<ScheduleUpdate>> stringListMap) {
    if (isFragmentNotAvailable()) return;
    List<ScheduleUpdate> scheduleUpdates = stringListMap.get(TODAY);
    if (scheduleUpdates.size() <= 0) return;
    CarouselItem carouselItem = new CarouselItem(new ScheduleUpdateAdapter(new ArrayList<>()));
    carouselItem.getUpdatesAdapter().setData(scheduleUpdates);
    mSectionDrama.add(carouselItem);
    mUpdatesGroupAdapter.add(mSectionDrama);
  }

  @Override public void error(String error, Object... objects) {
    if (isFragmentNotAvailable()) return;
    Toaster.show(error);
  }
}
