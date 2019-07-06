package defpackage;

/* renamed from: x reason: default package */
/* compiled from: GA */
public final class x extends q {
    private final int b;

    public x(int i) {
        this.b = i;
    }

    public final void c() {
        switch (this.b) {
            case 0:
                super.a(al.e());
                return;
            case 1:
                super.a(String.valueOf(System.currentTimeMillis()));
                break;
        }
    }
}
