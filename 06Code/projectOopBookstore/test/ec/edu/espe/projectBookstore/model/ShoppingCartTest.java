/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ec.edu.espe.projectBookstore.model;

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
public class ShoppingCartTest {
    private ShoppingCart cart;
    private Book book1;
    private Book book2;
    
    public ShoppingCartTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cart = new ShoppingCart(1, new Date(), 0.0f);
        book1 = new Book(1, "Don Quijote de la Mancha", "978-0000000001", 10.0f, 10, "Classic novel", null, null, null);
        book2 = new Book(2, "Ficciones", "978-0000000002", 20.0f, 1,"Short stories", null, null, null);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddBook() {
        System.out.println("addBook");
        cart.addBook(book1, 2);
        assertEquals(1, cart.getItems().size());
    }
 
    /**
     * Test of addBook method with the same book twice, of class ShoppingCart.
     */
    @Test
    public void testAddBookSameBookTwice() {
        System.out.println("addBookSameBookTwice");
        cart.addBook(book1, 2);
        cart.addBook(book1, 3);
        assertEquals(1, cart.getItems().size());
        assertEquals(5, cart.getItems().get(0).getQuantity());
    }
 
    /**
     * Test of calculateSubtotal method, of class ShoppingCart.
     */
    @Test
    public void testCalculateSubtotal() {
        System.out.println("calculateSubtotal");
        cart.addBook(book1, 2);
        cart.addBook(book2, 1);
        assertEquals(40.0f, cart.calculateSubtotal(), 0.001f);
    }
 
    /**
     * Test of removeBook method, of class ShoppingCart.
     */
    @Test
    public void testRemoveBook() {
        System.out.println("removeBook");
        cart.addBook(book1, 2);
        assertTrue(cart.removeBook(book1));
        assertTrue(cart.getItems().isEmpty());
    }
 
    /**
     * Test of removeBook method with a book that is not in the cart, of class ShoppingCart.
     */
    @Test
    public void testRemoveBookNotInCart() {
        System.out.println("removeBookNotInCart");
        cart.addBook(book1, 2);
        assertFalse(cart.removeBook(book2));
    }
 
    /**
     * Test of clearShoppingCart method, of class ShoppingCart.
     */
    @Test
    public void testClearShoppingCart() {
        System.out.println("clearShoppingCart");
        cart.addBook(book1, 2);
        cart.clearShoppingCart();
        assertTrue(cart.getItems().isEmpty());
        assertEquals(0.0f, cart.getSubTotal(), 0.001f);
    }
 
    /**
     * Test of updateStock method without enough stock, of class ShoppingCart.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateStockNotEnough() {
        System.out.println("updateStockNotEnough");
        cart.addBook(book2, 2);
        cart.updateStock();
    }
  
    /**
     * Test of addBook method (subtotal), of class ShoppingCart.
     */
    @Test
    public void testAddBookSubtotal() {
        System.out.println("addBookSubtotal");
        cart.addBook(book1, 2);
        assertEquals(10.0f, cart.getSubTotal(), 0.001f);
    }
 
    /**
     * Test of getContent method with an empty cart, of class ShoppingCart.
     */
    @Test
    public void testGetContentEmpty() {
        System.out.println("getContentEmpty");
        assertEquals("Cart is empty", cart.getContent());
    }
 
    /**
     * Test of updateStock method, of class ShoppingCart.
     */
    @Test
    public void testUpdateStock() {
        System.out.println("updateStock");
        cart.addBook(book1, 3);
        cart.updateStock();
        assertEquals(10, book1.getStock());
    }
 
}
 