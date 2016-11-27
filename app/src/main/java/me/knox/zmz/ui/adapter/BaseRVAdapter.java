package me.knox.zmz.ui.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import me.knox.zmz.binding.DataBindingViewHolder;

/**
 * Created by KNOX on 2016-11-27.
 */

public abstract class BaseRVAdapter<T extends ViewDataBinding, M>
    extends RecyclerView.Adapter<DataBindingViewHolder<T>> {

  List<M> mDataList;

  public BaseRVAdapter(List<M> mDataList) {
    this.mDataList = mDataList;
  }

  @Override public int getItemCount() {
    return mDataList != null ? mDataList.size() : 0;
  }

  public void setData(List<M> dataList) {
    if (this.mDataList != null && dataList != null) {
      if (this.mDataList.isEmpty()) {
        this.mDataList.clear();
        this.mDataList.addAll(dataList);
        notifyItemRangeInserted(0, dataList.size());
      } else {
        this.mDataList.clear();
        this.mDataList.addAll(dataList);
        notifyDataSetChanged();
      }
    }
  }

  public void addData(List<M> dataList) {
    if (this.mDataList != null && dataList != null) {
      int size = this.mDataList.size();
      this.mDataList.addAll(dataList);
      notifyItemRangeInserted(size, dataList.size());
    }
  }

  public M removeData(int position) {
    M obj = null;
    if (this.mDataList != null && this.mDataList.size() > position && position >= 0) {
      obj = mDataList.remove(position);
    }
    notifyItemRemoved(position);
    return obj;
  }

  public List<M> getDataList() {
    return mDataList;
  }
}
