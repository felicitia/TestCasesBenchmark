package com.contextlogic.wish.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.R;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardUtil {

    public enum CardType implements Parcelable {
        Visa,
        MasterCard,
        Discover,
        Amex,
        DinersClub,
        HiperCard,
        JCB,
        Carnet,
        Elo,
        Aura,
        Invalid;
        
        public static final Creator<CardType> CREATOR = null;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<CardType>() {
                public CardType createFromParcel(Parcel parcel) {
                    return CardType.values()[parcel.readInt()];
                }

                public CardType[] newArray(int i) {
                    return new CardType[i];
                }
            };
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public static class CreditCardHolder {
        private String mCardId;
        private String mCardNumber;
        private String mExpiryDate;
        private String mSecurityCode;

        public CreditCardHolder(String str, String str2, String str3, String str4) {
            setCardNumber(str2);
            setExpiryDate(str3);
            setSecurityCode(str4);
            setCardId(str);
        }

        public void setCardId(String str) {
            this.mCardId = str;
        }

        public String getCardId() {
            return this.mCardId;
        }

        public String getCardNumber() {
            return this.mCardNumber;
        }

        public void setCardNumber(String str) {
            this.mCardNumber = str;
        }

        public void setExpiryDate(String str) {
            this.mExpiryDate = str;
        }

        public int getExpiryYear() {
            return Integer.valueOf(this.mExpiryDate.split("/")[1]).intValue() + 2000;
        }

        public int getExpiryMonth() {
            return Integer.valueOf(this.mExpiryDate.split("/")[0]).intValue();
        }

        public String getSecurityCode() {
            return this.mSecurityCode;
        }

        public void setSecurityCode(String str) {
            this.mSecurityCode = str;
        }
    }

    public static String getCreditCardTypeString(CardType cardType) {
        switch (cardType) {
            case Visa:
                return "visa";
            case MasterCard:
                return "mastercard";
            case Discover:
                return "discover";
            case Amex:
                return "american express";
            case DinersClub:
                return "diners club";
            case HiperCard:
                return "hipercard";
            case JCB:
                return "jcb";
            case Carnet:
                return "carnet";
            case Aura:
                return "aura";
            case Elo:
                return "elo";
            default:
                return "Invalid";
        }
    }

    public static String getCreditCardTypeDisplayString(CardType cardType) {
        switch (cardType) {
            case Visa:
                return "Visa";
            case MasterCard:
                return "MasterCard";
            case Discover:
                return "Discover";
            case Amex:
                return "American Express";
            case DinersClub:
                return "Diners Club";
            case HiperCard:
                return "Hipercard";
            case JCB:
                return "JCB";
            case Carnet:
                return "Carnet";
            case Aura:
                return "Aura";
            case Elo:
                return "Elo";
            default:
                return "Invalid";
        }
    }

    public static int getValidSecurityCodeLength(CardType cardType) {
        return AnonymousClass1.$SwitchMap$com$contextlogic$wish$util$CreditCardUtil$CardType[cardType.ordinal()] != 4 ? 3 : 4;
    }

    public static CardType getCardTypeFromString(String str) {
        if (str.equals("visa")) {
            return CardType.Visa;
        }
        if (str.equals("mastercard")) {
            return CardType.MasterCard;
        }
        if (str.equals("discover")) {
            return CardType.Discover;
        }
        if (str.equals("american express")) {
            return CardType.Amex;
        }
        if (str.equals("diners club")) {
            return CardType.DinersClub;
        }
        if (str.equals("hipercard")) {
            return CardType.HiperCard;
        }
        if (str.equals("jcb")) {
            return CardType.JCB;
        }
        if (str.equals("carnet")) {
            return CardType.Carnet;
        }
        if (str.equals("aura")) {
            return CardType.Aura;
        }
        if (str.equals("elo")) {
            return CardType.Elo;
        }
        return CardType.Invalid;
    }

    public static CardType getCardTypeFromNumber(String str) {
        CardType[] values;
        if (str == null || str.length() < 4) {
            return CardType.Invalid;
        }
        String str2 = null;
        for (CardType cardType : CardType.values()) {
            switch (cardType) {
                case Visa:
                    str2 = "^4[0-9]{3}?";
                    break;
                case MasterCard:
                    str2 = "^((5[1-5][0-9]{2})|(2[2-7][0-9]{2}))$";
                    break;
                case Discover:
                    str2 = "^6(?:011|5[0-9]{2})$";
                    break;
                case Amex:
                    str2 = "^3[47][0-9]{2}$";
                    break;
                case DinersClub:
                    str2 = "^3(?:0[0-5]|[68][0-9])[0-9]$";
                    break;
                case HiperCard:
                    str2 = "^((6062|6370|6375)|(38[0-9]{2}))$";
                    break;
                case JCB:
                    str2 = "^(?:2131|1800|35[0-9]{2})$";
                    break;
                case Carnet:
                    str2 = "^5062$";
                    break;
                case Aura:
                    str2 = "^5[0-9]{3}$";
                    break;
                case Elo:
                    str2 = "^((5067)|(5090)|(6363)|(6362)|(5041)|(4389)|(4011)|(4576)|(4573)|(4312)|(6277))";
                    break;
            }
            if (Pattern.compile(str2).matcher(str.substring(0, 4)).matches()) {
                return cardType;
            }
        }
        return CardType.Invalid;
    }

    public static boolean isValidCardNumber(String str) {
        String str2;
        String sanitizeCreditCardNumber = sanitizeCreditCardNumber(str);
        CardType cardTypeFromNumber = getCardTypeFromNumber(sanitizeCreditCardNumber);
        boolean z = false;
        switch (cardTypeFromNumber) {
            case Visa:
                str2 = "^4[0-9]{3}?";
                break;
            case MasterCard:
                str2 = "^((5[1-5][0-9]{2})|(2[2-7][0-9]{2}))$";
                break;
            case Discover:
                str2 = "^6(?:011|5[0-9]{2})$";
                break;
            case Amex:
                str2 = "^3[47][0-9]{2}$";
                break;
            case DinersClub:
                str2 = "^3(?:0[0-5]|[68][0-9])[0-9]$";
                break;
            case HiperCard:
                str2 = "^((6062|6370|6375)|(38[0-9]{2}))$";
                break;
            case JCB:
                str2 = "^(?:2131|1800|35[0-9]{2})$";
                break;
            case Carnet:
                str2 = "^5062$";
                break;
            case Aura:
                str2 = "^5[0-9]{3}$";
                break;
            case Elo:
                str2 = "^((5067)|(5090)|(6363)|(6362)|(5041)|(4389)|(4011)|(4576)|(4573)|(4312)|(6277))";
                break;
            default:
                return false;
        }
        Matcher matcher = Pattern.compile(str2).matcher(str.substring(0, 4));
        if (cardTypeFromNumber == CardType.HiperCard) {
            if (matcher.matches() && (sanitizeCreditCardNumber.length() == 13 || sanitizeCreditCardNumber.length() == 16 || sanitizeCreditCardNumber.length() == 19)) {
                z = true;
            }
            return z;
        }
        if (matcher.matches() && validateCardNumber(sanitizeCreditCardNumber)) {
            z = true;
        }
        return z;
    }

    private static boolean validateCardNumber(String str) {
        boolean z = false;
        try {
            int i = 0;
            boolean z2 = false;
            for (int length = str.length() - 1; length >= 0; length--) {
                int parseInt = Integer.parseInt(str.substring(length, length + 1));
                if (z2) {
                    parseInt *= 2;
                    if (parseInt > 9) {
                        parseInt -= 9;
                    }
                }
                i += parseInt;
                z2 = !z2;
            }
            if (i % 10 == 0) {
                z = true;
            }
            return z;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static int getLengthOfFormattedString(CardType cardType, String str) {
        switch (cardType) {
            case Visa:
            case MasterCard:
            case Discover:
            case Carnet:
            case Elo:
                break;
            case Amex:
            case DinersClub:
                return 17;
            case HiperCard:
            case Aura:
                return 22;
            case JCB:
                if (!str.startsWith("35")) {
                    return 18;
                }
                break;
            default:
                return 0;
        }
        return 19;
    }

    public static String sanitizeCreditCardNumber(String str) {
        return str.replaceAll("\\s", "");
    }

    public static String getFormattedCardNumber(String str, CardType cardType) {
        String sanitizeCreditCardNumber = sanitizeCreditCardNumber(str);
        int length = sanitizeCreditCardNumber.length();
        if (length <= 4) {
            return sanitizeCreditCardNumber;
        }
        ArrayList arrayList = new ArrayList();
        int[] iArr = {0, 0, 0};
        switch (cardType) {
            case Visa:
            case MasterCard:
            case Discover:
            case Carnet:
            case Elo:
                arrayList.add(" ");
                iArr[0] = 4;
                arrayList.add(" ");
                iArr[1] = 4;
                arrayList.add(" ");
                iArr[2] = 4;
                break;
            case Amex:
                arrayList.add(" ");
                iArr[0] = 6;
                arrayList.add(" ");
                iArr[1] = 5;
                arrayList.add("");
                iArr[2] = 0;
                break;
            case DinersClub:
                arrayList.add(" ");
                iArr[0] = 4;
                arrayList.add(" ");
                iArr[1] = 4;
                arrayList.add(" ");
                iArr[2] = 2;
                break;
            case HiperCard:
            case Aura:
                arrayList.add(" ");
                iArr[0] = 4;
                arrayList.add(" ");
                iArr[1] = 4;
                arrayList.add(" ");
                iArr[2] = 7;
                break;
            case JCB:
                arrayList.add(" ");
                iArr[0] = 4;
                arrayList.add(" ");
                iArr[1] = 4;
                arrayList.add(" ");
                if (!sanitizeCreditCardNumber.startsWith("35")) {
                    iArr[2] = 3;
                    break;
                } else {
                    iArr[2] = 4;
                    break;
                }
            default:
                return str;
        }
        String substring = sanitizeCreditCardNumber.substring(0, 4);
        int i = iArr[0] + 4 > length ? length : iArr[0] + 4;
        String substring2 = sanitizeCreditCardNumber.substring(4, i);
        int i2 = iArr[1] + i > length ? length : iArr[1] + i;
        String substring3 = sanitizeCreditCardNumber.substring(i, i2);
        if (iArr[2] + i2 <= length) {
            length = iArr[2] + i2;
        }
        return String.format("%s%s%s%s%s%s%s", new Object[]{substring, arrayList.get(0), substring2, arrayList.get(1), substring3, arrayList.get(2), sanitizeCreditCardNumber.substring(i2, length)}).trim();
    }

    public static String getFormattedExpiryDate(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getTextWithLeadingZero(i));
        sb.append("/");
        sb.append(i2);
        return sb.toString();
    }

    public static String getTextWithLeadingZero(int i) {
        if (i >= 9) {
            return Integer.toString(i);
        }
        return String.format("0%d", new Object[]{Integer.valueOf(i)});
    }

    public static int cardImageForCardType(CardType cardType) {
        switch (cardType) {
            case Visa:
                return R.drawable.credit_card_visa_dark;
            case MasterCard:
                return R.drawable.credit_card_mastercard;
            case Discover:
                return R.drawable.credit_card_discover;
            case Amex:
                return R.drawable.credit_card_amex;
            case DinersClub:
                return R.drawable.credit_card_diners_club;
            case HiperCard:
                return R.drawable.credit_card_hipercard;
            case JCB:
                return R.drawable.credit_card_jcb;
            case Carnet:
                return R.drawable.credit_card_carnet;
            case Aura:
                return R.drawable.credit_card_aura;
            case Elo:
                return R.drawable.credit_card_elo;
            default:
                return R.drawable.creditcard_default_minimal;
        }
    }
}
