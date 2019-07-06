package com.onfido.c.a;

import android.app.Activity;
import android.os.Bundle;
import com.onfido.c.a.a.a;
import com.onfido.c.a.a.c;
import com.onfido.c.a.a.d;
import com.onfido.c.a.a.e;
import com.onfido.c.a.a.g;
import com.onfido.c.a.a.h;
import com.onfido.c.a.b.b;
import java.util.Map;

abstract class i {
    static final i a = new i() {
        /* access modifiers changed from: 0000 */
        public void a(String str, e<?> eVar, m mVar) {
            eVar.a();
        }

        public String toString() {
            return "Flush";
        }
    };
    static final i b = new i() {
        /* access modifiers changed from: 0000 */
        public void a(String str, e<?> eVar, m mVar) {
            eVar.d();
        }

        public String toString() {
            return "Reset";
        }
    };

    private i() {
    }

    static i a(final Activity activity) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                eVar.a(activity);
            }

            public String toString() {
                return "Activity Started";
            }
        };
    }

    static i a(final Activity activity, final Bundle bundle) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                eVar.a(activity, bundle);
            }

            public String toString() {
                return "Activity Created";
            }
        };
    }

    static i a(final a aVar) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                if (a(aVar.d(), str)) {
                    eVar.a(aVar);
                }
            }

            public String toString() {
                return aVar.toString();
            }
        };
    }

    static i a(final c cVar) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                if (a(cVar.d(), str)) {
                    eVar.a(cVar);
                }
            }

            public String toString() {
                return cVar.toString();
            }
        };
    }

    static i a(final d dVar) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                if (a(dVar.d(), str)) {
                    eVar.a(dVar);
                }
            }

            public String toString() {
                return dVar.toString();
            }
        };
    }

    static i a(final g gVar) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                if (a(gVar.d(), str)) {
                    eVar.a(gVar);
                }
            }

            public String toString() {
                return gVar.toString();
            }
        };
    }

    static i a(final h hVar) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                t d = hVar.d();
                t c2 = mVar.c();
                if (b.a((Map) c2)) {
                    if (a(d, str)) {
                        eVar.a(hVar);
                    }
                    return;
                }
                t a = c2.a(hVar.a());
                if (b.a((Map) a)) {
                    if (!b.a((Map) d)) {
                        if (a(d, str)) {
                            eVar.a(hVar);
                        }
                        return;
                    }
                    t a2 = c2.a("__default");
                    if (b.a((Map) a2)) {
                        eVar.a(hVar);
                        return;
                    }
                    if (a2.b("enabled", true) || "Segment.io".equals(str)) {
                        eVar.a(hVar);
                    }
                } else if (!a.b("enabled", true)) {
                    if ("Segment.io".equals(str)) {
                        eVar.a(hVar);
                    }
                } else {
                    t tVar = new t();
                    t a3 = a.a("integrations");
                    if (!b.a((Map) a3)) {
                        tVar.putAll(a3);
                    }
                    tVar.putAll(d);
                    if (a(tVar, str)) {
                        eVar.a(hVar);
                    }
                }
            }

            public String toString() {
                return hVar.toString();
            }
        };
    }

    static boolean a(t tVar, String str) {
        if (b.a((Map) tVar) || "Segment.io".equals(str)) {
            return true;
        }
        if (!tVar.containsKey(str)) {
            if (!tVar.containsKey("All")) {
                return true;
            }
            str = "All";
        }
        return tVar.b(str, true);
    }

    static i b(final Activity activity) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                eVar.b(activity);
            }

            public String toString() {
                return "Activity Resumed";
            }
        };
    }

    static i b(final Activity activity, final Bundle bundle) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                eVar.b(activity, bundle);
            }

            public String toString() {
                return "Activity Save Instance";
            }
        };
    }

    static i c(final Activity activity) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                eVar.c(activity);
            }

            public String toString() {
                return "Activity Paused";
            }
        };
    }

    static i d(final Activity activity) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                eVar.d(activity);
            }

            public String toString() {
                return "Activity Stopped";
            }
        };
    }

    static i e(final Activity activity) {
        return new i() {
            public void a(String str, e<?> eVar, m mVar) {
                eVar.e(activity);
            }

            public String toString() {
                return "Activity Destroyed";
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public abstract void a(String str, e<?> eVar, m mVar);
}
