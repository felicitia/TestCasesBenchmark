package com.threatmetrix.TrustDefender.internal;

import com.threatmetrix.TrustDefender.internal.P.O;

class V {

    /* renamed from: new reason: not valid java name */
    private static final String f551new = TL.m331if(V.class);

    /* renamed from: case reason: not valid java name */
    private String f552case;

    /* renamed from: do reason: not valid java name */
    private final String f553do;

    /* renamed from: for reason: not valid java name */
    private final String f554for;

    /* renamed from: if reason: not valid java name */
    private final String f555if;

    /* renamed from: int reason: not valid java name */
    private final String f556int;

    /* renamed from: try reason: not valid java name */
    private final boolean f557try;

    V(O o, String str, String str2, String str3, String str4, boolean z) {
        this.f555if = str;
        this.f553do = str2;
        this.f554for = str3;
        this.f556int = str4;
        this.f557try = z;
        String str5 = JG.m104do(o, str, str2);
        if (!NK.m215if(str5)) {
            this.f552case = str5;
        } else if (NK.m203byte(str3)) {
            JG.m111int(o, str, str2, str3);
            this.f552case = str3;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final boolean m346for(O o) {
        if (NK.m215if(this.f552case)) {
            this.f552case = JG.m104do(o, this.f555if, this.f553do);
        }
        if (NK.m215if(this.f554for)) {
            if ((NK.m203byte(this.f556int) && this.f557try) || NK.m203byte(this.f552case)) {
                return true;
            }
        } else if ((!this.f554for.equalsIgnoreCase(this.f556int) && this.f557try) || !this.f554for.equalsIgnoreCase(this.f552case)) {
            return true;
        }
        return false;
    }
}
