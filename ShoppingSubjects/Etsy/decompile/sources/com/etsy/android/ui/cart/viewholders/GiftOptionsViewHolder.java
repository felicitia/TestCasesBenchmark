package com.etsy.android.ui.cart.viewholders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.GiftOptions;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.ui.cart.a.e;
import com.etsy.android.uikit.text.a.C0111a;
import com.jakewharton.rxbinding2.widget.h;
import com.jakewharton.rxbinding2.widget.s;
import io.reactivex.a.b.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.q;
import java.util.concurrent.TimeUnit;

public class GiftOptionsViewHolder extends BaseCartGroupItemViewHolder {
    private static final int GIFT_MESSAGE_MAX_LENGTH = 150;
    private static final int TEXT_CHANGE_TIMEOUT = 500;
    private e mClickHandler;
    private EditText mGiftMessage;
    private CheckBox mGiftMessageCheckbox = ((CheckBox) findViewById(R.id.gift_options_gift_message_checkbox));
    private TextView mGiftMessageError = ((TextView) findViewById(R.id.gift_options_gift_message_error));
    private CheckBox mGiftWrapCheckbox = ((CheckBox) findViewById(R.id.gift_options_gift_wrap_checkbox));
    private View mGiftWrapContainer = findViewById(R.id.gift_options_gift_wrap_container);
    private View mGiftWrapDetails = findViewById(R.id.gift_options_gift_wrap_details);
    private CheckBox mIsGiftCheckbox = ((CheckBox) findViewById(R.id.gift_options_gift_checkbox));
    @Nullable
    private Disposable mTextChangeDisposable;
    private final q<s> mTextChangeObservable;

    public GiftOptionsViewHolder(ViewGroup viewGroup, e eVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_gift_options, viewGroup, false));
        this.mClickHandler = eVar;
        this.mGiftMessageError.setText(viewGroup.getContext().getString(R.string.gift_options_gift_message_too_long, new Object[]{Integer.valueOf(GIFT_MESSAGE_MAX_LENGTH)}));
        this.mGiftMessage = (EditText) findViewById(R.id.gift_options_gift_message);
        this.mGiftMessage.setHorizontallyScrolling(false);
        this.mGiftMessage.setMaxLines(Integer.MAX_VALUE);
        this.mTextChangeObservable = h.c(this.mGiftMessage).b(1).b(500, TimeUnit.MILLISECONDS).a(a.a());
        h.b(this.mGiftMessage).a(a.a()).a((Consumer<? super T>) new b<Object>(this), c.a);
        h.a(this.mGiftMessage).a(a.a()).a(d.a, e.a);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$new$0$GiftOptionsViewHolder(CharSequence charSequence) throws Exception {
        int i = charSequence.length() < GIFT_MESSAGE_MAX_LENGTH ? 8 : 0;
        if (i != this.mGiftMessageError.getVisibility()) {
            this.mGiftMessageError.setVisibility(i);
        }
    }

    static final /* synthetic */ void lambda$new$1$GiftOptionsViewHolder(com.jakewharton.rxbinding2.widget.q qVar) throws Exception {
        if (qVar.actionId() != 0) {
            qVar.view().clearFocus();
            com.etsy.android.lib.util.s.a((View) qVar.view());
        }
    }

    public void bindCartGroupItem(CartGroupItem cartGroupItem) {
        Context context = getRootView().getContext();
        GiftOptions giftOptions = (GiftOptions) cartGroupItem.getData();
        this.mIsGiftCheckbox.setOnCheckedChangeListener(null);
        this.mGiftMessageCheckbox.setOnCheckedChangeListener(null);
        this.mGiftWrapCheckbox.setOnCheckedChangeListener(null);
        this.mIsGiftCheckbox.setText(buildMarkedGiftLabel(context));
        this.mIsGiftCheckbox.setChecked(giftOptions.isGift());
        if (giftOptions.isGift()) {
            showExtraGiftOptions(giftOptions);
        }
        this.mIsGiftCheckbox.setOnCheckedChangeListener(new f(this, cartGroupItem));
        if (!TextUtils.isEmpty(giftOptions.getGiftMessage())) {
            this.mGiftMessageCheckbox.setChecked(true);
            this.mGiftMessage.setText(giftOptions.getGiftMessage());
        } else {
            this.mGiftMessageCheckbox.setChecked(false);
        }
        this.mGiftMessageCheckbox.setText(buildGiftMessageLabel(getRootView().getContext(), this.mGiftMessageCheckbox.isChecked()));
        this.mGiftMessageCheckbox.setOnCheckedChangeListener(new g(this, cartGroupItem));
        if (this.mTextChangeDisposable != null) {
            this.mTextChangeDisposable.dispose();
        }
        this.mTextChangeDisposable = this.mTextChangeObservable.a((Consumer<? super T>) new h<Object>(this, cartGroupItem), i.a);
        if (giftOptions.getGiftWrap() != null) {
            this.mGiftWrapCheckbox.setChecked(giftOptions.getGiftWrap().isSelected());
            this.mGiftWrapCheckbox.setText(context.getString(R.string.gift_options_gift_wrap, new Object[]{giftOptions.getGiftWrap().getPrice().getCurrencyFormattedShort()}));
            this.mGiftWrapCheckbox.setOnCheckedChangeListener(new j(this, cartGroupItem));
            this.mGiftWrapDetails.setOnClickListener(new k(this, giftOptions));
        }
        if (giftOptions.isGift()) {
            showExtraGiftOptions(giftOptions);
        } else {
            hideExtraGiftOptions();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$bindCartGroupItem$2$GiftOptionsViewHolder(CartGroupItem cartGroupItem, CompoundButton compoundButton, boolean z) {
        updateAsGift(cartGroupItem);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$bindCartGroupItem$3$GiftOptionsViewHolder(CartGroupItem cartGroupItem, CompoundButton compoundButton, boolean z) {
        this.mGiftMessageCheckbox.setText(buildGiftMessageLabel(getRootView().getContext(), z));
        if (z) {
            showGiftMessage();
        } else {
            hideGiftMessage(cartGroupItem);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$bindCartGroupItem$4$GiftOptionsViewHolder(CartGroupItem cartGroupItem, s sVar) throws Exception {
        updateGiftMessage(this.mGiftMessage.getText().toString(), cartGroupItem);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$bindCartGroupItem$5$GiftOptionsViewHolder(CartGroupItem cartGroupItem, CompoundButton compoundButton, boolean z) {
        updateGiftWrap(cartGroupItem);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$bindCartGroupItem$6$GiftOptionsViewHolder(GiftOptions giftOptions, View view) {
        showGiftWrapDetails(giftOptions);
    }

    private SpannableStringBuilder buildMarkedGiftLabel(Context context) {
        return new C0111a().a(context.getString(R.string.gift_options_marked_gift)).b(context.getString(R.string.gift_options_gift_price_hidden)).a(context);
    }

    private SpannableStringBuilder buildGiftMessageLabel(Context context, boolean z) {
        if (z) {
            return new C0111a().a(context.getString(R.string.gift_options_gift_message)).b(context.getString(R.string.gift_options_gift_message_reminder)).a(context);
        }
        return new SpannableStringBuilder(context.getString(R.string.gift_options_gift_message));
    }

    private void showExtraGiftOptions(@NonNull GiftOptions giftOptions) {
        int i = 8;
        this.mGiftMessageCheckbox.setVisibility(giftOptions.offersGiftMessage() ? 0 : 8);
        this.mGiftMessage.setVisibility(giftOptions.offersGiftMessage() && !TextUtils.isEmpty(giftOptions.getGiftMessage()) ? 0 : 8);
        View view = this.mGiftWrapContainer;
        if (giftOptions.getGiftWrap() != null) {
            i = 0;
        }
        view.setVisibility(i);
    }

    private void hideExtraGiftOptions() {
        this.mGiftMessageCheckbox.setVisibility(8);
        this.mGiftMessage.setVisibility(8);
        this.mGiftWrapContainer.setVisibility(8);
    }

    private void showGiftMessage() {
        this.mGiftMessage.setVisibility(0);
        com.etsy.android.lib.util.s.b((View) this.mGiftMessage);
    }

    private void hideGiftMessage(CartGroupItem cartGroupItem) {
        this.mGiftMessage.setVisibility(8);
        this.mGiftMessage.setText("");
        updateGiftMessage("", cartGroupItem);
        com.etsy.android.lib.util.s.a((View) this.mGiftMessage);
    }

    private void showGiftWrapDetails(GiftOptions giftOptions) {
        if (giftOptions != null && giftOptions.getGiftWrap() != null) {
            this.mClickHandler.a(giftOptions.getGiftWrap().getShopName(), giftOptions.getGiftWrap().getDescription(), giftOptions.getGiftWrap().getPreviewImage());
        }
    }

    private void updateAsGift(CartGroupItem cartGroupItem) {
        boolean isChecked = this.mIsGiftCheckbox.isChecked();
        ServerDrivenAction action = cartGroupItem.getAction("is_gift");
        if (action != null) {
            action.addParam(ResponseConstants.VALUE, String.valueOf(isChecked));
            this.mClickHandler.c(getRootView(), action);
        }
    }

    private void updateGiftMessage(String str, CartGroupItem cartGroupItem) {
        ((GiftOptions) cartGroupItem.getData()).setGiftMessage(str);
        ServerDrivenAction action = cartGroupItem.getAction("gift_message");
        if (action != null) {
            action.addParam(ResponseConstants.VALUE, str);
            this.mClickHandler.c(getRootView(), action);
        }
    }

    private void updateGiftWrap(CartGroupItem cartGroupItem) {
        boolean isChecked = this.mGiftWrapCheckbox.isChecked();
        ServerDrivenAction action = cartGroupItem.getAction("gift_wrap");
        if (action != null) {
            action.addParam(ResponseConstants.VALUE, String.valueOf(isChecked));
            this.mClickHandler.c(getRootView(), action);
        }
    }
}
