package me.knox.zmz.entity;

import com.genius.groupie.Item;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.TimeUnit;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutViewPagerBinding;
import me.knox.zmz.ui.adapter.HotAdapter;

/**
 * Created by KNOX.
 */

public class CarouselItem extends Item<LayoutViewPagerBinding> {

  private HotAdapter mHotAdapter;
  private LayoutViewPagerBinding mBinding;

  public CarouselItem(HotAdapter hotAdapter) {
    mHotAdapter = hotAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_view_pager;
  }

  @Override public void bind(LayoutViewPagerBinding viewBinding, int position) {
    viewBinding.vp.setAdapter(mHotAdapter);
    mBinding = viewBinding;
    // auto scroll hot list item
    Observable.timer(5, TimeUnit.SECONDS)
        .repeat()
        .observeOn(AndroidSchedulers.mainThread())
        .map(index -> viewBinding.vp.getCurrentItem())
        .filter(index -> viewBinding.vp.getAdapter().getCount() > 0)
        .map(index -> (index + 1) % viewBinding.vp.getAdapter().getCount())
        .subscribe(index -> viewBinding.vp.setCurrentItem(index, true));
  }

  public LayoutViewPagerBinding getBinding() {
    return mBinding;
  }
}
