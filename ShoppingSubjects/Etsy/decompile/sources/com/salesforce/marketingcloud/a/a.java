package com.salesforce.marketingcloud.a;

import android.support.annotation.CheckResult;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.Size;
import android.support.annotation.VisibleForTesting;
import org.apache.commons.lang3.time.DateUtils;

@RestrictTo({Scope.LIBRARY})
public abstract class a {
    @NonNull
    private final String a;
    private final long b;
    private final double c;
    private final long d;
    @NonNull
    private final String e;
    private final int f;
    private final boolean g;
    private final boolean h;

    /* renamed from: com.salesforce.marketingcloud.a.a$a reason: collision with other inner class name */
    public enum C0160a {
        REGISTRATION(909100) {
            /* access modifiers changed from: protected */
            public boolean a(@NonNull com.salesforce.marketingcloud.d.h hVar) {
                return com.salesforce.marketingcloud.registration.g.a(hVar);
            }

            /* access modifiers changed from: protected */
            public a b() {
                return new j(a());
            }
        },
        PI_ANALYTICS(909101) {
            /* access modifiers changed from: protected */
            public a b() {
                return new h(a());
            }
        },
        ET_ANALYTICS(909102) {
            /* access modifiers changed from: protected */
            public a b() {
                return new b(a());
            }
        },
        FETCH_BEACON_MESSAGES(909103) {
            /* access modifiers changed from: protected */
            public a b() {
                return new e(a());
            }
        },
        FETCH_FENCE_MESSAGES(909104) {
            /* access modifiers changed from: protected */
            public a b() {
                return new f(a());
            }
        },
        FETCH_BEACON_MESSAGES_DAILY(909105) {
            /* access modifiers changed from: protected */
            public a b() {
                return new c(a());
            }
        },
        FETCH_FENCE_MESSAGES_DAILY(909106) {
            /* access modifiers changed from: protected */
            public a b() {
                return new d(a());
            }
        },
        FETCH_INBOX_MESSAGES(909107) {
            /* access modifiers changed from: protected */
            public a b() {
                return new g(a());
            }
        },
        GCM_REGISTRATION(909108) {
            /* access modifiers changed from: protected */
            public a b() {
                return new i(a());
            }
        },
        UPDATE_INBOX_MESSAGE_STATUS(909110) {
            /* access modifiers changed from: protected */
            public a b() {
                return new k(a());
            }
        };
        
        private final int k;

        private C0160a(int i) {
            this.k = i;
        }

        public int a() {
            return this.k;
        }

        /* access modifiers changed from: protected */
        @CheckResult
        public boolean a(@NonNull com.salesforce.marketingcloud.d.h hVar) {
            return true;
        }

        /* access modifiers changed from: protected */
        public abstract a b();
    }

    private static final class b extends a {
        private b(int i) {
            this(i, "et_etanalytic_alarm_created_date", "et_etanalytic_next_alarm_interval", 15000, 2.0d, DateUtils.MILLIS_PER_DAY, false, false);
        }

        private b(int i, String str, String str2, long j, double d, long j2, boolean z, boolean z2) {
            super(i, str, str2, j, d, j2, z, z2);
        }
    }

    private static final class c extends a {
        private c(int i) {
            this(i, "et_fetch_background_beacon_messages_alarm_created_date", "et_fetch_background_beacon_messages_next_alarm_interval", DateUtils.MILLIS_PER_DAY, 1.0d, DateUtils.MILLIS_PER_DAY, false, true);
        }

        private c(int i, String str, String str2, long j, double d, long j2, boolean z, boolean z2) {
            super(i, str, str2, j, d, j2, z, z2);
        }
    }

    private static final class d extends a {
        private d(int i) {
            this(i, "et_fetch_background_fence_messages_alarm_created_date", "et_fetch_background_fence_messages_next_alarm_interval", DateUtils.MILLIS_PER_DAY, 1.0d, DateUtils.MILLIS_PER_DAY, false, true);
        }

        private d(int i, String str, String str2, long j, double d, long j2, boolean z, boolean z2) {
            super(i, str, str2, j, d, j2, z, z2);
        }
    }

    private static final class e extends a {
        private e(int i) {
            this(i, "et_fetch_beacon_messages_alarm_created_date", "et_fetch_beacon_messages_next_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, DateUtils.MILLIS_PER_DAY, false, false);
        }

        private e(int i, String str, String str2, long j, double d, long j2, boolean z, boolean z2) {
            super(i, str, str2, j, d, j2, z, z2);
        }
    }

    private static final class f extends a {
        private f(int i) {
            this(i, "et_fetch_fence_messages_alarm_created_date", "et_fetch_fence_messages_next_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, DateUtils.MILLIS_PER_DAY, false, false);
        }

        private f(int i, String str, String str2, long j, double d, long j2, boolean z, boolean z2) {
            super(i, str, str2, j, d, j2, z, z2);
        }
    }

    private static final class g extends a {
        private g(int i) {
            this(i, "et_fetch_cloud_messages_alarm_created_date", "et_fetch_cloud_messages_next_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, DateUtils.MILLIS_PER_DAY, true, false);
        }

        private g(int i, String str, String str2, long j, double d, long j2, boolean z, boolean z2) {
            super(i, str, str2, j, d, j2, z, z2);
        }
    }

    private static final class h extends a {
        private h(int i) {
            this(i, "et_wama_alarm_created_date", "et_wama_next_alarm_interval", 15000, 2.0d, DateUtils.MILLIS_PER_DAY, false, false);
        }

        private h(int i, String str, String str2, long j, double d, long j2, boolean z, boolean z2) {
            super(i, str, str2, j, d, j2, z, z2);
        }
    }

    private static final class i extends a {
        private i(int i) {
            this(i, "et_register_for_remote_notifications_alarm_created_date", "et_register_for_remote_notifications_next_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, DateUtils.MILLIS_PER_DAY, false, false);
        }

        private i(int i, String str, String str2, long j, double d, long j2, boolean z, boolean z2) {
            super(i, str, str2, j, d, j2, z, z2);
        }
    }

    private static final class j extends a {
        private j(int i) {
            this(i, "et_registration_alarm_created_date", "et_registration_next_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, DateUtils.MILLIS_PER_DAY, false, false);
        }

        private j(int i, String str, String str2, long j, double d, long j2, boolean z, boolean z2) {
            super(i, str, str2, j, d, j2, z, z2);
        }
    }

    private static final class k extends a {
        private k(int i) {
            this(i, "et_update_inbox_message_status_alarm_created_date", "et_update_inbox_message_status_next_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, DateUtils.MILLIS_PER_DAY, true, false);
        }

        private k(int i, String str, String str2, long j, double d, long j2, boolean z, boolean z2) {
            super(i, str, str2, j, d, j2, z, z2);
        }
    }

    @VisibleForTesting
    a(@IntRange(from = 1, to = 2147483647L) int i2, @Size(min = 1) @NonNull String str, @Size(min = 1) @NonNull String str2, @IntRange(from = 1, to = 86400000) long j2, @FloatRange(from = 1.0d, to = 10.0d) double d2, @IntRange(from = 1, to = 86400000) long j3, boolean z, boolean z2) {
        this.f = i2;
        this.e = str;
        this.a = str2;
        this.b = j2;
        this.c = d2;
        this.d = j3;
        this.g = z;
        this.h = z2;
    }

    /* access modifiers changed from: 0000 */
    public final boolean a() {
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public final String b() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public final long c() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final double d() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final long e() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public final String f() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public final int g() {
        return this.f;
    }
}
