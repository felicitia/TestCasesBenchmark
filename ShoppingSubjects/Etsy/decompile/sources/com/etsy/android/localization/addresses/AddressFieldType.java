package com.etsy.android.localization.addresses;

import android.support.v4.os.EnvironmentCompat;
import com.etsy.android.lib.models.ResponseConstants;

public enum AddressFieldType {
    NAME,
    FIRST_LINE,
    SECOND_LINE,
    CITY,
    STATE,
    ZIP,
    COUNTRY_NAME,
    PHONE,
    UNKNOWN;

    public String toString() {
        switch (this) {
            case NAME:
                return ResponseConstants.NAME;
            case FIRST_LINE:
                return ResponseConstants.FIRST_LINE;
            case SECOND_LINE:
                return ResponseConstants.SECOND_LINE;
            case CITY:
                return ResponseConstants.CITY;
            case STATE:
                return ResponseConstants.STATE;
            case ZIP:
                return ResponseConstants.ZIP;
            case COUNTRY_NAME:
                return ResponseConstants.COUNTRY_NAME;
            case PHONE:
                return ResponseConstants.PHONE;
            case UNKNOWN:
                return EnvironmentCompat.MEDIA_UNKNOWN;
            default:
                return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.etsy.android.localization.addresses.AddressFieldType fromString(java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -161037277: goto L_0x004e;
                case 120609: goto L_0x0044;
                case 3053931: goto L_0x003a;
                case 3373707: goto L_0x0030;
                case 106642798: goto L_0x0026;
                case 109757585: goto L_0x001c;
                case 265211103: goto L_0x0012;
                case 1481386388: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0058
        L_0x0008:
            java.lang.String r0 = "country_name"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 6
            goto L_0x0059
        L_0x0012:
            java.lang.String r0 = "second_line"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 2
            goto L_0x0059
        L_0x001c:
            java.lang.String r0 = "state"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 4
            goto L_0x0059
        L_0x0026:
            java.lang.String r0 = "phone"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 7
            goto L_0x0059
        L_0x0030:
            java.lang.String r0 = "name"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 0
            goto L_0x0059
        L_0x003a:
            java.lang.String r0 = "city"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 3
            goto L_0x0059
        L_0x0044:
            java.lang.String r0 = "zip"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 5
            goto L_0x0059
        L_0x004e:
            java.lang.String r0 = "first_line"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 1
            goto L_0x0059
        L_0x0058:
            r1 = -1
        L_0x0059:
            switch(r1) {
                case 0: goto L_0x0074;
                case 1: goto L_0x0071;
                case 2: goto L_0x006e;
                case 3: goto L_0x006b;
                case 4: goto L_0x0068;
                case 5: goto L_0x0065;
                case 6: goto L_0x0062;
                case 7: goto L_0x005f;
                default: goto L_0x005c;
            }
        L_0x005c:
            com.etsy.android.localization.addresses.AddressFieldType r1 = UNKNOWN
            return r1
        L_0x005f:
            com.etsy.android.localization.addresses.AddressFieldType r1 = PHONE
            return r1
        L_0x0062:
            com.etsy.android.localization.addresses.AddressFieldType r1 = COUNTRY_NAME
            return r1
        L_0x0065:
            com.etsy.android.localization.addresses.AddressFieldType r1 = ZIP
            return r1
        L_0x0068:
            com.etsy.android.localization.addresses.AddressFieldType r1 = STATE
            return r1
        L_0x006b:
            com.etsy.android.localization.addresses.AddressFieldType r1 = CITY
            return r1
        L_0x006e:
            com.etsy.android.localization.addresses.AddressFieldType r1 = SECOND_LINE
            return r1
        L_0x0071:
            com.etsy.android.localization.addresses.AddressFieldType r1 = FIRST_LINE
            return r1
        L_0x0074:
            com.etsy.android.localization.addresses.AddressFieldType r1 = NAME
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.localization.addresses.AddressFieldType.fromString(java.lang.String):com.etsy.android.localization.addresses.AddressFieldType");
    }
}
