package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.databinding.ItemResourceBinding;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.ui.activity.ResourceInfoActivity;

/**
 * Created by KNOX.
 */

public class ResourcesItemProvider extends ItemViewProvider<Resource.Data, ResourcesItemProvider.ViewHolder> {

  @NonNull @Override protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    return new ViewHolder(ItemResourceBinding.inflate(inflater, parent, false));
  }

  //@NonNull @Override protected DataBindingViewHolder<LayoutVerticalRvBinding> onCreateViewHolder(
  //    @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
  //  return new DataBindingViewHolder<>(LayoutVerticalRvBinding.inflate(inflater, parent, false));
  //}

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Resource.Data data) {
    holder.mBinding.setResource(data);
  }

  //@Override
  //protected void onBindViewHolder(@NonNull DataBindingViewHolder<LayoutVerticalRvBinding> holder,
  //    @NonNull ResourceList resourceList) {
  //  holder.getBinding().rvVertical.addItemDecoration(mDecoration);
  //  mAdapter.setData(resourceList.getList());
  //  holder.getBinding().rvVertical.setAdapter(mAdapter);
  //  holder.getBinding().rvVertical.addOnItemTouchListener(new RecyclerItemClickListener(
  //      holder.getBinding().getRoot().getContext(), (view, position) -> {
  //        int id = resourceList.getList().get(position).getId();
  //        String poster = resourceList.getList().get(position).getPoster();
  //        ResourceInfoActivity.start(view.getContext(), id, poster);
  //      }));
  //  holder.getBinding().executePendingBindings();
  //}

  static class ViewHolder extends RecyclerView.ViewHolder {

    private ItemResourceBinding mBinding;

    ViewHolder(ItemResourceBinding binding) {
      super(binding.getRoot());
      mBinding = binding;
      mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          int id = mBinding.getResource().getId();
          String poster = mBinding.getResource().getPoster();
          ResourceInfoActivity.start(v.getContext(), id, poster);
        }
      });
    }
  }
}
