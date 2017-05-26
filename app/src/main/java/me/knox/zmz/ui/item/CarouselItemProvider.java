package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import me.drakeet.multitype.ItemViewBinder;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.LayoutViewPagerBinding;
import me.knox.zmz.entity.HotList;
import me.knox.zmz.ui.adapter.HotAdapter;

/**
 * Created by KNOX.
 */

public class CarouselItemProvider extends
    ItemViewBinder<HotList, DataBindingViewHolder<LayoutViewPagerBinding>> {

  @NonNull @Override protected DataBindingViewHolder<LayoutViewPagerBinding> onCreateViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(LayoutViewPagerBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<LayoutViewPagerBinding> holder,
      @NonNull HotList hotList) {
    HotAdapter hotAdapter = new HotAdapter(new ArrayList<>());
    hotAdapter.setData(hotList.getList());
    holder.getBinding().vp.setAdapter(hotAdapter);
    holder.getBinding().vp.setCurrentItem(0);
    // auto scroll list item
    Observable.timer(10, TimeUnit.SECONDS)
        .repeat()
        .observeOn(AndroidSchedulers.mainThread())
        .map(index -> holder.getBinding().vp.getCurrentItem())
        .filter(index -> holder.getBinding().vp.getAdapter().getCount() > 0)
        .map(index -> (index + 1) % holder.getBinding().vp.getAdapter().getCount())
        .subscribe(index -> holder.getBinding().vp.setCurrentItem(index, true));
  }
}
