package ec.edu.espe.projectBookstore.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class Publisher {
 
    private int id;
    private String name;
    private String description;
    private List<Book> books;   
 
    public Publisher(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.books = new ArrayList<>();
    }
 
    public void viewBooks() {
        System.out.println("Books published by " + name + ":");
        if (books.isEmpty()) {
            System.out.println("  (no books)");
        }
        for (Book book : books) {
            System.out.println("  - " + book.getTitle());
        }
    }
 
    public void addBook(Book book) {
        if (book != null && !books.contains(book)) {
            books.add(book);
        }
    }
 
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
 
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
 
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
 
    public List<Book> getBooks() { return books; }
 
    @Override
    public String toString() {
        return "Publisher{id=" + id + ", name=" + name + "}";
    }
}