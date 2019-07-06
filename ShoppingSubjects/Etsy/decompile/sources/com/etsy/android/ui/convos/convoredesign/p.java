package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.extensions.i;
import com.etsy.android.lib.models.ConversationMessage2;
import com.etsy.android.ui.convos.convoredesign.aj.b;
import com.etsy.android.ui.convos.convoredesign.aj.c;
import com.etsy.android.ui.convos.convoredesign.aj.d;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.o;
import kotlin.f;
import kotlin.jvm.a.r;
import kotlin.text.m;

/* compiled from: ConvoMessageListItemOrganizers.kt */
public final class p {

    /* compiled from: Comparisons.kt */
    public static final class a<T> implements Comparator<T> {
        public final int compare(T t, T t2) {
            return kotlin.a.a.a(Long.valueOf(((ConversationMessage2) t).getCreationDateInMillis()), Long.valueOf(((ConversationMessage2) t2).getCreationDateInMillis()));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005a, code lost:
        if (r4 != null) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006a, code lost:
        if (r4 != null) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007a, code lost:
        if (r4 != null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008a, code lost:
        if (r12 != null) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0035, code lost:
        if (r1 != null) goto L_0x003a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.util.List<com.etsy.android.ui.convos.convoredesign.o> a(com.etsy.android.lib.models.convo.ConversationThread2 r12, long r13) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.p.b(r12, r0)
            java.util.List r0 = r12.getMessages()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            com.etsy.android.ui.convos.convoredesign.p$a r1 = new com.etsy.android.ui.convos.convoredesign.p$a
            r1.<init>()
            java.util.Comparator r1 = (java.util.Comparator) r1
            java.util.List r0 = kotlin.collections.o.a(r0, r1)
            java.lang.Object r1 = kotlin.collections.o.f(r0)
            com.etsy.android.lib.models.ConversationMessage2 r1 = (com.etsy.android.lib.models.ConversationMessage2) r1
            if (r1 == 0) goto L_0x014b
            com.etsy.android.ui.convos.convoredesign.o$b r2 = new com.etsy.android.ui.convos.convoredesign.o$b
            long r3 = r1.getCreationDateInMillis()
            r2.<init>(r3)
            com.etsy.android.lib.models.Conversation3 r1 = r12.getConversation()
            com.etsy.android.lib.models.ConvoUser r1 = r1.getOtherUser()
            if (r1 == 0) goto L_0x0038
            java.lang.String r1 = r1.getAvatarUrl()
            if (r1 == 0) goto L_0x0038
            goto L_0x003a
        L_0x0038:
            java.lang.String r1 = ""
        L_0x003a:
            com.etsy.android.lib.models.Conversation3 r12 = r12.getConversation()
            com.etsy.android.lib.models.convo.context.ConvoContext r12 = r12.getConversationContext()
            boolean r3 = r12 instanceof com.etsy.android.lib.models.convo.context.CustomOrderContext
            if (r3 == 0) goto L_0x0099
            com.etsy.android.lib.models.convo.context.CustomOrderContext r12 = (com.etsy.android.lib.models.convo.context.CustomOrderContext) r12
            com.etsy.android.lib.models.convo.CustomOrderCard r3 = r12.getOrderCard()
            if (r3 == 0) goto L_0x0099
            com.etsy.android.ui.convos.convoredesign.ak r3 = new com.etsy.android.ui.convos.convoredesign.ak
            com.etsy.android.lib.models.convo.CustomOrderCard r4 = r12.getOrderCard()
            if (r4 == 0) goto L_0x005d
            java.lang.String r4 = r4.getTitle()
            if (r4 == 0) goto L_0x005d
            goto L_0x005f
        L_0x005d:
            java.lang.String r4 = ""
        L_0x005f:
            r5 = r4
            com.etsy.android.lib.models.convo.CustomOrderCard r4 = r12.getOrderCard()
            if (r4 == 0) goto L_0x006d
            java.lang.String r4 = r4.getSubtitle()
            if (r4 == 0) goto L_0x006d
            goto L_0x006f
        L_0x006d:
            java.lang.String r4 = ""
        L_0x006f:
            r6 = r4
            com.etsy.android.lib.models.convo.CustomOrderCard r4 = r12.getOrderCard()
            if (r4 == 0) goto L_0x007d
            java.lang.String r4 = r4.getImageUrl()
            if (r4 == 0) goto L_0x007d
            goto L_0x007f
        L_0x007d:
            java.lang.String r4 = ""
        L_0x007f:
            r7 = r4
            com.etsy.android.lib.models.convo.CustomOrderCard r12 = r12.getOrderCard()
            if (r12 == 0) goto L_0x008d
            java.lang.String r12 = r12.getUrl()
            if (r12 == 0) goto L_0x008d
            goto L_0x008f
        L_0x008d:
            java.lang.String r12 = ""
        L_0x008f:
            r8 = r12
            r9 = 0
            r10 = 16
            r11 = 0
            r4 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            goto L_0x009a
        L_0x0099:
            r3 = 0
        L_0x009a:
            java.util.List r12 = a(r0)
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            r0 = 2
            r4 = 1
            java.util.List r12 = kotlin.collections.o.a(r12, r0, r4, r4)
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.List r2 = kotlin.collections.o.a(r2)
            java.util.Iterator r12 = r12.iterator()
        L_0x00b0:
            boolean r5 = r12.hasNext()
            if (r5 == 0) goto L_0x014a
            java.lang.Object r5 = r12.next()
            java.util.List r5 = (java.util.List) r5
            int r6 = r5.size()
            r7 = 0
            if (r6 != r0) goto L_0x011e
            java.lang.Object r6 = r5.get(r7)
            kotlin.Pair r6 = (kotlin.Pair) r6
            java.lang.Object r5 = r5.get(r4)
            kotlin.Pair r5 = (kotlin.Pair) r5
            java.lang.Object r7 = r6.component1()
            com.etsy.android.lib.models.ConversationMessage2 r7 = (com.etsy.android.lib.models.ConversationMessage2) r7
            java.lang.Object r6 = r6.component2()
            com.etsy.android.ui.convos.convoredesign.aj r6 = (com.etsy.android.ui.convos.convoredesign.aj) r6
            java.lang.Object r5 = r5.getFirst()
            com.etsy.android.lib.models.ConversationMessage2 r5 = (com.etsy.android.lib.models.ConversationMessage2) r5
            long r8 = r7.getUserId()
            kotlin.jvm.a.r r8 = a(r13, r8)
            com.etsy.android.ui.convos.convoredesign.ak r9 = a(r7, r3)
            java.lang.Object r6 = r8.invoke(r7, r6, r1, r9)
            com.etsy.android.ui.convos.convoredesign.o r6 = (com.etsy.android.ui.convos.convoredesign.o) r6
            long r7 = a(r7, r5)
            r9 = 10
            long r9 = com.etsy.android.extensions.i.a(r9)
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x0117
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.List r2 = kotlin.collections.o.a(r2, r6)
            java.util.Collection r2 = (java.util.Collection) r2
            com.etsy.android.ui.convos.convoredesign.o$b r6 = new com.etsy.android.ui.convos.convoredesign.o$b
            long r7 = r5.getCreationDateInMillis()
            r6.<init>(r7)
            java.util.List r2 = kotlin.collections.o.a(r2, r6)
            goto L_0x00b0
        L_0x0117:
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.List r2 = kotlin.collections.o.a(r2, r6)
            goto L_0x00b0
        L_0x011e:
            java.lang.Object r5 = r5.get(r7)
            kotlin.Pair r5 = (kotlin.Pair) r5
            java.lang.Object r6 = r5.component1()
            com.etsy.android.lib.models.ConversationMessage2 r6 = (com.etsy.android.lib.models.ConversationMessage2) r6
            java.lang.Object r5 = r5.component2()
            com.etsy.android.ui.convos.convoredesign.aj r5 = (com.etsy.android.ui.convos.convoredesign.aj) r5
            long r7 = r6.getUserId()
            kotlin.jvm.a.r r7 = a(r7, r13)
            com.etsy.android.ui.convos.convoredesign.ak r8 = a(r6, r3)
            java.lang.Object r5 = r7.invoke(r6, r5, r1, r8)
            com.etsy.android.ui.convos.convoredesign.o r5 = (com.etsy.android.ui.convos.convoredesign.o) r5
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.List r2 = kotlin.collections.o.a(r2, r5)
            goto L_0x00b0
        L_0x014a:
            return r2
        L_0x014b:
            java.util.List r12 = kotlin.collections.o.a()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convoredesign.p.a(com.etsy.android.lib.models.convo.ConversationThread2, long):java.util.List");
    }

    public static final ak a(ConversationMessage2 conversationMessage2, ak akVar) {
        kotlin.jvm.internal.p.b(conversationMessage2, "message");
        if (conversationMessage2.getMessageOrder() != 1 || akVar == null) {
            return a(a(conversationMessage2.getMessage()));
        }
        return akVar;
    }

    public static final q a(String str) {
        kotlin.jvm.internal.p.b(str, "message");
        List a2 = r.a(str);
        if (!a2.isEmpty()) {
            return (q) a2.get(0);
        }
        return null;
    }

    public static final ak a(q qVar) {
        if (!(qVar instanceof com.etsy.android.ui.convos.convoredesign.q.a)) {
            return null;
        }
        ak akVar = new ak(null, null, null, qVar.a(), ((com.etsy.android.ui.convos.convoredesign.q.a) qVar).b(), 7, null);
        return akVar;
    }

    private static final List<Pair<ConversationMessage2, aj>> a(List<ConversationMessage2> list) {
        Pair pair = new Pair(o.e((List) list), new b(((ConversationMessage2) o.e((List) list)).getMessage()));
        Iterable<ConversationMessage2> subList = list.subList(1, list.size());
        List<Pair<ConversationMessage2, aj>> a2 = o.a(pair);
        for (ConversationMessage2 a3 : subList) {
            Pair pair2 = (Pair) o.g((List) a2);
            Pair a4 = a(f.a((ConversationMessage2) pair2.component1(), (aj) pair2.component2()), a3);
            Pair pair3 = (Pair) a4.component1();
            a2 = o.a((Collection) o.a((Collection) o.a((List) a2, 1), (Object) pair3), (Object) (Pair) a4.component2());
        }
        return a2;
    }

    private static final Pair<Pair<ConversationMessage2, aj>, Pair<ConversationMessage2, aj>> a(Pair<ConversationMessage2, ? extends aj> pair, ConversationMessage2 conversationMessage2) {
        ConversationMessage2 conversationMessage22 = (ConversationMessage2) pair.component1();
        aj ajVar = (aj) pair.component2();
        if (a(conversationMessage2, conversationMessage22) > i.a(2) || !b(conversationMessage2, conversationMessage22)) {
            return f.a(pair, f.a(conversationMessage2, new b(conversationMessage2.getMessage())));
        }
        if (ajVar instanceof b) {
            ajVar = new d(conversationMessage22.getMessage());
        } else if (ajVar instanceof com.etsy.android.ui.convos.convoredesign.aj.a) {
            ajVar = new c(conversationMessage22.getMessage());
        }
        return f.a(f.a(conversationMessage22, ajVar), f.a(conversationMessage2, new com.etsy.android.ui.convos.convoredesign.aj.a(conversationMessage2.getMessage())));
    }

    private static final r<ConversationMessage2, aj, String, ak, o> a(long j, long j2) {
        if (j != j2) {
            return ConvoMessageListItemOrganizersKt$buildConvoListItemFactory$1.INSTANCE;
        }
        return ConvoMessageListItemOrganizersKt$buildConvoListItemFactory$2.INSTANCE;
    }

    public static final aj a(ak akVar, aj ajVar) {
        aj ajVar2;
        kotlin.jvm.internal.p.b(ajVar, "messageItem");
        if (akVar != null) {
            String d = akVar.d();
            if (d != null) {
                String a2 = m.a(ajVar.a(), d, "", false, 4, (Object) null);
                if (a2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                String obj = m.b(a2).toString();
                if (obj != null) {
                    if (ajVar instanceof d) {
                        ajVar2 = new d(obj);
                    } else if (ajVar instanceof c) {
                        ajVar2 = new c(obj);
                    } else if (ajVar instanceof com.etsy.android.ui.convos.convoredesign.aj.a) {
                        ajVar2 = new com.etsy.android.ui.convos.convoredesign.aj.a(obj);
                    } else if (ajVar instanceof b) {
                        ajVar2 = new b(obj);
                    } else {
                        throw new NoWhenBranchMatchedException();
                    }
                    return ajVar2;
                }
            }
        }
        return ajVar;
    }

    private static final long a(ConversationMessage2 conversationMessage2, ConversationMessage2 conversationMessage22) {
        return Math.abs(conversationMessage2.getCreationDateInMillis() - conversationMessage22.getCreationDateInMillis());
    }

    private static final boolean b(ConversationMessage2 conversationMessage2, ConversationMessage2 conversationMessage22) {
        return conversationMessage2.getUserId() == conversationMessage22.getUserId();
    }
}
