package me.knox.zmz.entity;

import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutInfoHeaderBinding;

/**
 * Created by KNOX.
 */

public class ResourceInfoHeaderItem extends Item<LayoutInfoHeaderBinding> {

  private String mCname;
  private String mEname;

  public ResourceInfoHeaderItem(String cname, String ename) {
    mCname = cname;
    mEname = ename;
  }

  @Override public int getLayout() {
    return R.layout.layout_info_header;
  }

  @Override public void bind(LayoutInfoHeaderBinding viewBinding, int position) {
    viewBinding.setCname(mCname);
    viewBinding.setEname(mEname);
  }
}
