package com.apiguard;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.List;

/* compiled from: GA */
public interface AGCallbackInterface {
    void checkCertificates(List<Certificate> list, String str) throws IOException;

    void logAGMessage(String str);

    void reauthenticate();
}
