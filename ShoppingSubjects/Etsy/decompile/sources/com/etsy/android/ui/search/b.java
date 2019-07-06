package com.etsy.android.ui.search;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.arch.persistence.room.RoomDatabase;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.SearchView.SearchAutoComplete;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.etsy.android.R;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.search.v2.SearchOptions;
import com.etsy.android.ui.search.v2.SearchV2Activity;

/* compiled from: SearchViewHelper */
public class b {
    private static final String a = f.a(b.class);
    private FragmentActivity b;
    private SearchView c;
    private RelativeLayout d;
    /* access modifiers changed from: private */
    public boolean e;
    private String f;
    /* access modifiers changed from: private */
    public OnQueryTextListener g;
    private OnFocusChangeListener h;

    /* compiled from: SearchViewHelper */
    public static class a {
        private FragmentActivity a;
        private boolean b = true;
        private String c;
        private OnQueryTextListener d;
        private OnFocusChangeListener e;

        public a(FragmentActivity fragmentActivity) {
            this.a = fragmentActivity;
            this.c = fragmentActivity.getString(R.string.search_etsy_hint);
        }

        public a a() {
            this.b = false;
            return this;
        }

        public a a(@StringRes int i) {
            this.c = this.a.getString(i);
            return this;
        }

        public a a(OnQueryTextListener onQueryTextListener) {
            this.d = onQueryTextListener;
            return this;
        }

        public a a(OnFocusChangeListener onFocusChangeListener) {
            this.e = onFocusChangeListener;
            return this;
        }

        public b b() {
            b bVar = new b(this.a, this.b, this.c, this.d, this.e);
            return bVar;
        }
    }

    /* renamed from: com.etsy.android.ui.search.b$b reason: collision with other inner class name */
    /* compiled from: SearchViewHelper */
    private static class C0097b extends CursorAdapter {
        public void bindView(View view, Context context, Cursor cursor) {
        }

        public int getCount() {
            return 0;
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return null;
        }

        public C0097b(Context context) {
            super(context, null);
        }
    }

    /* compiled from: SearchViewHelper */
    private class c implements OnFocusChangeListener {
        private OnFocusChangeListener b;

        c(OnFocusChangeListener onFocusChangeListener) {
            this.b = onFocusChangeListener;
        }

        public void onFocusChange(View view, boolean z) {
            if (this.b != null) {
                this.b.onFocusChange(view, z);
            }
        }
    }

    /* compiled from: SearchViewHelper */
    public interface d {
        b getSearchViewHelper();
    }

    private b(FragmentActivity fragmentActivity, boolean z, String str, OnQueryTextListener onQueryTextListener, OnFocusChangeListener onFocusChangeListener) {
        this.e = true;
        this.b = fragmentActivity;
        this.e = z;
        this.f = str;
        this.g = onQueryTextListener;
        this.h = new c(onFocusChangeListener);
        g();
    }

    private void g() {
        SearchManager searchManager = (SearchManager) this.b.getSystemService("search");
        this.c = new SearchView(this.b);
        this.c.setIconifiedByDefault(false);
        this.c.setQueryHint(this.f);
        this.c.setSubmitButtonEnabled(false);
        this.c.setFocusable(false);
        if (this.h != null) {
            this.c.setOnQueryTextFocusChangeListener(this.h);
        }
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(new ComponentName(this.b, SearchV2Activity.class));
        this.c.setSearchableInfo(searchableInfo);
        i();
        if (this.e) {
            this.c.setQueryRefinementEnabled(false);
            this.c.setSuggestionsAdapter(new SearchSuggestionsAdapter(this.b, this.c, searchableInfo));
        } else {
            this.c.setSuggestionsAdapter(new C0097b(this.b));
        }
        this.c.setInputType(32769);
        this.d = new RelativeLayout(this.b);
        this.d.setLayoutParams(new LayoutParams(-1, -1));
        this.c.setId(RoomDatabase.MAX_BIND_PARAMETER_CNT);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(9);
        this.c.setLayoutParams(layoutParams);
        a(this.c);
        this.d.addView(this.c);
        h();
    }

    public void a(@StringRes int i) {
        a(this.b.getString(i));
    }

    public void a(String str) {
        this.c.setQueryHint(str);
    }

    private void h() {
        Context context = this.c.getContext();
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.sk_space_2);
        ViewGroup viewGroup = (ViewGroup) this.c.findViewById(R.id.search_edit_frame);
        if (viewGroup != null) {
            l.a(viewGroup, 0, 0, 0, 0);
        }
        ImageView imageView = (ImageView) this.c.findViewById(R.id.search_mag_icon);
        if (imageView != null) {
            imageView.setPadding(0, 8, 16, 8);
            imageView.setImageDrawable(com.etsy.android.uikit.c.a(context, R.drawable.sk_ic_search, R.color.sk_gray_50));
            l.a(imageView, 0, 0, 0, 0);
        }
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) this.c.findViewById(R.id.search_src_text);
        if (searchAutoComplete != null) {
            searchAutoComplete.setPadding(0, searchAutoComplete.getPaddingTop(), searchAutoComplete.getPaddingRight(), searchAutoComplete.getPaddingBottom());
            searchAutoComplete.setHintTextColor(this.c.getResources().getColor(R.color.text_mid_grey));
            searchAutoComplete.setTextSize(0, (float) this.b.getResources().getDimensionPixelSize(R.dimen.text_large));
            searchAutoComplete.setTypeface(com.etsy.android.uikit.text.typeface.a.a().a(context.getResources().getString(R.string.sk_typeface_normal)));
        }
        a(true);
        ImageView imageView2 = (ImageView) this.c.findViewById(R.id.search_close_btn);
        if (imageView2 != null) {
            imageView2.setImageDrawable(com.etsy.android.uikit.c.a(context, R.drawable.sk_ic_close, R.color.sk_gray_50));
            imageView2.setPadding(imageView2.getPaddingLeft(), imageView2.getPaddingTop(), dimensionPixelOffset, imageView2.getPaddingBottom());
            l.a(imageView2, 0, dimensionPixelOffset, 0, dimensionPixelOffset);
            this.c.setPadding(this.c.getPaddingLeft(), this.c.getPaddingTop(), this.c.getPaddingRight(), this.c.getPaddingBottom());
            imageView2.setBackground(context.getResources().getDrawable(R.drawable.sk_unbounded_selector));
        }
        ImageView imageView3 = (ImageView) this.c.findViewById(R.id.search_voice_btn);
        if (imageView3 != null) {
            imageView3.setVisibility(0);
            imageView3.setPadding(imageView3.getPaddingLeft(), imageView3.getPaddingTop(), dimensionPixelOffset, imageView3.getPaddingBottom());
            l.a(imageView3, 0, dimensionPixelOffset, 0, dimensionPixelOffset);
            imageView3.setImageDrawable(com.etsy.android.uikit.c.a(context, R.drawable.sk_ic_microphone, R.color.sk_gray_50));
            imageView3.setBackground(context.getResources().getDrawable(R.drawable.sk_unbounded_selector));
        }
    }

    @Nullable
    public CharSequence a() {
        return this.c.getQuery();
    }

    private void a(SearchView searchView) {
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        if (searchAutoComplete != null) {
            searchAutoComplete.setDropDownVerticalOffset(searchView.getResources().getDimensionPixelOffset(R.dimen.search_view_vertical_offset));
        }
    }

    private void a(boolean z) {
        LinearLayout linearLayout = (LinearLayout) this.c.findViewById(R.id.search_edit_frame);
        if (linearLayout != null) {
            linearLayout.setBackground(z ? AppCompatResources.getDrawable(this.b, R.drawable.bg_search_view_box) : null);
        }
    }

    public void b(String str) {
        a(str, false);
    }

    public void a(String str, boolean z) {
        if (this.c != null && !this.c.getQuery().equals(str)) {
            if (this.c.isIconified()) {
                this.c.setIconified(false);
            }
            this.c.setQuery(str, z);
            if (!z) {
                this.c.clearFocus();
            }
        }
    }

    public void a(OnQueryTextListener onQueryTextListener) {
        this.g = onQueryTextListener;
        i();
    }

    private void i() {
        this.c.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                if (b.this.g != null) {
                    return b.this.g.onQueryTextSubmit(str);
                }
                return false;
            }

            public boolean onQueryTextChange(String str) {
                if (b.this.g == null) {
                    return false;
                }
                if (b.this.e) {
                    return b.this.g.onQueryTextChange(str);
                }
                b.this.g.onQueryTextChange(str);
                return true;
            }
        });
    }

    public String a(Intent intent) {
        String stringExtra = intent.getStringExtra("intent_extra_data_key");
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = intent.getStringExtra(ResponseConstants.QUERY);
        }
        String str = a;
        StringBuilder sb = new StringBuilder();
        sb.append("getSearchQuery - got : ");
        sb.append(stringExtra);
        f.c(str, sb.toString());
        return stringExtra;
    }

    public SearchOptions b(Intent intent) {
        return (SearchOptions) intent.getParcelableExtra("SEARCH_OPTIONS");
    }

    public void b() {
        this.c.onActionViewExpanded();
        this.c.postDelayed(new c(this), 400);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void f() {
        this.c.setQuery("", true);
        this.c.setIconified(false);
    }

    public void c() {
        this.c.clearFocus();
    }

    public void d() {
        this.c.requestFocus();
        b();
    }

    public View e() {
        return this.d;
    }

    public void a(String str, int i) {
        e.a(this.b).a().a(str, String.valueOf(i), this.c.getQuery().toString());
    }
}
