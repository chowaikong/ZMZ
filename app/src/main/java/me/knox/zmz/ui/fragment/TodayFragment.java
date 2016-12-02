package me.knox.zmz.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import me.knox.zmz.contract.ScheduleUpdatesContract;
import me.knox.zmz.databinding.FragmentTodayBinding;
import me.knox.zmz.di.component.DaggerScheduleUpdatesComponent;
import me.knox.zmz.di.module.ScheduleUpdatesModule;
import me.knox.zmz.entity.ScheduleUpdate;
import me.knox.zmz.presenter.ScheduleUpdatesPresenter;
import org.joda.time.LocalDate;

/**
 * Created by KNOX.
 */

public class TodayFragment extends BaseFragment implements ScheduleUpdatesContract.View {

  @Inject ScheduleUpdatesPresenter mScheduleUpdatesPresenter;

  public static TodayFragment newInstance() {
    Bundle bundle = new Bundle();
    TodayFragment fragment = new TodayFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    DaggerScheduleUpdatesComponent.builder()
        .scheduleUpdatesModule(new ScheduleUpdatesModule(this))
        .build()
        .inject(this);

    mScheduleUpdatesPresenter.getScheduleUpdates(LocalDate.now().toString(),
        LocalDate.now().toString());

    return FragmentTodayBinding.inflate(inflater, container, false).getRoot();
  }

  @Override
  public void obtainScheduleUpdatesSucceed(Map<String, List<ScheduleUpdate>> stringListMap) {

  }

  @Override public void obtainScheduleUpdatesFailed(String error) {

  }
}
