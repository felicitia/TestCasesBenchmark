package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.lib.models.Conversation3;
import kotlin.jvm.internal.p;

/* compiled from: ConvoListViewItem.kt */
public abstract class e {

    /* compiled from: ConvoListViewItem.kt */
    public static final class a extends e {
        private final Conversation3 a;

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
            if (kotlin.jvm.internal.p.a((java.lang.Object) r1.a, (java.lang.Object) ((com.etsy.android.ui.convos.convolistredesign.e.a) r2).a) != false) goto L_0x0015;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r2) {
            /*
                r1 = this;
                if (r1 == r2) goto L_0x0015
                boolean r0 = r2 instanceof com.etsy.android.ui.convos.convolistredesign.e.a
                if (r0 == 0) goto L_0x0013
                com.etsy.android.ui.convos.convolistredesign.e$a r2 = (com.etsy.android.ui.convos.convolistredesign.e.a) r2
                com.etsy.android.lib.models.Conversation3 r0 = r1.a
                com.etsy.android.lib.models.Conversation3 r2 = r2.a
                boolean r2 = kotlin.jvm.internal.p.a(r0, r2)
                if (r2 == 0) goto L_0x0013
                goto L_0x0015
            L_0x0013:
                r2 = 0
                return r2
            L_0x0015:
                r2 = 1
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convolistredesign.e.a.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            Conversation3 conversation3 = this.a;
            if (conversation3 != null) {
                return conversation3.hashCode();
            }
            return 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ConvoItem(conversation=");
            sb.append(this.a);
            sb.append(")");
            return sb.toString();
        }

        public a(Conversation3 conversation3) {
            p.b(conversation3, "conversation");
            super(null);
            this.a = conversation3;
        }

        public final Conversation3 a() {
            return this.a;
        }
    }

    /* compiled from: ConvoListViewItem.kt */
    public static final class b extends e {
        private final Conversation3 a;
        private final String b;

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
            if (kotlin.jvm.internal.p.a((java.lang.Object) r2.b, (java.lang.Object) r3.b) != false) goto L_0x001f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x001f
                boolean r0 = r3 instanceof com.etsy.android.ui.convos.convolistredesign.e.b
                if (r0 == 0) goto L_0x001d
                com.etsy.android.ui.convos.convolistredesign.e$b r3 = (com.etsy.android.ui.convos.convolistredesign.e.b) r3
                com.etsy.android.lib.models.Conversation3 r0 = r2.a
                com.etsy.android.lib.models.Conversation3 r1 = r3.a
                boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
                if (r0 == 0) goto L_0x001d
                java.lang.String r0 = r2.b
                java.lang.String r3 = r3.b
                boolean r3 = kotlin.jvm.internal.p.a(r0, r3)
                if (r3 == 0) goto L_0x001d
                goto L_0x001f
            L_0x001d:
                r3 = 0
                return r3
            L_0x001f:
                r3 = 1
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convolistredesign.e.b.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            Conversation3 conversation3 = this.a;
            int i = 0;
            int hashCode = (conversation3 != null ? conversation3.hashCode() : 0) * 31;
            String str = this.b;
            if (str != null) {
                i = str.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ConvoItemWithDateHeader(conversation=");
            sb.append(this.a);
            sb.append(", displayDate=");
            sb.append(this.b);
            sb.append(")");
            return sb.toString();
        }

        public final Conversation3 a() {
            return this.a;
        }

        public b(Conversation3 conversation3, String str) {
            p.b(conversation3, "conversation");
            p.b(str, "displayDate");
            super(null);
            this.a = conversation3;
            this.b = str;
        }

        public final String b() {
            return this.b;
        }
    }

    private e() {
    }

    public /* synthetic */ e(o oVar) {
        this();
    }
}
