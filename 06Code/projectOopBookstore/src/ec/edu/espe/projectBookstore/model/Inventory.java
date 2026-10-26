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

    /**
     * @return the lastRestockDate
     */
    public Date getLastRestockDate() {
        return lastRestockDate;
    }

    /**
     * @param lastRestockDate the lastRestockDate to set
     */
    public void setLastRestockDate(Date lastRestockDate) {
        this.lastRestockDate = lastRestockDate;
    }

    /**
     * @return the reorderLevel
     */
    public int getReorderLevel() {
        return reorderLevel;
    }

    /**
     * @param reorderLevel the reorderLevel to set
     */
    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Inventory{" + "lastRestockDate=" + lastRestockDate + ", reorderLevel=" + reorderLevel + ", book=" + book + '}';
    }
}