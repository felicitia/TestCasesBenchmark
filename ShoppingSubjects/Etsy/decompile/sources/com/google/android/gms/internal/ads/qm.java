package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.tp.b;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class qm<P> {
    private static final Charset a = Charset.forName("UTF-8");
    private ConcurrentMap<String, List<qn<P>>> b = new ConcurrentHashMap();
    private qn<P> c;

    public final qn<P> a() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public final qn<P> a(P p, b bVar) throws GeneralSecurityException {
        byte[] bArr;
        byte b2;
        ByteBuffer byteBuffer;
        switch (bVar.e()) {
            case LEGACY:
            case CRUNCHY:
                byteBuffer = ByteBuffer.allocate(5);
                b2 = 0;
                break;
            case TINK:
                byteBuffer = ByteBuffer.allocate(5);
                b2 = 1;
                break;
            case RAW:
                bArr = qc.a;
                break;
            default:
                throw new GeneralSecurityException("unknown output prefix type");
        }
        bArr = byteBuffer.put(b2).putInt(bVar.d()).array();
        qn<P> qnVar = new qn<>(p, bArr, bVar.c(), bVar.e());
        ArrayList arrayList = new ArrayList();
        arrayList.add(qnVar);
        String str = new String(qnVar.b(), a);
        List list = (List) this.b.put(str, Collections.unmodifiableList(arrayList));
        if (list != null) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(list);
            arrayList2.add(qnVar);
            this.b.put(str, Collections.unmodifiableList(arrayList2));
        }
        return qnVar;
    }

    /* access modifiers changed from: protected */
    public final void a(qn<P> qnVar) {
        this.c = qnVar;
    }
}
