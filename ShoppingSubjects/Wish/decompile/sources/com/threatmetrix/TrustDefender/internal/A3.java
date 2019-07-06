package com.threatmetrix.TrustDefender.internal;

final class A3 extends Thread {

    /* renamed from: for reason: not valid java name */
    final Runnable f48for;

    public A3(Runnable runnable) {
        this.f48for = runnable;
    }

    public final void run() {
        this.f48for.run();
    }

    public final void interrupt() {
        if (this.f48for instanceof F) {
            ((F) this.f48for).f167new.m123new();
        }
        super.interrupt();
    }
}
