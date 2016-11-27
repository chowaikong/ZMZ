package me.knox.zmz.binding;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by KNOX on 2016-11-27.
 */

public class DataBindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

  private T mBinding;

  public DataBindingViewHolder(T binding) {
    super(binding.getRoot());
    this.mBinding = binding;
  }

  public T getBinding() {
    return mBinding;
  }
}
