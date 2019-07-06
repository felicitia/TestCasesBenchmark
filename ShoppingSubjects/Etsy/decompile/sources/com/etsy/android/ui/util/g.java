package com.etsy.android.ui.util;

import com.etsy.android.lib.core.k;
import com.etsy.android.uikit.e;

/* compiled from: StockResponseFactory */
public class g {

    /* compiled from: StockResponseFactory */
    public static class a implements com.etsy.android.lib.core.f.a {
        protected e a;

        public a(e eVar) {
            this.a = eVar;
        }

        public void a(k kVar) {
            if (this.a.getApiOffset() == 0) {
                this.a.showEmptyView();
            } else {
                this.a.stopEndless();
            }
        }
    }

    /* compiled from: StockResponseFactory */
    public static class b implements com.etsy.android.lib.core.f.b {
        protected e a;

        public b(e eVar) {
            this.a = eVar;
        }

        public void a(int i, String str, k kVar) {
            if (this.a.getApiOffset() == 0) {
                this.a.showErrorView();
            } else {
                this.a.showEndlessError();
            }
        }
    }

    public static com.etsy.android.lib.core.f.a a(e eVar) {
        return new a(eVar);
    }

    public static com.etsy.android.lib.core.f.b b(e eVar) {
        return new b(eVar);
    }
}
