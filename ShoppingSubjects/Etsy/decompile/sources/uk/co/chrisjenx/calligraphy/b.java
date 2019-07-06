package uk.co.chrisjenx.calligraphy;

import android.os.Build.VERSION;
import android.text.TextUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: CalligraphyConfig */
public class b {
    private static final Map<Class<? extends TextView>, Integer> a = new HashMap();
    private static b b;
    private final boolean c;
    private final String d;
    private final int e;
    private final boolean f;
    private final boolean g;
    private final Map<Class<? extends TextView>, Integer> h;

    /* compiled from: CalligraphyConfig */
    public static class a {
        /* access modifiers changed from: private */
        public boolean a;
        /* access modifiers changed from: private */
        public boolean b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public boolean d;
        /* access modifiers changed from: private */
        public String e;
        /* access modifiers changed from: private */
        public Map<Class<? extends TextView>, Integer> f;

        public a() {
            this.a = VERSION.SDK_INT >= 11;
            this.b = true;
            this.c = uk.co.chrisjenx.calligraphy.f.a.fontPath;
            this.d = false;
            this.e = null;
            this.f = new HashMap();
        }

        public a a(String str) {
            this.d = !TextUtils.isEmpty(str);
            this.e = str;
            return this;
        }

        public b a() {
            this.d = !TextUtils.isEmpty(this.e);
            return new b(this);
        }
    }

    static {
        a.put(TextView.class, Integer.valueOf(16842884));
        a.put(Button.class, Integer.valueOf(16842824));
        a.put(EditText.class, Integer.valueOf(16842862));
        a.put(AutoCompleteTextView.class, Integer.valueOf(16842859));
        a.put(MultiAutoCompleteTextView.class, Integer.valueOf(16842859));
        a.put(CheckBox.class, Integer.valueOf(16842860));
        a.put(RadioButton.class, Integer.valueOf(16842878));
        a.put(ToggleButton.class, Integer.valueOf(16842827));
    }

    public static void a(b bVar) {
        b = bVar;
    }

    public static b a() {
        if (b == null) {
            b = new b(new a());
        }
        return b;
    }

    protected b(a aVar) {
        this.c = aVar.d;
        this.d = aVar.e;
        this.e = aVar.c;
        this.f = aVar.a;
        this.g = aVar.b;
        HashMap hashMap = new HashMap(a);
        hashMap.putAll(aVar.f);
        this.h = Collections.unmodifiableMap(hashMap);
    }

    public String b() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.f;
    }

    public boolean e() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public Map<Class<? extends TextView>, Integer> f() {
        return this.h;
    }

    public int g() {
        return this.e;
    }
}
