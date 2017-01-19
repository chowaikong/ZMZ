package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.LayoutVerticalRvBinding;
import me.knox.zmz.entity.UpdateList;
import me.knox.zmz.ui.adapter.UpdatesAdapter;
import me.knox.zmz.ui.widget.VerticalSpaceItemDecoration;

/**
 * Created by KNOX.
 */

public class UpdatesItemProvider extends ItemViewProvider<UpdateList, DataBindingViewHolder<LayoutVerticalRvBinding>> {
  @NonNull @Override protected DataBindingViewHolder<LayoutVerticalRvBinding> onCreateViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(LayoutVerticalRvBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<LayoutVerticalRvBinding> holder,
      @NonNull UpdateList updateList) {
    holder.getBinding().rvVertical.addItemDecoration(new VerticalSpaceItemDecoration(20));
    UpdatesAdapter adapter = new UpdatesAdapter(new ArrayList<>());
    adapter.setData(updateList.getList());
    holder.getBinding().rvVertical.setAdapter(adapter);
    holder.getBinding().executePendingBindings();
  }
}
