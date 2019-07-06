package com.stripe.model;

public final class EvidenceSubObject extends StripeObject {
    protected String accessActivityLog;
    protected String billingAddress;
    protected String cancellationPolicy;
    protected String cancellationPolicyDisclosure;
    protected String cancellationRebuttal;
    protected String customerCommunication;
    protected String customerEmailAddress;
    protected String customerName;
    protected String customerPurchaseIp;
    protected String customerSignature;
    protected String duplicateChargeDocumentation;
    protected String duplicateChargeExplanation;
    protected String duplicateChargeId;
    protected String productDescription;
    protected String receipt;
    protected String refundPolicy;
    protected String refundPolicyDisclosure;
    protected String refundRefusalExplanation;
    protected String serviceDate;
    protected String serviceDocumentation;
    protected String shippingAddress;
    protected String shippingDate;
    protected String shippingDocumentation;
    protected String shippingTrackingNumber;
    protected String uncategorizedFile;
    protected String uncategorizedText;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EvidenceSubObject evidenceSubObject = (EvidenceSubObject) obj;
        if (this.accessActivityLog == null ? evidenceSubObject.accessActivityLog != null : !this.accessActivityLog.equals(evidenceSubObject.accessActivityLog)) {
            return false;
        }
        if (this.billingAddress == null ? evidenceSubObject.billingAddress != null : !this.billingAddress.equals(evidenceSubObject.billingAddress)) {
            return false;
        }
        if (this.cancellationPolicy == null ? evidenceSubObject.cancellationPolicy != null : !this.cancellationPolicy.equals(evidenceSubObject.cancellationPolicy)) {
            return false;
        }
        if (this.cancellationPolicyDisclosure == null ? evidenceSubObject.cancellationPolicyDisclosure != null : !this.cancellationPolicyDisclosure.equals(evidenceSubObject.cancellationPolicyDisclosure)) {
            return false;
        }
        if (this.cancellationRebuttal == null ? evidenceSubObject.cancellationRebuttal != null : !this.cancellationRebuttal.equals(evidenceSubObject.cancellationRebuttal)) {
            return false;
        }
        if (this.customerCommunication == null ? evidenceSubObject.customerCommunication != null : !this.customerCommunication.equals(evidenceSubObject.customerCommunication)) {
            return false;
        }
        if (this.customerEmailAddress == null ? evidenceSubObject.customerEmailAddress != null : !this.customerEmailAddress.equals(evidenceSubObject.customerEmailAddress)) {
            return false;
        }
        if (this.customerName == null ? evidenceSubObject.customerName != null : !this.customerName.equals(evidenceSubObject.customerName)) {
            return false;
        }
        if (this.customerPurchaseIp == null ? evidenceSubObject.customerPurchaseIp != null : !this.customerPurchaseIp.equals(evidenceSubObject.customerPurchaseIp)) {
            return false;
        }
        if (this.customerSignature == null ? evidenceSubObject.customerSignature != null : !this.customerSignature.equals(evidenceSubObject.customerSignature)) {
            return false;
        }
        if (this.duplicateChargeDocumentation == null ? evidenceSubObject.duplicateChargeDocumentation != null : !this.duplicateChargeDocumentation.equals(evidenceSubObject.duplicateChargeDocumentation)) {
            return false;
        }
        if (this.duplicateChargeExplanation == null ? evidenceSubObject.duplicateChargeExplanation != null : !this.duplicateChargeExplanation.equals(evidenceSubObject.duplicateChargeExplanation)) {
            return false;
        }
        if (this.duplicateChargeId == null ? evidenceSubObject.duplicateChargeId != null : !this.duplicateChargeId.equals(evidenceSubObject.duplicateChargeId)) {
            return false;
        }
        if (this.productDescription == null ? evidenceSubObject.productDescription != null : !this.productDescription.equals(evidenceSubObject.productDescription)) {
            return false;
        }
        if (this.receipt == null ? evidenceSubObject.receipt != null : !this.receipt.equals(evidenceSubObject.receipt)) {
            return false;
        }
        if (this.refundPolicy == null ? evidenceSubObject.refundPolicy != null : !this.refundPolicy.equals(evidenceSubObject.refundPolicy)) {
            return false;
        }
        if (this.refundPolicyDisclosure == null ? evidenceSubObject.refundPolicyDisclosure != null : !this.refundPolicyDisclosure.equals(evidenceSubObject.refundPolicyDisclosure)) {
            return false;
        }
        if (this.refundRefusalExplanation == null ? evidenceSubObject.refundRefusalExplanation != null : !this.refundRefusalExplanation.equals(evidenceSubObject.refundRefusalExplanation)) {
            return false;
        }
        if (this.serviceDate == null ? evidenceSubObject.serviceDate != null : !this.serviceDate.equals(evidenceSubObject.serviceDate)) {
            return false;
        }
        if (this.serviceDocumentation == null ? evidenceSubObject.serviceDocumentation != null : !this.serviceDocumentation.equals(evidenceSubObject.serviceDocumentation)) {
            return false;
        }
        if (this.shippingAddress == null ? evidenceSubObject.shippingAddress != null : !this.shippingAddress.equals(evidenceSubObject.shippingAddress)) {
            return false;
        }
        if (this.shippingDate == null ? evidenceSubObject.shippingDate != null : !this.shippingDate.equals(evidenceSubObject.shippingDate)) {
            return false;
        }
        if (this.shippingDocumentation == null ? evidenceSubObject.shippingDocumentation != null : !this.shippingDocumentation.equals(evidenceSubObject.shippingDocumentation)) {
            return false;
        }
        if (this.shippingTrackingNumber == null ? evidenceSubObject.shippingTrackingNumber != null : !this.shippingTrackingNumber.equals(evidenceSubObject.shippingTrackingNumber)) {
            return false;
        }
        if (this.uncategorizedFile == null ? evidenceSubObject.uncategorizedFile == null : this.uncategorizedFile.equals(evidenceSubObject.uncategorizedFile)) {
            return this.uncategorizedText == null ? evidenceSubObject.uncategorizedText == null : this.uncategorizedText.equals(evidenceSubObject.uncategorizedText);
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((((((((((((((((((((((((((((((((((((this.productDescription != null ? this.productDescription.hashCode() : 0) * 31) + (this.customerName != null ? this.customerName.hashCode() : 0)) * 31) + (this.customerEmailAddress != null ? this.customerEmailAddress.hashCode() : 0)) * 31) + (this.customerPurchaseIp != null ? this.customerPurchaseIp.hashCode() : 0)) * 31) + (this.billingAddress != null ? this.billingAddress.hashCode() : 0)) * 31) + (this.receipt != null ? this.receipt.hashCode() : 0)) * 31) + (this.shippingAddress != null ? this.shippingAddress.hashCode() : 0)) * 31) + (this.shippingDate != null ? this.shippingDate.hashCode() : 0)) * 31) + (this.shippingTrackingNumber != null ? this.shippingTrackingNumber.hashCode() : 0)) * 31) + (this.customerSignature != null ? this.customerSignature.hashCode() : 0)) * 31) + (this.shippingDocumentation != null ? this.shippingDocumentation.hashCode() : 0)) * 31) + (this.accessActivityLog != null ? this.accessActivityLog.hashCode() : 0)) * 31) + (this.serviceDate != null ? this.serviceDate.hashCode() : 0)) * 31) + (this.serviceDocumentation != null ? this.serviceDocumentation.hashCode() : 0)) * 31) + (this.customerCommunication != null ? this.customerCommunication.hashCode() : 0)) * 31) + (this.duplicateChargeId != null ? this.duplicateChargeId.hashCode() : 0)) * 31) + (this.duplicateChargeExplanation != null ? this.duplicateChargeExplanation.hashCode() : 0)) * 31) + (this.duplicateChargeDocumentation != null ? this.duplicateChargeDocumentation.hashCode() : 0)) * 31) + (this.refundPolicy != null ? this.refundPolicy.hashCode() : 0)) * 31) + (this.refundPolicyDisclosure != null ? this.refundPolicyDisclosure.hashCode() : 0)) * 31) + (this.refundRefusalExplanation != null ? this.refundRefusalExplanation.hashCode() : 0)) * 31) + (this.cancellationPolicy != null ? this.cancellationPolicy.hashCode() : 0)) * 31) + (this.cancellationPolicyDisclosure != null ? this.cancellationPolicyDisclosure.hashCode() : 0)) * 31) + (this.cancellationRebuttal != null ? this.cancellationRebuttal.hashCode() : 0)) * 31) + (this.uncategorizedText != null ? this.uncategorizedText.hashCode() : 0)) * 31;
        if (this.uncategorizedFile != null) {
            i = this.uncategorizedFile.hashCode();
        }
        return hashCode + i;
    }
}
