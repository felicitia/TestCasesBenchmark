package com.braintree.org.bouncycastle.asn1.x509;

import com.braintree.org.bouncycastle.asn1.ASN1Encodable;
import com.braintree.org.bouncycastle.asn1.ASN1EncodableVector;
import com.braintree.org.bouncycastle.asn1.ASN1Sequence;
import com.braintree.org.bouncycastle.asn1.DERInteger;
import com.braintree.org.bouncycastle.asn1.DERObject;
import com.braintree.org.bouncycastle.asn1.DERSequence;
import java.math.BigInteger;
import java.util.Enumeration;

public class RSAPublicKeyStructure extends ASN1Encodable {
    private BigInteger modulus;
    private BigInteger publicExponent;

    public static RSAPublicKeyStructure getInstance(Object obj) {
        if (obj == null || (obj instanceof RSAPublicKeyStructure)) {
            return (RSAPublicKeyStructure) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new RSAPublicKeyStructure((ASN1Sequence) obj);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid RSAPublicKeyStructure: ");
        sb.append(obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public RSAPublicKeyStructure(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Bad sequence size: ");
            sb.append(aSN1Sequence.size());
            throw new IllegalArgumentException(sb.toString());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        this.modulus = DERInteger.getInstance(objects.nextElement()).getPositiveValue();
        this.publicExponent = DERInteger.getInstance(objects.nextElement()).getPositiveValue();
    }

    public BigInteger getModulus() {
        return this.modulus;
    }

    public BigInteger getPublicExponent() {
        return this.publicExponent;
    }

    public DERObject toASN1Object() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DERInteger(getModulus()));
        aSN1EncodableVector.add(new DERInteger(getPublicExponent()));
        return new DERSequence(aSN1EncodableVector);
    }
}
