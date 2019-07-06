package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.ui.search.b.d;
import io.reactivex.q;
import io.reactivex.subjects.PublishSubject;

public class SearchSuggestionsPager extends ViewPager {
    private static final String STATE_CURRENT_TAB = "currentTab";
    private static final String STATE_SUPER = "super";
    public static final int TAB_ITEMS = 0;
    public static final int TAB_NONE = -1;
    public static final int TAB_SHOPS = 1;
    /* access modifiers changed from: private */
    public int mLastFinishedTab = -1;
    /* access modifiers changed from: private */
    public d mSearchViewHelperProvider;
    /* access modifiers changed from: private */
    public int mSelectedTab;
    /* access modifiers changed from: private */
    public SearchSuggestionsLayout mSuggestionsLayout;
    /* access modifiers changed from: private */
    public PublishSubject<SearchTab> tabChangeObservable;

    public enum SearchTab {
        ITEMS,
        SHOP
    }

    private class a extends TabLayoutOnPageChangeListener {
        public a(TabLayout tabLayout) {
            super(tabLayout);
        }

        public void onPageSelected(int i) {
            SearchSuggestionsPager.this.mSelectedTab = i;
            switch (SearchSuggestionsPager.this.mSelectedTab) {
                case 0:
                    b.a().a("search_items");
                    SearchSuggestionsPager.this.tabChangeObservable.onNext(SearchTab.ITEMS);
                    break;
                case 1:
                    b.a().a("search_shops");
                    SearchSuggestionsPager.this.tabChangeObservable.onNext(SearchTab.SHOP);
                    break;
            }
            super.onPageSelected(i);
        }
    }

    public SearchSuggestionsPager(Context context) {
        super(context);
        init(context);
    }

    public SearchSuggestionsPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        final LayoutInflater from = LayoutInflater.from(context);
        setAdapter(new PagerAdapter() {
            public int getCount() {
                return 2;
            }

            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }

            public Object instantiateItem(ViewGroup viewGroup, int i) {
                switch (i) {
                    case 0:
                        View inflate = from.inflate(R.layout.layout_search_pager_items, viewGroup, false);
                        TextView textView = (TextView) inflate.findViewById(R.id.search_background_text);
                        ((ImageView) inflate.findViewById(R.id.search_background_image)).setImageResource(R.drawable.ic_new_search_bg_items);
                        textView.setText(R.string.new_search_items_description);
                        SearchSuggestionsPager.this.mSuggestionsLayout = (SearchSuggestionsLayout) inflate.findViewById(R.id.search_suggestions_layout);
                        SearchSuggestionsPager.this.mSuggestionsLayout.setSearchViewHelper(SearchSuggestionsPager.this.mSearchViewHelperProvider.getSearchViewHelper());
                        viewGroup.addView(inflate);
                        return inflate;
                    case 1:
                        View inflate2 = from.inflate(R.layout.layout_search_pager_bg, viewGroup, false);
                        TextView textView2 = (TextView) inflate2.findViewById(R.id.search_background_text);
                        ((ImageView) inflate2.findViewById(R.id.search_background_image)).setImageResource(R.drawable.ic_new_search_bg_shops);
                        textView2.setText(R.string.new_search_shops_description);
                        viewGroup.addView(inflate2);
                        return inflate2;
                    default:
                        return null;
                }
            }

            public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                viewGroup.removeView((View) obj);
            }

            public void finishUpdate(ViewGroup viewGroup) {
                super.finishUpdate(viewGroup);
                if (SearchSuggestionsPager.this.mLastFinishedTab != SearchSuggestionsPager.this.mSelectedTab) {
                    SearchSuggestionsPager.this.mLastFinishedTab = SearchSuggestionsPager.this.mSelectedTab;
                    if (SearchSuggestionsPager.this.mSelectedTab == 0) {
                        SearchSuggestionsPager.this.mSuggestionsLayout.refreshUiState();
                    }
                }
            }

            public CharSequence getPageTitle(int i) {
                switch (i) {
                    case 0:
                        return SearchSuggestionsPager.this.getResources().getString(R.string.new_search_category_items);
                    case 1:
                        return SearchSuggestionsPager.this.getResources().getString(R.string.new_search_category_shops);
                    default:
                        return null;
                }
            }
        });
        setCurrentItem(0);
        this.mSelectedTab = 0;
        this.tabChangeObservable = PublishSubject.a();
    }

    public void setCurrentItem(int i) {
        this.mLastFinishedTab = -1;
        super.setCurrentItem(i);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable(STATE_SUPER));
            this.mSelectedTab = bundle.getInt(STATE_CURRENT_TAB);
            setCurrentItem(this.mSelectedTab);
        }
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_SUPER, super.onSaveInstanceState());
        bundle.putInt(STATE_CURRENT_TAB, this.mSelectedTab);
        return bundle;
    }

    public void setupWithTabLayout(TabLayout tabLayout) {
        tabLayout.setupWithViewPager(this);
        addOnPageChangeListener(new a(tabLayout));
    }

    public q<SearchTab> tabChangedObservable() {
        return this.tabChangeObservable;
    }

    public void setSearchViewHelperProvider(d dVar) {
        this.mSearchViewHelperProvider = dVar;
    }

    public int getTabSelected() {
        return this.mSelectedTab;
    }

    public SearchSuggestionsLayout getSuggestionsLayout() {
        return this.mSuggestionsLayout;
    }
}
