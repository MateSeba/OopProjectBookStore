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
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @return the driver
     */
    public DeliveryDriver getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(DeliveryDriver driver) {
        this.driver = driver;
    }

    /**
     * @return the dispatchTime
     */
    public Date getDispatchTime() {
        return dispatchTime;
    }

    /**
     * @param dispatchTime the dispatchTime to set
     */
    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
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

    @Override
    public String toString() {
        return "DeliveryAssignment{id=" + getId() + ", status=" + getStatus() + "}";
    }
}