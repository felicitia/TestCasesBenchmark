package com.etsy.android.contentproviders;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.text.TextUtils;
import com.etsy.android.R;
import com.etsy.android.lib.core.f.c;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.SearchSuggestion;
import com.etsy.android.lib.models.apiv3.SearchSuggestions;
import java.util.List;
import java.util.Locale;

/* compiled from: SearchSuggestionCursorProvider */
public class b {
    private static final C0044b a = new C0044b();
    private static String[] b = {"_id", "suggest_text_1", "suggest_intent_data", "suggest_intent_extra_data", "suggest_flags"};
    private static Cursor c = new MatrixCursor(b, 0);

    /* compiled from: SearchSuggestionCursorProvider */
    public interface a {
        void a(Cursor cursor);
    }

    /* renamed from: com.etsy.android.contentproviders.b$b reason: collision with other inner class name */
    /* compiled from: SearchSuggestionCursorProvider */
    private static class C0044b {
        private C0044b() {
        }
    }

    /* access modifiers changed from: private */
    public static Cursor b(Context context, List<SearchSuggestion> list, String str, int i, boolean z) {
        int min = Math.min(list.size(), i);
        int i2 = min + 1;
        MatrixCursor matrixCursor = new MatrixCursor(b, i2);
        int i3 = 0;
        while (i3 < min) {
            String query = ((SearchSuggestion) list.get(i3)).getQuery();
            i3++;
            matrixCursor.addRow(new Object[]{Integer.valueOf(i3), query, query, query, Integer.valueOf(1)});
        }
        if (z && str.length() >= 3) {
            matrixCursor.addRow(new Object[]{Integer.valueOf(i2), context.getString(R.string.shops_or_users_containing, new Object[]{str}), "ETSY_SHOP_USER", str, Integer.valueOf(0)});
        }
        return matrixCursor;
    }

    public static Cursor a(final Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return c;
        }
        final Cursor[] cursorArr = {c};
        final String lowerCase = str.toLowerCase(Locale.getDefault());
        v.a().j().b(a(str, (c<SearchSuggestions>) new c<SearchSuggestions>() {
            public void a(List<SearchSuggestions> list, int i, k<SearchSuggestions> kVar) {
                if (!list.isEmpty()) {
                    cursorArr[0] = b.b(context, ((SearchSuggestions) list.get(0)).getResults(), lowerCase, 6, true);
                }
            }
        }));
        return cursorArr[0];
    }

    public static void a(final Context context, String str, final a aVar) {
        if (!TextUtils.isEmpty(str)) {
            final String lowerCase = str.toLowerCase(Locale.getDefault());
            i a2 = a(lowerCase, (c<SearchSuggestions>) new c<SearchSuggestions>() {
                public void a(List<SearchSuggestions> list, int i, k<SearchSuggestions> kVar) {
                    if (!list.isEmpty()) {
                        aVar.a(b.b(context, ((SearchSuggestions) list.get(0)).getResults(), lowerCase, 6, false));
                    }
                }
            });
            a();
            v.a().j().a((Object) a, (g<Result>) a2);
        }
    }

    public static void a() {
        v.a().j().a((Object) a);
    }

    private static i a(String str, c<SearchSuggestions> cVar) {
        return m.a(SearchSuggestions.class, "/etsyapps/v3/public/search/suggestions").a(ResponseConstants.QUERY, str).a(cVar).a();
    }
}
