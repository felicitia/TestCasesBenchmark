package com.threatmetrix.TrustDefender.internal;

import com.threatmetrix.TrustDefender.THMStatusCode;
import java.io.IOException;
import java.util.Map;

class C extends F {

    /* renamed from: try reason: not valid java name */
    private static final String f60try = TL.m331if(C.class);

    /* renamed from: if reason: not valid java name */
    public CG f61if = null;

    /* renamed from: int reason: not valid java name */
    private final Y f62int;

    public C(HF hf, String str, X x, Map<String, String> map, Y y) {
        super(hf, L.f169for, str, x, map, y);
        this.f62int = y;
    }

    public void run() {
        this.f61if = null;
        String str = f60try;
        StringBuilder sb = new StringBuilder("starting retrieval: ");
        sb.append(this.f163do);
        sb.append("?");
        sb.append(this.f164for.m375if());
        TL.m338new(str, sb.toString());
        super.run();
        if (this.f167new.m114case() == 200) {
            this.f61if = new CG();
            try {
                this.f61if.m31if(this.f167new.m116do());
            } catch (IOException e) {
                if (this.f62int.m443for()) {
                    TL.m338new(f60try, "IO Error, probably due to cancel");
                } else {
                    TL.m337int(f60try, "IO Error", e);
                }
            } catch (Throwable th) {
                this.f167new.m120if();
                throw th;
            }
            this.f167new.m120if();
            return;
        }
        String str2 = f60try;
        StringBuilder sb2 = new StringBuilder("Conf Connection failed with error code ");
        sb2.append(this.f167new.m114case());
        TL.m332if(str2, sb2.toString());
    }

    /* renamed from: do reason: not valid java name */
    public final THMStatusCode m22do() {
        String str;
        String str2 = f60try;
        StringBuilder sb = new StringBuilder("Conf connection status is ");
        sb.append(this.f167new.f252if);
        sb.append(" m_config is ");
        boolean z = false;
        if (this.f61if != null) {
            StringBuilder sb2 = new StringBuilder(" usable? ");
            CG cg = this.f61if;
            sb2.append(cg.f75if != null && !cg.f75if.isEmpty());
            str = sb2.toString();
        } else {
            str = "null";
        }
        sb.append(str);
        TL.m338new(str2, sb.toString());
        if (this.f167new.f252if != THMStatusCode.THM_OK) {
            return super.m63do();
        }
        if (this.f61if != null) {
            CG cg2 = this.f61if;
            if (cg2.f75if != null && !cg2.f75if.isEmpty()) {
                z = true;
            }
            if (z) {
                return THMStatusCode.THM_OK;
            }
        }
        return THMStatusCode.THM_ConfigurationError;
    }
}
