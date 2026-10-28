package ec.edu.espe.projectBookstore.model;

import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Lino Diaz, Brain Training, @ESPE
 */
public class OrderTest {

    public OrderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConfirmOrder() {
        Order instance = new Order(1, new Date(), 100.0f, null, "Pending", "Tarjeta");
        instance.confirmOrder();
        assertEquals("Confirmed", instance.getStatus());
    }

    @Test
    public void testCalculateTotal() {
        Order instance = new Order(2, new Date(), 0.0f, null, "Pending", "Efectivo");
        instance.addDetail(new OrderDetail(1, 2, null, 75.25f));
        float expResult = 150.50f;
        float result = instance.calculateTotal();
        assertEquals(expResult, result, 0.001f);
    }

    @Test
    public void testGetId() {
        Order instance = new Order(10, new Date(), 50.0f, null, "Pending", "Tarjeta");
        int expResult = 10;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPaymentMethod() {
        Order instance = new Order(3, new Date(), 75.0f, null, "Pending", "Transferencia");
        String expResult = "Transferencia";
        String result = instance.getPaymentMethod();
        assertEquals(expResult, result);
    }

    @Test
    public void testCancelOrder() {
        Order instance = new Order(4, new Date(), 80.0f, null, "Pending", "Tarjeta");
        instance.cancelOrder();
        assertEquals("Cancelled", instance.getStatus());
    }

    @Test
    public void testUpdateStatus() {
        Order instance = new Order(5, new Date(), 120.0f, null, "Pending", "Efectivo");
        instance.updateStatus("Shipped");
        assertEquals("Shipped", instance.getStatus());
    }

    @Test
    public void testSetId() {
        Order instance = new Order(6, new Date(), 200.0f, null, "Pending", "Tarjeta");
        instance.setId(100);
        assertEquals(100, instance.getId());
    }

    @Test
    public void testSetTotal() {
        Order instance = new Order(7, new Date(), 45.0f, null, "Pending", "Efectivo");
        instance.setTotal(90.0f);
        assertEquals(90.0f, instance.getTotal(), 0.001f);
    }

    @Test
    public void testSetPaymentMethod() {
        Order instance = new Order(8, new Date(), 30.0f, null, "Pending", "Efectivo");
        instance.setPaymentMethod("Paypal");
        assertEquals("Paypal", instance.getPaymentMethod());
    }

    @Test
    public void testToString() {
        Order instance = new Order(9, new Date(), 60.0f, null, "Pending", "Tarjeta");
        String expResult = "Order{id=9, total=60.0, status=Pending}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testCancelDeliveredOrderIsNotAllowed() {
        Order instance = new Order(11, new Date(), 0f, null, "Delivered", "Tarjeta");
        try {
            instance.cancelOrder();
        } catch (IllegalArgumentException | IllegalStateException e) {
        }
        assertEquals("Delivered", instance.getStatus());
    }

    @Test
    public void testConfirmEmptyOrderIsNotAllowed() {
        Order instance = new Order(12, new Date(), 0f, null, "Pending", "Efectivo");
        try {
            instance.confirmOrder();
        } catch (IllegalArgumentException | IllegalStateException e) {
        }
        assertEquals("Pending", instance.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTotalNegativeIsRejected() {
        Order instance = new Order(13, new Date(), 0f, null, "Pending", "Tarjeta");
        instance.setTotal(-50.0f);
    }
}
