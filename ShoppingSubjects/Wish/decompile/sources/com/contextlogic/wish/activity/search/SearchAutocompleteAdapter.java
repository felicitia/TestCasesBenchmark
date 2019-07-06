package com.contextlogic.wish.activity.search;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

public class SearchAutocompleteAdapter extends CursorAdapter implements Filterable {
    private static final String[] COLUMNS = {"_id", "suggest_text_1"};
    private SearchActivity mActivity;
    private String mCurrentQuery;
    private MatrixCursor mDefaultCursor = new MatrixCursor(COLUMNS);
    private SearchFragment mFragment;
    private ArrayList<String> mPendingQueryResults;
    private CountDownLatch mQueryLatch;
    private ArrayList<String> mSearchHistory;

    private static class ItemRowHolder {
        TextView rowText;

        private ItemRowHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public SearchAutocompleteAdapter(SearchActivity searchActivity, SearchFragment searchFragment) {
        super(searchActivity, new MatrixCursor(COLUMNS), false);
        this.mActivity = searchActivity;
        this.mFragment = searchFragment;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = this.mActivity.getLayoutInflater().inflate(R.layout.search_fragment_row, viewGroup, false);
        ItemRowHolder itemRowHolder = new ItemRowHolder();
        itemRowHolder.rowText = (TextView) inflate.findViewById(R.id.search_fragment_row_text);
        inflate.setTag(itemRowHolder);
        return inflate;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ItemRowHolder itemRowHolder = (ItemRowHolder) view.getTag();
        itemRowHolder.rowText.setText(cursor.getString(cursor.getColumnIndex("suggest_text_1")));
    }

    public String convertToString(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex("suggest_text_1"));
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        int i = 0;
        if (charSequence != null && !charSequence.toString().trim().equals("")) {
            MatrixCursor matrixCursor = new MatrixCursor(COLUMNS);
            String trim = charSequence.toString().trim();
            this.mCurrentQuery = trim;
            this.mQueryLatch = new CountDownLatch(1);
            this.mFragment.fetchAutocompleteResults(trim);
            try {
                this.mQueryLatch.await();
            } catch (InterruptedException unused) {
            }
            if (this.mPendingQueryResults == null) {
                return matrixCursor;
            }
            Iterator it = this.mPendingQueryResults.iterator();
            while (it.hasNext()) {
                matrixCursor.addRow(createAutocompleteRow(i, (String) it.next()));
                i++;
            }
            return matrixCursor;
        } else if (!ExperimentDataCenter.getInstance().shouldSeeSearchHistoryInAutocomplete()) {
            return this.mDefaultCursor;
        } else {
            MatrixCursor matrixCursor2 = new MatrixCursor(COLUMNS);
            if (this.mSearchHistory == null) {
                return matrixCursor2;
            }
            Iterator it2 = this.mSearchHistory.iterator();
            while (it2.hasNext()) {
                String str = (String) it2.next();
                if (i >= 5) {
                    return matrixCursor2;
                }
                matrixCursor2.addRow(createAutocompleteRow(i, str));
                i++;
            }
            return matrixCursor2;
        }
    }

    public void updateAutocompleteResults(ArrayList<String> arrayList) {
        if (this.mQueryLatch != null) {
            this.mPendingQueryResults = arrayList;
            this.mQueryLatch.countDown();
        }
    }

    public void setSearchHistory(ArrayList<String> arrayList) {
        this.mSearchHistory = arrayList;
    }

    private static Object[] createAutocompleteRow(int i, String str) {
        return new Object[]{Integer.valueOf(i), str};
    }

    public ArrayList<String> getPendingQueryResults() {
        return this.mPendingQueryResults;
    }

    public String getCurrentQuery() {
        return this.mCurrentQuery;
    }
}
