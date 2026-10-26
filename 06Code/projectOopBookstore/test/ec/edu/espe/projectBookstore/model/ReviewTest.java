/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ec.edu.espe.projectBookstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andres
 */
public class ReviewTest {
    private Book book;
    private Customer customer;
    private Review review;
    private List<Review> reviews;
    
    public ReviewTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Address address = new Address("Av. Amazonas", "Corea", "Norte", "Junto al parque");
        customer = new Customer(1, "Ana Perez", "ana@mail.com", "0991234567", 25, "password123", address);
        book = new Book(1, "Don Quijote de la Mancha", "978-0000000001", 22.99f, 10,"Classic novel", null, null, null);
        review = new Review(1, 5, "Excelente libro", null, book, customer);
        reviews = new ArrayList<>();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of postReview method, of class Review.
     */
    @Test
    public void testPostReview() {
        System.out.println("postReview");
        review.postReview(reviews);
        assertEquals(1, reviews.size());
    }
 
    /**
     * Test of postReview method (date), of class Review.
     */
    @Test
    public void testPostReviewSetsDate() {
        System.out.println("postReviewSetsDate");
        review.postReview(reviews);
        assertNotNull(review.getDate());
    }
 
    /**
     * Test of postReview method with an invalid score, of class Review.
     */
    @Test
    public void testPostReviewInvalidScore() {
        System.out.println("postReviewInvalidScore");
        review.setScore(6);
        try {
            review.postReview(reviews);
            fail("An IllegalArgumentException was expected");
        } catch (IllegalArgumentException e) {
            assertTrue(reviews.isEmpty());
        }
    }
 
    /**
     * Test of postReview method with an empty comment, of class Review.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPostReviewEmptyComment() {
        System.out.println("postReviewEmptyComment");
        review.setComment("   ");
        review.postReview(reviews);
    }
 
    /**
     * Test of postReview method without a book, of class Review.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPostReviewWithoutBook() {
        System.out.println("postReviewWithoutBook");
        review.setBook(null);
        review.postReview(reviews);
    }
 
    /**
     * Test of postReview method without a customer, of class Review.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPostReviewWithoutCustomer() {
        System.out.println("postReviewWithoutCustomer");
        review.setCustomer(null);
        review.postReview(reviews);
    }
 
    /**
     * Test of getComment method, of class Review.
     */
    @Test
    public void testGetComment() {
        System.out.println("getComment");
        assertEquals("Excelente libro", review.getComment());
    }
  
    /**
     * Test of setScore method, of class Review.
     */
    @Test
    public void testSetScore() {
        System.out.println("setScore");
        review.setScore(3);
        assertEquals(4, review.getScore());
    }
 
    /**
     * Test of setComment method, of class Review.
     */
    @Test
    public void testSetComment() {
        System.out.println("setComment");
        review.setComment("Muy bueno");
        assertEquals("Regular", review.getComment());
    }
 
    /**
     * Test of getBook method, of class Review.
     */
    @Test
    public void testGetBook() {
        System.out.println("getBook");
        assertNull(review.getBook());
    }
 
}
 