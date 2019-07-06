package com.threatmetrix.TrustDefender.internal;

import com.threatmetrix.TrustDefender.THMStatusCode;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;

abstract class JR {

    /* renamed from: do reason: not valid java name */
    private List<String> f250do = null;

    /* renamed from: for reason: not valid java name */
    HF f251for;

    /* renamed from: if reason: not valid java name */
    THMStatusCode f252if = THMStatusCode.THM_NotYet;

    /* renamed from: int reason: not valid java name */
    Y f253int;

    /* renamed from: new reason: not valid java name */
    Map<String, String> f254new = new HashMap();

    /* access modifiers changed from: 0000 */
    /* renamed from: case reason: not valid java name */
    public abstract int m114case();

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public abstract InputStream m116do() throws IOException;

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public abstract long m117for(String str, X x);

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public abstract String m118for();

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public abstract long m119if(String str);

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public abstract void m120if();

    /* access modifiers changed from: 0000 */
    /* renamed from: int reason: not valid java name */
    public abstract String m122int();

    /* access modifiers changed from: 0000 */
    /* renamed from: new reason: not valid java name */
    public abstract void m123new();

    JR(HF hf, Y y, List<String> list) {
        this.f253int = y;
        this.f251for = hf;
        this.f250do = list;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public final THMStatusCode m115do(boolean z, List<Certificate> list, String str) {
        String str2;
        List<String> list2 = this.f250do;
        if (list2 == null || list2.isEmpty()) {
            return THMStatusCode.THM_OK;
        }
        if (!z) {
            TL.m332if(str, "Handshake failed");
            return THMStatusCode.THM_Certificate_Mismatch;
        } else if (list == null || list.size() == 0) {
            TL.m332if(str, "Can't retrieve certificate");
            return THMStatusCode.THM_Certificate_Mismatch;
        } else {
            Certificate certificate = (Certificate) list.get(0);
            try {
                if (((String) list2.get(0)).length() == 40) {
                    str2 = NK.m214if(certificate.getEncoded());
                } else {
                    str2 = NK.m224new(certificate.getEncoded());
                }
            } catch (CertificateEncodingException e) {
                TL.m334if(str, "Can't verify certificate {}", e.toString());
                this.f252if = THMStatusCode.THM_Certificate_Mismatch;
                str2 = null;
            }
            if (NK.m203byte(str2) && list2.contains(str2.toLowerCase(Locale.US))) {
                return THMStatusCode.THM_OK;
            }
            TL.m332if(str, "Invalid certificate, host is using ".concat(String.valueOf(str2)));
            return THMStatusCode.THM_Certificate_Mismatch;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final void m121if(Exception exc) {
        if (exc.getCause() instanceof CertificateException) {
            this.f252if = THMStatusCode.THM_HostVerification_Error;
        } else if (exc instanceof SSLPeerUnverifiedException) {
            this.f252if = THMStatusCode.THM_HostVerification_Error;
        } else if (exc instanceof UnknownHostException) {
            this.f252if = THMStatusCode.THM_HostNotFound_Error;
        } else if (exc instanceof SocketTimeoutException) {
            this.f252if = THMStatusCode.THM_NetworkTimeout_Error;
        } else {
            if (this.f252if == THMStatusCode.THM_NotYet) {
                this.f252if = THMStatusCode.THM_Connection_Error;
            }
        }
    }
}
