package ec.edu.espe.projectBookstore.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class Author {
 
    private int id;
    private String name;
    private String nationality;
    private String biography;
    private List<Book> books;   
 
    public Author(int id, String name, String nationality, String biography) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.biography = biography;
        this.books = new ArrayList<>();
    }
 
    public List<Book> getBooks() {
        return books;
    }
 
    public void addBook(Book book) {
        if (book != null && !books.contains(book)) {
            getBooks().add(book);
        }
    }
 
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
 
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
 
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
 
    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }
 
    @Override
    public String toString() {
        return "Author{id=" + getId() + ", name=" + getName() + ", nationality=" + getNationality() + "}";
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}