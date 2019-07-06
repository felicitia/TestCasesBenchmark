package com.contextlogic.wish.activity.signup.redesign;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.login.signin.LoginFormDropdownEditText;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFooterView.FooterManager;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class SignupGetEmailView extends LoadingPageView implements LoadingPageManager {
    private LoginFormDropdownEditText mEmailText;
    /* access modifiers changed from: private */
    public ThemedTextView mErrorMessageView;
    /* access modifiers changed from: private */
    public SignupFlowFooterView mFooterView;
    private AutoReleasableImageView mImage;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.signup_get_email;
    }

    public void handleReload() {
    }

    public boolean hasItems() {
        return true;
    }

    public SignupGetEmailView(Context context) {
        this(context, null);
    }

    public SignupGetEmailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLoadingPageManager(this);
    }

    public void setOnFinishClicked(FooterManager footerManager) {
        this.mFooterView.setFooterManager(footerManager);
    }

    public void initializeLoadingContentView(View view) {
        this.mImage = (AutoReleasableImageView) findViewById(R.id.sign_up_get_email_icon);
        this.mFooterView = (SignupFlowFooterView) findViewById(R.id.get_email_footer_view);
        this.mEmailText = (LoginFormDropdownEditText) findViewById(R.id.sign_up_get_email_edit_text);
        this.mErrorMessageView = (ThemedTextView) findViewById(R.id.get_email_error_message);
        if (ExperimentDataCenter.getInstance().shouldSeeSecureGetEmail()) {
            this.mFooterView.setup(getResources().getString(R.string.secure_your_account), getResources().getString(R.string.receive_important_order_information), getResources().getString(R.string.finish));
            this.mImage.setImageResource(R.drawable.security_140);
        } else if (ExperimentDataCenter.getInstance().shouldSeeOrderConfirmationGetEmail()) {
            this.mFooterView.setup(getResources().getString(R.string.get_your_order_confirmation), getResources().getString(R.string.receive_tracking_and_order_information), getResources().getString(R.string.finish));
            this.mImage.setLayoutParams(new LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.get_email_order_confirmation_banner_height)));
            this.mImage.setImageResource(R.drawable.order_confirmation_get_email_icon);
        } else if (ExperimentDataCenter.getInstance().shouldSeeDiscountGetEmail()) {
            this.mFooterView.setup(getResources().getString(R.string.get_a_coupon_sent_your_email), getResources().getString(R.string.use_it_towards_your_first_purchase), getResources().getString(R.string.finish));
            this.mImage.setImageResource(R.drawable.email_discount);
        }
        this.mEmailText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                SignupGetEmailView.this.mFooterView.getButton().performClick();
                return true;
            }
        });
        this.mEmailText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (SignupGetEmailView.this.mErrorMessageView.getVisibility() == 0) {
                    SignupGetEmailView.this.mErrorMessageView.setVisibility(8);
                }
            }
        });
        markLoadingComplete();
    }

    public String getEnteredEmail() {
        return this.mEmailText.getText().toString();
    }

    public void showInvalidEmail(String str) {
        this.mEmailText.setError();
        if (str != null) {
            this.mErrorMessageView.setText(str);
            this.mErrorMessageView.setVisibility(0);
        }
    }
}
