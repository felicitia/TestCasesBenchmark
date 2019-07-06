package com.klarna.checkout.internal.c;

import com.klarna.checkout.internal.d;
import java.util.LinkedList;
import java.util.Queue;

public final class b {
    public final Queue<d> a = new LinkedList();
    public final Queue<d> b = new LinkedList();
    public boolean c;
    public boolean d;

    public static void a(Queue<d> queue) {
        while (!queue.isEmpty()) {
            d dVar = (d) queue.poll();
            StringBuilder sb = new StringBuilder(".....deque-ing: ");
            sb.append(dVar.a);
            sb.append(" overlay=");
            sb.append(dVar.b());
            dVar.a();
        }
    }
}
