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

    public boolean checkAvailability() {
        return getStock() > 0;
    }

    public void updateStock(int quantity) {
        if (getStock() + quantity >= 0) {
            setStock(getStock() + quantity);
        }
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("The stock cannot be negative");
        }
        this.stock = stock;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return the publisher
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return getInfo();
    }
}