package ec.edu.espe.projectBookstore.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Lino Diaz, Brain Training, @ESPE
 */
public class Order {

    private int id;
    private Date orderDate;
    private List<OrderDetail> details;
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

    public void addDetail(OrderDetail detail) {
        if (detail != null) {
            details.add(detail);
            calculateTotal();
        }
    }

    public void confirmOrder() {
        this.status = "Confirmed";
    }
    
    public float calculateTotal() {
        float sum = 0;
        for (OrderDetail detail : details) {
            sum += detail.getSubTotal();
        }
        this.total = sum;
        return this.total;
    }

    public void cancelOrder() {
        this.status = "Cancelled";
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
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
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the details (read-only view, use addDetail() to add)
     */
    public List<OrderDetail> getDetails() {
        return Collections.unmodifiableList(details);
    }

    /**
     * @param details the details to set
     */
    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return the shippingAddress
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     * @param shippingAddress the shippingAddress to set
     */
    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
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

    @Override
    public String toString() {
        return "Order{id=" + getId() + ", total=" + getTotal() + ", status=" + getStatus() + "}";
    }
}