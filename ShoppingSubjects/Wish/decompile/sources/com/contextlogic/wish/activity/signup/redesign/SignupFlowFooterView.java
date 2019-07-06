package com.contextlogic.wish.activity.signup.redesign;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.SignupFlowPageInfo;

public class SignupFlowFooterView extends LinearLayout {
    private TextView mBody;
    private TextView mButton;
    private TextView mTitle;

    public interface FooterManager {
        void handleFooterButtonClick();
    }

    public SignupFlowFooterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignupFlowFooterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.signup_flow_footer, this);
        this.mTitle = (TextView) findViewById(R.id.signup_flow_footer_title);
        this.mBody = (TextView) findViewById(R.id.signup_flow_footer_body);
        this.mButton = (TextView) findViewById(R.id.signup_flow_footer_button);
    }

    public TextView getButton() {
        return this.mButton;
    }

    public void setFooterManager(final FooterManager footerManager) {
        this.mButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (footerManager != null) {
                    footerManager.handleFooterButtonClick();
                }
            }
        });
    }

    public void setup(String str, String str2, String str3) {
        this.mTitle.setText(str);
        this.mBody.setText(str2);
        this.mButton.setText(str3);
    }

    public void setup(SignupFlowPageInfo signupFlowPageInfo) {
        setup(signupFlowPageInfo.getTitleText(), signupFlowPageInfo.getBodyText(), signupFlowPageInfo.getButtonText());
    }
}
