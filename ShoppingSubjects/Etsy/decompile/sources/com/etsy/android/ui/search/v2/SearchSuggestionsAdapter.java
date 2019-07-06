package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.f;
import com.etsy.android.uikit.adapter.BaseRecyclerViewCursorAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class SearchSuggestionsAdapter extends BaseRecyclerViewCursorAdapter<SuggestionsHolder> {
    private static final int INVALID_INDEX = -1;
    private static final String TAG = f.a(SearchSuggestionsAdapter.class);
    /* access modifiers changed from: private */
    public a mListener;
    private String mQuery;
    private int mTextCol;

    public static final class SuggestionsHolder extends ViewHolder {
        ImageView mIconRefine;
        TextView mText;

        public SuggestionsHolder(View view) {
            super(view);
            this.mText = (TextView) view.findViewById(16908308);
            this.mIconRefine = (ImageView) view.findViewById(R.id.edit_query);
        }
    }

    public interface a {
        void onSuggestionSelected(String str, int i);
    }

    public SearchSuggestionsAdapter(Context context, Cursor cursor, String str, a aVar) {
        super(context, cursor);
        getColumnIndices(cursor);
        this.mQuery = str;
        this.mListener = aVar;
    }

    public void changeCursor(Cursor cursor) {
        super.changeCursor(cursor);
        getColumnIndices(cursor);
    }

    /* access modifiers changed from: protected */
    public SuggestionsHolder createViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new SuggestionsHolder(layoutInflater.inflate(R.layout.search_suggestion_item, viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void bindViewHolder(SuggestionsHolder suggestionsHolder, Cursor cursor) {
        suggestionsHolder.itemView.setBackgroundResource(R.drawable.list_selector_white);
        final String stringOrNull = getStringOrNull(cursor, this.mTextCol);
        final int position = cursor.getPosition() + 1;
        suggestionsHolder.mText.setSingleLine(true);
        setViewText(suggestionsHolder.mText, stringOrNull);
        suggestionsHolder.mIconRefine.setVisibility(8);
        suggestionsHolder.itemView.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (SearchSuggestionsAdapter.this.mListener != null) {
                    SearchSuggestionsAdapter.this.mListener.onSuggestionSelected(stringOrNull, position);
                }
            }
        });
    }

    private void getColumnIndices(Cursor cursor) {
        if (cursor != null) {
            this.mTextCol = cursor.getColumnIndex("suggest_text_1");
        }
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
