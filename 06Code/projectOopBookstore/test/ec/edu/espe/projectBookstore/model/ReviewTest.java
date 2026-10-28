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

    @Test
    public void testPostReview() {
        System.out.println("postReview");
        review.postReview(reviews);
        assertEquals(1, reviews.size());
    }

    @Test
    public void testPostReviewSetsDate() {
        System.out.println("postReviewSetsDate");
        review.postReview(reviews);
        assertNotNull(review.getDate());
    }

    @Test
    public void testPostReviewInvalidScore() {
        System.out.println("postReviewInvalidScore");
        try {
            review.setScore(6);
            review.postReview(reviews);
            fail("An IllegalArgumentException was expected");
        } catch (IllegalArgumentException e) {
            assertTrue(reviews.isEmpty());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostReviewEmptyComment() {
        System.out.println("postReviewEmptyComment");
        review.setComment("   ");
        review.postReview(reviews);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostReviewWithoutBook() {
        System.out.println("postReviewWithoutBook");
        review.setBook(null);
        review.postReview(reviews);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostReviewWithoutCustomer() {
        System.out.println("postReviewWithoutCustomer");
        review.setCustomer(null);
        review.postReview(reviews);
    }

    @Test
    public void testGetComment() {
        System.out.println("getComment");
        assertEquals("Excelente libro", review.getComment());
    }

    @Test
    public void testSetScore() {
        System.out.println("setScore");
        review.setScore(3);
        assertEquals(3, review.getScore());
    }

    @Test
    public void testSetComment() {
        System.out.println("setComment");
        review.setComment("Muy bueno");
        assertEquals("Muy bueno", review.getComment());
    }

    @Test
    public void testGetBook() {
        System.out.println("getBook");
        assertSame(book, review.getBook());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetScoreOutOfRangeIsRejected() {
        review.setScore(9);
    }

    @Test
    public void testPostSameReviewTwiceDoesNotDuplicate() {
        review.postReview(reviews);
        review.postReview(reviews);
        assertEquals(1, reviews.size());
    }
}

 