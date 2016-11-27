package me.knox.zmz.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import me.knox.zmz.R;
import me.knox.zmz.contract.HotListContract;
import me.knox.zmz.databinding.ActivityMainBinding;
import me.knox.zmz.di.component.DaggerHotListComponent;
import me.knox.zmz.di.module.HotListModule;
import me.knox.zmz.entity.Hot;
import me.knox.zmz.presenter.HotListPresenter;
import me.knox.zmz.ui.adapter.HotListAdapter;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainActivity extends BaseActivity implements HotListContract.View {

  @Inject HotListPresenter mHotListPresenter;
  private ActivityMainBinding mActivityMainBinding;
  private HotListAdapter mHotListAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    DaggerHotListComponent.builder().hotListModule(new HotListModule(this)).build().inject(this);

    mHotListAdapter = new HotListAdapter(new ArrayList<>());
    mActivityMainBinding.hotList.setAdapter(mHotListAdapter);
    mActivityMainBinding.hotList.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));

    mHotListPresenter.getHot();
  }

  @Override public void obtainHotListSuccess(List<Hot> hotList) {
    if (isFinishing()) return;
    mHotListAdapter.setData(hotList);
  }

  @Override public void obtainHotListFailed(String error) {
    if (isFinishing()) return;
    Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
  }
}
