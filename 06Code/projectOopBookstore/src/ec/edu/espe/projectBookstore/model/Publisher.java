package ec.edu.espe.projectBookstore.model;

import java.util.ArrayList;
import java.util.Collections;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the books (read-only view, use addBook() to add)
     */
    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    @Override
    public String toString() {
        return "Publisher{id=" + id + ", name=" + name + "}";
    }
}