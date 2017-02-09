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
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import me.knox.zmz.R;
import me.knox.zmz.contract.SearchContract;
import me.knox.zmz.databinding.ActivitySearchBinding;
import me.knox.zmz.di.component.DaggerSearchComponent;
import me.knox.zmz.di.module.SearchModule;
import me.knox.zmz.entity.Search;
import me.knox.zmz.entity.SearchResult;
import me.knox.zmz.presenter.SearchPresenter;
import me.knox.zmz.ui.adapter.SearchResultAdapter;
import me.knox.zmz.ui.util.RecyclerItemClickListener;
import me.knox.zmz.ui.util.ZLog;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by KNOX.
 */

public class SearchActivity extends BaseBindingActivity<ActivitySearchBinding>
    implements SearchContract.View {

  @Inject SearchPresenter mSearchPresenter;

  private final List<Search> mSearches = new ArrayList<>();
  private final SearchResultAdapter mSearchResultAdapter = new SearchResultAdapter(mSearches);

  public static void startWithTransition(Activity activity, View shareElement, String transitionName) {
    Intent intent = new Intent(activity, SearchActivity.class);
    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, shareElement, transitionName);
    activity.startActivity(intent, options.toBundle());
  }

  @Override
  protected ActivitySearchBinding setDataBindingContentView(@Nullable Bundle savedInstanceState) {
    return DataBindingUtil.setContentView(this, R.layout.activity_search);
  }

  @Override protected void initView() {
    mDataBinding.list.rvVertical.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
    mDataBinding.list.rvVertical.setAdapter(mSearchResultAdapter);
    mDataBinding.setIsEmpty(true);
  }

  @Override protected void initData() {
    DaggerSearchComponent.builder().searchModule(new SearchModule(this)).build().inject(this);
  }

  @Override protected void initListener() {
    mDataBinding.edtSearch.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        mDataBinding.setIsEmpty(false);
      }

      @Override public void afterTextChanged(Editable s) {
        String keyword = s.toString();
        if (TextUtils.isEmpty(keyword)) return;
        if (mSearchPresenter != null) {
          mSearchPresenter.search(keyword);
          mDataBinding.progress.setVisibility(View.VISIBLE);
        }
      }
    });
    mDataBinding.list.rvVertical.addOnItemTouchListener(
        new RecyclerItemClickListener(getContext(), (view, position) -> {
          String type = mSearches.get(position).getType();
          int id = mSearches.get(position).getId();
          switch (type) {
            case "resource":
              ResourceInfoActivity.startWithoutTransition(view.getContext(), id);
              break;
            case "article":
              NewsInfoActivity.startWithoutTransition(this, id);
              break;
          }
        }));

    mDataBinding.ivClear.setOnClickListener(v -> {
      mDataBinding.edtSearch.setText("");
      mDataBinding.setIsEmpty(true);
    });
  }

  @Override public void obtainSearchResultSuccess(SearchResult searchResult) {
    if (isFinishing()) return;
    mSearches.addAll(searchResult.getSearches());
    mSearchResultAdapter.setData(searchResult.getSearches());
    mDataBinding.progress.setVisibility(View.GONE);
  }

  @Override public void error(String error, Object... objects) {
    if (isFinishing()) return;
    ZLog.e(error);
  }
}
