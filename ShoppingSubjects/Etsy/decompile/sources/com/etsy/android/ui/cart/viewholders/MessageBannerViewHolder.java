package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.MessageBanner;
import com.etsy.android.ui.cart.a.a;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: MessageBannerViewHolder.kt */
public final class MessageBannerViewHolder extends BaseViewHolder<CartGroupItem> {
    /* access modifiers changed from: private */
    public final a clickHandler;
    private final Button dismissButton;
    private final c imageBatch;
    private final ImageView imageView;
    private final TextView messageView;
    private final TextView titleView;

    public MessageBannerViewHolder(ViewGroup viewGroup, c cVar, a aVar) {
        p.b(viewGroup, ResponseConstants.PARENT);
        p.b(cVar, "imageBatch");
        p.b(aVar, "clickHandler");
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_message_banner, viewGroup, false));
        this.imageBatch = cVar;
        this.clickHandler = aVar;
        View findViewById = findViewById(R.id.txt_title);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.titleView = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.txt_message);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.messageView = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.btn_dismiss);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
        }
        this.dismissButton = (Button) findViewById3;
        View findViewById4 = findViewById(R.id.image);
        if (findViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.imageView = (ImageView) findViewById4;
    }

    public void bind(CartGroupItem cartGroupItem) {
        p.b(cartGroupItem, "item");
        MessageBanner messageBanner = (MessageBanner) cartGroupItem.getData();
        if (messageBanner != null) {
            this.titleView.setText(messageBanner.getTitle());
            this.messageView.setText(messageBanner.getMessage());
            this.imageBatch.a(messageBanner.getImageUrl(), this.imageView);
            this.dismissButton.setText(messageBanner.getButtonText());
            Button button = this.dismissButton;
            String buttonText = messageBanner.getButtonText();
            p.a((Object) buttonText, "buttonText");
            int i = 0;
            if (buttonText.length() == 0) {
                i = 8;
            }
            button.setVisibility(i);
            this.dismissButton.setOnClickListener(new MessageBannerViewHolder$bind$$inlined$apply$lambda$1(this, cartGroupItem));
        }
    }
}
