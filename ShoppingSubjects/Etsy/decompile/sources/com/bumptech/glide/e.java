package com.bumptech.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.bumptech.glide.e.a;
import com.bumptech.glide.g.h;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.f;
import com.bumptech.glide.manager.g;
import com.bumptech.glide.manager.k;
import com.bumptech.glide.request.GenericRequest;
import com.bumptech.glide.request.a.d;
import com.bumptech.glide.request.c;
import com.bumptech.glide.request.target.i;

/* compiled from: GenericRequestBuilder */
public class e<ModelType, DataType, ResourceType, TranscodeType> implements Cloneable {
    private boolean A;
    private Drawable B;
    private int C;
    protected final Class<ModelType> a;
    protected final Context b;
    protected final Glide c;
    protected final Class<TranscodeType> d;
    protected final k e;
    protected final g f;
    private a<ModelType, DataType, ResourceType, TranscodeType> g;
    private ModelType h;
    private b i;
    private boolean j;
    private int k;
    private int l;
    private c<? super ModelType, TranscodeType> m;
    private Float n;
    private e<?, ?, ?, TranscodeType> o;
    private Float p;
    private Drawable q;
    private Drawable r;
    private Priority s;
    private boolean t;
    private d<TranscodeType> u;
    private int v;
    private int w;
    private DiskCacheStrategy x;
    private f<ResourceType> y;
    private boolean z;

    /* renamed from: com.bumptech.glide.e$1 reason: invalid class name */
    /* compiled from: GenericRequestBuilder */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                android.widget.ImageView$ScaleType[] r0 = android.widget.ImageView.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001f }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x002a }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_START     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_END     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.e.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: 0000 */
    public void d() {
    }

    /* access modifiers changed from: 0000 */
    public void e() {
    }

    e(com.bumptech.glide.e.f<ModelType, DataType, ResourceType, TranscodeType> fVar, Class<TranscodeType> cls, e<ModelType, ?, ?, ?> eVar) {
        this(eVar.b, eVar.a, fVar, cls, eVar.c, eVar.e, eVar.f);
        this.h = eVar.h;
        this.j = eVar.j;
        this.i = eVar.i;
        this.x = eVar.x;
        this.t = eVar.t;
    }

    e(Context context, Class<ModelType> cls, com.bumptech.glide.e.f<ModelType, DataType, ResourceType, TranscodeType> fVar, Class<TranscodeType> cls2, Glide glide, k kVar, g gVar) {
        this.i = com.bumptech.glide.f.a.a();
        this.p = Float.valueOf(1.0f);
        a<ModelType, DataType, ResourceType, TranscodeType> aVar = null;
        this.s = null;
        this.t = true;
        this.u = com.bumptech.glide.request.a.e.a();
        this.v = -1;
        this.w = -1;
        this.x = DiskCacheStrategy.RESULT;
        this.y = com.bumptech.glide.load.resource.d.b();
        this.b = context;
        this.a = cls;
        this.d = cls2;
        this.c = glide;
        this.e = kVar;
        this.f = gVar;
        if (fVar != null) {
            aVar = new a<>(fVar);
        }
        this.g = aVar;
        if (context == null) {
            throw new NullPointerException("Context can't be null");
        } else if (cls != null && fVar == null) {
            throw new NullPointerException("LoadProvider must not be null");
        }
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.load.d<DataType, ResourceType> dVar) {
        if (this.g != null) {
            this.g.a(dVar);
        }
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.load.a<DataType> aVar) {
        if (this.g != null) {
            this.g.a(aVar);
        }
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(DiskCacheStrategy diskCacheStrategy) {
        this.x = diskCacheStrategy;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(f<ResourceType>... fVarArr) {
        this.z = true;
        if (fVarArr.length == 1) {
            this.y = fVarArr[0];
        } else {
            this.y = new com.bumptech.glide.load.c(fVarArr);
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public e<ModelType, DataType, ResourceType, TranscodeType> a(d<TranscodeType> dVar) {
        if (dVar == null) {
            throw new NullPointerException("Animation factory must not be null!");
        }
        this.u = dVar;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(int i2) {
        this.k = i2;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(boolean z2) {
        this.t = !z2;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(int i2, int i3) {
        if (!h.a(i2, i3)) {
            throw new IllegalArgumentException("Width and height must be Target#SIZE_ORIGINAL or > 0");
        }
        this.w = i2;
        this.v = i3;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Signature must not be null");
        }
        this.i = bVar;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(ModelType modeltype) {
        this.h = modeltype;
        this.j = true;
        return this;
    }

    /* renamed from: f */
    public e<ModelType, DataType, ResourceType, TranscodeType> clone() {
        try {
            e<ModelType, DataType, ResourceType, TranscodeType> eVar = (e) super.clone();
            eVar.g = this.g != null ? this.g.clone() : null;
            return eVar;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public <Y extends i<TranscodeType>> Y a(Y y2) {
        h.a();
        if (y2 == null) {
            throw new IllegalArgumentException("You must pass in a non null Target");
        } else if (!this.j) {
            throw new IllegalArgumentException("You must first set a model (try #load())");
        } else {
            com.bumptech.glide.request.a c2 = y2.c();
            if (c2 != null) {
                c2.d();
                this.e.b(c2);
                c2.a();
            }
            com.bumptech.glide.request.a b2 = b((i<TranscodeType>) y2);
            y2.a(b2);
            this.f.a(y2);
            this.e.a(b2);
            return y2;
        }
    }

    public i<TranscodeType> a(ImageView imageView) {
        h.a();
        if (imageView == null) {
            throw new IllegalArgumentException("You must pass in a non null View");
        }
        if (!this.z && imageView.getScaleType() != null) {
            switch (AnonymousClass1.a[imageView.getScaleType().ordinal()]) {
                case 1:
                    e();
                    break;
                case 2:
                case 3:
                case 4:
                    d();
                    break;
            }
        }
        return a((Y) this.c.a(imageView, this.d));
    }

    private Priority a() {
        if (this.s == Priority.LOW) {
            return Priority.NORMAL;
        }
        if (this.s == Priority.NORMAL) {
            return Priority.HIGH;
        }
        return Priority.IMMEDIATE;
    }

    private com.bumptech.glide.request.a b(i<TranscodeType> iVar) {
        if (this.s == null) {
            this.s = Priority.NORMAL;
        }
        return a(iVar, null);
    }

    private com.bumptech.glide.request.a a(i<TranscodeType> iVar, com.bumptech.glide.request.e eVar) {
        if (this.o != null) {
            if (this.A) {
                throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
            }
            if (this.o.u.equals(com.bumptech.glide.request.a.e.a())) {
                this.o.u = this.u;
            }
            if (this.o.s == null) {
                this.o.s = a();
            }
            if (h.a(this.w, this.v) && !h.a(this.o.w, this.o.v)) {
                this.o.b(this.w, this.v);
            }
            com.bumptech.glide.request.e eVar2 = new com.bumptech.glide.request.e(eVar);
            com.bumptech.glide.request.a a2 = a(iVar, this.p.floatValue(), this.s, eVar2);
            this.A = true;
            com.bumptech.glide.request.a a3 = this.o.a(iVar, eVar2);
            this.A = false;
            eVar2.a(a2, a3);
            return eVar2;
        } else if (this.n == null) {
            return a(iVar, this.p.floatValue(), this.s, eVar);
        } else {
            com.bumptech.glide.request.e eVar3 = new com.bumptech.glide.request.e(eVar);
            eVar3.a(a(iVar, this.p.floatValue(), this.s, eVar3), a(iVar, this.n.floatValue(), a(), eVar3));
            return eVar3;
        }
    }

    private com.bumptech.glide.request.a a(i<TranscodeType> iVar, float f2, Priority priority, com.bumptech.glide.request.b bVar) {
        a<ModelType, DataType, ResourceType, TranscodeType> aVar = this.g;
        ModelType modeltype = this.h;
        b bVar2 = this.i;
        Context context = this.b;
        Drawable drawable = this.q;
        int i2 = this.k;
        Drawable drawable2 = this.r;
        int i3 = this.l;
        Drawable drawable3 = this.B;
        int i4 = this.C;
        c<? super ModelType, TranscodeType> cVar = this.m;
        com.bumptech.glide.load.engine.b b2 = this.c.b();
        f<ResourceType> fVar = this.y;
        Class<TranscodeType> cls = this.d;
        boolean z2 = this.t;
        d<TranscodeType> dVar = this.u;
        f<ResourceType> fVar2 = fVar;
        return GenericRequest.a(aVar, modeltype, bVar2, context, priority, iVar, f2, drawable, i2, drawable2, i3, drawable3, i4, cVar, bVar, b2, fVar2, cls, z2, dVar, this.w, this.v, this.x);
    }
}
