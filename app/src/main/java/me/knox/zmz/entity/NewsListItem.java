package me.knox.zmz.entity;

import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutVerticalRvBinding;
import me.knox.zmz.ui.adapter.NewsListAdapter;
import me.knox.zmz.ui.widget.VerticalSpaceItemDecoration;

/**
 * Created by KNOX.
 */

public class NewsListItem extends Item<LayoutVerticalRvBinding> {

  private NewsListAdapter mNewsListAdapter;
  private LayoutVerticalRvBinding mRvBinding;

  public NewsListItem(NewsListAdapter newsListAdapter) {
    mNewsListAdapter = newsListAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_vertical_rv;
  }

  @Override public void bind(LayoutVerticalRvBinding viewBinding, int position) {
    mRvBinding = viewBinding;
    viewBinding.rvVertical.addItemDecoration(new VerticalSpaceItemDecoration(30));
    viewBinding.rvVertical.setAdapter(mNewsListAdapter);
  }
}
