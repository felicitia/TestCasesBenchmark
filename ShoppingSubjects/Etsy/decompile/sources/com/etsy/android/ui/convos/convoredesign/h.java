package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.lib.models.ConvoUser;
import kotlin.jvm.internal.p;

/* compiled from: ConvoDbModel.kt */
public final class h {
    private final long a;
    private final long b;
    private final int c;
    private final boolean d;
    private final boolean e;
    private final String f;
    private final String g;
    private final long h;
    private final long i;
    private final String j;
    private final String k;
    private final String l;
    private final boolean m;
    private final boolean n;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof h) {
            h hVar = (h) obj;
            if (this.a == hVar.a) {
                if (this.b == hVar.b) {
                    if (this.c == hVar.c) {
                        if (this.d == hVar.d) {
                            if ((this.e == hVar.e) && p.a((Object) this.f, (Object) hVar.f) && p.a((Object) this.g, (Object) hVar.g)) {
                                if (this.h == hVar.h) {
                                    if ((this.i == hVar.i) && p.a((Object) this.j, (Object) hVar.j) && p.a((Object) this.k, (Object) hVar.k) && p.a((Object) this.l, (Object) hVar.l)) {
                                        if (this.m == hVar.m) {
                                            if (this.n == hVar.n) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        long j2 = this.a;
        int i2 = ((int) (j2 ^ (j2 >>> 32))) * 31;
        long j3 = this.b;
        int i3 = (((i2 + ((int) (j3 ^ (j3 >>> 32)))) * 31) + this.c) * 31;
        int i4 = this.d;
        if (i4 != 0) {
            i4 = 1;
        }
        int i5 = (i3 + i4) * 31;
        int i6 = this.e;
        if (i6 != 0) {
            i6 = 1;
        }
        int i7 = (i5 + i6) * 31;
        String str = this.f;
        int i8 = 0;
        int hashCode = (i7 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.g;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        long j4 = this.h;
        int i9 = (hashCode2 + ((int) (j4 ^ (j4 >>> 32)))) * 31;
        long j5 = this.i;
        int i10 = (i9 + ((int) (j5 ^ (j5 >>> 32)))) * 31;
        String str3 = this.j;
        int hashCode3 = (i10 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.k;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.l;
        if (str5 != null) {
            i8 = str5.hashCode();
        }
        int i11 = (hashCode4 + i8) * 31;
        int i12 = this.m;
        if (i12 != 0) {
            i12 = 1;
        }
        int i13 = (i11 + i12) * 31;
        int i14 = this.n;
        if (i14 != 0) {
            i14 = 1;
        }
        return i13 + i14;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConvoDbModel(conversationId=");
        sb.append(this.a);
        sb.append(", userId=");
        sb.append(this.b);
        sb.append(", messageCount=");
        sb.append(this.c);
        sb.append(", isRead=");
        sb.append(this.d);
        sb.append(", hasAttachment=");
        sb.append(this.e);
        sb.append(", title=");
        sb.append(this.f);
        sb.append(", lastMessage=");
        sb.append(this.g);
        sb.append(", lastUpdated=");
        sb.append(this.h);
        sb.append(", otherUserId=");
        sb.append(this.i);
        sb.append(", otherUserNameUser=");
        sb.append(this.j);
        sb.append(", otherUserNameFull=");
        sb.append(this.k);
        sb.append(", otherUserAvatarUrl=");
        sb.append(this.l);
        sb.append(", otherUserIsGuest=");
        sb.append(this.m);
        sb.append(", isCustomShop=");
        sb.append(this.n);
        sb.append(")");
        return sb.toString();
    }

    public h(long j2, long j3, int i2, boolean z, boolean z2, String str, String str2, long j4, long j5, String str3, String str4, String str5, boolean z3, boolean z4) {
        String str6 = str;
        String str7 = str2;
        String str8 = str3;
        String str9 = str4;
        String str10 = str5;
        p.b(str6, "title");
        p.b(str7, "lastMessage");
        p.b(str8, "otherUserNameUser");
        p.b(str9, "otherUserNameFull");
        p.b(str10, "otherUserAvatarUrl");
        this.a = j2;
        this.b = j3;
        this.c = i2;
        this.d = z;
        this.e = z2;
        this.f = str6;
        this.g = str7;
        this.h = j4;
        this.i = j5;
        this.j = str8;
        this.k = str9;
        this.l = str10;
        this.m = z3;
        this.n = z4;
    }

    public final long b() {
        return this.a;
    }

    public final long c() {
        return this.b;
    }

    public final int d() {
        return this.c;
    }

    public final boolean e() {
        return this.d;
    }

    public final boolean f() {
        return this.e;
    }

    public final String g() {
        return this.f;
    }

    public final String h() {
        return this.g;
    }

    public final long i() {
        return this.h;
    }

    public final long j() {
        return this.i;
    }

    public final String k() {
        return this.j;
    }

    public final String l() {
        return this.k;
    }

    public final String m() {
        return this.l;
    }

    public final boolean n() {
        return this.m;
    }

    public final boolean o() {
        return this.n;
    }

    public final Conversation3 a() {
        long j2 = this.i;
        ConvoUser convoUser = new ConvoUser(this.k, this.l, this.j, j2, this.m);
        long j3 = this.a;
        int i2 = this.c;
        boolean z = this.d;
        boolean z2 = this.e;
        boolean z3 = this.n;
        long j4 = this.h / ((long) 1000);
        Conversation3 conversation3 = new Conversation3(j3, i2, this.f, z, convoUser, j4, this.g, null, null, 0, 0, 0, z2, z3, 0, 0, null, 118656, null);
        return conversation3;
    }
}
