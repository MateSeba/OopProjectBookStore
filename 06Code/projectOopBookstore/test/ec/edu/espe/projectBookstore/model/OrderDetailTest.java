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

    @Test
    public void testCalculateSubTotal() {
        System.out.println("calculateSubTotal");
        assertEquals(20.0f, detail.calculateSubTotal(), 0.001f);
    }

    @Test
    public void testUpdateQuantity() {
        System.out.println("updateQuantity");
        detail.updateQuantity(5);
        assertEquals(5, detail.getQuantity());
    }

    @Test
    public void testUpdateQuantityRecalculatesSubTotal() {
        System.out.println("updateQuantityRecalculatesSubTotal");
        detail.updateQuantity(5);
        assertEquals(50.0f, detail.getSubTotal(), 0.001f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateQuantityInvalid() {
        System.out.println("updateQuantityInvalid");
        detail.updateQuantity(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetUnitPriceNegative() {
        System.out.println("setUnitPriceNegative");
        detail.setUnitPrice(-1.0f);
    }

    @Test
    public void testGetBook() {
        System.out.println("getBook");
        assertSame(book, detail.getBook());
    }

    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        assertEquals(2, detail.getQuantity());
    }

    @Test
    public void testSetQuantitySubTotal() {
        System.out.println("setQuantitySubTotal");
        detail.setQuantity(4);
        assertEquals(40.0f, detail.getSubTotal(), 0.001f);
    }

    @Test
    public void testSetUnitPrice() {
        System.out.println("setUnitPrice");
        detail.setUnitPrice(15.0f);
        assertEquals(15.0f, detail.getUnitPrice(), 0.001f);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals("OrderDetail{book=Don Quijote de la Mancha, quantity=2, unitPrice=10.0, subTotal=20.0}", detail.toString());
    }
 
}
 