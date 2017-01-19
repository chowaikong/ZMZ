package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.LayoutVerticalRvBinding;
import me.knox.zmz.entity.ResourceList;
import me.knox.zmz.ui.activity.ResourceInfoActivity;
import me.knox.zmz.ui.adapter.ResourcesAdapter;
import me.knox.zmz.ui.util.RecyclerItemClickListener;
import me.knox.zmz.ui.widget.VerticalSpaceItemDecoration;

/**
 * Created by KNOX.
 */

public class ResourcesItemProvider extends ItemViewProvider<ResourceList, DataBindingViewHolder<LayoutVerticalRvBinding>> {
  @NonNull @Override protected DataBindingViewHolder<LayoutVerticalRvBinding> onCreateViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(LayoutVerticalRvBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<LayoutVerticalRvBinding> holder,
      @NonNull ResourceList resourceList) {
    holder.getBinding().rvVertical.addItemDecoration(new VerticalSpaceItemDecoration(20));
    ResourcesAdapter adapter = new ResourcesAdapter(new ArrayList<>());
    adapter.setData(resourceList.getList());
    holder.getBinding().rvVertical.setAdapter(adapter);
    holder.getBinding().rvVertical.addOnItemTouchListener(new RecyclerItemClickListener(
        holder.getBinding().getRoot().getContext(), (view, position) -> {
          int id = resourceList.getList().get(position).getId();
          String poster = resourceList.getList().get(position).getPoster();
          ResourceInfoActivity.start(view.getContext(), id, poster);
        }));
    holder.getBinding().executePendingBindings();
  }
}
