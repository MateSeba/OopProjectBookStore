package ec.edu.espe.projectBookstore.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class Category {
 
    private int id;
    private String name;
    private String description;
    private List<Book> books; 
 
    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.books = new ArrayList<>();
    }
 
    // Operaciones del diagrama
    public List<Book> getBooks() {
        return books;
    }
 
    public void addBooks(Book book) {
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
 
    @Override
    public String toString() {
        return "Category{id=" + id + ", name=" + name + "}";
    }
}
 