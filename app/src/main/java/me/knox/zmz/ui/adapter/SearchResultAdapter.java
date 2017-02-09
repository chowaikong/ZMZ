package me.knox.zmz.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemSearchResultBinding;
import me.knox.zmz.entity.Search;

/**
 * Created by KNOX.
 */

public class SearchResultAdapter extends BaseRVAdapter<ItemSearchResultBinding, Search> {

  public SearchResultAdapter(List<Search> mDataList) {
    super(mDataList);
  }

  @Override
  public DataBindingViewHolder<ItemSearchResultBinding> onCreateViewHolder(ViewGroup parent,
      int viewType) {
    return new DataBindingViewHolder<>(ItemSearchResultBinding.inflate(LayoutInflater.from(parent.getContext())));
  }

  @Override public void onBindViewHolder(DataBindingViewHolder<ItemSearchResultBinding> holder,
      int position) {
    holder.getBinding().setSearch(mDataList.get(holder.getAdapterPosition()));
    holder.getBinding().executePendingBindings();
  }
}
