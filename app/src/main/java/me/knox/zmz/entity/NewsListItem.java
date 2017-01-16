package me.knox.zmz.entity;

import com.genius.groupie.Item;
import java.util.List;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutVerticalRvBinding;
import me.knox.zmz.ui.activity.NewsInfoActivity;
import me.knox.zmz.ui.adapter.NewsListAdapter;
import me.knox.zmz.ui.util.RecyclerItemClickListener;
import me.knox.zmz.ui.widget.VerticalSpaceItemDecoration;

/**
 * Created by KNOX.
 */

public class NewsListItem extends Item<LayoutVerticalRvBinding> {

  private NewsListAdapter mNewsListAdapter;
  private List<News> mNewses;

  public NewsListItem(NewsListAdapter newsListAdapter) {
    mNewsListAdapter = newsListAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_vertical_rv;
  }

  @Override public void bind(LayoutVerticalRvBinding viewBinding, int position) {
    viewBinding.rvVertical.addItemDecoration(new VerticalSpaceItemDecoration(20));
    viewBinding.rvVertical.setAdapter(mNewsListAdapter);
    if (mNewses != null && mNewses.size() > 0) {
      viewBinding.rvVertical.addOnItemTouchListener(new RecyclerItemClickListener(
          viewBinding.getRoot().getContext(),
          (view, index) -> NewsInfoActivity.start(view.getContext(), mNewses.get(index))));
    }
  }

  public void setNewses(List<News> newses) {
    mNewses = newses;
  }
}
