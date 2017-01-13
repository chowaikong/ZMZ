package me.knox.zmz.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import me.knox.zmz.databinding.ItemHotBinding;
import me.knox.zmz.entity.Hot;

/**
 * Created by KNOX.
 */

public class HotAdapter extends PagerAdapter {

  private List<Hot> mHotList;

  public HotAdapter(@NonNull List<Hot> hotList) {
    mHotList = hotList;
  }

  @Override public int getCount() {
    return mHotList == null ? 0 : mHotList.size();
  }

  @Override public int getItemPosition(Object object) {
    return POSITION_NONE;
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    ItemHotBinding binding = ItemHotBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
    binding.setHot(mHotList.get(position));
    container.addView(binding.getRoot());
    return binding.getRoot();
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  public void setData(@NonNull List<Hot> data) {
    mHotList.clear();
    mHotList.addAll(data);
    notifyDataSetChanged();
  }
}
