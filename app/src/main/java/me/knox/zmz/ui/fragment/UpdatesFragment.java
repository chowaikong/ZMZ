package me.knox.zmz.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import me.knox.zmz.entity.ScheduleUpdate;
import me.knox.zmz.entity.Update;
import me.knox.zmz.presenter.ScheduleUpdatesPresenter;
import me.knox.zmz.presenter.UpdatesPresenter;
import me.knox.zmz.ui.adapter.ScheduleUpdateAdapter;
import me.knox.zmz.ui.adapter.UpdatesAdapter;
import me.knox.zmz.ui.util.Toaster;
import org.joda.time.LocalDate;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by KNOX.
 */

public class UpdatesFragment extends BaseFragment implements UpdatesContract.View,
    ScheduleUpdatesContract.View {

  private final List<Update> mUpdates = new ArrayList<>();
  private final List<ScheduleUpdate> mScheduleUpdates = new ArrayList<>();
  private final UpdatesAdapter mUpdatesAdapter = new UpdatesAdapter(mUpdates);
  private final ScheduleUpdateAdapter mScheduleUpdateAdapter = new ScheduleUpdateAdapter(mScheduleUpdates);

  @Inject UpdatesPresenter mUpdatesPresenter;
  @Inject ScheduleUpdatesPresenter mScheduleUpdatesPresenter;

  public static UpdatesFragment newInstance() {
    Bundle bundle = new Bundle();
    UpdatesFragment fragment = new UpdatesFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    FragmentUpdatesBinding fragmentUpdatesBinding =
        FragmentUpdatesBinding.inflate(inflater, container, false);

    DaggerUpdatesComponent.builder().updatesModule(new UpdatesModule(this)).scheduleUpdatesModule(new ScheduleUpdatesModule(this)).build().inject(this);

    fragmentUpdatesBinding.updateList.addItemDecoration(
        new DividerItemDecoration(getContext(), VERTICAL));
    fragmentUpdatesBinding.updateList.setAdapter(mUpdatesAdapter);
    fragmentUpdatesBinding.scheduleUpdateList.setAdapter(mScheduleUpdateAdapter);

    mUpdatesPresenter.getUpdates();
    mScheduleUpdatesPresenter.getScheduleUpdates(LocalDate.now().toString(),
        LocalDate.now().toString());

    return fragmentUpdatesBinding.getRoot();
  }

  @Override public void obtainUpdatesSucceed(List<Update> updates) {
    if (isFragmentNotAvailable()) return;
    mUpdatesAdapter.setData(updates);
  }

  @Override public void obtainUpdatesFailed(String error) {
    if (isFragmentNotAvailable()) return;
    Toaster.showLongToast(error);
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
    mScheduleUpdateAdapter.setData(stringListMap.get(LocalDate.now().toString()));
  }

  @Override public void obtainScheduleUpdatesFailed(String error) {
    if (isFragmentNotAvailable()) return;
    Toaster.showShortToast(error);
  }
}
