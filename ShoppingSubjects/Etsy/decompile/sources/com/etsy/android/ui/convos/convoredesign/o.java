package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.ImageInfo;
import java.util.List;
import kotlin.jvm.internal.p;

/* compiled from: ConvoListItem.kt */
public abstract class o {

    /* compiled from: ConvoListItem.kt */
    public static final class a extends o {
        private final aj a;
        private final List<ImageInfo> b;
        private final ak c;

        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
            if (kotlin.jvm.internal.p.a((java.lang.Object) r2.c, (java.lang.Object) r3.c) != false) goto L_0x0029;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x0029
                boolean r0 = r3 instanceof com.etsy.android.ui.convos.convoredesign.o.a
                if (r0 == 0) goto L_0x0027
                com.etsy.android.ui.convos.convoredesign.o$a r3 = (com.etsy.android.ui.convos.convoredesign.o.a) r3
                com.etsy.android.ui.convos.convoredesign.aj r0 = r2.a
                com.etsy.android.ui.convos.convoredesign.aj r1 = r3.a
                boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
                if (r0 == 0) goto L_0x0027
                java.util.List<com.etsy.android.lib.models.ImageInfo> r0 = r2.b
                java.util.List<com.etsy.android.lib.models.ImageInfo> r1 = r3.b
                boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
                if (r0 == 0) goto L_0x0027
                com.etsy.android.ui.convos.convoredesign.ak r0 = r2.c
                com.etsy.android.ui.convos.convoredesign.ak r3 = r3.c
                boolean r3 = kotlin.jvm.internal.p.a(r0, r3)
                if (r3 == 0) goto L_0x0027
                goto L_0x0029
            L_0x0027:
                r3 = 0
                return r3
            L_0x0029:
                r3 = 1
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convoredesign.o.a.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            aj ajVar = this.a;
            int i = 0;
            int hashCode = (ajVar != null ? ajVar.hashCode() : 0) * 31;
            List<ImageInfo> list = this.b;
            int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
            ak akVar = this.c;
            if (akVar != null) {
                i = akVar.hashCode();
            }
            return hashCode2 + i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("CurrentUserMessageListItem(groupedMessageItem=");
            sb.append(this.a);
            sb.append(", images=");
            sb.append(this.b);
            sb.append(", linkCard=");
            sb.append(this.c);
            sb.append(")");
            return sb.toString();
        }

        public final aj a() {
            return this.a;
        }

        public final List<ImageInfo> b() {
            return this.b;
        }

        public a(aj ajVar, List<ImageInfo> list, ak akVar) {
            p.b(ajVar, "groupedMessageItem");
            p.b(list, "images");
            super(null);
            this.a = ajVar;
            this.b = list;
            this.c = akVar;
        }

        public final ak c() {
            return this.c;
        }
    }

    /* compiled from: ConvoListItem.kt */
    public static final class b extends o {
        private final long a;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof b) {
                if (this.a == ((b) obj).a) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            long j = this.a;
            return (int) (j ^ (j >>> 32));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MessageDateItem(timeStamp=");
            sb.append(this.a);
            sb.append(")");
            return sb.toString();
        }

        public b(long j) {
            super(null);
            this.a = j;
        }

        public final long a() {
            return this.a;
        }
    }

    /* compiled from: ConvoListItem.kt */
    public static final class c extends o {
        private final aj a;
        private final List<ImageInfo> b;
        private final String c;
        private final ak d;

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
            if (kotlin.jvm.internal.p.a((java.lang.Object) r2.d, (java.lang.Object) r3.d) != false) goto L_0x0033;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x0033
                boolean r0 = r3 instanceof com.etsy.android.ui.convos.convoredesign.o.c
                if (r0 == 0) goto L_0x0031
                com.etsy.android.ui.convos.convoredesign.o$c r3 = (com.etsy.android.ui.convos.convoredesign.o.c) r3
                com.etsy.android.ui.convos.convoredesign.aj r0 = r2.a
                com.etsy.android.ui.convos.convoredesign.aj r1 = r3.a
                boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
                if (r0 == 0) goto L_0x0031
                java.util.List<com.etsy.android.lib.models.ImageInfo> r0 = r2.b
                java.util.List<com.etsy.android.lib.models.ImageInfo> r1 = r3.b
                boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
                if (r0 == 0) goto L_0x0031
                java.lang.String r0 = r2.c
                java.lang.String r1 = r3.c
                boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
                if (r0 == 0) goto L_0x0031
                com.etsy.android.ui.convos.convoredesign.ak r0 = r2.d
                com.etsy.android.ui.convos.convoredesign.ak r3 = r3.d
                boolean r3 = kotlin.jvm.internal.p.a(r0, r3)
                if (r3 == 0) goto L_0x0031
                goto L_0x0033
            L_0x0031:
                r3 = 0
                return r3
            L_0x0033:
                r3 = 1
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convoredesign.o.c.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            aj ajVar = this.a;
            int i = 0;
            int hashCode = (ajVar != null ? ajVar.hashCode() : 0) * 31;
            List<ImageInfo> list = this.b;
            int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
            String str = this.c;
            int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
            ak akVar = this.d;
            if (akVar != null) {
                i = akVar.hashCode();
            }
            return hashCode3 + i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("OtherUserMessageListItem(groupedMessageItem=");
            sb.append(this.a);
            sb.append(", images=");
            sb.append(this.b);
            sb.append(", avatarUrl=");
            sb.append(this.c);
            sb.append(", linkCard=");
            sb.append(this.d);
            sb.append(")");
            return sb.toString();
        }

        public final aj a() {
            return this.a;
        }

        public final List<ImageInfo> b() {
            return this.b;
        }

        public final String c() {
            return this.c;
        }

        public c(aj ajVar, List<ImageInfo> list, String str, ak akVar) {
            p.b(ajVar, "groupedMessageItem");
            p.b(list, "images");
            super(null);
            this.a = ajVar;
            this.b = list;
            this.c = str;
            this.d = akVar;
        }

        public final ak d() {
            return this.d;
        }
    }

    private o() {
    }

    public /* synthetic */ o(o oVar) {
        this();
    }
}
