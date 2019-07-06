package com.etsy.android.ui.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.models.ReceiptShipment;
import com.etsy.android.lib.models.Review;
import com.etsy.android.lib.models.Transaction;
import com.etsy.android.lib.util.af;
import com.etsy.android.uikit.adapter.BaseModelArrayAdapter;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.RatingIconView;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PurchaseReceiptAdapter extends BaseModelArrayAdapter<Receipt> {
    private final int AVATAR_CORNER_DIMEN;
    private final int AVATAR_DIMEN;
    private final int ITEM_IMAGE_DIMEN_HEIGHT;
    private final int ITEM_IMAGE_DIMEN_WIDTH;
    /* access modifiers changed from: private */
    public a mClickListener;
    private SimpleDateFormat mShipDateFormat = new SimpleDateFormat("MMM dd", Locale.getDefault());
    private boolean mShowTransparentPricingMessage = com.etsy.android.lib.config.a.a().d().c(com.etsy.android.lib.config.b.bL);

    public static abstract class a {
        public abstract void a(Receipt receipt);

        public abstract void a(String str);
    }

    private static class b {
        ImageView a;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        LinearLayout g;

        b(View view) {
            this.a = (ImageView) view.findViewById(R.id.avatar_image);
            this.b = (TextView) view.findViewById(R.id.name);
            this.c = (TextView) view.findViewById(R.id.date);
            this.d = (TextView) view.findViewById(R.id.status);
            this.e = (TextView) view.findViewById(R.id.multi_shop_note);
            this.f = (TextView) view.findViewById(R.id.price);
            this.g = (LinearLayout) view.findViewById(R.id.transaction_holder);
        }
    }

    private static class c {
        ImageView a;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        RatingIconView g;

        c(View view) {
            this.a = (ImageView) view.findViewById(R.id.item_image);
            this.b = (TextView) view.findViewById(R.id.listing_title);
            this.c = (TextView) view.findViewById(R.id.price);
            this.d = (TextView) view.findViewById(R.id.quantity);
            this.e = (TextView) view.findViewById(R.id.review_callout);
            this.f = (TextView) view.findViewById(R.id.text_transparent_pricing);
            this.g = (RatingIconView) view.findViewById(R.id.rating);
        }
    }

    public PurchaseReceiptAdapter(FragmentActivity fragmentActivity, com.etsy.android.lib.core.img.c cVar, a aVar) {
        super(fragmentActivity, R.layout.list_item_receipt, cVar);
        this.AVATAR_DIMEN = fragmentActivity.getResources().getDimensionPixelOffset(R.dimen.card_avatar_small);
        this.AVATAR_CORNER_DIMEN = fragmentActivity.getResources().getDimensionPixelOffset(R.dimen.gen_avatar_corners_small);
        this.ITEM_IMAGE_DIMEN_WIDTH = fragmentActivity.getResources().getDimensionPixelOffset(R.dimen.receipt_image_width);
        this.ITEM_IMAGE_DIMEN_HEIGHT = fragmentActivity.getResources().getDimensionPixelOffset(R.dimen.receipt_image_height);
        this.mClickListener = aVar;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        String str = null;
        if (view == null) {
            view = getLayoutInflater().inflate(getLayoutId(), null);
            bVar = new b(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        final Receipt receipt = (Receipt) getItem(i);
        setReceiptDetails(bVar, receipt);
        setStatus(bVar, receipt);
        if (receipt.hasTransparentPriceMessage()) {
            str = receipt.getTransparentPriceMessage();
        }
        setTransactions(bVar, receipt.getTransactions(), str);
        int i2 = 0;
        if (this.mClickListener != null) {
            view.setOnClickListener(new TrackingOnClickListener(new i[]{receipt}) {
                public void onViewClick(View view) {
                    PurchaseReceiptAdapter.this.mClickListener.a(receipt);
                }
            });
        }
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        if (i == getCount() - 1) {
            i2 = view.getPaddingTop();
        }
        view.setPadding(paddingLeft, paddingTop, paddingRight, i2);
        return view;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setReceiptDetails(com.etsy.android.ui.adapters.PurchaseReceiptAdapter.b r9, com.etsy.android.lib.models.Receipt r10) {
        /*
            r8 = this;
            com.etsy.android.lib.models.User r0 = r10.getSeller()
            if (r0 == 0) goto L_0x0046
            com.etsy.android.lib.models.Shop r1 = r0.getMainShop()
            if (r1 == 0) goto L_0x0037
            com.etsy.android.lib.models.Shop r1 = r0.getMainShop()
            android.util.Pair<java.lang.Integer, java.lang.String> r2 = com.etsy.android.lib.models.apiv3.ShopIcon.IMG_SIZE_75
            java.lang.Object r2 = r2.first
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.lang.String r1 = r1.getIconUrl(r2)
            boolean r1 = com.etsy.android.lib.util.af.a(r1)
            if (r1 == 0) goto L_0x0037
            com.etsy.android.lib.models.Shop r1 = r0.getMainShop()
            android.util.Pair<java.lang.Integer, java.lang.String> r2 = com.etsy.android.lib.models.apiv3.ShopIcon.IMG_SIZE_75
            java.lang.Object r2 = r2.first
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.lang.String r1 = r1.getIconUrl(r2)
            goto L_0x0047
        L_0x0037:
            com.etsy.android.lib.models.UserProfile r1 = r0.getProfile()
            if (r1 == 0) goto L_0x0046
            com.etsy.android.lib.models.UserProfile r1 = r0.getProfile()
            java.lang.String r1 = r1.getImageUrl75x75()
            goto L_0x0047
        L_0x0046:
            r1 = 0
        L_0x0047:
            r3 = r1
            android.widget.ImageView r1 = r9.a
            r2 = 2131230820(0x7f080064, float:1.8077704E38)
            r1.setBackgroundResource(r2)
            com.etsy.android.lib.core.img.c r2 = r8.getImageBatch()
            android.widget.ImageView r4 = r9.a
            int r5 = r8.AVATAR_CORNER_DIMEN
            int r6 = r8.AVATAR_DIMEN
            int r7 = r8.AVATAR_DIMEN
            r2.b(r3, r4, r5, r6, r7)
            if (r0 == 0) goto L_0x0082
            com.etsy.android.lib.models.Shop r1 = r0.getMainShop()
            if (r1 == 0) goto L_0x0082
            com.etsy.android.lib.models.Shop r1 = r0.getMainShop()
            java.lang.String r1 = r1.getShopName()
            boolean r1 = com.etsy.android.lib.util.af.b(r1)
            if (r1 == 0) goto L_0x0082
            android.widget.TextView r1 = r9.b
            com.etsy.android.lib.models.Shop r0 = r0.getMainShop()
            java.lang.String r0 = r0.getShopName()
            r1.setText(r0)
        L_0x0082:
            android.widget.TextView r0 = r9.f
            java.lang.String r1 = r10.getFormattedGrandTotal()
            r0.setText(r1)
            android.widget.TextView r0 = r9.c
            java.util.Date r1 = r10.getCreationDate()
            java.lang.String r1 = com.etsy.android.lib.util.af.a(r1)
            r0.setText(r1)
            boolean r0 = r10.hasMultiShopNote()
            if (r0 == 0) goto L_0x00a8
            android.widget.TextView r9 = r9.e
            java.lang.String r10 = r10.getMultiShopNote()
            r9.setText(r10)
            goto L_0x00af
        L_0x00a8:
            android.widget.TextView r9 = r9.e
            java.lang.String r10 = ""
            r9.setText(r10)
        L_0x00af:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.adapters.PurchaseReceiptAdapter.setReceiptDetails(com.etsy.android.ui.adapters.PurchaseReceiptAdapter$b, com.etsy.android.lib.models.Receipt):void");
    }

    private void setStatus(b bVar, Receipt receipt) {
        if (receipt.isInPerson()) {
            bVar.d.setText(getContext().getString(R.string.ipp_purchased_in_person));
        } else if (receipt.wasShipped() && receipt.getShipDate() != null) {
            ReceiptShipment displayShipment = getDisplayShipment(receipt);
            if (displayShipment != null) {
                String majorTrackingState = displayShipment.getMajorTrackingState();
                if (!af.b(majorTrackingState)) {
                    majorTrackingState = getContext().getString(displayShipment.getStatus().getStringResource());
                }
                bVar.d.setText(majorTrackingState);
                return;
            }
            bVar.d.setText(getContext().getString(receipt.daysUntilShipped() > 0 ? R.string.ships_on_date : R.string.shipped, new Object[]{this.mShipDateFormat.format(receipt.getShipDate())}));
        } else if (receipt.wasPaid()) {
            bVar.d.setText(getContext().getString(R.string.paid));
        } else {
            bVar.d.setText(getContext().getString(R.string.unpaid));
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

    private void setTransactions(b bVar, List<Transaction> list, @Nullable String str) {
        int size = list.size();
        while (bVar.g.getChildCount() < size) {
            View inflate = getLayoutInflater().inflate(R.layout.receipt_transaction, null);
            inflate.setTag(new c(inflate));
            bVar.g.addView(inflate);
        }
        for (int i = 0; i < bVar.g.getChildCount(); i++) {
            View childAt = bVar.g.getChildAt(i);
            if (i < size) {
                c cVar = (c) childAt.getTag();
                Transaction transaction = (Transaction) list.get(i);
                childAt.setVisibility(0);
                fillTransaction(cVar, transaction, str);
            } else {
                childAt.setVisibility(8);
            }
        }
    }

    private void fillTransaction(c cVar, Transaction transaction, @Nullable String str) {
        cVar.b.setText(transaction.getTitle());
        TextView textView = cVar.c;
        StringBuilder sb = new StringBuilder();
        sb.append(getContext().getString(R.string.search_filter_price_title));
        sb.append(": ");
        sb.append(transaction.getFormattedPrice());
        textView.setText(sb.toString());
        TextView textView2 = cVar.d;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getContext().getString(R.string.quantity));
        sb2.append(": ");
        sb2.append(transaction.getQuantity());
        textView2.setText(sb2.toString());
        if (!this.mShowTransparentPricingMessage || TextUtils.isEmpty(str)) {
            cVar.f.setVisibility(8);
        } else {
            cVar.f.setText(Html.fromHtml(str));
            if (this.mClickListener != null) {
                linkifyTransparentPricingMessage(cVar.f);
            }
            cVar.f.setVisibility(0);
        }
        Review review = transaction.getReview();
        boolean z = review != null;
        cVar.e.setVisibility((!transaction.isFeedbackMutable() || z) ? 8 : 0);
        if (z) {
            cVar.g.setRating((float) review.getRating());
            cVar.g.setVisibility(0);
        } else {
            cVar.g.setVisibility(8);
        }
        String str2 = null;
        if (transaction.isGiftCard() && transaction.getGiftCardDesign() != null) {
            str2 = transaction.getGiftCardDesign().getUrl150x119();
        } else if (transaction.getMainImage() != null) {
            str2 = transaction.getMainImage().getImageUrlForPixelWidth(this.ITEM_IMAGE_DIMEN_WIDTH);
        }
        getImageBatch().a(str2, cVar.a, this.ITEM_IMAGE_DIMEN_WIDTH, this.ITEM_IMAGE_DIMEN_HEIGHT);
    }

    private void linkifyTransparentPricingMessage(TextView textView) {
        URLSpan[] urls = textView.getUrls();
        if (urls.length > 0) {
            final String url = urls[0].getURL();
            EtsyLinkify.a(textView, true, false, (OnClickListener) new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    PurchaseReceiptAdapter.this.mClickListener.a(url);
                }
            });
        }
    }
}
