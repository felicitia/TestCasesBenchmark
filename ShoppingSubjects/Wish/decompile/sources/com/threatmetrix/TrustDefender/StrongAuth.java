package com.threatmetrix.TrustDefender;

import android.content.Intent;
import com.threatmetrix.TrustDefender.internal.TL;

public class StrongAuth {
    public static final String AUTH_ACTION = "auth_action";
    public static final String AUTH_CONTEXT = "auth_context";
    public static final String AUTH_METHOD = "auth_method";
    public static final String AUTH_PROMPT = "auth_prompt";
    public static final String AUTH_SESSION = "auth_session";
    public static final String AUTH_TITLE = "title";

    /* renamed from: if reason: not valid java name */
    private static final String f13if = TL.m331if(StrongAuth.class);

    public enum AuthenticationStatus {
        THM_STRONG_AUTH_NOT_POSSIBLE,
        THM_STRONG_AUTH_OK,
        THM_STRONG_AUTH_FAILED,
        THM_STRONG_AUTH_CANCELLED
    }

    public interface StrongAuthCallback {
        int callIntent(Intent intent);
    }

    public static final class StrongAuthConfiguration {
        String auth_action;
        String auth_context;
        String auth_method;
        String auth_prompt;
        String auth_session;
        String auth_title;

        public StrongAuthConfiguration() {
        }

        public StrongAuthConfiguration(String str, String str2, String str3, String str4, String str5, String str6) {
            this.auth_method = str2;
            this.auth_prompt = str6;
            this.auth_context = str4;
            this.auth_session = str3;
            this.auth_action = str;
            this.auth_title = str5;
        }

        public final StrongAuthConfiguration setAuthSession(String str) {
            this.auth_session = str;
            return this;
        }

        public final StrongAuthConfiguration setAuthContext(String str) {
            this.auth_context = str;
            return this;
        }

        public final StrongAuthConfiguration setAuthMethod(String str) {
            this.auth_method = str;
            return this;
        }

        public final StrongAuthConfiguration setAuthAction(String str) {
            this.auth_action = str;
            return this;
        }

        public final StrongAuthConfiguration setAuthPrompt(String str) {
            this.auth_prompt = str;
            return this;
        }

        public final StrongAuthConfiguration setAuthTitle(String str) {
            this.auth_title = str;
            return this;
        }
    }

    public interface StrongAuthPromptCallback extends StrongAuthCallback {
        AuthenticationStatus prompt(String str, String str2, String str3);
    }

    private StrongAuth() {
    }
}
