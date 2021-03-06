package me.knox.zmz.ui.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import javax.inject.Inject;
import me.knox.zmz.R;
import me.knox.zmz.databinding.ActivityNewsBinding;
import me.knox.zmz.di.component.DaggerNewsInfoComponent;
import me.knox.zmz.di.module.NewsInfoModule;
import me.knox.zmz.entity.News;
import me.knox.zmz.entity.NewsInfo;
import me.knox.zmz.mvp.contract.NewsInfoContract;
import me.knox.zmz.mvp.presenter.NewsInfoPresenter;
import me.knox.zmz.ui.util.Toaster;

import static me.knox.zmz.misc.Constants.TRANSITION_POSTER;
import static me.knox.zmz.misc.Constants.TRANSITION_TITLE;

/**
 * Created by KNOX.
 */

public class NewsInfoActivity extends BaseBindingActivity<ActivityNewsBinding>
    implements NewsInfoContract.View {

  private static final String TAG = "NewsInfoActivity";
  private static final String NEWS = "news";
  private static final String ID = "id";

  @Inject NewsInfoPresenter mNewsInfoPresenter;

  public static void startWithTransition(Activity activity, News news, ImageView poster, TextView title) {
    Intent intent = new Intent(activity, NewsInfoActivity.class);
    intent.putExtra(NEWS, news);
    Pair posterPair = Pair.create(poster, TRANSITION_POSTER);
    Pair titlePair = Pair.create(title, TRANSITION_TITLE);
    ActivityOptions
        options = ActivityOptions.makeSceneTransitionAnimation(activity, posterPair, titlePair);
    activity.startActivity(intent, options.toBundle());
  }
  public static void startWithoutTransition(Activity activity, int id) {
    Intent intent = new Intent(activity, NewsInfoActivity.class);
    intent.putExtra(ID, id);
    activity.startActivity(intent);
  }

  @Override
  protected ActivityNewsBinding setDataBindingContentView(@Nullable Bundle savedInstanceState) {
    return DataBindingUtil.setContentView(this, R.layout.activity_news);
  }

  @Override protected void initView() {
    setupToolbar(mDataBinding.toolbarNews);
  }

  @Override protected void initData() {
    DaggerNewsInfoComponent.builder().newsInfoModule(new NewsInfoModule(this)).build().inject(this);

    if (getIntent() == null) {
      return;
    }
    News news = getIntent().getParcelableExtra(NEWS);
    int id = getIntent().getIntExtra(ID, 0);
    if (news == null) {
      if (id < 0) {
        return;
      }
      mNewsInfoPresenter.getNewsInfo(id);
    } else {
      mDataBinding.toolbarNews.setTitle(news.getTitle());
      mDataBinding.setPoster(news.getPoster());
      mDataBinding.setIntro(news.getIntro());
      mNewsInfoPresenter.getNewsInfo(news.getId());
    }
  }

  @Override protected void initListener() {
  }

  @Override public void obtainNewsInfoSucceed(NewsInfo newsInfo) {
    if (isFinishing()) {
      return;
    }
    mDataBinding.setPoster(newsInfo.getPoster());
    mDataBinding.setInfo(newsInfo);
    //mDataBinding.progress.bar.setVisibility(View.GONE);
  }

  @Override public void error(String error, Object... objects) {
    if (isFinishing()) {
      return;
    }
    Log.e(TAG, error);
    Toaster.show(getString(R.string.something_wrong_happened));
  }

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      finish();
    }
    return super.onKeyDown(keyCode, event);
  }
}
