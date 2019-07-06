package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.lib.c.e;
import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.ui.convos.convolistredesign.a.C0088a;
import com.etsy.android.ui.convos.convoredesign.f;
import io.reactivex.functions.g;
import io.reactivex.v;
import java.util.Comparator;
import kotlin.jvm.internal.p;

/* compiled from: ConversationListRepository.kt */
public final class b {
    /* access modifiers changed from: private */
    public final f a;
    private final e b;
    /* access modifiers changed from: private */
    public final com.etsy.android.lib.util.sharedprefs.b c;

    /* compiled from: ConversationListRepository.kt */
    static final class a<T, R> implements g<T, R> {
        final /* synthetic */ b a;
        final /* synthetic */ n b;

        /* renamed from: com.etsy.android.ui.convos.convolistredesign.b$a$a reason: collision with other inner class name */
        /* compiled from: Comparisons.kt */
        public static final class C0089a<T> implements Comparator<T> {
            public final int compare(T t, T t2) {
                return kotlin.a.a.a(Long.valueOf(((Conversation3) t2).getLastUpdateDateInMillis()), Long.valueOf(((Conversation3) t).getLastUpdateDateInMillis()));
            }
        }

        a(b bVar, n nVar) {
            this.a = bVar;
            this.b = nVar;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
            if (r1 != null) goto L_0x0032;
         */
        /* JADX WARNING: Removed duplicated region for block: B:10:0x0025  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0051 A[LOOP:0: B:14:0x004b->B:16:0x0051, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x00cc  */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.etsy.android.ui.convos.convolistredesign.c apply(retrofit2.adapter.rxjava2.d<java.util.List<com.etsy.android.lib.models.Conversation3>> r8) {
            /*
                r7 = this;
                java.lang.String r0 = "it"
                kotlin.jvm.internal.p.b(r8, r0)
                retrofit2.l r0 = r8.a()
                if (r0 == 0) goto L_0x001e
                okhttp3.s r0 = r0.c()
                if (r0 == 0) goto L_0x001e
                java.lang.String r1 = "x-total-count"
                java.lang.String r0 = r0.a(r1)
                if (r0 == 0) goto L_0x001e
                int r0 = java.lang.Integer.parseInt(r0)
                goto L_0x001f
            L_0x001e:
                r0 = 0
            L_0x001f:
                retrofit2.l r1 = r8.a()
                if (r1 == 0) goto L_0x002e
                java.lang.Object r1 = r1.e()
                java.util.List r1 = (java.util.List) r1
                if (r1 == 0) goto L_0x002e
                goto L_0x0032
            L_0x002e:
                java.util.List r1 = kotlin.collections.o.a()
            L_0x0032:
                java.lang.String r2 = "responseList"
                kotlin.jvm.internal.p.a(r1, r2)
                r2 = r1
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.ArrayList r3 = new java.util.ArrayList
                r4 = 10
                int r5 = kotlin.collections.o.a(r2, r4)
                r3.<init>(r5)
                java.util.Collection r3 = (java.util.Collection) r3
                java.util.Iterator r2 = r2.iterator()
            L_0x004b:
                boolean r5 = r2.hasNext()
                if (r5 == 0) goto L_0x0061
                java.lang.Object r5 = r2.next()
                com.etsy.android.lib.models.Conversation3 r5 = (com.etsy.android.lib.models.Conversation3) r5
                com.etsy.android.ui.convos.convolistredesign.b r6 = r7.a
                com.etsy.android.ui.convos.convoredesign.h r5 = r6.a(r5)
                r3.add(r5)
                goto L_0x004b
            L_0x0061:
                java.util.List r3 = (java.util.List) r3
                com.etsy.android.ui.convos.convolistredesign.b r2 = r7.a
                com.etsy.android.ui.convos.convoredesign.f r2 = r2.a
                r2.a(r3)
                boolean r8 = r8.b()
                if (r8 == 0) goto L_0x00cc
                com.etsy.android.ui.convos.convolistredesign.b r8 = r7.a
                com.etsy.android.ui.convos.convoredesign.f r8 = r8.a
                com.etsy.android.ui.convos.convolistredesign.b r0 = r7.a
                com.etsy.android.lib.util.sharedprefs.b r0 = r0.c
                com.etsy.android.lib.models.datatypes.EtsyId r0 = r0.a()
                long r0 = r0.getIdAsLong()
                java.util.List r8 = r8.a(r0)
                java.lang.Iterable r8 = (java.lang.Iterable) r8
                java.util.ArrayList r0 = new java.util.ArrayList
                int r1 = kotlin.collections.o.a(r8, r4)
                r0.<init>(r1)
                java.util.Collection r0 = (java.util.Collection) r0
                java.util.Iterator r8 = r8.iterator()
            L_0x009b:
                boolean r1 = r8.hasNext()
                if (r1 == 0) goto L_0x00af
                java.lang.Object r1 = r8.next()
                com.etsy.android.ui.convos.convoredesign.h r1 = (com.etsy.android.ui.convos.convoredesign.h) r1
                com.etsy.android.lib.models.Conversation3 r1 = r1.a()
                r0.add(r1)
                goto L_0x009b
            L_0x00af:
                java.util.List r0 = (java.util.List) r0
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                com.etsy.android.ui.convos.convolistredesign.b$a$a r8 = new com.etsy.android.ui.convos.convolistredesign.b$a$a
                r8.<init>()
                java.util.Comparator r8 = (java.util.Comparator) r8
                java.util.List r8 = kotlin.collections.o.a(r0, r8)
                com.etsy.android.ui.convos.convolistredesign.c$a r0 = new com.etsy.android.ui.convos.convolistredesign.c$a
                int r1 = r8.size()
                com.etsy.android.ui.convos.convolistredesign.n r2 = r7.b
                r0.<init>(r8, r1, r2)
                com.etsy.android.ui.convos.convolistredesign.c r0 = (com.etsy.android.ui.convos.convolistredesign.c) r0
                return r0
            L_0x00cc:
                com.etsy.android.ui.convos.convolistredesign.c$b r8 = new com.etsy.android.ui.convos.convolistredesign.c$b
                com.etsy.android.ui.convos.convolistredesign.n r2 = r7.b
                r8.<init>(r1, r0, r2)
                com.etsy.android.ui.convos.convolistredesign.c r8 = (com.etsy.android.ui.convos.convolistredesign.c) r8
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convolistredesign.b.a.apply(retrofit2.adapter.rxjava2.d):com.etsy.android.ui.convos.convolistredesign.c");
        }
    }

    public b(f fVar, e eVar, com.etsy.android.lib.util.sharedprefs.b bVar) {
        p.b(fVar, "convoDao");
        p.b(eVar, "moshiRetrofit");
        p.b(bVar, "sharedPreferencesProvider");
        this.a = fVar;
        this.b = eVar;
        this.c = bVar;
    }

    public final v<c> a(n nVar) {
        p.b(nVar, "spec");
        v<c> b2 = C0088a.a((a) this.b.a().a(a.class), nVar.c(), nVar.b(), false, 4, null).b((g<? super T, ? extends R>) new a<Object,Object>(this, nVar));
        p.a((Object) b2, "moshiRetrofit.v3moshiRet…       \n                }");
        return b2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006b, code lost:
        if (r9 != null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007c, code lost:
        if (r9 != null) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0037, code lost:
        if (r9 != null) goto L_0x003c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.etsy.android.ui.convos.convoredesign.h a(com.etsy.android.lib.models.Conversation3 r22) {
        /*
            r21 = this;
            java.lang.String r0 = "$receiver"
            r1 = r22
            kotlin.jvm.internal.p.b(r1, r0)
            com.etsy.android.ui.convos.convoredesign.h r0 = new com.etsy.android.ui.convos.convoredesign.h
            long r2 = r22.getConversationId()
            r15 = r21
            com.etsy.android.lib.util.sharedprefs.b r4 = r15.c
            com.etsy.android.lib.models.datatypes.EtsyId r4 = r4.a()
            long r4 = r4.getIdAsLong()
            int r6 = r22.getMessageCount()
            boolean r7 = r22.getRead()
            boolean r8 = r22.getHasAttachments()
            boolean r19 = r22.isCustomShop()
            java.lang.String r10 = r22.getLastMessage()
            com.etsy.android.lib.models.ConvoUser r9 = r22.getOtherUser()
            if (r9 == 0) goto L_0x003a
            java.lang.String r9 = r9.getAvatarUrl()
            if (r9 == 0) goto L_0x003a
            goto L_0x003c
        L_0x003a:
            java.lang.String r9 = ""
        L_0x003c:
            r17 = r9
            com.etsy.android.lib.models.ConvoUser r9 = r22.getOtherUser()
            if (r9 == 0) goto L_0x004f
            com.etsy.android.lib.models.datatypes.EtsyId r9 = r9.getUserId()
            if (r9 == 0) goto L_0x004f
            long r11 = r9.getIdAsLong()
            goto L_0x0051
        L_0x004f:
            r11 = 0
        L_0x0051:
            r13 = r11
            com.etsy.android.lib.models.ConvoUser r9 = r22.getOtherUser()
            if (r9 == 0) goto L_0x005f
            boolean r9 = r9.isGuest()
        L_0x005c:
            r18 = r9
            goto L_0x0061
        L_0x005f:
            r9 = 0
            goto L_0x005c
        L_0x0061:
            com.etsy.android.lib.models.ConvoUser r9 = r22.getOtherUser()
            if (r9 == 0) goto L_0x006e
            java.lang.String r9 = r9.getDisplayName()
            if (r9 == 0) goto L_0x006e
            goto L_0x0070
        L_0x006e:
            java.lang.String r9 = ""
        L_0x0070:
            r16 = r9
            com.etsy.android.lib.models.ConvoUser r9 = r22.getOtherUser()
            if (r9 == 0) goto L_0x007f
            java.lang.String r9 = r9.getUsername()
            if (r9 == 0) goto L_0x007f
            goto L_0x0081
        L_0x007f:
            java.lang.String r9 = ""
        L_0x0081:
            r20 = r9
            java.lang.String r9 = r22.getTitle()
            long r11 = r22.getLastUpdateDateInMillis()
            r1 = r0
            r15 = r20
            r1.<init>(r2, r4, r6, r7, r8, r9, r10, r11, r13, r15, r16, r17, r18, r19)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convolistredesign.b.a(com.etsy.android.lib.models.Conversation3):com.etsy.android.ui.convos.convoredesign.h");
    }
}
