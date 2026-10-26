package ec.edu.espe.projectBookstore.model;

import java.util.Date;

/**
 *
 * @author Andres
 */
public class Notification {

    private int id;
    private Customer customer;
    private String message;
    private Date sentDate;
    private boolean read;

    public Notification(int id, Customer customer, String message, Date sentDate, boolean read) {
        this.id = id;
        this.customer = customer;
        this.message = message;
        this.sentDate = sentDate;
        this.read = read;
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
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the sentDate
     */
    public Date getSentDate() {
        return sentDate;
    }

    /**
     * @param sentDate the sentDate to set
     */
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    /**
     * @return the read
     */
    public boolean isRead() {
        return read;
    }

    /**
     * @param read the read to set
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "Notification{id=" + id + ", message=" + message + ", read=" + read + "}";
    }
}
