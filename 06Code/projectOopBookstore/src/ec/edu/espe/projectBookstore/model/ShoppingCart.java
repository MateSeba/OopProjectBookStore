package ec.edu.espe.projectBookstore.model;

import java.util.Date;

/**
 *
 * @author Andres
 */
public class ShoppingCart{

    private int id;
    private Date creationDate;
    private float subTotal;

    public ShoppingCart(int id, Date creationDate, float subTotal) {
        this.id = id;
        this.creationDate = creationDate;
        this.subTotal = subTotal;
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
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the subTotal
     */
    public float getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "ShoppingCart{id=" + id + ", subTotal=" + subTotal + "}";
    }
}
