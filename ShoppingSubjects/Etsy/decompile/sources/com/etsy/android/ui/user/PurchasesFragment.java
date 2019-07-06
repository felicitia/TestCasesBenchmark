package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.UsersRequest;
import com.etsy.android.lib.util.m;
import com.etsy.android.ui.EtsyEndlessListFragment;
import com.etsy.android.ui.adapters.PurchaseReceiptAdapter;
import com.etsy.android.ui.nav.e;

public class PurchasesFragment extends EtsyEndlessListFragment {
    private static final int BATCH_SIZE = 4;
    private com.etsy.android.ui.adapters.PurchaseReceiptAdapter.a mClickListener = new com.etsy.android.ui.adapters.PurchaseReceiptAdapter.a() {
        public void a(Receipt receipt) {
            if (receipt.getReceiptId().hasId()) {
                e.a((FragmentActivity) PurchasesFragment.this.mActivity).a().d(receipt.getReceiptId());
            }
        }

        public void a(String str) {
            e.a((FragmentActivity) PurchasesFragment.this.mActivity).a().f(str);
        }
    };
    /* access modifiers changed from: private */
    public int mOffset = 0;
    /* access modifiers changed from: private */
    public PurchaseReceiptAdapter mResultAdapter;

    private class a extends i<Receipt> {
        private a() {
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<Receipt> a() {
            UsersRequest findAllUserBuyerReceipts = UsersRequest.findAllUserBuyerReceipts();
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put("limit", "4");
            StringBuilder sb = new StringBuilder();
            sb.append(PurchasesFragment.this.mOffset);
            sb.append("");
            arrayMap.put("offset", sb.toString());
            arrayMap.put("fields", "receipt_id,grandtotal,currency_code,was_paid,was_shipped,creation_tsz,is_in_person,shipments,shipped_tsz,multi_shop_note");
            arrayMap.put("includes", "Transactions(transaction_id,quantity,is_gift_card,title,price,currency_code,is_feedback_mutable)/MainImage(url_170x135),Transactions(transaction_id)/GiftCardDesign(url_150x119),Transactions(transaction_id)/UserReview(rating),Seller(login_name)/Profile(image_url_75x75,first_name,last_name,login_name),Seller(login_name)/Shops(shop_name,icon_url_fullxfull)");
            findAllUserBuyerReceipts.addParams(arrayMap);
            return findAllUserBuyerReceipts;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<Receipt> kVar) {
            PurchasesFragment.this.mOffset = PurchasesFragment.this.mOffset + m.a(kVar, PurchasesFragment.this.mResultAdapter, PurchasesFragment.this);
        }
    }

    @NonNull
    public String getTrackingName() {
        return "your_purchases";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void onResume() {
        super.onResume();
        setTitle();
    }

    private void setTitle() {
        this.mActivity.setTitle(getResources().getString(R.string.your_purchases));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setEmptyText((int) R.string.empty_purchases);
        if (this.mResultAdapter == null) {
            this.mResultAdapter = new PurchaseReceiptAdapter(getActivity(), getImageBatch(), this.mClickListener);
            showLoadingView();
            getReceipts();
        } else if (this.mResultAdapter.getCount() == 0) {
            showEmptyView();
        } else {
            showListView();
        }
        this.mListView.setDivider(null);
        setListAdapter(this.mResultAdapter);
    }

    /* access modifiers changed from: protected */
    public void onRetryClicked() {
        showLoadingView();
        getReceipts();
    }

    public void onLoadMoreItems() {
        getReceipts();
    }

    public void getReceipts() {
        getRequestQueue().a((Object) this, (g<Result>) new a<Result>());
    }
}
