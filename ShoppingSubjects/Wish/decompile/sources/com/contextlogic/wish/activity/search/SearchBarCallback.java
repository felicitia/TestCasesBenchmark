package com.contextlogic.wish.activity.search;

import android.support.v4.widget.CursorAdapter;

public interface SearchBarCallback {
    CursorAdapter getSearchTypeaheadAdapter();

    void handleSearchTypeaheadClick(int i);

    void onQueryChanged(String str);

    void onSearchSubmit(String str);
}
