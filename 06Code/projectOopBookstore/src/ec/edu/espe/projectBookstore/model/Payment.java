package ec.edu.espe.projectBookstore.model;

import java.util.Date;

/**
 * @author Lino Diaz, Brain Training, @ESPE
 */
public class Payment {

    private int id;
    private float amount;
    private Date paymentDate;
    private String paymentMethod;
    private String status;
    private String referenceNumber;

    public Payment(int id, float amount, Date paymentDate, String paymentMethod, String status, String referenceNumber) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.referenceNumber = referenceNumber;
    }

    public void processPayment() {
        this.status = "Processed";
        System.out.println("Processing payment for amount: " + amount);
    }

    public boolean validatePayment() {
        return amount > 0 && referenceNumber != null && !referenceNumber.isEmpty();
    }

    public String getPaymentStatus() {
        return status;
    }

    public void generateReceipt() {
        System.out.println("Receipt generated for payment ID: " + id);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the referenceNumber
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * @param referenceNumber the referenceNumber to set
     */
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Override
    public String toString() {
        return "Payment{id=" + getId() + ", amount=" + getAmount() + ", paymentMethod=" + getPaymentMethod() + ", status=" + getStatus() + "}";
    }
}