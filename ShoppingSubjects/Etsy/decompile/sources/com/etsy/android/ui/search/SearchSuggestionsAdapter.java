package com.etsy.android.ui.search;

import android.app.SearchableInfo;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri.Builder;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v7.widget.SearchView;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.af;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class SearchSuggestionsAdapter extends ResourceCursorAdapter {
    private static final int INVALID_INDEX = -1;
    private static final int QUERY_LIMIT = 50;
    private static final String TAG = f.a(SearchSuggestionsAdapter.class);
    private OnClickListener mClickListener;
    private boolean mClosed = false;
    private Context mContext;
    private int mFlagsCol;
    private String mQuery;
    /* access modifiers changed from: private */
    public SearchView mSearchView;
    private SearchableInfo mSearchable;
    private int mTextCol;

    private static final class a {
        public final TextView a;
        public final ImageView b;

        public a(View view) {
            this.a = (TextView) view.findViewById(16908308);
            this.b = (ImageView) view.findViewById(R.id.edit_query);
        }
    }

    public boolean hasStableIds() {
        return false;
    }

    public SearchSuggestionsAdapter(Context context, SearchView searchView, SearchableInfo searchableInfo) {
        super(context, (int) R.layout.search_suggestion_item, (Cursor) null, true);
        this.mContext = context;
        this.mSearchView = searchView;
        this.mSearchable = searchableInfo;
        this.mClickListener = createClickListener();
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String str;
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("runQueryOnBackgroundThread(");
        sb.append(charSequence);
        sb.append(")");
        f.c(str2, sb.toString());
        if (charSequence == null) {
            str = "";
        } else {
            str = charSequence.toString();
        }
        this.mQuery = str;
        if (this.mSearchView.getVisibility() != 0 || this.mSearchView.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor suggestions = getSuggestions(this.mQuery, 50);
            if (suggestions != null) {
                suggestions.getCount();
                return suggestions;
            }
        } catch (RuntimeException e) {
            f.d(TAG, "Search suggestions query threw an exception.", (Throwable) e);
        }
        return null;
    }

    public Cursor getSuggestions(String str, int i) {
        String[] strArr = null;
        if (this.mSearchable == null) {
            return null;
        }
        String suggestAuthority = this.mSearchable.getSuggestAuthority();
        if (suggestAuthority == null) {
            return null;
        }
        Builder fragment = new Builder().scheme(ResponseConstants.CONTENT).authority(suggestAuthority).query("").fragment("");
        String suggestPath = this.mSearchable.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = this.mSearchable.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.mContext.getContentResolver().query(fragment.build(), null, suggestSelection, strArr2, null);
    }

    public void close() {
        f.c(TAG, "close()");
        changeCursor(null);
        this.mClosed = true;
    }

    public void changeCursor(Cursor cursor) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("changeCursor(");
        sb.append(cursor);
        sb.append(")");
        f.c(str, sb.toString());
        if (this.mClosed) {
            f.d(TAG, "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
            }
            return;
        }
        try {
            super.changeCursor(cursor);
            if (cursor != null) {
                this.mTextCol = cursor.getColumnIndex("suggest_text_1");
                this.mFlagsCol = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e) {
            f.e(TAG, "error changing cursor and caching columns", e);
        }
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View newView = super.newView(context, cursor, viewGroup);
        newView.setTag(new a(newView));
        return newView;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        Spannable spannable;
        a aVar = (a) view.getTag();
        if (cursor != null && cursor.getColumnCount() > 0) {
            int i = (this.mFlagsCol >= cursor.getColumnCount() || this.mFlagsCol == -1) ? 0 : cursor.getInt(this.mFlagsCol);
            if (aVar.a != null) {
                String stringOrNull = getStringOrNull(cursor, this.mTextCol);
                aVar.a.setSingleLine(true);
                if (!TextUtils.isEmpty(this.mQuery)) {
                    aVar.a.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    if (i != 1) {
                        aVar.a.setSingleLine(false);
                        spannable = af.a(stringOrNull, this.mQuery, this.mContext);
                    } else {
                        spannable = af.b(stringOrNull, this.mQuery, this.mContext);
                    }
                    setViewText(aVar.a, spannable);
                } else if (TextUtils.isEmpty(this.mQuery)) {
                    setViewText(aVar.a, stringOrNull);
                    aVar.a.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_recentsearch, 0, 0, 0);
                }
                if ((i & 1) != 0) {
                    aVar.b.setVisibility(0);
                    aVar.b.setImageResource(R.drawable.ic_searchfocus);
                    aVar.b.setTag(aVar.a.getText());
                    aVar.b.setOnClickListener(this.mClickListener);
                    return;
                }
                aVar.b.setVisibility(8);
            }
        }
    }

    private OnClickListener createClickListener() {
        return new TrackingOnClickListener() {
            public void onViewClick(View view) {
                Object tag = view.getTag();
                if (tag instanceof CharSequence) {
                    String obj = tag.toString();
                    SearchSuggestionsAdapter.this.mSearchView.setQuery(obj, false);
                    SearchSuggestionsAdapter.this.trackSearchSuggestion(obj);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void trackSearchSuggestion(String str) {
        b.a().a("autosuggest_listing_searched", "browselistings", str);
    }

    private void setViewText(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private String getStringOrNull(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e) {
            f.e(TAG, "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }
}
