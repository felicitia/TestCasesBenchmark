package com.stripe.android.model;

import com.stripe.android.util.TextUtils;
import com.stripe.model.StripeObject;

public class Card extends StripeObject {
    public static final String[] PREFIXES_AMERICAN_EXPRESS = {"34", "37"};
    public static final String[] PREFIXES_DINERS_CLUB = {"300", "301", "302", "303", "304", "305", "309", "36", "38", "39"};
    public static final String[] PREFIXES_DISCOVER = {"60", "62", "64", "65"};
    public static final String[] PREFIXES_JCB = {"35"};
    public static final String[] PREFIXES_MASTERCARD = {"50", "51", "52", "53", "54", "55"};
    public static final String[] PREFIXES_VISA = {"4"};
    private String addressCity;
    private String addressCountry;
    private String addressLine1;
    private String addressLine2;
    private String addressState;
    private String addressZip;
    private String country;
    private String currency;
    private String cvc;
    private Integer expMonth;
    private Integer expYear;
    private String fingerprint;
    private String last4;
    private String name;
    private String number;
    private String type;

    public Card(String str, Integer num, Integer num2, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
        this.number = TextUtils.nullIfBlank(normalizeCardNumber(str));
        this.expMonth = num;
        this.expYear = num2;
        this.cvc = TextUtils.nullIfBlank(str2);
        this.name = TextUtils.nullIfBlank(str3);
        this.addressLine1 = TextUtils.nullIfBlank(str4);
        this.addressLine2 = TextUtils.nullIfBlank(str5);
        this.addressCity = TextUtils.nullIfBlank(str6);
        this.addressState = TextUtils.nullIfBlank(str7);
        this.addressZip = TextUtils.nullIfBlank(str8);
        this.addressCountry = TextUtils.nullIfBlank(str9);
        this.last4 = TextUtils.nullIfBlank(str10);
        this.type = TextUtils.nullIfBlank(str11);
        this.fingerprint = TextUtils.nullIfBlank(str12);
        this.country = TextUtils.nullIfBlank(str13);
        this.type = getType();
        this.last4 = getLast4();
        this.currency = TextUtils.nullIfBlank(this.currency);
    }

    public Card(String str, Integer num, Integer num2, String str2) {
        this(str, num, num2, str2, null, null, null, null, null, null, null, null, null, null, null);
    }

    private String normalizeCardNumber(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().replaceAll("\\s+|-", "");
    }

    public String getNumber() {
        return this.number;
    }

    public String getCVC() {
        return this.cvc;
    }

    public Integer getExpMonth() {
        return this.expMonth;
    }

    public Integer getExpYear() {
        return this.expYear;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public void setAddressLine1(String str) {
        this.addressLine1 = str;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public void setAddressLine2(String str) {
        this.addressLine2 = str;
    }

    public String getAddressCity() {
        return this.addressCity;
    }

    public void setAddressCity(String str) {
        this.addressCity = str;
    }

    public String getAddressZip() {
        return this.addressZip;
    }

    public void setAddressZip(String str) {
        this.addressZip = str;
    }

    public String getAddressState() {
        return this.addressState;
    }

    public void setAddressState(String str) {
        this.addressState = str;
    }

    public String getAddressCountry() {
        return this.addressCountry;
    }

    public void setAddressCountry(String str) {
        this.addressCountry = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getLast4() {
        if (!TextUtils.isBlank(this.last4)) {
            return this.last4;
        }
        if (this.number == null || this.number.length() <= 4) {
            return null;
        }
        return this.number.substring(this.number.length() - 4, this.number.length());
    }

    public String getType() {
        if (!TextUtils.isBlank(this.type) || TextUtils.isBlank(this.number)) {
            return this.type;
        }
        if (TextUtils.hasAnyPrefix(this.number, PREFIXES_AMERICAN_EXPRESS)) {
            return "American Express";
        }
        if (TextUtils.hasAnyPrefix(this.number, PREFIXES_DISCOVER)) {
            return "Discover";
        }
        if (TextUtils.hasAnyPrefix(this.number, PREFIXES_JCB)) {
            return "JCB";
        }
        if (TextUtils.hasAnyPrefix(this.number, PREFIXES_DINERS_CLUB)) {
            return "Diners Club";
        }
        if (TextUtils.hasAnyPrefix(this.number, PREFIXES_VISA)) {
            return "Visa";
        }
        return TextUtils.hasAnyPrefix(this.number, PREFIXES_MASTERCARD) ? "MasterCard" : "Unknown";
    }
}
