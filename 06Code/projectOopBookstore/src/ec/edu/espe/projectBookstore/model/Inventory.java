package ec.edu.espe.projectBookstore.model;

import java.util.Date;

/**
 *
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class Inventory {
 
    private Date lastRestockDate;
    private int reorderLevel;
    private Book book;
 
    public Inventory(Date lastRestockDate, int reorderLevel, Book book) {
        this.lastRestockDate = lastRestockDate;
        this.reorderLevel = reorderLevel;
        this.book = book;
    }
 
    public void updateStock(int quantity) {
        book.updateStock(quantity);
        if (quantity > 0) {
            lastRestockDate = new Date();
        }
    }
 
    public boolean checkReorderStatus() {
        return book.getStock() <= reorderLevel;
    }
 
    public Date getLastRestockDate() { return lastRestockDate; }
    public void setLastRestockDate(Date lastRestockDate) { this.lastRestockDate = lastRestockDate; }
 
    public int getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(int reorderLevel) { this.reorderLevel = reorderLevel; }
 
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
}