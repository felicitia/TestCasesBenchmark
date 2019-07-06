package com.etsy.android.ui.giftcards;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.b.d;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.c;
import com.etsy.android.lib.core.http.request.c.b;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.GiftCardAmountValues;
import com.etsy.android.lib.models.GiftCardAmounts;
import com.etsy.android.lib.models.GiftCardDesign;
import com.etsy.android.lib.models.GiftCardDesigns;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.dialog.EtsyTrioDialogFragment;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class GiftCardCreateActivity extends BOENavDrawerActivity implements TextWatcher, OnCheckedChangeListener, a {
    private static final String SAVE_AMOUNTS = "save_amounts";
    private static final String SAVE_DESIGNS = "save_designs";
    private static final String TAG = f.a(GiftCardCreateActivity.class);
    private Disposable amountDisposable;
    private Disposable designDisposable;
    d giftCardEndpoint;
    private Button mBtnAddToCard;
    private View mErrorView;
    private ViewGroup mFormLayout;
    private List<Integer> mGiftCardAmounts;
    private GiftCardDesignSelecterView mGiftCardDesignSelector;
    private List<GiftCardDesign> mGiftCardDesigns;
    private ViewGroup mLayoutEmail;
    /* access modifiers changed from: private */
    public View mLoadingView;
    private RadioGroup mRadioGroupCardValue;
    private RadioGroup mRadioGroupDeliveryOption;
    private Button mRetryButton;
    TrackingOnClickListener mRetryClickListener = new TrackingOnClickListener() {
        public void onViewClick(View view) {
            GiftCardCreateActivity.this.startLoading();
        }
    };
    private TextView mTxtEmailConfirm;
    private TextView mTxtMessage;
    private TextView mTxtRecipientEmail;
    private TextView mTxtRecipientName;
    private TextView mTxtSenderName;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @NonNull
    public String getTrackingName() {
        return "create_gift_card";
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_gift_card_create);
        setTitle(R.string.create_gift_card);
        this.mFormLayout = (ViewGroup) findViewById(R.id.form_layout);
        this.mLoadingView = findViewById(R.id.loading_view);
        this.mLoadingView.setBackgroundColor(getResources().getColor(R.color.white_transparent));
        this.mErrorView = findViewById(R.id.error_view);
        this.mRetryButton = (Button) findViewById(R.id.btn_retry_internet);
        this.mRetryButton.setOnClickListener(this.mRetryClickListener);
        this.mTxtSenderName = (TextView) findViewById(R.id.txt_sender_name);
        this.mTxtSenderName.addTextChangedListener(this);
        this.mTxtRecipientName = (TextView) findViewById(R.id.txt_recipient_name);
        this.mTxtRecipientName.addTextChangedListener(this);
        this.mTxtRecipientEmail = (TextView) findViewById(R.id.txt_email);
        this.mTxtRecipientEmail.addTextChangedListener(this);
        this.mTxtEmailConfirm = (TextView) findViewById(R.id.txt_email_confirm);
        this.mTxtEmailConfirm.addTextChangedListener(this);
        this.mLayoutEmail = (ViewGroup) findViewById(R.id.layout_email);
        this.mTxtMessage = (TextView) findViewById(R.id.txt_message);
        this.mGiftCardDesignSelector = (GiftCardDesignSelecterView) findViewById(R.id.view_gift_card_design_selector);
        this.mRadioGroupDeliveryOption = (RadioGroup) findViewById(R.id.delivery_option);
        this.mRadioGroupDeliveryOption.setOnCheckedChangeListener(new a(this));
        this.mRadioGroupCardValue = (RadioGroup) findViewById(R.id.card_value);
        this.mRadioGroupCardValue.setOnCheckedChangeListener(this);
        this.mBtnAddToCard = (Button) findViewById(R.id.button_add_to_cart);
        this.mBtnAddToCard.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                GiftCardCreateActivity.this.onAddToCart();
            }
        });
        if (bundle != null) {
            this.mGiftCardDesigns = (List) org.parceler.d.a(bundle.getParcelable(SAVE_DESIGNS));
            this.mGiftCardAmounts = (List) org.parceler.d.a(bundle.getParcelable(SAVE_AMOUNTS));
            if (this.mGiftCardDesigns == null || this.mGiftCardAmounts == null) {
                showError();
            } else {
                updateFormDisplay();
            }
        } else {
            startLoading();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onCreate$0$GiftCardCreateActivity(RadioGroup radioGroup, int i) {
        int i2 = 0;
        boolean z = i == R.id.option_emailed;
        ViewGroup viewGroup = this.mLayoutEmail;
        if (!z) {
            i2 = 8;
        }
        viewGroup.setVisibility(i2);
        this.mLayoutEmail.animate().alpha(z ? 1.0f : 0.0f);
        this.mBtnAddToCard.setEnabled(validateFormData());
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.designDisposable != null) {
            this.designDisposable.dispose();
        }
        if (this.amountDisposable != null) {
            this.amountDisposable.dispose();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(SAVE_DESIGNS, org.parceler.d.a(this.mGiftCardDesigns));
        bundle.putParcelable(SAVE_AMOUNTS, org.parceler.d.a(this.mGiftCardAmounts));
    }

    private void requestGiftCardDesigns() {
        this.designDisposable = this.giftCardEndpoint.a().b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).a((Consumer<? super T>) new b<Object>(this), (Consumer<? super Throwable>) new c<Object>(this));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$requestGiftCardDesigns$1$GiftCardCreateActivity(GiftCardDesigns giftCardDesigns) throws Exception {
        this.mGiftCardDesigns = giftCardDesigns.results;
        updateFormDisplay();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$requestGiftCardDesigns$2$GiftCardCreateActivity(Throwable th) throws Exception {
        f.e(f.a(GiftCardCreateActivity.class), th.getMessage());
        showError();
    }

    private void requestGiftCardAmounts() {
        this.amountDisposable = this.giftCardEndpoint.b().b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).a((Consumer<? super T>) new d<Object>(this), (Consumer<? super Throwable>) new e<Object>(this));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$requestGiftCardAmounts$3$GiftCardCreateActivity(GiftCardAmounts giftCardAmounts) throws Exception {
        if (giftCardAmounts.getResults().size() > 0) {
            GiftCardAmountValues giftCardAmountValues = (GiftCardAmountValues) giftCardAmounts.getResults().get(0);
            if (giftCardAmountValues != null) {
                this.mGiftCardAmounts = new ArrayList();
                this.mGiftCardAmounts.addAll(giftCardAmountValues.getValues());
                updateFormDisplay();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$requestGiftCardAmounts$4$GiftCardCreateActivity(Throwable th) throws Exception {
        f.e(f.a(GiftCardCreateActivity.class), th.getMessage());
        showError();
    }

    private void updateFormDisplay() {
        if (this.mGiftCardDesigns != null && this.mGiftCardAmounts != null) {
            this.mGiftCardDesignSelector.setGiftCardDesigns(this.mGiftCardDesigns);
            addGiftCardAmountButtons(this.mGiftCardAmounts);
            stopLoading();
        }
    }

    /* access modifiers changed from: private */
    public void onAddToCart() {
        if (validateFormData()) {
            int checkedRadioButtonId = this.mRadioGroupCardValue.getCheckedRadioButtonId();
            if (checkedRadioButtonId == 0) {
                f.e(TAG, "No Gift Card Value was retrieved");
                return;
            }
            c.a a = c.a.a(new g().a(checkedRadioButtonId, this.mRadioGroupDeliveryOption.getCheckedRadioButtonId() == R.id.option_emailed ? this.mTxtRecipientEmail.getText().toString().trim() : "", this.mTxtRecipientName.getText().toString(), this.mTxtSenderName.getText().toString(), this.mGiftCardDesignSelector.getSelectedGiftCardId(), this.mTxtMessage.getText().toString()));
            a.a((C0065a<ResultType>) new b<EmptyResult>() {
                public void a(@NonNull List<EmptyResult> list, int i, @NonNull k<EmptyResult> kVar) {
                    GiftCardCreateActivity.this.getAnalyticsContext().a("gift_card_created", null);
                    Toast.makeText(GiftCardCreateActivity.this.getApplicationContext(), GiftCardCreateActivity.this.getString(R.string.gift_card_create_success), 0).show();
                    GiftCardCreateActivity.this.setResult(-1);
                    GiftCardCreateActivity.this.finish();
                    com.etsy.android.ui.cart.b.a((Context) GiftCardCreateActivity.this);
                }

                public void a(int i, @Nullable String str, @NonNull k<EmptyResult> kVar) {
                    GiftCardCreateActivity.this.getAnalyticsContext().a("gift_card_create_error", null);
                    f.e(f.a(GiftCardCreateActivity.class), str);
                    GiftCardCreateActivity.this.mLoadingView.setVisibility(8);
                    com.etsy.android.ui.nav.a d = e.a((FragmentActivity) GiftCardCreateActivity.this).d();
                    if (TextUtils.isEmpty(str)) {
                        str = GiftCardCreateActivity.this.getString(R.string.gift_card_create_submit_error);
                    }
                    d.a((EtsyTrioDialogFragment.a) null, (int) R.string.ok, 0, 0, str);
                }
            }, (Activity) this);
            this.mLoadingView.setVisibility(0);
            getRequestQueue().a((Object) this, a.c());
        }
    }

    private boolean validateFormData() {
        String trim = this.mTxtSenderName.getText().toString().trim();
        String trim2 = this.mTxtRecipientName.getText().toString().trim();
        CharSequence text = this.mTxtRecipientEmail.getText();
        CharSequence text2 = this.mTxtEmailConfirm.getText();
        boolean z = false;
        boolean z2 = !TextUtils.isEmpty(trim) && !TextUtils.isEmpty(trim2) && this.mRadioGroupCardValue.getCheckedRadioButtonId() != -1;
        if (this.mRadioGroupDeliveryOption.getCheckedRadioButtonId() != R.id.option_emailed) {
            return z2;
        }
        if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(text2) && Patterns.EMAIL_ADDRESS.matcher(text).matches() && TextUtils.equals(text, text2)) {
            z = true;
        }
        return z2 & z;
    }

    /* access modifiers changed from: private */
    public void startLoading() {
        this.mGiftCardDesigns = null;
        this.mGiftCardAmounts = null;
        this.mFormLayout.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(0);
        requestGiftCardDesigns();
        requestGiftCardAmounts();
    }

    private void stopLoading() {
        this.mLoadingView.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mFormLayout.setVisibility(0);
    }

    private void showError() {
        this.mLoadingView.setVisibility(8);
        this.mFormLayout.setVisibility(8);
        this.mErrorView.setVisibility(0);
    }

    private void addGiftCardAmountButtons(List<Integer> list) {
        this.mRadioGroupCardValue.setWeightSum((float) list.size());
        LayoutInflater layoutInflater = getLayoutInflater();
        for (int i = 0; i < list.size(); i++) {
            RadioButton radioButton = (RadioButton) layoutInflater.inflate(R.layout.view_gift_card_radio_button, this.mRadioGroupCardValue, false);
            int intValue = ((Integer) list.get(i)).intValue();
            String a = CurrencyUtil.a(getApplicationContext());
            radioButton.setId(intValue);
            radioButton.setText(new EtsyMoney(intValue).withCurrency(a).setMaximumFractionDigits(Integer.valueOf(0)).format());
            this.mRadioGroupCardValue.addView(radioButton);
        }
        ((RadioButton) this.mRadioGroupCardValue.getChildAt(0)).setChecked(true);
    }

    public void afterTextChanged(Editable editable) {
        this.mBtnAddToCard.setEnabled(validateFormData());
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.mBtnAddToCard.setEnabled(validateFormData());
    }
}
