package ec.edu.espe.projectBookstore.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Andres
 */
public class ShoppingCart{

    private int id;
    private Date creationDate;
    private float subTotal;

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
    
    private List<OrderDetail> items;
    private int nextItemId;

    public ShoppingCart(int id, Date creationDate, float subTotal) {
        this.id = id;
        this.creationDate = creationDate;
        this.subTotal = subTotal;
        this.items = new ArrayList<>();
        this.nextItemId = 1;
    }
 
    public List<OrderDetail> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addBook(Book book, int quantity) {
        if (book == null) {
            throw new IllegalArgumentException("The book is invalid");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("The quantity must be greater than 0");
        }
        for (OrderDetail item : items) {
            if (item.getBook().getId() == book.getId()) {
                item.updateQuantity(item.getQuantity() + quantity);
                calculateSubtotal();
                return;
            }
        }
        items.add(new OrderDetail(nextItemId++, quantity, book, book.getPrice()));
        calculateSubtotal();
    }

    public boolean removeBook(Book book) {
        for (OrderDetail item : items) {
            if (item.getBook().getId() == book.getId()) {
                items.remove(item);
                calculateSubtotal();
                return true;
            }
        }
        return false;
    }

    public void updateStock() {
        for (OrderDetail item : items) {
            if (item.getQuantity() > item.getBook().getStock()) {
                throw new IllegalArgumentException("Not enough stock for: " + item.getBook().getTitle());
            }
        }
        for (OrderDetail item : items) {
            item.getBook().updateStock(-item.getQuantity());
        }
    }

    public void clearShoppingCart() {
        items.clear();
        this.subTotal = 0;
    }   

    public float calculateSubtotal() {
        float sum = 0;
        for (OrderDetail item : items) {
            sum += item.getSubTotal();
        }
        this.subTotal = sum;
        return this.subTotal;
    }

    public String getContent() {
        if (items.isEmpty()) {
            return "The shopping cart is empty";
        }
        StringBuilder content = new StringBuilder();
        for (OrderDetail item : items) {
            content.append(item).append("\n");
        }
        content.append("Subtotal: $").append(subTotal);
        return content.toString();
}
    
}
