package com.etsy.android.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.c.e;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.d.a.C0071a;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.models.Payment;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.models.ReceiptShipment;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.Transaction;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.qualtrics.QualtricsController;
import com.etsy.android.lib.qualtrics.b;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.ReceiptEndpoint;
import com.etsy.android.lib.requests.ReceiptId;
import com.etsy.android.lib.requests.ReceiptsRequest;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.EtsyCommonListFragment;
import com.etsy.android.ui.util.f;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ReceiptFragment extends EtsyCommonListFragment implements com.etsy.android.lib.core.b.a, C0071a, b, com.etsy.android.ui.user.TransactionAdapter.a {
    public static final String FRAGMENT_TAG = "receipt_fragment";
    l logcat;
    private TransactionAdapter mAdapter;
    private View mFooterView;
    private f mHeaderUtil;
    private View mHeaderView;
    private boolean mIsSellerOrder = false;
    /* access modifiers changed from: private */
    public Receipt mReceipt;
    /* access modifiers changed from: private */
    public EtsyId mReceiptId;
    private TextView mReceiptStatus;
    private TextView mReceiptStatusTracking;
    private com.etsy.android.lib.d.a mRefundHelper;
    private com.etsy.android.ui.view.viewholders.a mRefundHolder;
    private com.etsy.android.ui.view.viewholders.b mTotalsHolder;
    private View mView;
    @Nullable
    private Disposable receiptIdDisposable;
    e retrofit;
    com.etsy.android.lib.f.a schedulers;
    @NonNull
    private EtsyId transactionId = new EtsyId();

    private class a extends i<Receipt> {
        boolean a;

        a(boolean z) {
            this.a = z;
        }

        /* access modifiers changed from: protected */
        public void b_() {
            ReceiptFragment.this.showLoadingView();
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<Receipt> a() {
            ReceiptsRequest receipt = ReceiptsRequest.getReceipt(ReceiptFragment.this.mReceiptId);
            ArrayMap arrayMap = new ArrayMap();
            String str = ReceiptFragment.this.getConfigMap().c(com.etsy.android.lib.config.b.a) ? ",variations" : "";
            StringBuilder sb = new StringBuilder();
            sb.append("Transactions(is_feedback_mutable,feedback_open_date,transaction_id,title,listing_id,quantity,price,shipping_cost,currency_code,is_gift_card,gift_card_info");
            sb.append(str);
            sb.append(")/MainImage(url_75x75,url_170x135),Transactions(transaction_id)/GiftCardDesign(url_150x119),Transactions(transaction_id)/UserReview/AppreciationPhoto(url_fullxfull,is_seller_approved,status),Buyer(");
            sb.append("user_id,login_name");
            sb.append(")/");
            sb.append("Profile(image_url_75x75,city,first_name,last_name,login_name)/Country");
            sb.append(",");
            sb.append(Includes.COUPON);
            sb.append(",");
            sb.append(Receipt.ADDRESS_INCLUDES);
            sb.append(",Location(map_android,location_name)");
            String sb2 = sb.toString();
            if (this.a) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb2);
                sb3.append(",Seller(user_id,login_name)/Profile(image_url_75x75,city,first_name,last_name,login_name)/Country,Seller(user_id)/Shops(shop_id,shop_name,creation_tsz,title,listing_active_count,icon_url_fullxfull)");
                sb2 = sb3.toString();
            }
            arrayMap.put("includes", sb2);
            receipt.addParams(arrayMap);
            return receipt;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<Receipt> kVar) {
            if (kVar != null && kVar.a() && kVar.i()) {
                ReceiptFragment.this.mReceipt = (Receipt) kVar.g().get(0);
                ReceiptFragment.this.populateViews(ReceiptFragment.this.mReceipt);
                ReceiptFragment.this.showListView();
            } else if (!this.a || kVar == null || !af.a(kVar.c()) || !kVar.c().contains("is not a valid user_id")) {
                ReceiptFragment.this.showErrorView();
            } else {
                g().a((Object) ReceiptFragment.this, (g<Result>) new a<Result>(false));
            }
        }
    }

    @NonNull
    public String getTrackingName() {
        return "view_receipt";
    }

    public ReceiptFragment() {
        super(R.layout.fragment_receipt);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mReceiptId = (EtsyId) bundle.getSerializable(ResponseConstants.RECEIPT_ID);
            this.mReceipt = (Receipt) bundle.getSerializable("receipt");
            this.mIsSellerOrder = bundle.getBoolean("is_seller_order");
        } else if (getArguments() != null && getArguments().containsKey(ResponseConstants.RECEIPT_ID)) {
            this.mReceiptId = (EtsyId) getArguments().getSerializable(ResponseConstants.RECEIPT_ID);
        } else if (getArguments() != null && getArguments().containsKey("receipt_transaction_id")) {
            EtsyId etsyId = (EtsyId) getArguments().getSerializable("receipt_transaction_id");
            if (etsyId != null) {
                this.transactionId = etsyId;
            }
        }
        if (this.mReceiptId == null) {
            this.mReceiptId = new EtsyId();
        }
        this.mHeaderUtil = new f(getResources(), "view_receipt", true);
        this.mRefundHelper = new com.etsy.android.lib.d.a(this, this.mReceiptId, v.a().l(), false);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mHeaderUtil.a(this.mView.findViewById(R.id.panel_shop_header), this.mView.findViewById(R.id.shop_header_background));
        createListLayout();
        this.mReceiptStatus = (TextView) this.mView.findViewById(R.id.text_order_details);
        this.mReceiptStatusTracking = (TextView) this.mView.findViewById(R.id.text_order_details_tracking);
        this.mTotalsHolder = new com.etsy.android.ui.view.viewholders.b(this.mView.findViewById(R.id.totals_layout), getAnalyticsContext());
        this.mRefundHolder = new com.etsy.android.ui.view.viewholders.a(this.mView);
        return this.mView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRefundHelper.a(true);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mHeaderUtil.a((Activity) this.mActivity, getImageBatch(), getAnalyticsContext());
        if (this.mAdapter == null) {
            this.mAdapter = new TransactionAdapter(getActivity(), getImageBatch());
        }
        this.mAdapter.setTransactionClickListener(this);
        if (this.mReceipt == null) {
            getReceipt();
        } else {
            populateViews(this.mReceipt);
        }
        this.mListView.setAdapter(this.mAdapter);
    }

    public void onStop() {
        super.onStop();
        if (this.receiptIdDisposable != null) {
            this.receiptIdDisposable.dispose();
        }
    }

    private void createListLayout() {
        this.mHeaderView = getActivity().getLayoutInflater().inflate(R.layout.receipt_header, null);
        this.mFooterView = getActivity().getLayoutInflater().inflate(R.layout.receipt_footer, null);
        this.mListView.addHeaderView(this.mHeaderView);
        this.mListView.addFooterView(this.mFooterView);
        this.mListView.setDivider(getResources().getDrawable(R.drawable.list_divider_padded_v2));
        this.mListView.setHeaderDividersEnabled(false);
    }

    public void onFragmentResume() {
        super.onFragmentResume();
        this.mActivity.invalidateOptionsMenu();
        setTitle();
    }

    public void onResume() {
        super.onResume();
        this.mActivity.invalidateOptionsMenu();
        setTitle();
        QualtricsController.a((b) this);
    }

    public void onPause() {
        super.onPause();
        QualtricsController.b((b) this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mRefundHelper.c();
    }

    public void onCreateOptionsMenuWithIcons(Menu menu, MenuInflater menuInflater) {
        menu.removeItem(R.id.menu_share);
    }

    private void setTitle() {
        String string = getActivity().getResources().getString(R.string.order);
        if (this.mReceiptId.hasId()) {
            BaseActivity baseActivity = this.mActivity;
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(" #");
            sb.append(this.mReceiptId.getId());
            baseActivity.setTitle(sb.toString());
            return;
        }
        this.mActivity.setTitle(string);
    }

    /* access modifiers changed from: protected */
    public void onRetryClicked() {
        getReceipt();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putSerializable(ResponseConstants.RECEIPT_ID, this.mReceiptId);
        bundle.putSerializable("receipt", this.mReceipt);
        bundle.putBoolean("is_seller_order", this.mIsSellerOrder);
    }

    public void onTransactionClick(Transaction transaction, User user) {
        ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((FragmentActivity) this.mActivity).a().a(401, (Fragment) this).a((j) this)).f(transaction.getTransactionId());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 401 && i2 == 411 && this.mAdapter.getCount() > 0) {
            Transaction transaction = (Transaction) intent.getSerializableExtra("transaction");
            if (transaction != null) {
                List transactions = this.mReceipt.getTransactions();
                Iterator it = transactions.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Transaction transaction2 = (Transaction) it.next();
                    if (transaction2.getTransactionId().equals(transaction.getTransactionId())) {
                        transactions.remove(transaction2);
                        transactions.add(transaction);
                        break;
                    }
                }
                this.mAdapter.clear();
                this.mAdapter.addAll((Collection<? extends T>) transactions);
                this.mAdapter.notifyDataSetChanged();
                if (transaction.getReview().hasAppreciationPhoto()) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("transaction", transaction);
                    bundle.putSerializable(ResponseConstants.TRANSACTION_ID, transaction.getTransactionId());
                    if (this.mReceipt.getSeller() != null) {
                        if (this.mReceipt.getSeller().getMainShop() != null) {
                            bundle.putString(ResponseConstants.SHOP_NAME, this.mReceipt.getSeller().getMainShop().getShopName());
                        }
                        if (this.mReceipt.getSeller().getProfile() != null) {
                            bundle.putSerializable("seller_avatar_url", this.mReceipt.getSeller().getProfile().getImageUrl75x75());
                        }
                    }
                    com.etsy.android.ui.nav.e.a(getActivity()).a().f(bundle);
                }
            }
        }
    }

    private void getReceipt() {
        if (this.mReceiptId.hasId() || !this.transactionId.hasId()) {
            getRequestQueue().a((Object) this, (g<Result>) new a<Result>(true));
        } else {
            this.receiptIdDisposable = ((ReceiptEndpoint) this.retrofit.a().a(ReceiptEndpoint.class)).getReceiptIdFromTransactionId(this.transactionId).b(this.schedulers.a()).a(this.schedulers.b()).a((Consumer<? super T>) new d<Object>(this), (Consumer<? super Throwable>) new e<Object>(this));
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$getReceipt$0$ReceiptFragment(ReceiptId receiptId) throws Exception {
        this.mReceiptId = new EtsyId(receiptId.getId());
        getRequestQueue().a((Object) this, (g<Result>) new a<Result>(true));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$getReceipt$1$ReceiptFragment(Throwable th) throws Exception {
        this.logcat.b("Failed to fetch receipt id from transaction id", th);
    }

    public void updateRefundStatus(Payment payment) {
        if (payment.isFullRefund() && this.mReceiptStatus != null) {
            this.mReceiptStatus.setText(R.string.refunded);
        } else if (payment.hasRefund() && this.mReceiptStatus != null) {
            this.mReceiptStatus.setText(R.string.partially_refunded);
        }
        this.mRefundHolder.a(payment);
    }

    /* access modifiers changed from: private */
    public void populateViews(Receipt receipt) {
        this.mRefundHelper.a();
        this.mIsSellerOrder = isSeller(receipt.getSeller());
        this.mAdapter.setIsSellOrder(this.mIsSellerOrder);
        this.mAdapter.setUser(receipt.getSeller());
        populateOtherUserView(receipt);
        populateShippingHeader(receipt, this.mHeaderView);
        populateReceiptDetails(receipt);
        if (this.mReceipt.isGiftCardReceipt()) {
            this.mHeaderUtil.a();
        }
        this.mAdapter.clear();
        if (receipt.getTransactions().size() > 0) {
            this.mAdapter.addAll((Collection<? extends T>) receipt.getTransactions());
        }
        this.mAdapter.notifyDataSetChanged();
    }

    private boolean isSeller(User user) {
        boolean z = false;
        if (user == null) {
            return false;
        }
        EtsyId m = v.a().m();
        if (m.hasId() && m.equals(user.getUserId())) {
            z = true;
        }
        return z;
    }

    private void populateOtherUserView(Receipt receipt) {
        String string = getString(R.string.re_subject_from_receipt, Long.valueOf(this.mReceiptId.getIdAsLong()), af.a(receipt.getCreationDate()));
        String string2 = getString(R.string.receipt_invoice_link, Long.valueOf(this.mReceiptId.getIdAsLong()));
        if (this.mIsSellerOrder) {
            this.mHeaderUtil.a(receipt.getBuyer(), string, string2);
        } else if (receipt.getSeller() != null && receipt.getSeller().getMainShop() != null) {
            this.mHeaderUtil.a(receipt.getSeller(), receipt.getSeller().getMainShop(), string, string2);
        }
    }

    private ReceiptShipment getDisplayShipment(Receipt receipt) {
        ReceiptShipment receiptShipment = null;
        for (ReceiptShipment receiptShipment2 : receipt.getShipments()) {
            if (receiptShipment == null || receiptShipment2.getStatus().compareTo(receiptShipment.getStatus()) == 1) {
                receiptShipment = receiptShipment2;
            }
        }
        return receiptShipment;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void populateShippingHeader(com.etsy.android.lib.models.Receipt r10, android.view.View r11) {
        /*
            r9 = this;
            r0 = 2131362967(0x7f0a0497, float:1.834573E38)
            android.view.View r0 = r11.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = r10.getShippingNote()
            boolean r1 = com.etsy.android.lib.util.af.a(r1)
            r2 = 8
            if (r1 == 0) goto L_0x0022
            java.lang.String r1 = r10.getShippingNote()
            r0.setText(r1)
            com.etsy.android.uikit.BaseActivity r1 = r9.mActivity
            com.etsy.android.uikit.util.EtsyLinkify.a(r1, r0)
            goto L_0x0025
        L_0x0022:
            r0.setVisibility(r2)
        L_0x0025:
            android.support.v4.app.FragmentActivity r1 = r9.getActivity()
            boolean r1 = com.etsy.android.lib.util.l.d(r1)
            if (r1 == 0) goto L_0x003c
            int r0 = r0.getVisibility()
            if (r0 != r2) goto L_0x003c
            android.widget.ListView r10 = r9.mListView
            r10.removeHeaderView(r11)
            goto L_0x012e
        L_0x003c:
            android.support.v4.app.FragmentActivity r0 = r9.getActivity()
            boolean r0 = com.etsy.android.lib.util.l.d(r0)
            if (r0 == 0) goto L_0x0052
            r10 = 2131362689(0x7f0a0381, float:1.8345166E38)
            android.view.View r10 = r11.findViewById(r10)
            r10.setVisibility(r2)
            goto L_0x012e
        L_0x0052:
            com.etsy.android.lib.d.a r0 = r9.mRefundHelper
            boolean r0 = r0.b()
            r1 = -1
            r3 = 0
            if (r0 == 0) goto L_0x0063
            r0 = 2131756876(0x7f10074c, float:1.9144672E38)
        L_0x005f:
            r4 = r3
            r5 = r4
            goto L_0x00ce
        L_0x0063:
            boolean r0 = r10.isInPerson()
            if (r0 == 0) goto L_0x006d
            r0 = 2131756121(0x7f100459, float:1.914314E38)
            goto L_0x005f
        L_0x006d:
            boolean r0 = r10.wasShipped()
            if (r0 == 0) goto L_0x00c0
            com.etsy.android.lib.models.ReceiptShipment r0 = r9.getDisplayShipment(r10)
            if (r0 == 0) goto L_0x00ad
            java.lang.String r3 = r0.getMajorTrackingState()
            boolean r4 = com.etsy.android.lib.util.af.b(r3)
            if (r4 != 0) goto L_0x008c
            com.etsy.android.lib.models.ReceiptShipment$ShippingState r4 = r0.getStatus()
            int r4 = r4.getStringResource()
            goto L_0x008d
        L_0x008c:
            r4 = r1
        L_0x008d:
            java.lang.String r5 = r0.getTrackingUrl()
            java.util.Date r6 = r0.getStatusDate()
            if (r6 == 0) goto L_0x00a5
            com.etsy.android.lib.models.ReceiptShipment$ShippingState r6 = r0.getStatus()
            com.etsy.android.lib.models.ReceiptShipment$ShippingState r7 = com.etsy.android.lib.models.ReceiptShipment.ShippingState.IN_TRANSIT
            if (r6 != r7) goto L_0x00a0
            goto L_0x00a5
        L_0x00a0:
            java.util.Date r0 = r0.getStatusDate()
            goto L_0x00a9
        L_0x00a5:
            java.util.Date r0 = r0.getShippedDate()
        L_0x00a9:
            r8 = r4
            r4 = r0
            r0 = r8
            goto L_0x00ce
        L_0x00ad:
            int r0 = r10.daysUntilShipped()
            if (r0 <= 0) goto L_0x00b7
            r0 = 2131757185(0x7f100881, float:1.9145299E38)
            goto L_0x00ba
        L_0x00b7:
            r0 = 2131757137(0x7f100851, float:1.9145201E38)
        L_0x00ba:
            java.util.Date r4 = r10.getShippingNotificationDate()
            r5 = r3
            goto L_0x00ce
        L_0x00c0:
            boolean r0 = r10.wasPaid()
            if (r0 == 0) goto L_0x00ca
            r0 = 2131756677(0x7f100685, float:1.9144268E38)
            goto L_0x005f
        L_0x00ca:
            r0 = 2131757731(0x7f100aa3, float:1.9146406E38)
            goto L_0x005f
        L_0x00ce:
            android.widget.TextView r6 = r9.mReceiptStatus
            if (r6 == 0) goto L_0x012e
            if (r3 == 0) goto L_0x00df
            android.widget.TextView r0 = r9.mReceiptStatus
            r0.setText(r3)
            android.widget.TextView r0 = r9.mReceiptStatusTracking
            r0.setText(r3)
            goto L_0x00eb
        L_0x00df:
            if (r0 == r1) goto L_0x00eb
            android.widget.TextView r1 = r9.mReceiptStatus
            r1.setText(r0)
            android.widget.TextView r1 = r9.mReceiptStatusTracking
            r1.setText(r0)
        L_0x00eb:
            boolean r0 = com.etsy.android.lib.util.af.a(r5)
            android.widget.TextView r1 = r9.mReceiptStatusTracking
            r3 = 0
            if (r0 == 0) goto L_0x00f6
            r6 = r3
            goto L_0x00f7
        L_0x00f6:
            r6 = r2
        L_0x00f7:
            r1.setVisibility(r6)
            android.widget.TextView r1 = r9.mReceiptStatus
            if (r0 == 0) goto L_0x0100
            r6 = r2
            goto L_0x0101
        L_0x0100:
            r6 = r3
        L_0x0101:
            r1.setVisibility(r6)
            if (r0 == 0) goto L_0x0115
            android.widget.TextView r0 = r9.mReceiptStatusTracking
            com.etsy.android.ui.user.ReceiptFragment$1 r1 = new com.etsy.android.ui.user.ReceiptFragment$1
            r6 = 1
            com.etsy.android.lib.logger.i[] r6 = new com.etsy.android.lib.logger.i[r6]
            r6[r3] = r10
            r1.<init>(r6, r5)
            r0.setOnClickListener(r1)
        L_0x0115:
            r10 = 2131363161(0x7f0a0559, float:1.8346123E38)
            android.view.View r10 = r11.findViewById(r10)
            android.widget.TextView r10 = (android.widget.TextView) r10
            if (r4 == 0) goto L_0x012b
            java.lang.String r11 = com.etsy.android.lib.util.af.a(r4)
            r10.setText(r11)
            r10.setVisibility(r3)
            goto L_0x012e
        L_0x012b:
            r10.setVisibility(r2)
        L_0x012e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.user.ReceiptFragment.populateShippingHeader(com.etsy.android.lib.models.Receipt, android.view.View):void");
    }

    private void populateReceiptDetails(Receipt receipt) {
        View view = this.mFooterView;
        if (com.etsy.android.lib.util.l.d((Activity) getActivity())) {
            this.mView.findViewById(R.id.receipt_layout).setVisibility(0);
            view = this.mView;
        }
        populatePaymentStatus(receipt, view);
        populateShippingStatus(receipt, view);
        populateInPersonInfo(receipt, view);
        this.mTotalsHolder.a(getActivity(), receipt);
        populateMessageFromSeller(receipt, this.mFooterView);
        populateMessageFromBuyer(receipt, this.mFooterView);
        populateSellerInformation(receipt, this.mFooterView);
    }

    private void populatePaymentStatus(Receipt receipt, View view) {
        ((TextView) view.findViewById(R.id.payment_status)).setText(receipt.wasPaid() ? R.string.paid : R.string.unpaid);
        TextView textView = (TextView) view.findViewById(R.id.payment_details);
        String stringForPaymentMethod = receipt.getStringForPaymentMethod(getResources(), false);
        if (af.a(stringForPaymentMethod)) {
            textView.setText(stringForPaymentMethod);
        } else {
            textView.setVisibility(8);
        }
    }

    private View addShipmentSection(ViewGroup viewGroup) {
        return getActivity().getLayoutInflater().inflate(R.layout.receipt_shipping_status, viewGroup, false);
    }

    private void populateReceiptShipment(ReceiptShipment receiptShipment, View view) {
        String majorTrackingState = receiptShipment.getMajorTrackingState();
        if (!af.b(majorTrackingState)) {
            majorTrackingState = getActivity().getString(receiptShipment.getStatus().getStringResource());
        }
        ((TextView) view.findViewById(R.id.shipping_status)).setText(majorTrackingState);
        TextView textView = (TextView) view.findViewById(R.id.shipping_service);
        TextView textView2 = (TextView) view.findViewById(R.id.shipping_details);
        if (af.a(receiptShipment.getCarrierName())) {
            textView.setText(receiptShipment.getCarrierName());
        } else {
            textView.setText(R.string.shipping_carrier_unknown);
        }
        StringBuilder sb = new StringBuilder();
        View findViewById = view.findViewById(R.id.track_package);
        if (af.a(receiptShipment.getTrackingCode())) {
            sb.append(receiptShipment.getTrackingCode());
            sb.append("\n");
            final String trackingUrl = receiptShipment.getTrackingUrl();
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new TrackingOnClickListener(new com.etsy.android.lib.logger.i[]{receiptShipment, this.mReceipt}) {
                public void onViewClick(View view) {
                    com.etsy.android.ui.nav.e.a(ReceiptFragment.this.getActivity()).a().e(trackingUrl);
                }
            });
        } else {
            findViewById.setVisibility(8);
        }
        if (receiptShipment.getShippedDate() != null) {
            sb.append(getString(receiptShipment.getShippingString()));
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(af.a(receiptShipment.getShippedDate()));
        }
        if (af.a(sb.toString())) {
            textView2.setText(sb.toString());
        } else {
            textView2.setVisibility(8);
        }
    }

    private void populateShippingStatus(Receipt receipt, View view) {
        if (receipt.isGiftCardReceipt() || receipt.isInPerson()) {
            view.findViewById(R.id.shipping_status_layout).setVisibility(8);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.shipments);
        if (receipt.getShipments().size() > 0) {
            for (ReceiptShipment receiptShipment : receipt.getShipments()) {
                View addShipmentSection = addShipmentSection(viewGroup);
                populateReceiptShipment(receiptShipment, addShipmentSection);
                viewGroup.addView(addShipmentSection);
            }
        } else if (receipt.wasShipped()) {
            ReceiptShipment receiptShipment2 = new ReceiptShipment(receipt);
            View addShipmentSection2 = addShipmentSection(viewGroup);
            populateReceiptShipment(receiptShipment2, addShipmentSection2);
            viewGroup.addView(addShipmentSection2);
        }
        populateShippingAddress(receipt, view);
    }

    private void populateShippingAddress(Receipt receipt, View view) {
        SpannableStringBuilder formattedAddressWithLineBreaks = receipt.getFormattedAddressWithLineBreaks();
        if (af.a(receipt.getName())) {
            formattedAddressWithLineBreaks.insert(0, "\n");
            formattedAddressWithLineBreaks.insert(0, receipt.getName());
        }
        ((TextView) view.findViewById(R.id.shipping_address)).setText(formattedAddressWithLineBreaks);
    }

    private void populateInPersonInfo(Receipt receipt, View view) {
        if (receipt.isInPerson()) {
            TextView textView = (TextView) view.findViewById(R.id.purchase_location_title);
            if (receipt.getLocation() != null) {
                if (!TextUtils.isEmpty(receipt.getLocation().getLocationName())) {
                    textView.setText(receipt.getLocation().getLocationName());
                } else {
                    textView.setText(R.string.ipp_no_location_name);
                }
                getImageBatch().a(receipt.getLocation().getMapUrl(), (ImageView) view.findViewById(R.id.in_person_map));
                return;
            }
            textView.setText(R.string.ipp_no_location);
            view.findViewById(R.id.in_person_map).setVisibility(8);
            return;
        }
        view.findViewById(R.id.in_person_info_layout).setVisibility(8);
    }

    private void populateMessageFromSeller(Receipt receipt, View view) {
        if (receipt.isGiftCardReceipt() || !af.a(receipt.getMessageFromSeller())) {
            view.findViewById(R.id.seller_message_layout).setVisibility(8);
            return;
        }
        ((TextView) view.findViewById(R.id.seller_message_title)).setText(this.mIsSellerOrder ? R.string.your_message_to_buyer : R.string.message_from_seller_header);
        TextView textView = (TextView) view.findViewById(R.id.message_from_seller);
        textView.setText(receipt.getMessageFromSeller());
        EtsyLinkify.a((Context) this.mActivity, textView);
    }

    private void populateMessageFromBuyer(Receipt receipt, View view) {
        if (receipt.isGiftCardReceipt() || !af.a(receipt.getMessageFromBuyer())) {
            view.findViewById(R.id.buyer_message_layout).setVisibility(8);
            return;
        }
        ((TextView) view.findViewById(R.id.buyer_message_title)).setText(this.mIsSellerOrder ? R.string.message_from_buyer : R.string.message_to_seller_header);
        TextView textView = (TextView) view.findViewById(R.id.message_from_buyer);
        textView.setText(receipt.getMessageFromBuyer());
        EtsyLinkify.a((Context) this.mActivity, textView);
    }

    private void populateSellerInformation(Receipt receipt, View view) {
        if (!af.a(receipt.getSellerEmail()) || this.mIsSellerOrder) {
            view.findViewById(R.id.seller_info_layout).setVisibility(8);
            return;
        }
        TextView textView = (TextView) view.findViewById(R.id.seller_information);
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.sellers_email));
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(receipt.getSellerEmail());
        textView.setText(sb.toString());
        EtsyLinkify.a((Context) this.mActivity, textView);
    }

    public ViewGroup getContainerRootViewGroupForQualtricsSurvey() {
        return (ViewGroup) this.mView;
    }

    public Context getContextForQualtricsPrompt() {
        return getContext();
    }
}
