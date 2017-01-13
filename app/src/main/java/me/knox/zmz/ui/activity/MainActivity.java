package me.knox.zmz.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import me.knox.zmz.R;
import me.knox.zmz.databinding.ActivityMainBinding;
import me.knox.zmz.ui.fragment.ResourcesFragment;
import me.knox.zmz.ui.fragment.TodayFragment;
import me.knox.zmz.ui.fragment.UpdatesFragment;

import static me.knox.zmz.R.drawable.selector_tab_hot;
import static me.knox.zmz.R.drawable.selector_tab_rescource;
import static me.knox.zmz.R.drawable.selector_tab_today;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding> {

  private static final int TAB_ICONS[] =
      new int[] { selector_tab_today, selector_tab_hot, selector_tab_rescource };

  @Override
  protected ActivityMainBinding setDataBindingContentView(@Nullable Bundle savedInstanceState) {
    return DataBindingUtil.setContentView(this, R.layout.activity_main);
  }

  @Override protected void initView() {
    HomePagerAdapter pagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
    mDataBinding.vp.setAdapter(pagerAdapter);
    mDataBinding.vp.setOffscreenPageLimit(pagerAdapter.getCount());
    mDataBinding.vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mDataBinding.tab));
    for (int TAB : TAB_ICONS) {
      mDataBinding.tab.addTab(mDataBinding.tab.newTab().setIcon(TAB));
    }
  }

  @Override protected void initData() {

  }

  @Override protected void initListener() {
    mDataBinding.tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
        mDataBinding.vp.setCurrentItem(tab.getPosition());
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }

  class HomePagerAdapter extends FragmentStatePagerAdapter {

    private UpdatesFragment mUpdatesFragment;
    private TodayFragment mTodayFragment;
    private ResourcesFragment mResourcesFragment;

    HomePagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      switch (position) {
        case 0:
          if (mUpdatesFragment == null) {
            mUpdatesFragment = UpdatesFragment.newInstance();
          }
          return mUpdatesFragment;
        case 1:
          if (mTodayFragment == null) {
            mTodayFragment = TodayFragment.newInstance();
          }
          return mTodayFragment;
        case 2:
          if (mResourcesFragment == null) {
            mResourcesFragment = ResourcesFragment.newInstance();
          }
          return mResourcesFragment;
      }
      return mUpdatesFragment;
    }

    @Override public int getCount() {
      return TAB_ICONS.length;
    }
  }
}
