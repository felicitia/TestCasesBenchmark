package com.contextlogic.wish.activity.cart.items;

import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;

public class CartItemsRowWarningView extends LinearLayout implements ImageRestorable {
    private NetworkImageView mIcon;
    private TextView mMessageText;

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.String, code=java.lang.CharSequence, for r8v0, types: [java.lang.CharSequence, java.lang.String] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CartItemsRowWarningView(android.content.Context r6, java.lang.String r7, java.lang.CharSequence r8, java.lang.String r9) {
        /*
            r5 = this;
            r5.<init>(r6)
            android.content.Context r6 = r5.getContext()
            java.lang.String r0 = "layout_inflater"
            java.lang.Object r6 = r6.getSystemService(r0)
            android.view.LayoutInflater r6 = (android.view.LayoutInflater) r6
            r0 = 2131427412(0x7f0b0054, float:1.847644E38)
            android.view.View r6 = r6.inflate(r0, r5)
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r1 = -2
            r0.<init>(r1, r1)
            r5.setLayoutParams(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x004c
            android.text.SpannableString r0 = new android.text.SpannableString
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r7)
            java.lang.String r7 = ": "
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r0.<init>(r7)
            android.text.style.StyleSpan r7 = new android.text.style.StyleSpan
            r7.<init>(r1)
            int r3 = r0.length()
            r4 = 33
            r0.setSpan(r7, r2, r3, r4)
            goto L_0x004d
        L_0x004c:
            r0 = 0
        L_0x004d:
            if (r0 == 0) goto L_0x005a
            r7 = 2
            java.lang.CharSequence[] r7 = new java.lang.CharSequence[r7]
            r7[r2] = r0
            r7[r1] = r8
            java.lang.CharSequence r8 = android.text.TextUtils.concat(r7)
        L_0x005a:
            r7 = 2131296610(0x7f090162, float:1.8211142E38)
            android.view.View r7 = r6.findViewById(r7)
            android.widget.TextView r7 = (android.widget.TextView) r7
            r5.mMessageText = r7
            android.widget.TextView r7 = r5.mMessageText
            r7.setText(r8)
            com.contextlogic.wish.api.datacenter.ExperimentDataCenter r7 = com.contextlogic.wish.api.datacenter.ExperimentDataCenter.getInstance()
            boolean r7 = r7.shouldSeeRedesignedCartNotices()
            if (r7 == 0) goto L_0x0084
            android.widget.TextView r7 = r5.mMessageText
            android.content.res.Resources r8 = r5.getResources()
            r0 = 2131034314(0x7f0500ca, float:1.7679142E38)
            int r8 = r8.getColor(r0)
            r7.setTextColor(r8)
        L_0x0084:
            r7 = 2131296609(0x7f090161, float:1.821114E38)
            android.view.View r6 = r6.findViewById(r7)
            com.contextlogic.wish.ui.image.NetworkImageView r6 = (com.contextlogic.wish.ui.image.NetworkImageView) r6
            r5.mIcon = r6
            com.contextlogic.wish.api.datacenter.ExperimentDataCenter r6 = com.contextlogic.wish.api.datacenter.ExperimentDataCenter.getInstance()
            boolean r6 = r6.shouldSeeRedesignedCartNotices()
            if (r6 == 0) goto L_0x00c3
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams
            android.content.res.Resources r7 = r5.getResources()
            r8 = 2131099879(0x7f0600e7, float:1.7812124E38)
            int r7 = r7.getDimensionPixelSize(r8)
            android.content.res.Resources r0 = r5.getResources()
            int r8 = r0.getDimensionPixelSize(r8)
            r6.<init>(r7, r8)
            android.content.res.Resources r7 = r5.getResources()
            r8 = 2131100151(0x7f0601f7, float:1.7812675E38)
            int r7 = r7.getDimensionPixelSize(r8)
            r6.rightMargin = r7
            com.contextlogic.wish.ui.image.NetworkImageView r7 = r5.mIcon
            r7.setLayoutParams(r6)
        L_0x00c3:
            boolean r6 = android.text.TextUtils.isEmpty(r9)
            if (r6 != 0) goto L_0x00dc
            java.lang.String r6 = "null"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00dc
            com.contextlogic.wish.ui.image.NetworkImageView r6 = r5.mIcon
            r6.setVisibility(r2)
            com.contextlogic.wish.ui.image.NetworkImageView r6 = r5.mIcon
            r6.setImageUrl(r9)
            goto L_0x00fb
        L_0x00dc:
            com.contextlogic.wish.api.datacenter.ExperimentDataCenter r6 = com.contextlogic.wish.api.datacenter.ExperimentDataCenter.getInstance()
            boolean r6 = r6.shouldSeeRedesignedCartNotices()
            if (r6 == 0) goto L_0x00f4
            com.contextlogic.wish.ui.image.NetworkImageView r6 = r5.mIcon
            r6.setVisibility(r2)
            com.contextlogic.wish.ui.image.NetworkImageView r6 = r5.mIcon
            r7 = 2131165843(0x7f070293, float:1.7945915E38)
            r6.setImageResource(r7)
            goto L_0x00fb
        L_0x00f4:
            com.contextlogic.wish.ui.image.NetworkImageView r6 = r5.mIcon
            r7 = 8
            r6.setVisibility(r7)
        L_0x00fb:
            r5.setOrientation(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.cart.items.CartItemsRowWarningView.<init>(android.content.Context, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public void releaseImages() {
        if (this.mIcon != null) {
            this.mIcon.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mIcon != null) {
            this.mIcon.restoreImages();
        }
    }
}
