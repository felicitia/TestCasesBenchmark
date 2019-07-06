package com.braintreegateway.encryption;

public class Braintree {
    private final String publicKey;

    public Braintree(String str) {
        this.publicKey = str;
    }

    public String encrypt(String str) throws BraintreeEncryptionException {
        byte[] secureRandomBytes = Random.secureRandomBytes(32);
        String encrypt = Aes.encrypt(str, secureRandomBytes, Random.secureRandomBytes(16));
        String encrypt2 = Rsa.encrypt(secureRandomBytes, this.publicKey);
        StringBuilder sb = new StringBuilder();
        sb.append(getPrefix());
        sb.append(encrypt2);
        sb.append("$");
        sb.append(encrypt);
        return sb.toString();
    }

    private String getPrefix() {
        StringBuilder sb = new StringBuilder();
        sb.append("$bt3|android_");
        sb.append("2.1.0".replace(".", "_"));
        sb.append("$");
        return sb.toString();
    }
}
