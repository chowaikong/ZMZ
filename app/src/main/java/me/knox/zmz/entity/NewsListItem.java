package me.knox.zmz.entity;

import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutVerticalRvBinding;
import me.knox.zmz.ui.adapter.NewsListAdapter;

/**
 * Created by KNOX.
 */

public class NewsListItem extends Item<LayoutVerticalRvBinding> {

  private NewsListAdapter mNewsListAdapter;

  public NewsListItem(NewsListAdapter newsListAdapter) {
    mNewsListAdapter = newsListAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_vertical_rv;
  }

  @Override public void bind(LayoutVerticalRvBinding viewBinding, int position) {
    viewBinding.rvVertical.setAdapter(mNewsListAdapter);
    viewBinding.executePendingBindings();
  }
}
