package com.etsy.android.ui.search.v2;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri.Builder;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.ui.search.b;
import com.etsy.android.ui.search.v2.SearchSuggestionsAdapter.a;
import com.etsy.android.uikit.adapter.BaseRecyclerViewCursorAdapter;

public class SearchSuggestionsLayout extends LinearLayout implements OnQueryTextListener, a {
    private static final String TAG = f.a(SearchSuggestionsLayout.class);
    /* access modifiers changed from: private */
    public View mHistoryClearBtn;
    /* access modifiers changed from: private */
    public TextView mHistoryTitle;
    private SearchSuggestionsAdapter mRecentSearchesAdapter;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;
    private b mSearchViewHelper;

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public SearchSuggestionsLayout(Context context) {
        super(context);
        init(context);
    }

    public SearchSuggestionsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SearchSuggestionsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @TargetApi(21)
    public SearchSuggestionsLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    private void init(Context context) {
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.layout_search_suggestions, this, true);
        this.mHistoryTitle = (TextView) findViewById(R.id.search_suggestions_recent_title);
        this.mHistoryClearBtn = findViewById(R.id.search_suggestions_recent_clear_btn);
        this.mHistoryClearBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                com.etsy.android.contentproviders.a.c(SearchSuggestionsLayout.this.getContext());
                SearchSuggestionsLayout.this.refreshUiState();
            }
        });
        this.mRecyclerView = (RecyclerView) findViewById(R.id.search_suggestions_recyclerview);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
    }

    private boolean resetRecentSearchesAdapter() {
        if (this.mRecentSearchesAdapter != null) {
            this.mRecentSearchesAdapter.closeCursor();
        }
        SearchableInfo searchableInfo = ((SearchManager) getContext().getSystemService("search")).getSearchableInfo(new ComponentName(getContext(), SearchV2Activity.class));
        if (searchableInfo == null) {
            com.etsy.android.lib.logger.legacy.b.a().b(TAG, "resetRecentSearchesAdapter(): SearchManager.getSearchableInfo() returned null");
            CrashUtil.a().a(new Throwable("resetRecentSearchesAdapter(): SearchManager.getSearchableInfo() returned null"));
            return false;
        }
        Builder fragment = new Builder().scheme(ResponseConstants.CONTENT).authority(searchableInfo.getSuggestAuthority()).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        if (searchableInfo.getSuggestSelection() == null) {
            fragment.appendPath("");
        }
        fragment.appendPath("search_suggest_query");
        this.mRecentSearchesAdapter = new SearchSuggestionsAdapter(getContext(), getContext().getContentResolver().query(fragment.build(), null, null, new String[]{""}, null), "", this);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.etsy.android.contentproviders.b.a();
        if (this.mRecentSearchesAdapter != null) {
            this.mRecentSearchesAdapter.closeCursor();
        }
        if (this.mRecyclerView.getAdapter() != null && this.mRecyclerView.getAdapter() != this.mRecentSearchesAdapter) {
            ((BaseRecyclerViewCursorAdapter) this.mRecyclerView.getAdapter()).closeCursor();
        }
    }

    public boolean onQueryTextChange(String str) {
        refreshUiState(str);
        return false;
    }

    public void onSuggestionSelected(String str, int i) {
        if (this.mSearchViewHelper != null) {
            this.mSearchViewHelper.a(str, i);
        }
    }

    public void setSearchViewHelper(b bVar) {
        this.mSearchViewHelper = bVar;
    }

    public void refreshUiState() {
        if (this.mSearchViewHelper != null) {
            refreshUiState(this.mSearchViewHelper.a());
        } else {
            f.d(TAG, "No SearchViewHelper set for SearchSuggestionsLayout");
        }
    }

    private void refreshUiState(@Nullable final CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            setVisibility(0);
            com.etsy.android.contentproviders.b.a(getContext(), charSequence.toString(), new com.etsy.android.contentproviders.b.a() {
                public void a(Cursor cursor) {
                    SearchSuggestionsLayout.this.mHistoryTitle.setVisibility(8);
                    SearchSuggestionsLayout.this.mHistoryClearBtn.setVisibility(8);
                    SearchSuggestionsLayout.this.mRecyclerView.setAdapter(new SearchSuggestionsAdapter(SearchSuggestionsLayout.this.getContext(), cursor, charSequence.toString(), SearchSuggestionsLayout.this));
                }
            });
            return;
        }
        com.etsy.android.contentproviders.b.a();
        if (!(com.etsy.android.contentproviders.a.b(getContext()) > 0) || !resetRecentSearchesAdapter()) {
            setVisibility(4);
            return;
        }
        this.mHistoryTitle.setVisibility(0);
        this.mHistoryClearBtn.setVisibility(0);
        this.mRecyclerView.setAdapter(this.mRecentSearchesAdapter);
        setVisibility(0);
    }
}
