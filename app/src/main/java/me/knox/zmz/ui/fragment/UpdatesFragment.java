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
import me.knox.zmz.contract.UpdatesContract;
import me.knox.zmz.databinding.FragmentUpdatesBinding;
import me.knox.zmz.di.component.DaggerUpdatesCpmponent;
import me.knox.zmz.di.module.UpdatesModule;
import me.knox.zmz.entity.Update;
import me.knox.zmz.presenter.UpdatesPresenter;
import me.knox.zmz.ui.adapter.UpdatesAdapter;
import me.knox.zmz.ui.util.Toaster;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by KNOX.
 */

public class UpdatesFragment extends BaseFragment implements UpdatesContract.View {

  private final List<Update> mUpdates = new ArrayList<>();
  private final UpdatesAdapter mUpdatesAdapter = new UpdatesAdapter(mUpdates);

  @Inject UpdatesPresenter mUpdatesPresenter;

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

    DaggerUpdatesCpmponent.builder().updatesModule(new UpdatesModule(this)).build().inject(this);

    fragmentUpdatesBinding.updateList.addItemDecoration(
        new DividerItemDecoration(getContext(), VERTICAL));
    fragmentUpdatesBinding.updateList.setAdapter(mUpdatesAdapter);

    mUpdatesPresenter.getUpdates();

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
  }
}
