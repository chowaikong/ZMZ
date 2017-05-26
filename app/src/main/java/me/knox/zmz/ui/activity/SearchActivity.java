package me.knox.zmz.ui.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import com.github.nukc.LoadMoreWrapper.LoadMoreWrapper;
import javax.inject.Inject;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.knox.zmz.R;
import me.knox.zmz.databinding.ActivitySearchBinding;
import me.knox.zmz.di.component.DaggerSearchComponent;
import me.knox.zmz.di.module.SearchModule;
import me.knox.zmz.entity.Search;
import me.knox.zmz.entity.SearchResult;
import me.knox.zmz.mvp.contract.SearchContract;
import me.knox.zmz.mvp.presenter.SearchPresenter;
import me.knox.zmz.ui.item.SearchItemProvider;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by KNOX.
 */

public class SearchActivity extends BaseBindingActivity<ActivitySearchBinding>
    implements SearchContract.View {

  private static final String TAG = "SearchActivity";

  @Inject SearchPresenter mSearchPresenter;

  private final Items mItems = new Items();
  private final MultiTypeAdapter mAdapter = new MultiTypeAdapter(mItems);
  private final LoadMoreWrapper mLoadMoreWrapper = LoadMoreWrapper.with(mAdapter);

  private int mPage = 0;
  private int mLimit = 10;
  private int mPreviousPosition = 0;
  private String mKeyword;

  public static void startWithTransition(Activity activity, View shareElement,
      String transitionName) {
    Intent intent = new Intent(activity, SearchActivity.class);
    ActivityOptions options =
        ActivityOptions.makeSceneTransitionAnimation(activity, shareElement, transitionName);
    activity.startActivity(intent, options.toBundle());
  }

  @Override
  protected ActivitySearchBinding setDataBindingContentView(@Nullable Bundle savedInstanceState) {
    return DataBindingUtil.setContentView(this, R.layout.activity_search);
  }

  @Override protected void initView() {
    mAdapter.register(Search.class, new SearchItemProvider());
    mDataBinding.list.rvVertical.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
    mDataBinding.list.rvVertical.setAdapter(mAdapter);
    mDataBinding.setIsKeyWordEmpty(true);
  }

  @Override protected void initData() {
    DaggerSearchComponent.builder().searchModule(new SearchModule(this)).build().inject(this);
  }

  @Override protected void initListener() {
    mDataBinding.edtSearch.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        mDataBinding.setIsKeyWordEmpty(false);
      }

      @Override public void afterTextChanged(Editable s) {
        mKeyword = s.toString().trim();
        if (TextUtils.isEmpty(mKeyword)) {
          mDataBinding.progress.setVisibility(View.GONE);
          return;
        }
        if (mSearchPresenter != null) {
          mSearchPresenter.search(mKeyword, mLimit, mPage);
          mDataBinding.progress.setVisibility(View.VISIBLE);
        }

        mItems.clear();
        mAdapter.notifyDataSetChanged();
        mDataBinding.progress.setVisibility(View.VISIBLE);
        mPage = 0;
      }
    });

    mDataBinding.ivClear.setOnClickListener(v -> {
      mDataBinding.edtSearch.setText("");
      mDataBinding.setIsKeyWordEmpty(true);
      mItems.clear();
      mPreviousPosition = 0;
      mAdapter.notifyDataSetChanged();
    });

    // load more data settings
    //mLoadMoreWrapper
    //    .setLoadMoreEnabled(false)
    //    .setListener(enabled -> {
    //      if (enabled.getLoadMoreEnabled()) {
    //        mPage++;
    //        mSearchPresenter.search(mKeyword, mLimit, mPage);
    //      }
    //    })
    //    .into(mDataBinding.list.rvVertical);
  }

  @Override public void obtainSearchResultSuccess(SearchResult searchResult) {
    if (isFinishing()) {
      return;
    }

    if (searchResult == null) {
      return;
    }

    mDataBinding.progress.setVisibility(View.GONE);


    if (searchResult.getCount() != 0) {
      mLoadMoreWrapper.setLoadMoreEnabled(true);
      mItems.addAll(searchResult.getSearches());
      mAdapter.notifyItemRangeInserted(mPreviousPosition, searchResult.getSearches().size());
      mPreviousPosition = mItems.size();

      // server may return false but not list as result, so have to prevent it before the gson error show up
      if (mItems.size() == searchResult.getCount() || searchResult.getSearches().size() < mLimit) {
        mLoadMoreWrapper.setLoadMoreEnabled(false);
        mAdapter.notifyDataSetChanged();
      }
    } else {
      mLoadMoreWrapper.setLoadMoreEnabled(false);
      mAdapter.notifyDataSetChanged();
    }

  }

  @Override public void error(String error, Object... objects) {
    if (isFinishing()) {
      return;
    }
    Log.e(TAG, error);
  }
}
