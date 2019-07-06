package com.etsy.android.ui.search.v2.impressions;

import com.android.volley.toolbox.BasicNetwork;
import com.etsy.android.lib.config.c;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.HttpUtil;
import com.etsy.android.lib.requests.apiv3.GzippedRequestBody;
import io.reactivex.v;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Pair;
import kotlin.collections.af;
import kotlin.collections.o;
import kotlin.f;
import kotlin.jvm.internal.p;
import kotlin.text.m;
import okhttp3.ab;
import okhttp3.u;
import okhttp3.z;

/* compiled from: SearchImpressionRepository.kt */
public final class d {
    public static final a a = new a(null);
    private final c b;
    private final l c;
    private final a d;
    private final f e;

    /* compiled from: SearchImpressionRepository.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    /* compiled from: SearchImpressionRepository.kt */
    static final class b<V> implements Callable<T> {
        final /* synthetic */ d a;
        final /* synthetic */ c b;

        b(d dVar, c cVar) {
            this.a = dVar;
            this.b = cVar;
        }

        public /* synthetic */ Object call() {
            return Long.valueOf(a());
        }

        public final long a() {
            return this.a.b(this.b);
        }
    }

    public d(c cVar, l lVar, a aVar, f fVar) {
        p.b(cVar, "configMap");
        p.b(lVar, "logCat");
        p.b(aVar, "searchImpressionDao");
        p.b(fVar, "searchImpressionsEndpoint");
        this.b = cVar;
        this.c = lVar;
        this.d = aVar;
        this.e = fVar;
    }

    /* access modifiers changed from: private */
    public final long b(c cVar) {
        this.c.a("insert()");
        return this.d.a(cVar);
    }

    public final v<Long> a(c cVar) {
        p.b(cVar, "searchImpression");
        v<Long> b2 = v.b((Callable<? extends T>) new b<Object>(this, cVar));
        p.a((Object) b2, "Single.fromCallable { insert(searchImpression) }");
        return b2;
    }

    public final void a() {
        this.c.a("uploadAll() - Starting");
        List a2 = this.d.a(1000);
        while (!a2.isEmpty()) {
            a(a2);
            this.d.a(a2);
            a2 = this.d.a(1000);
        }
        this.c.a("uploadAll() - Complete");
    }

    private final void a(List<c> list) {
        Pair pair;
        if (list.isEmpty()) {
            this.c.a("uploadBatch() - No search impressions to upload.");
            return;
        }
        l lVar = this.c;
        StringBuilder sb = new StringBuilder();
        sb.append("uploadBatch() - Attempting to upload ");
        sb.append(list.size());
        sb.append(" search impressions");
        lVar.a(sb.toString());
        String b2 = b(list);
        if (this.b.c(com.etsy.android.lib.config.b.cD)) {
            pair = b(b2);
        } else {
            pair = a(b2);
        }
        try {
            retrofit2.l lVar2 = (retrofit2.l) this.e.a((z) pair.getFirst(), (HashMap) pair.getSecond()).a();
            p.a((Object) lVar2, ResponseConstants.RESPONSE);
            if (lVar2.d()) {
                l lVar3 = this.c;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("uploadBatch() - Uploaded ");
                sb2.append(list.size());
                sb2.append(" search impressions");
                lVar3.a(sb2.toString());
            } else {
                l lVar4 = this.c;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("uploadBatch() - Failed to upload ");
                sb3.append(list.size());
                sb3.append(" search impressions: ");
                sb3.append("");
                sb3.append(lVar2.a());
                sb3.append(" - ");
                ab f = lVar2.f();
                sb3.append(f != null ? f.toString() : null);
                lVar4.d(sb3.toString());
            }
        } catch (Throwable th) {
            l lVar5 = this.c;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("uploadBatch() - Error uploading ");
            sb4.append(list.size());
            sb4.append(" search impressions");
            lVar5.a(sb4.toString(), th);
        }
    }

    private final String b(List<c> list) {
        Iterable<c> iterable = list;
        Collection arrayList = new ArrayList(o.a(iterable, 10));
        for (c a2 : iterable) {
            arrayList.add(a2.a());
        }
        Iterable<String> iterable2 = (List) arrayList;
        Collection arrayList2 = new ArrayList(o.a(iterable2, 10));
        for (String str : iterable2) {
            StringBuilder sb = new StringBuilder();
            sb.append("a.1-");
            sb.append(str);
            arrayList2.add(sb.toString());
        }
        String a3 = o.a((List) arrayList2, ",", null, null, 0, null, null, 62, null);
        Collection arrayList3 = new ArrayList(o.a(iterable, 10));
        for (c b2 : iterable) {
            arrayList3.add(b2.b());
        }
        String a4 = o.a((List) arrayList3, ",", null, null, 0, null, null, 62, null);
        Collection arrayList4 = new ArrayList(o.a(iterable, 10));
        for (c c2 : iterable) {
            arrayList4.add(c2.c());
        }
        String a5 = o.a((List) arrayList4, ",", null, null, 0, null, null, 62, null);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\n            {\n                \"display_locs\":\"");
        sb2.append(a3);
        sb2.append("\",\n                \"logging_keys\":\"");
        sb2.append(a4);
        sb2.append("\",\n                \"impressions_data\":\"");
        sb2.append(a5);
        sb2.append("\"\n            }\n            ");
        return m.a(sb2.toString());
    }

    private final Pair<z, HashMap<String, String>> a(String str) {
        return new Pair<>(z.a(u.a(HttpUtil.JSON_CONTENT_TYPE), str), new HashMap());
    }

    private final Pair<z, HashMap<String, String>> b(String str) {
        return new Pair<>(GzippedRequestBody.Companion.create(u.a(HttpUtil.JSON_CONTENT_TYPE), str), af.b(f.a(BasicNetwork.HEADER_CONTENT_ENCODING, BasicNetwork.ENCODING_GZIP)));
    }
}
