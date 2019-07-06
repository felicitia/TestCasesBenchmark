package com.etsy.android.util;

import com.etsy.android.lib.config.EtsyBuild;
import com.etsy.android.lib.devconfigs.a;

/* compiled from: EtsyBuildHelper */
public class d {
    public static final EtsyBuild a = EtsyBuild.fromString("play");

    public static boolean c() {
        return false;
    }

    public static boolean a() {
        return a == EtsyBuild.ALPHA;
    }

    public static boolean b() {
        return a() || c();
    }

    public static a d() {
        a aVar = new a();
        aVar.c = "release/boe-5.6.0";
        aVar.b = "1536092146";
        aVar.a = "";
        aVar.d = "d301578066f8d914859c8b373286d5802bd1fc73";
        return aVar;
    }
}
