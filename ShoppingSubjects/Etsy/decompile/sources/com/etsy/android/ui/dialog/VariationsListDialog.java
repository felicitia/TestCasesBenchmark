package com.etsy.android.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.models.Option;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.ListingsRequest;
import com.etsy.android.lib.util.ak;
import com.etsy.android.ui.EtsyCommonListFragment;
import com.etsy.android.ui.adapters.VariationAdapter;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import java.util.List;

public class VariationsListDialog extends EtsyCommonListFragment implements OnItemClickListener {
    private VariationAdapter mAdapter;
    private b mListener;
    /* access modifiers changed from: private */
    public EtsyId mListingId;
    private IDialogFragment mParentDialog;
    private Variation mVariation;
    /* access modifiers changed from: private */
    public long mVariationId;

    private class a extends i<Variation> {
        private a() {
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<Variation> a() {
            return ListingsRequest.getVariations(String.valueOf(VariationsListDialog.this.mListingId));
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<Variation> kVar) {
            if (!kVar.a()) {
                VariationsListDialog.this.onLoadError();
            } else if (kVar.i()) {
                ak.a(VariationsListDialog.this.mListingId, kVar.g());
                VariationsListDialog.this.onItemsLoaded(ak.a(kVar.g(), VariationsListDialog.this.mVariationId));
            } else {
                VariationsListDialog.this.showEmptyView();
            }
        }
    }

    public interface b<T> {
        void a(Variation variation, Option option);
    }

    @NonNull
    public String getTrackingName() {
        return "listing_variation_options";
    }

    public static VariationsListDialog newInstance(EtsyId etsyId, long j, b bVar) {
        VariationsListDialog variationsListDialog = new VariationsListDialog();
        variationsListDialog.setListener(bVar);
        variationsListDialog.setListingId(etsyId);
        variationsListDialog.setVariation(j);
        return variationsListDialog;
    }

    public void setListener(b bVar) {
        this.mListener = bVar;
    }

    public void setListingId(EtsyId etsyId) {
        this.mListingId = etsyId;
    }

    public void setVariation(long j) {
        this.mVariationId = j;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mVariation = ak.a(this.mListingId, this.mVariationId);
        if (this.mVariation == null) {
            getRequestQueue().a((g<Result>) new a<Result>());
            showLoadingView();
            return;
        }
        onItemsLoaded(this.mVariation);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mParentDialog = (IDialogFragment) getParentFragment();
        this.mParentDialog.setWindowMode(WindowMode.WRAP);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setDivider(getResources().getDrawable(R.drawable.list_divider_padded));
        this.mListView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.divider_height));
        this.mListView.setLayoutParams(new LayoutParams(-1, -2));
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.mListener.a(this.mVariation, (Option) this.mAdapter.getItem(i));
        this.mParentDialog.dismissAllowingStateLoss();
    }

    public void onItemsLoaded(Variation variation) {
        this.mVariation = variation;
        if (this.mVariation == null || this.mVariation.getOptions() == null) {
            showNoneLoadedAndDismiss();
            return;
        }
        makeAdapter(this.mVariation.getOptions());
        showListView();
    }

    private void makeAdapter(List<Option> list) {
        this.mAdapter = new VariationAdapter(this.mActivity, list);
        this.mListView.setAdapter(this.mAdapter);
    }

    private void showNoneLoadedAndDismiss() {
        showErrorAndDismiss(R.string.variations_none_found);
    }

    public void onLoadError() {
        showErrorAndDismiss(R.string.whoops_somethings_wrong);
    }

    private void showErrorAndDismiss(int i) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), getString(i), 0).show();
        }
        if (this.mParentDialog != null && !this.mParentDialog.isDetached()) {
            this.mParentDialog.dismissAllowingStateLoss();
        }
    }
}
