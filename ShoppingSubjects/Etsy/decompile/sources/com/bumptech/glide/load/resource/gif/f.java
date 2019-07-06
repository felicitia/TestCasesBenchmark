package com.bumptech.glide.load.resource.gif;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.a.c;
import com.bumptech.glide.load.b.l;

/* compiled from: GifFrameModelLoader */
class f implements l<com.bumptech.glide.b.a, com.bumptech.glide.b.a> {

    /* compiled from: GifFrameModelLoader */
    private static class a implements c<com.bumptech.glide.b.a> {
        private final com.bumptech.glide.b.a a;

        public void a() {
        }

        public void c() {
        }

        public a(com.bumptech.glide.b.a aVar) {
            this.a = aVar;
        }

        /* renamed from: b */
        public com.bumptech.glide.b.a a(Priority priority) {
            return this.a;
        }

        public String b() {
            return String.valueOf(this.a.d());
        }
    }

    f() {
    }

    public c<com.bumptech.glide.b.a> a(com.bumptech.glide.b.a aVar, int i, int i2) {
        return new a(aVar);
    }
}
