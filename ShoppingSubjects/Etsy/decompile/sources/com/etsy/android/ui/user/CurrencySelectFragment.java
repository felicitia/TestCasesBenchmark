package com.etsy.android.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.etsy.android.R;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.EtsyCurrency;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.CurrencyUtil.DefaultCurrency;
import com.etsy.android.lib.util.CurrencyUtil.b;
import com.etsy.android.lib.util.CurrencyUtil.c;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.aj;
import com.etsy.android.ui.EtsyCommonListFragment;
import com.etsy.android.ui.adapters.CurrencyAdapter;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import java.util.Collection;
import java.util.List;

public class CurrencySelectFragment extends EtsyCommonListFragment implements com.etsy.android.lib.util.CurrencyUtil.a {
    private static final String TAG = f.a(CurrencySelectFragment.class);
    private CurrencyAdapter mAdapter;
    private a mCallback;

    public interface a {
        void a();

        void a(EtsyCurrency etsyCurrency);
    }

    @NonNull
    public String getTrackingName() {
        return "view_locale_preferences";
    }

    public void setCurrencySelectedCallback(a aVar) {
        this.mCallback = aVar;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        setEmptyText((CharSequence) this.mActivity.getString(R.string.prefs_currency_empty));
        this.mListView.setBackgroundColor(getResources().getColor(R.color.sk_white));
        this.mAdapter = new CurrencyAdapter(this.mActivity);
        this.mListView.setAdapter(this.mAdapter);
        getCurrencies();
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            ((IDialogFragment) getParentFragment()).setTitle(this.mActivity.getString(R.string.prefs_set_currency_title));
        }
    }

    public void onListItemClick(ListView listView, View view, int i, long j) {
        EtsyCurrency etsyCurrency = (EtsyCurrency) this.mAdapter.getItem(i);
        updateUserPrefs(etsyCurrency);
        if (this.mCallback != null) {
            this.mCallback.a(etsyCurrency);
        }
        goBack();
    }

    /* access modifiers changed from: protected */
    public void onRetryClicked() {
        getCurrencies();
    }

    private void getCurrencies() {
        showLoadingView();
        b a2 = CurrencyUtil.a((com.etsy.android.lib.util.CurrencyUtil.a) this);
        if (a2 != null) {
            getRequestQueue().a((g<Result>) a2);
        }
    }

    private void updateUserPrefs(EtsyCurrency etsyCurrency) {
        if (v.a().e() && etsyCurrency != null) {
            if (NetworkUtils.a().b()) {
                getRequestQueue().a((g<Result>) new c<Result>(this.mActivity.getApplicationContext(), etsyCurrency.getCode()));
            } else {
                aj.b((Context) this.mActivity, (int) R.string.currency_save_error);
            }
        }
        CurrencyUtil.a((Context) getActivity(), etsyCurrency);
    }

    public boolean handleBackPressed() {
        goBack();
        if (this.mCallback != null) {
            this.mCallback.a();
        }
        return true;
    }

    private void goBack() {
        if (getParentFragment() != null && getParentFragment().getChildFragmentManager() != null) {
            getParentFragment().getChildFragmentManager().popBackStack();
        }
    }

    public void onCurrencyLoaded(List<EtsyCurrency> list) {
        this.mAdapter.addAll((Collection<? extends T>) list);
        if (this.mActivity != null) {
            this.mAdapter.insert(new DefaultCurrency(this.mActivity), 0);
        }
        showListView();
    }

    public void onCurrencyNoInternet() {
        showErrorView();
    }

    public void onCurrencyError() {
        showEmptyView();
    }
}
