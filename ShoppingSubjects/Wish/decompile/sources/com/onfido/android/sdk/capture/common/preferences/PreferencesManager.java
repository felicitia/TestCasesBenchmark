package com.onfido.android.sdk.capture.common.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

public class PreferencesManager {
    public static final Companion Companion = new Companion(null);
    static final /* synthetic */ KProperty[] a = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(PreferencesManager.class), "prefs", "getPrefs()Landroid/content/SharedPreferences;"))};
    private final Lazy b;
    private boolean c = true;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static final class a extends Lambda implements Function0<SharedPreferences> {
        final /* synthetic */ Context a;

        a(Context context) {
            this.a = context;
            super(0);
        }

        /* renamed from: a */
        public final SharedPreferences invoke() {
            return this.a.getSharedPreferences("ONFIDO_PREFERENCES", 0);
        }
    }

    static final class b extends Lambda implements Function1<Editor, Unit> {
        final /* synthetic */ String a;
        final /* synthetic */ Object b;

        b(String str, Object obj) {
            this.a = str;
            this.b = obj;
            super(1);
        }

        public final void a(Editor editor) {
            Intrinsics.checkParameterIsNotNull(editor, "it");
            editor.putString(this.a, (String) this.b);
        }

        public /* synthetic */ Object invoke(Object obj) {
            a((Editor) obj);
            return Unit.INSTANCE;
        }
    }

    static final class c extends Lambda implements Function1<Editor, Unit> {
        final /* synthetic */ String a;
        final /* synthetic */ Object b;

        c(String str, Object obj) {
            this.a = str;
            this.b = obj;
            super(1);
        }

        public final void a(Editor editor) {
            Intrinsics.checkParameterIsNotNull(editor, "it");
            editor.putInt(this.a, ((Number) this.b).intValue());
        }

        public /* synthetic */ Object invoke(Object obj) {
            a((Editor) obj);
            return Unit.INSTANCE;
        }
    }

    static final class d extends Lambda implements Function1<Editor, Unit> {
        final /* synthetic */ String a;
        final /* synthetic */ Object b;

        d(String str, Object obj) {
            this.a = str;
            this.b = obj;
            super(1);
        }

        public final void a(Editor editor) {
            Intrinsics.checkParameterIsNotNull(editor, "it");
            editor.putBoolean(this.a, ((Boolean) this.b).booleanValue());
        }

        public /* synthetic */ Object invoke(Object obj) {
            a((Editor) obj);
            return Unit.INSTANCE;
        }
    }

    static final class e extends Lambda implements Function1<Editor, Unit> {
        final /* synthetic */ String a;
        final /* synthetic */ Object b;

        e(String str, Object obj) {
            this.a = str;
            this.b = obj;
            super(1);
        }

        public final void a(Editor editor) {
            Intrinsics.checkParameterIsNotNull(editor, "it");
            editor.putFloat(this.a, ((Number) this.b).floatValue());
        }

        public /* synthetic */ Object invoke(Object obj) {
            a((Editor) obj);
            return Unit.INSTANCE;
        }
    }

    static final class f extends Lambda implements Function1<Editor, Unit> {
        final /* synthetic */ String a;
        final /* synthetic */ Object b;

        f(String str, Object obj) {
            this.a = str;
            this.b = obj;
            super(1);
        }

        public final void a(Editor editor) {
            Intrinsics.checkParameterIsNotNull(editor, "it");
            editor.putLong(this.a, ((Number) this.b).longValue());
        }

        public /* synthetic */ Object invoke(Object obj) {
            a((Editor) obj);
            return Unit.INSTANCE;
        }
    }

    public PreferencesManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.b = LazyKt.lazy(new a(context));
    }

    private final SharedPreferences a() {
        Lazy lazy = this.b;
        KProperty kProperty = a[0];
        return (SharedPreferences) lazy.getValue();
    }

    private final void a(SharedPreferences sharedPreferences, String str, Object obj) {
        Function1 fVar;
        if (obj != null ? obj instanceof String : true) {
            fVar = new b(str, obj);
        } else if (obj instanceof Integer) {
            fVar = new c(str, obj);
        } else if (obj instanceof Boolean) {
            fVar = new d(str, obj);
        } else if (obj instanceof Float) {
            fVar = new e(str, obj);
        } else if (obj instanceof Long) {
            fVar = new f(str, obj);
        } else {
            throw new UnsupportedOperationException("Preference type not supported yet");
        }
        a(sharedPreferences, fVar);
    }

    private final void a(SharedPreferences sharedPreferences, Function1<? super Editor, Unit> function1) {
        Editor edit = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
        function1.invoke(edit);
        edit.apply();
    }

    public boolean getHasAcceptedPrivacyPolicy() {
        return this.c;
    }

    public void hasAcceptedPrivacyPolicy() {
        a(a(), "ONFIDO_PRIVACY_POLICY_SHOWN", Boolean.valueOf(true));
    }

    public void resetPrivacyPolicy() {
        a(a(), "ONFIDO_PRIVACY_POLICY_SHOWN", Boolean.valueOf(false));
    }
}
