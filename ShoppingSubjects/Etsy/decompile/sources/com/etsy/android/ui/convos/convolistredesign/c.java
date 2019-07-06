package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.lib.models.Conversation3;
import java.util.List;
import kotlin.jvm.internal.p;

/* compiled from: ConversationListRepository.kt */
public abstract class c {

    /* compiled from: ConversationListRepository.kt */
    public static final class a extends c {
        private final List<Conversation3> a;
        private final int b;
        private final n c;

        public final List<Conversation3> a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public a(List<Conversation3> list, int i, n nVar) {
            p.b(list, "conversationList");
            p.b(nVar, "spec");
            super(null);
            this.a = list;
            this.b = i;
            this.c = nVar;
        }

        public final n c() {
            return this.c;
        }
    }

    /* compiled from: ConversationListRepository.kt */
    public static final class b extends c {
        private final List<Conversation3> a;
        private final int b;
        private final n c;

        public final List<Conversation3> a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public b(List<Conversation3> list, int i, n nVar) {
            p.b(list, "conversationList");
            p.b(nVar, "spec");
            super(null);
            this.a = list;
            this.b = i;
            this.c = nVar;
        }

        public final n c() {
            return this.c;
        }
    }

    private c() {
    }

    public /* synthetic */ c(o oVar) {
        this();
    }
}
