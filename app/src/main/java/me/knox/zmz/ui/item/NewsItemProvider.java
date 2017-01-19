package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.LayoutVerticalRvBinding;
import me.knox.zmz.entity.NewsList;
import me.knox.zmz.ui.activity.NewsInfoActivity;
import me.knox.zmz.ui.adapter.NewsListAdapter;
import me.knox.zmz.ui.util.RecyclerItemClickListener;
import me.knox.zmz.ui.widget.VerticalSpaceItemDecoration;

/**
 * Created by KNOX.
 */

public class NewsItemProvider extends ItemViewProvider<NewsList, DataBindingViewHolder<LayoutVerticalRvBinding>> {
  @NonNull @Override protected DataBindingViewHolder<LayoutVerticalRvBinding> onCreateViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(LayoutVerticalRvBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<LayoutVerticalRvBinding> holder,
      @NonNull NewsList newsList) {
    holder.getBinding().rvVertical.addItemDecoration(new VerticalSpaceItemDecoration(20));
    NewsListAdapter adapter = new NewsListAdapter(new ArrayList<>());
    adapter.setData(newsList.getList());
    holder.getBinding().rvVertical.setAdapter(adapter);
    holder.getBinding().rvVertical.addOnItemTouchListener(new RecyclerItemClickListener(
        holder.getBinding().getRoot().getContext(),
        (view, position) -> NewsInfoActivity.start(view.getContext(), newsList.getList().get(position))));
    holder.getBinding().executePendingBindings();
  }
}
