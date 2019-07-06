package com.contextlogic.wish.activity.cart.billing.creditcardform;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreditCardFormExpiryDateEditText extends CreditCardFormBaseEditText {
    private CreditCardFormFieldsDelegate mDelegate;
    private String mPreviousString;

    public CreditCardFormExpiryDateEditText(Context context) {
        super(context);
        init();
    }

    public CreditCardFormExpiryDateEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CreditCardFormExpiryDateEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        super.init();
        setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_form));
        setHint(getContext().getString(R.string.mm_yy));
        setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_primary));
        setHintTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_hint));
        setBackgroundResource(R.drawable.edit_text_background);
        setGravity(19);
    }

    public void showRedesignedBackground() {
        setBackgroundResource(R.drawable.redesign_edit_text_normal);
        setHintTextColor(getResources().getColor(R.color.gray4));
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mPreviousString = charSequence.toString();
    }

    public void afterTextChanged(Editable editable) {
        String obj = editable.toString();
        if (obj.length() > this.mPreviousString.length()) {
            removeTextChangedListener(this);
            String formattedExpirationDate = getFormattedExpirationDate(editable.toString());
            setText(formattedExpirationDate);
            setSelection(formattedExpirationDate.length());
            addTextChangedListener(this);
            if (formattedExpirationDate.length() == 5) {
                onExpirationDateValid();
                setValid(true);
            } else if (formattedExpirationDate.length() < obj.length()) {
                onBadInput();
                setValid(false);
            }
        } else if (obj.length() < 5) {
            setValid(false);
        }
    }

    private void onExpirationDateValid() {
        if (this.mDelegate != null) {
            this.mDelegate.onEntryComplete();
        }
    }

    private void onBadInput() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
        setTextColor(-65536);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                CreditCardFormExpiryDateEditText.this.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_primary));
            }
        }, 300);
    }

    @SuppressLint({"SimpleDateFormat"})
    private String getFormattedExpirationDate(String str) {
        try {
            switch (str.length()) {
                case 1:
                    if (Integer.parseInt(str) < 2) {
                        return str;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("0");
                    sb.append(str);
                    sb.append("/");
                    return sb.toString();
                case 2:
                    if (str.endsWith("/")) {
                        String substring = str.substring(0, 1);
                        if (Integer.parseInt(substring) < 2) {
                            return substring;
                        }
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("0");
                        sb2.append(substring);
                        sb2.append("/");
                        return sb2.toString();
                    }
                    int parseInt = Integer.parseInt(str);
                    if (parseInt <= 12) {
                        if (parseInt >= 1) {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(str);
                            sb3.append("/");
                            return sb3.toString();
                        }
                    }
                    return str.substring(0, 1);
                case 3:
                    if (!str.substring(2, 3).equalsIgnoreCase("/")) {
                        if (str.charAt(1) == '/') {
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append('0');
                            sb4.append(str);
                            str = sb4.toString();
                        }
                        if (!str.contains("/")) {
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append(str.substring(0, 2));
                            sb5.append("/");
                            sb5.append(str.substring(2, 3));
                            str = sb5.toString();
                            break;
                        } else {
                            return str;
                        }
                    } else {
                        return str;
                    }
                case 4:
                    break;
                case 5:
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy", Locale.US);
                    simpleDateFormat.setLenient(false);
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(str.substring(0, 3));
                    sb6.append("20");
                    sb6.append(str.substring(3));
                    Date parse = simpleDateFormat.parse(sb6.toString());
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse);
                    instance.add(2, 1);
                    if (instance.getTime().after(new Date())) {
                        return str;
                    }
                    return str.substring(0, 4);
                default:
                    return str.length() > 5 ? str.substring(0, 5) : str;
            }
            if (Integer.parseInt(str.substring(3, 4)) < Integer.parseInt(String.valueOf(Calendar.getInstance().get(1)).substring(2, 3))) {
                return str.substring(0, 3);
            }
            return str;
        } catch (ParseException unused) {
            return "";
        } catch (NumberFormatException unused2) {
            return "";
        }
    }

    public CreditCardFormFieldsDelegate getDelegate() {
        return this.mDelegate;
    }

    public void setDelegate(CreditCardFormFieldsDelegate creditCardFormFieldsDelegate) {
        this.mDelegate = creditCardFormFieldsDelegate;
    }
}
