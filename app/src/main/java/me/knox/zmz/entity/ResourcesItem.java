package me.knox.zmz.entity;

import com.genius.groupie.Item;
import java.util.List;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutVerticalRvBinding;
import me.knox.zmz.ui.activity.ResourceInfoActivity;
import me.knox.zmz.ui.adapter.ResourcesAdapter;
import me.knox.zmz.ui.util.RecyclerItemClickListener;
import me.knox.zmz.ui.widget.VerticalSpaceItemDecoration;

/**
 * Created by KNOX.
 */

public class ResourcesItem extends Item<LayoutVerticalRvBinding> {

  private ResourcesAdapter mResourcesAdapter;
  private List<Resource.Data> mDataList;

  public ResourcesItem(ResourcesAdapter resourcesAdapter) {
    mResourcesAdapter = resourcesAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_vertical_rv;
  }

  @Override public void bind(LayoutVerticalRvBinding viewBinding, int position) {
    viewBinding.rvVertical.addItemDecoration(new VerticalSpaceItemDecoration(20));
    viewBinding.rvVertical.setAdapter(mResourcesAdapter);

    if (mDataList != null && mDataList.size() > 0) {
      viewBinding.rvVertical.addOnItemTouchListener(new RecyclerItemClickListener(
          viewBinding.getRoot().getContext(),
          (view, index) -> {
            int id = mDataList.get(index).getId();
            String poster = mDataList.get(index).getPoster();
            ResourceInfoActivity.start(view.getContext(), id, poster);
          }));
    }
  }

  public void setDataList(List<Resource.Data> dataList) {
    mDataList = dataList;
  }
}
