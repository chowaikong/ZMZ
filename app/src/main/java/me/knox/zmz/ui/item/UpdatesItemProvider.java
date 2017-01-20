package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.databinding.ItemUpdateBinding;
import me.knox.zmz.entity.Update;

/**
 * Created by KNOX.
 */

public class UpdatesItemProvider extends ItemViewProvider<Update, UpdatesItemProvider.ViewHolder> {

  @NonNull @Override protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    return new ViewHolder(ItemUpdateBinding.inflate(inflater, parent, false));
  }
  //
  //@NonNull @Override protected DataBindingViewHolder<LayoutVerticalRvBinding> onCreateViewHolder(
  //    @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
  //  return new DataBindingViewHolder<>(LayoutVerticalRvBinding.inflate(inflater, parent, false));
  //}

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Update update) {
    holder.mBinding.setUpdate(update);
  }

  //@Override
  //protected void onBindViewHolder(@NonNull DataBindingViewHolder<LayoutVerticalRvBinding> holder,
  //    @NonNull UpdateList updateList) {
  //
  //  holder.getBinding().rvVertical.removeItemDecoration(mDecoration);
  //  holder.getBinding().rvVertical.addItemDecoration(mDecoration);
  //
  //  mAdapter.setData(updateList.getList());
  //  holder.getBinding().rvVertical.setAdapter(mAdapter);
  //  holder.getBinding().executePendingBindings();
  //}

  static class ViewHolder extends RecyclerView.ViewHolder {

    private ItemUpdateBinding mBinding;

    ViewHolder(ItemUpdateBinding binding) {
      super(binding.getRoot());
      mBinding = binding;
    }
  }
}
