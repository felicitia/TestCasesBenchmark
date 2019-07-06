package com.etsy.android.ui.search.v2;

import com.etsy.android.ui.search.v2.SearchSuggestionsPager.SearchTab;
import io.reactivex.functions.Consumer;

final /* synthetic */ class p implements Consumer {
    private final SearchV2Activity a;

    p(SearchV2Activity searchV2Activity) {
        this.a = searchV2Activity;
    }

    public void accept(Object obj) {
        this.a.lambda$onCreate$0$SearchV2Activity((SearchTab) obj);
    }
}
