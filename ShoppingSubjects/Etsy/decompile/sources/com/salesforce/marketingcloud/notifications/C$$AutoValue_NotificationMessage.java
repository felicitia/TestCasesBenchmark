package com.salesforce.marketingcloud.notifications;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.salesforce.marketingcloud.notifications.NotificationMessage.Sound;
import com.salesforce.marketingcloud.notifications.NotificationMessage.Trigger;
import com.salesforce.marketingcloud.notifications.NotificationMessage.Type;
import java.util.Map;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.salesforce.marketingcloud.notifications.$$AutoValue_NotificationMessage reason: invalid class name */
abstract class C$$AutoValue_NotificationMessage extends NotificationMessage {
    private final String b;
    private final String c;
    private final int d;
    private final String e;
    private final Sound f;
    private final String g;
    private final int h;
    private final String i;
    private final String j;
    private final Type k;
    private final Trigger l;
    private final String m;
    private final String n;
    private final String o;
    private final Map<String, String> p;
    private final String q;
    private final Bundle r;

    /* renamed from: com.salesforce.marketingcloud.notifications.$$AutoValue_NotificationMessage$a */
    static final class a extends com.salesforce.marketingcloud.notifications.NotificationMessage.a {
        private String a;
        private String b;
        private Integer c;
        private String d;
        private Sound e;
        private String f;
        private Integer g;
        private String h;
        private String i;
        private Type j;
        private Trigger k;
        private String l;
        private String m;
        private String n;
        private Map<String, String> o;
        private String p;
        private Bundle q;

        a() {
        }

        /* access modifiers changed from: 0000 */
        public Sound a() {
            if (this.e != null) {
                return this.e;
            }
            throw new IllegalStateException("Property \"sound\" has not been set");
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a a(int i2) {
            this.c = Integer.valueOf(i2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a a(@Nullable Bundle bundle) {
            this.q = bundle;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a a(Sound sound) {
            if (sound == null) {
                throw new NullPointerException("Null sound");
            }
            this.e = sound;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a a(Trigger trigger) {
            if (trigger == null) {
                throw new NullPointerException("Null trigger");
            }
            this.k = trigger;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a a(Type type) {
            if (type == null) {
                throw new NullPointerException("Null type");
            }
            this.j = type;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a a(String str) {
            if (str == null) {
                throw new NullPointerException("Null id");
            }
            this.a = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a a(Map<String, String> map) {
            if (map == null) {
                throw new NullPointerException("Null customKeys");
            }
            this.o = map;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a b(int i2) {
            this.g = Integer.valueOf(i2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a b(@Nullable String str) {
            this.b = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public String b() {
            return this.f;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a c(String str) {
            if (str == null) {
                throw new NullPointerException("Null alert");
            }
            this.d = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public NotificationMessage c() {
            String str = "";
            if (this.a == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" id");
                str = sb.toString();
            }
            if (this.c == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" notificationId");
                str = sb2.toString();
            }
            if (this.d == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" alert");
                str = sb3.toString();
            }
            if (this.e == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" sound");
                str = sb4.toString();
            }
            if (this.g == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str);
                sb5.append(" smallIconResId");
                str = sb5.toString();
            }
            if (this.j == null) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str);
                sb6.append(" type");
                str = sb6.toString();
            }
            if (this.k == null) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(str);
                sb7.append(" trigger");
                str = sb7.toString();
            }
            if (this.o == null) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append(str);
                sb8.append(" customKeys");
                str = sb8.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb9 = new StringBuilder();
                sb9.append("Missing required properties:");
                sb9.append(str);
                throw new IllegalStateException(sb9.toString());
            }
            String str2 = this.a;
            String str3 = this.b;
            int intValue = this.c.intValue();
            String str4 = this.d;
            Sound sound = this.e;
            String str5 = this.f;
            int intValue2 = this.g.intValue();
            String str6 = this.h;
            String str7 = this.i;
            Type type = this.j;
            Trigger trigger = this.k;
            String str8 = this.l;
            String str9 = this.m;
            String str10 = str9;
            String str11 = str10;
            b bVar = new b(str2, str3, intValue, str4, sound, str5, intValue2, str6, str7, type, trigger, str8, str11, this.n, this.o, this.p, this.q);
            return bVar;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a d(@Nullable String str) {
            this.f = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a e(@Nullable String str) {
            this.h = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a f(@Nullable String str) {
            this.i = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a g(@Nullable String str) {
            this.l = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a h(@Nullable String str) {
            this.m = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a i(@Nullable String str) {
            this.n = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.notifications.NotificationMessage.a j(@Nullable String str) {
            this.p = str;
            return this;
        }
    }

    C$$AutoValue_NotificationMessage(String str, @Nullable String str2, int i2, String str3, Sound sound, @Nullable String str4, int i3, @Nullable String str5, @Nullable String str6, Type type, Trigger trigger, @Nullable String str7, @Nullable String str8, @Nullable String str9, Map<String, String> map, @Nullable String str10, @Nullable Bundle bundle) {
        String str11 = str;
        String str12 = str3;
        Sound sound2 = sound;
        Type type2 = type;
        Trigger trigger2 = trigger;
        Map<String, String> map2 = map;
        if (str11 == null) {
            throw new NullPointerException("Null id");
        }
        this.b = str11;
        this.c = str2;
        this.d = i2;
        if (str12 == null) {
            throw new NullPointerException("Null alert");
        }
        this.e = str12;
        if (sound2 == null) {
            throw new NullPointerException("Null sound");
        }
        this.f = sound2;
        this.g = str4;
        this.h = i3;
        this.i = str5;
        this.j = str6;
        if (type2 == null) {
            throw new NullPointerException("Null type");
        }
        this.k = type2;
        if (trigger2 == null) {
            throw new NullPointerException("Null trigger");
        }
        this.l = trigger2;
        this.m = str7;
        this.n = str8;
        this.o = str9;
        if (map2 == null) {
            throw new NullPointerException("Null customKeys");
        }
        this.p = map2;
        this.q = str10;
        this.r = bundle;
    }

    @NonNull
    public String alert() {
        return this.e;
    }

    @Nullable
    public String custom() {
        return this.q;
    }

    @NonNull
    public Map<String, String> customKeys() {
        return this.p;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NotificationMessage)) {
            return false;
        }
        NotificationMessage notificationMessage = (NotificationMessage) obj;
        if (this.b.equals(notificationMessage.id()) && (this.c != null ? this.c.equals(notificationMessage.regionId()) : notificationMessage.regionId() == null) && this.d == notificationMessage.notificationId() && this.e.equals(notificationMessage.alert()) && this.f.equals(notificationMessage.sound()) && (this.g != null ? this.g.equals(notificationMessage.soundName()) : notificationMessage.soundName() == null) && this.h == notificationMessage.smallIconResId() && (this.i != null ? this.i.equals(notificationMessage.title()) : notificationMessage.title() == null) && (this.j != null ? this.j.equals(notificationMessage.subTitle()) : notificationMessage.subTitle() == null) && this.k.equals(notificationMessage.type()) && this.l.equals(notificationMessage.trigger()) && (this.m != null ? this.m.equals(notificationMessage.url()) : notificationMessage.url() == null) && (this.n != null ? this.n.equals(notificationMessage.mediaUrl()) : notificationMessage.mediaUrl() == null) && (this.o != null ? this.o.equals(notificationMessage.mediaAltText()) : notificationMessage.mediaAltText() == null) && this.p.equals(notificationMessage.customKeys()) && (this.q != null ? this.q.equals(notificationMessage.custom()) : notificationMessage.custom() == null)) {
            if (this.r == null) {
                if (notificationMessage.payload() == null) {
                    return true;
                }
            } else if (this.r.equals(notificationMessage.payload())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i2 = 0;
        int hashCode = (((((((((((((((((((((((((((((((this.b.hashCode() ^ 1000003) * 1000003) ^ (this.c == null ? 0 : this.c.hashCode())) * 1000003) ^ this.d) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode()) * 1000003) ^ (this.g == null ? 0 : this.g.hashCode())) * 1000003) ^ this.h) * 1000003) ^ (this.i == null ? 0 : this.i.hashCode())) * 1000003) ^ (this.j == null ? 0 : this.j.hashCode())) * 1000003) ^ this.k.hashCode()) * 1000003) ^ this.l.hashCode()) * 1000003) ^ (this.m == null ? 0 : this.m.hashCode())) * 1000003) ^ (this.n == null ? 0 : this.n.hashCode())) * 1000003) ^ (this.o == null ? 0 : this.o.hashCode())) * 1000003) ^ this.p.hashCode()) * 1000003) ^ (this.q == null ? 0 : this.q.hashCode())) * 1000003;
        if (this.r != null) {
            i2 = this.r.hashCode();
        }
        return hashCode ^ i2;
    }

    @NonNull
    public String id() {
        return this.b;
    }

    @Nullable
    public String mediaAltText() {
        return this.o;
    }

    @Nullable
    public String mediaUrl() {
        return this.n;
    }

    public int notificationId() {
        return this.d;
    }

    @Nullable
    public Bundle payload() {
        return this.r;
    }

    @Nullable
    public String regionId() {
        return this.c;
    }

    @DrawableRes
    public int smallIconResId() {
        return this.h;
    }

    @NonNull
    public Sound sound() {
        return this.f;
    }

    @Nullable
    public String soundName() {
        return this.g;
    }

    @Nullable
    public String subTitle() {
        return this.j;
    }

    @Nullable
    public String title() {
        return this.i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NotificationMessage{id=");
        sb.append(this.b);
        sb.append(", regionId=");
        sb.append(this.c);
        sb.append(", notificationId=");
        sb.append(this.d);
        sb.append(", alert=");
        sb.append(this.e);
        sb.append(", sound=");
        sb.append(this.f);
        sb.append(", soundName=");
        sb.append(this.g);
        sb.append(", smallIconResId=");
        sb.append(this.h);
        sb.append(", title=");
        sb.append(this.i);
        sb.append(", subTitle=");
        sb.append(this.j);
        sb.append(", type=");
        sb.append(this.k);
        sb.append(", trigger=");
        sb.append(this.l);
        sb.append(", url=");
        sb.append(this.m);
        sb.append(", mediaUrl=");
        sb.append(this.n);
        sb.append(", mediaAltText=");
        sb.append(this.o);
        sb.append(", customKeys=");
        sb.append(this.p);
        sb.append(", custom=");
        sb.append(this.q);
        sb.append(", payload=");
        sb.append(this.r);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    @NonNull
    public Trigger trigger() {
        return this.l;
    }

    @NonNull
    public Type type() {
        return this.k;
    }

    @Nullable
    public String url() {
        return this.m;
    }
}
