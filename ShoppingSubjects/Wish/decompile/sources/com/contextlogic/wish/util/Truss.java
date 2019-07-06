package com.contextlogic.wish.util;

import android.text.SpannableStringBuilder;
import java.util.ArrayDeque;
import java.util.Deque;

public class Truss {
    private final SpannableStringBuilder builder = new SpannableStringBuilder();
    private final Deque<Span> stack = new ArrayDeque();

    private static final class Span {
        final Object span;
        final int start;

        public Span(int i, Object obj) {
            this.start = i;
            this.span = obj;
        }
    }

    public Truss append(String str) {
        this.builder.append(str);
        return this;
    }

    public Truss pushSpan(Object obj) {
        this.stack.addLast(new Span(this.builder.length(), obj));
        return this;
    }

    public Truss popSpan() {
        Span span = (Span) this.stack.removeLast();
        this.builder.setSpan(span.span, span.start, this.builder.length(), 17);
        return this;
    }

    public CharSequence build() {
        while (!this.stack.isEmpty()) {
            popSpan();
        }
        return this.builder;
    }
}
