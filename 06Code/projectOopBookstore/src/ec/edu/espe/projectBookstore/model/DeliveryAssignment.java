package ec.edu.espe.projectBookstore.model;

import java.util.Date;

/**
 * @author Lino Diaz, Brain Training, @ESPE
 */
public class DeliveryAssignment {

    private int id;
    private Order order;
    private DeliveryDriver driver;
    private Date dispatchTime;
    private String status;

    public DeliveryAssignment(int id, Order order, DeliveryDriver driver, Date dispatchTime, String status) {
        this.id = id;
        this.order = order;
        this.driver = driver;
        this.dispatchTime = dispatchTime;
        this.status = status;
    }

    public void assignDriver(DeliveryDriver newDriver) {
        this.driver = newDriver;
        this.status = "Assigned";
    }

    public void completeDelivery() {
        this.status = "Completed";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public DeliveryDriver getDriver() { return driver; }
    public void setDriver(DeliveryDriver driver) { this.driver = driver; }

    public Date getDispatchTime() { return dispatchTime; }
    public void setDispatchTime(Date dispatchTime) { this.dispatchTime = dispatchTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "DeliveryAssignment{id=" + getId() + ", status=" + getStatus() + "}";
    }
}