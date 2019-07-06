package com.threatmetrix.TrustDefender.internal;

import java.util.Arrays;

class NN {

    /* renamed from: do reason: not valid java name */
    final String f468do;

    /* renamed from: for reason: not valid java name */
    final String f469for;

    /* renamed from: if reason: not valid java name */
    final int f470if;

    /* renamed from: int reason: not valid java name */
    private final long f471int;

    /* renamed from: new reason: not valid java name */
    private final short[] f472new;

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final void m229if(StringBuilder sb, short[] sArr) {
        sb.append(this.f470if > 0 ? "r" : "i");
        sb.append(":");
        if (this.f469for != null) {
            sb.append(this.f469for);
        }
        if (sArr != null) {
            sb.append(":");
            if (this.f472new != null) {
                int numberOfLeadingZeros = (((32 - Integer.numberOfLeadingZeros(sArr.length)) + 4) - 1) / 4;
                for (int i = 0; i < sArr.length; i++) {
                    if (Arrays.binarySearch(this.f472new, sArr[i]) >= 0) {
                        String hexString = Integer.toHexString(i);
                        sb.append("00000000".substring(0, numberOfLeadingZeros - hexString.length()));
                        sb.append(hexString);
                    }
                }
            }
        }
    }

    /* renamed from: if reason: not valid java name */
    static int m228if(int i) {
        return (((32 - Integer.numberOfLeadingZeros(i)) + 4) - 1) / 4;
    }

    public NN(String str, String str2, short[] sArr, long j, int i) {
        this.f469for = str;
        this.f470if = i;
        this.f472new = sArr;
        this.f468do = str2;
        this.f471int = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f469for);
        sb.append(":n;");
        sb.append(this.f468do);
        sb.append(":s;");
        sb.append(this.f471int);
        sb.append(":p");
        String[] strArr = PH.m275do().m295if(this.f472new);
        if (strArr != null) {
            for (String str : strArr) {
                sb.append(";");
                sb.append(str);
            }
        }
        return sb.toString();
    }
}
