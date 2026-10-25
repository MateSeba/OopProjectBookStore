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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public float getAmount() { return amount; }
    public void setAmount(float amount) { this.amount = amount; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReferenceNumber() { return referenceNumber; }
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }

    @Override
    public String toString() {
        return "Payment{id=" + getId() + ", amount=" + getAmount() + ", paymentMethod=" + getPaymentMethod() + ", status=" + getStatus() + "}";
    }
}