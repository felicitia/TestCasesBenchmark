package com.etsy.android.ui.convos.convoredesign;

import android.content.Context;
import android.support.constraint.Group;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.e.a;
import com.etsy.android.extensions.j;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.ImageInfo;
import com.etsy.android.lib.models.datatypes.TrackedObject;
import com.etsy.android.ui.convos.convoredesign.af.b;
import com.etsy.android.ui.convos.convoredesign.af.e;
import com.etsy.android.ui.convos.convoredesign.aj.d;
import com.etsy.android.uikit.view.AttachmentThumbnailsView;
import kotlin.jvm.internal.p;

/* compiled from: OtherUserConvoThreadViewHolder.kt */
public final class OtherUserConvoThreadViewHolder extends ViewHolder {
    private final c imageBatch;
    /* access modifiers changed from: private */
    public final b imageClickListener;
    /* access modifiers changed from: private */
    public final e linkCardClickListener;

    public OtherUserConvoThreadViewHolder(View view, c cVar, b bVar, e eVar) {
        p.b(view, "view");
        p.b(cVar, "imageBatch");
        p.b(bVar, "imageClickListener");
        p.b(eVar, "linkCardClickListener");
        super(view);
        this.imageBatch = cVar;
        this.imageClickListener = bVar;
        this.linkCardClickListener = eVar;
    }

    public final void bind(o.c cVar) {
        p.b(cVar, "item");
        c cVar2 = this.imageBatch;
        String c = cVar.c();
        View view = this.itemView;
        p.a((Object) view, "itemView");
        ImageView imageView = (ImageView) view.findViewById(a.sender_avatar);
        View view2 = this.itemView;
        p.a((Object) view2, "itemView");
        Context context = view2.getContext();
        p.a((Object) context, "itemView.context");
        cVar2.b(c, imageView, context.getResources().getDimensionPixelSize(R.dimen.convo_smaller_avatar));
        View view3 = this.itemView;
        p.a((Object) view3, "itemView");
        ((AttachmentThumbnailsView) view3.findViewById(a.linear_convo_images)).setImageBatch(this.imageBatch);
        View view4 = this.itemView;
        p.a((Object) view4, "itemView");
        ((AttachmentThumbnailsView) view4.findViewById(a.linear_convo_images)).clear();
        boolean z = true;
        if (cVar.b().isEmpty()) {
            View view5 = this.itemView;
            p.a((Object) view5, "itemView");
            j.b((AttachmentThumbnailsView) view5.findViewById(a.linear_convo_images));
        } else {
            View view6 = this.itemView;
            p.a((Object) view6, "itemView");
            j.a((AttachmentThumbnailsView) view6.findViewById(a.linear_convo_images));
            int i = 0;
            for (ImageInfo imageInfo : cVar.b()) {
                int i2 = i + 1;
                View view7 = this.itemView;
                p.a((Object) view7, "itemView");
                AttachmentThumbnailsView attachmentThumbnailsView = (AttachmentThumbnailsView) view7.findViewById(a.linear_convo_images);
                String url75x75 = imageInfo.getUrl75x75();
                OtherUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1 otherUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1 = new OtherUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1(i, imageInfo, new i[]{new TrackedObject(AnalyticsLogAttribute.URL, imageInfo.getUrlFullxFull())}, this, cVar);
                attachmentThumbnailsView.addImage(url75x75, (OnClickListener) otherUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1);
                i = i2;
            }
        }
        View view8 = this.itemView;
        p.a((Object) view8, "itemView");
        j.b((Group) view8.findViewById(a.order_card_group));
        ak d = cVar.d();
        if (d != null) {
            View view9 = this.itemView;
            p.a((Object) view9, "itemView");
            j.a((Group) view9.findViewById(a.order_card_group));
            c cVar3 = this.imageBatch;
            String c2 = d.c();
            View view10 = this.itemView;
            p.a((Object) view10, "itemView");
            cVar3.a(c2, (ImageView) view10.findViewById(a.order_card_image));
            View view11 = this.itemView;
            p.a((Object) view11, "itemView");
            TextView textView = (TextView) view11.findViewById(a.order_card_title);
            p.a((Object) textView, "itemView.order_card_title");
            textView.setText(d.a());
            if (d.c().length() == 0) {
                View view12 = this.itemView;
                p.a((Object) view12, "itemView");
                ((ImageView) view12.findViewById(a.order_card_image)).setImageResource(R.drawable.sk_ic_help);
            }
            View view13 = this.itemView;
            p.a((Object) view13, "itemView");
            TextView textView2 = (TextView) view13.findViewById(a.order_card_subtitle);
            p.a((Object) textView2, "itemView.order_card_subtitle");
            textView2.setText(d.b());
            View view14 = this.itemView;
            p.a((Object) view14, "itemView");
            View findViewById = view14.findViewById(a.order_card_background);
            p.a((Object) findViewById, "itemView.order_card_background");
            j.a(findViewById, new OtherUserConvoThreadViewHolder$bind$$inlined$let$lambda$1(d, this));
        }
        if (cVar.a().a().length() <= 0) {
            z = false;
        }
        if (z) {
            View view15 = this.itemView;
            p.a((Object) view15, "itemView");
            j.a((TextView) view15.findViewById(a.sender_message));
            View view16 = this.itemView;
            p.a((Object) view16, "itemView");
            TextView textView3 = (TextView) view16.findViewById(a.sender_message);
            p.a((Object) textView3, "itemView.sender_message");
            textView3.setText(cVar.a().a());
        } else {
            View view17 = this.itemView;
            p.a((Object) view17, "itemView");
            j.b((TextView) view17.findViewById(a.sender_message));
        }
        updateGroupingLayout(cVar.a());
    }

    private final void updateGroupingLayout(aj ajVar) {
        View view = this.itemView;
        p.a((Object) view, "itemView");
        int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.sk_space_2);
        View view2 = this.itemView;
        p.a((Object) view2, "itemView");
        int dimensionPixelSize2 = view2.getResources().getDimensionPixelSize(R.dimen.convo_card_gutter_width);
        View view3 = this.itemView;
        p.a((Object) view3, "itemView");
        int dimensionPixelSize3 = view3.getResources().getDimensionPixelSize(R.dimen.convo_grouped_message_padding);
        if (ajVar instanceof d) {
            this.itemView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize3);
            View view4 = this.itemView;
            p.a((Object) view4, "itemView");
            view4.findViewById(a.chat_background).setBackgroundResource(R.drawable.bg_chat_message_other_user_top);
        } else if (ajVar instanceof aj.c) {
            this.itemView.setPadding(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize2, dimensionPixelSize3);
            View view5 = this.itemView;
            p.a((Object) view5, "itemView");
            view5.findViewById(a.chat_background).setBackgroundResource(R.drawable.bg_chat_message_other_user_middle);
        } else if (ajVar instanceof aj.a) {
            this.itemView.setPadding(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize2, dimensionPixelSize);
            View view6 = this.itemView;
            p.a((Object) view6, "itemView");
            view6.findViewById(a.chat_background).setBackgroundResource(R.drawable.bg_chat_message_other_user_bottom);
        } else if (ajVar instanceof aj.b) {
            this.itemView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize);
            View view7 = this.itemView;
            p.a((Object) view7, "itemView");
            view7.findViewById(a.chat_background).setBackgroundResource(R.drawable.bg_chat_message_other_user);
        }
    }
}
