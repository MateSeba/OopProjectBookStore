package ec.edu.espe.projectBookstore.model;

import java.util.ArrayList;
import java.util.Collections;
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
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the biography
     */
    public String getBiography() {
        return biography;
    }

    /**
     * @param biography the biography to set
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     * @return the books (read-only view, use addBook() to add)
     */
    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{id=" + getId() + ", name=" + getName() + ", nationality=" + getNationality() + "}";
    }
}