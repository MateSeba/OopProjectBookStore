package ec.edu.espe.projectBookstore.model;

import java.util.Date;

/**
 *
 * @author Andres
 */
public class Review {

    private int id;
    private int score;
    private String comment;
    private Date date;
    private Book book;
    private Customer customer;

    public Review(int id, int score, String comment, Date date, Book book, Customer customer) {
        this.id = id;
        this.score = score;
        this.comment = comment;
        this.date = date;
        this.book = book;
        this.customer = customer;
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
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Review{" + "id=" + id + ", score=" + score + ", comment=" + comment + ", date=" + date + ", book=" + book + ", customer=" + customer + '}';
    }
}
