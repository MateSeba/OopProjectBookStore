/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ec.edu.espe.projectBookstore.model;

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
public class OrderDetailTest {
    private Book book;
    private OrderDetail detail;
    
    public OrderDetailTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        book = new Book(1, "Don Quijote de la Mancha", "978-0000000001", 22.99f, 10,"Classic novel", null, null, null);
        detail = new OrderDetail(1, 2, book, 10.0f);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateSubTotal method, of class OrderDetail.
     */
    @Test
    public void testCalculateSubTotal() {
        System.out.println("calculateSubTotal");
        assertEquals(20.0f, detail.calculateSubTotal(), 0.001f);
    }
 
    /**
     * Test of updateQuantity method, of class OrderDetail.
     */
    @Test
    public void testUpdateQuantity() {
        System.out.println("updateQuantity");
        detail.updateQuantity(5);
        assertEquals(5, detail.getQuantity());
    }
 
    /**
     * Test of updateQuantity method (subtotal), of class OrderDetail.
     */
    @Test
    public void testUpdateQuantityRecalculatesSubTotal() {
        System.out.println("updateQuantityRecalculatesSubTotal");
        detail.updateQuantity(5);
        assertEquals(50.0f, detail.getSubTotal(), 0.001f);
    }
 
    /**
     * Test of updateQuantity method with an invalid value, of class OrderDetail.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateQuantityInvalid() {
        System.out.println("updateQuantityInvalid");
        detail.updateQuantity(0);
    }
 
    /**
     * Test of setUnitPrice method with a negative value, of class OrderDetail.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetUnitPriceNegative() {
        System.out.println("setUnitPriceNegative");
        detail.setUnitPrice(-1.0f);
    }
 
    /**
     * Test of getBook method, of class OrderDetail.
     */
    @Test
    public void testGetBook() {
        System.out.println("getBook");
        assertSame(book, detail.getBook());
    }
  
    /**
     * Test of getQuantity method, of class OrderDetail.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        assertEquals(3, detail.getQuantity());
    }
 
    /**
     * Test of setQuantity method, of class OrderDetail.
     */
    @Test
    public void testSetQuantitySubTotal() {
        System.out.println("setQuantitySubTotal");
        detail.setQuantity(4);
        assertEquals(30.0f, detail.getSubTotal(), 0.001f);
    }
 
    /**
     * Test of setUnitPrice method, of class OrderDetail.
     */
    @Test
    public void testSetUnitPrice() {
        System.out.println("setUnitPrice");
        detail.setUnitPrice(15.0f);
        assertEquals(12.0f, detail.getUnitPrice(), 0.001f);
    }
 
    /**
     * Test of toString method, of class OrderDetail.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals("OrderDetail{book=N/A, quantity=0, unitPrice=0.0, subTotal=0.0}", detail.toString());
    }
 
}
 