package com.threatmetrix.TrustDefender.internal;

import com.threatmetrix.TrustDefender.THMStatusCode;
import java.util.Map;

class F implements Runnable {

    /* renamed from: char reason: not valid java name */
    private static final String f162char = TL.m331if(F.class);

    /* renamed from: do reason: not valid java name */
    final String f163do;

    /* renamed from: for reason: not valid java name */
    final X f164for;

    /* renamed from: if reason: not valid java name */
    private final int f165if;

    /* renamed from: int reason: not valid java name */
    private final Y f166int;

    /* renamed from: new reason: not valid java name */
    final JR f167new;

    enum L {
        ;
        

        /* renamed from: for reason: not valid java name */
        public static final int f169for = 1;

        /* renamed from: if reason: not valid java name */
        public static final int f170if = 3;

        /* renamed from: int reason: not valid java name */
        public static final int f171int = 4;

        /* renamed from: new reason: not valid java name */
        public static final int f172new = 2;

        static {
            f168do = new int[]{f169for, f172new, f170if, f171int};
        }

        public static int[] values$279e395f() {
            return (int[]) f168do.clone();
        }
    }

    F(HF hf, int i, String str, X x, Map<String, String> map, Y y) {
        this.f166int = y;
        this.f167new = hf.m67new(y);
        this.f167new.f254new.putAll(map);
        this.f165if = i;
        this.f163do = str;
        this.f164for = x;
    }

    public void run() {
        long j;
        String str;
        long nanoTime = System.nanoTime();
        String str2 = f162char;
        StringBuilder sb = new StringBuilder("starting retrieval: ");
        sb.append(this.f163do);
        TL.m338new(str2, sb.toString());
        if (this.f165if == L.f169for || this.f165if == L.f172new) {
            if (this.f164for.isEmpty()) {
                str = this.f163do;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.f163do);
                sb2.append("?");
                sb2.append(this.f164for.m375if());
                str = sb2.toString();
            }
            j = this.f167new.m119if(str);
        } else if (this.f165if == L.f170if || this.f165if == L.f171int) {
            j = this.f167new.m117for(this.f163do, this.f164for);
        } else {
            j = -1;
        }
        long nanoTime2 = (System.nanoTime() - nanoTime) / 1000000;
        if (j < 0) {
            String str3 = f162char;
            StringBuilder sb3 = new StringBuilder("failed to retrieve from ");
            sb3.append(this.f167new.m118for());
            sb3.append(" with ");
            sb3.append(this.f167new.f252if.toString());
            sb3.append(" in ");
            sb3.append(nanoTime2);
            sb3.append("ms");
            TL.m325do(str3, sb3.toString());
            return;
        }
        String str4 = f162char;
        StringBuilder sb4 = new StringBuilder("retrieved: ");
        sb4.append(this.f167new.m122int());
        sb4.append(" in ");
        sb4.append(nanoTime2);
        sb4.append("ms");
        TL.m338new(str4, sb4.toString());
        if (j != 200) {
            String str5 = f162char;
            StringBuilder sb5 = new StringBuilder("error (");
            sb5.append(j);
            sb5.append(") status on request to ");
            sb5.append(this.f167new.m118for());
            TL.m325do(str5, sb5.toString());
        }
        if (j != 200 || this.f165if == L.f172new || this.f165if == L.f171int) {
            TL.m338new(f162char, "consuming content");
            this.f167new.m120if();
        }
    }

    /* renamed from: do reason: not valid java name */
    public THMStatusCode m63do() {
        return this.f167new.f252if;
    }
}
