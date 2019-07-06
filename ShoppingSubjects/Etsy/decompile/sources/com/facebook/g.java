package com.facebook;

import android.os.Handler;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: GraphRequestBatch */
public class g extends AbstractList<GraphRequest> {
    private static AtomicInteger a = new AtomicInteger();
    private Handler b;
    private List<GraphRequest> c;
    private int d;
    private final String e;
    private List<a> f;
    private String g;

    /* compiled from: GraphRequestBatch */
    public interface a {
        void a(g gVar);
    }

    /* compiled from: GraphRequestBatch */
    public interface b extends a {
        void a(g gVar, long j, long j2);
    }

    public g() {
        this.c = new ArrayList();
        this.d = 0;
        this.e = Integer.valueOf(a.incrementAndGet()).toString();
        this.f = new ArrayList();
        this.c = new ArrayList();
    }

    public g(Collection<GraphRequest> collection) {
        this.c = new ArrayList();
        this.d = 0;
        this.e = Integer.valueOf(a.incrementAndGet()).toString();
        this.f = new ArrayList();
        this.c = new ArrayList(collection);
    }

    public g(GraphRequest... graphRequestArr) {
        this.c = new ArrayList();
        this.d = 0;
        this.e = Integer.valueOf(a.incrementAndGet()).toString();
        this.f = new ArrayList();
        this.c = Arrays.asList(graphRequestArr);
    }

    public int a() {
        return this.d;
    }

    public void a(a aVar) {
        if (!this.f.contains(aVar)) {
            this.f.add(aVar);
        }
    }

    /* renamed from: a */
    public final boolean add(GraphRequest graphRequest) {
        return this.c.add(graphRequest);
    }

    /* renamed from: a */
    public final void add(int i, GraphRequest graphRequest) {
        this.c.add(i, graphRequest);
    }

    public final void clear() {
        this.c.clear();
    }

    /* renamed from: a */
    public final GraphRequest get(int i) {
        return (GraphRequest) this.c.get(i);
    }

    /* renamed from: b */
    public final GraphRequest remove(int i) {
        return (GraphRequest) this.c.remove(i);
    }

    /* renamed from: b */
    public final GraphRequest set(int i, GraphRequest graphRequest) {
        return (GraphRequest) this.c.set(i, graphRequest);
    }

    public final int size() {
        return this.c.size();
    }

    /* access modifiers changed from: 0000 */
    public final String b() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public final Handler c() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final void a(Handler handler) {
        this.b = handler;
    }

    /* access modifiers changed from: 0000 */
    public final List<GraphRequest> d() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final List<a> e() {
        return this.f;
    }

    public final String f() {
        return this.g;
    }

    public final List<GraphResponse> g() {
        return i();
    }

    public final GraphRequestAsyncTask h() {
        return j();
    }

    /* access modifiers changed from: 0000 */
    public List<GraphResponse> i() {
        return GraphRequest.b(this);
    }

    /* access modifiers changed from: 0000 */
    public GraphRequestAsyncTask j() {
        return GraphRequest.c(this);
    }
}
