package ec.edu.espe.projectBookstore.model;

/**
 *
 * @author Andres
 */
public class OrderDetail {

    private int id;
    private int quantity;
    private Book book;
    private float unitPrice;
    private float subTotal;

    public OrderDetail(int id, int quantity, Book book, float unitPrice) {
        this.id = id;
        this.book = book;
        setQuantity(quantity);
        setUnitPrice(unitPrice);
    }
    
    private void recalculateSubTotal() {
        this.subTotal = quantity * unitPrice;
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
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("The quantity must be greater than 0");
        }
        this.quantity = quantity;
        recalculateSubTotal();
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

    /**
     * @return the unitPrice
     */
    public float getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(float unitPrice) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("The price cannot be negative");
        }
        this.unitPrice = unitPrice;
        recalculateSubTotal();
    }

    /**
     * @return the subTotal (calculated, it has no setter)
     */
    public float getSubTotal() {
        return subTotal;
    }

    @Override
    public String toString() {
        return "OrderDetail{book=" + (book != null ? book.getTitle() : "N/A")
                + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", subTotal=" + subTotal + "}";
    }
}
