package com.onfido.c.a;

import com.onfido.c.a.a.b;
import com.onfido.c.a.j.a;
import java.util.List;

class p implements a {
    private int a;
    private final b b;
    private final List<j> c;
    private final a d;

    p(int i, b bVar, List<j> list, a aVar) {
        this.a = i;
        this.b = bVar;
        this.c = list;
        this.d = aVar;
    }

    public void a(b bVar) {
        if (this.a < this.c.size()) {
            ((j) this.c.get(this.a)).a(new p(this.a + 1, bVar, this.c, this.d));
            return;
        }
        this.d.b(bVar);
    }
}
