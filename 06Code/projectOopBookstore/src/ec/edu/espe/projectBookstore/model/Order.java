package ec.edu.espe.projectBookstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lino Diaz, Brain Training, @ESPE
 */
public class Order {

    private int id;
    private Date orderDate;
    private List<Object> details; 
    private float total;
    private Address shippingAddress;
    private String status;
    private String paymentMethod;

    public Order(int id, Date orderDate, float total, Address shippingAddress, String status, String paymentMethod) {
        this.id = id;
        this.orderDate = orderDate;
        this.details = new ArrayList<>();
        this.total = total;
        this.shippingAddress = shippingAddress;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public void confirmOrder() {
        this.status = "Confirmed";
    }

    public float calculateTotal() {
        return this.total;
    }

    public void cancelOrder() {
        this.status = "Cancelled";
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public List<Object> getDetails() { return details; }
    public void setDetails(List<Object> details) { this.details = details; }

    public float getTotal() { return total; }
    public void setTotal(float total) { this.total = total; }

    public Address getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(Address shippingAddress) { this.shippingAddress = shippingAddress; }

    public void setStatus(String status) { this.status = status; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    @Override
    public String toString() {
        return "Order{id=" + getId() + ", total=" + getTotal() + ", status=" + getStatus() + "}";
    }
}