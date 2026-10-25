package ec.edu.espe.projectBookstore.model;

/**
 *
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class Book {
 
    private int id;
    private String title;
    private String isbn;
    private float price;
    private int stock;
    private String description;
    private Author author;
    private Publisher publisher;
    private Category category;
 
    public Book(int id, String title, String isbn, float price, int stock,
                String description, Author author, Publisher publisher, Category category) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
 
        if (author != null) author.addBook(this);
        if (publisher != null) publisher.addBook(this);
        if (category != null) category.addBooks(this);
    }
 
    public String getInfo() {
        return "Title: " + getTitle()
                + " | ISBN: " + getIsbn()
                + " | Price: $" + getPrice()
                + " | Stock: " + getStock()
                + " | Author: " + (getAuthor() != null ? getAuthor().getName() : "N/A")
                + " | Publisher: " + (getPublisher() != null ? getPublisher().getName() : "N/A")
                + " | Category: " + (getCategory() != null ? getCategory().getName() : "N/A");
    }
 
    public float getPrice() {
        return price;
    }
 
    public void setPrice(float price) {
        if (price >= 0) {
            this.price = price;
        }
    }
 
    public boolean checkAvailability() {
        return getStock() > 0;
    }
 
    public void updateStock(int quantity) {
        if (getStock() + quantity >= 0) {
            setStock(getStock() + quantity);
        }
    }
 
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
 
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
 
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
 
    public int getStock() { return stock; }
 
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
 
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
 
    public Publisher getPublisher() { return publisher; }
    public void setPublisher(Publisher publisher) { this.publisher = publisher; }
 
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    
    
 
    @Override
    public String toString() {
        return getInfo();
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}