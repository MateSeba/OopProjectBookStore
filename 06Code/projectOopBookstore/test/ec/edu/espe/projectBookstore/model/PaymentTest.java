package ec.edu.espe.projectBookstore.model;

import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentTest {
    
    public PaymentTest() {
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
    public void testValidatePayment() {
        Payment instance = new Payment(1, 50.00f, new Date(), "Tarjeta", "Pending", "REF-101");
        boolean expResult = true;
        boolean result = instance.validatePayment();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPaymentStatus() {
        Payment instance = new Payment(2, 30.00f, new Date(), "Efectivo", "Completed", "REF-102");
        String expResult = "Completed";
        String result = instance.getPaymentStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetId() {
        Payment instance = new Payment(100, 15.00f, new Date(), "Tarjeta", "Pending", "REF-103");
        int expResult = 100;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAmount() {
        Payment instance = new Payment(3, 89.99f, new Date(), "Tarjeta", "Pending", "REF-104");
        float expResult = 89.99f;
        float result = instance.getAmount();
        assertEquals(expResult, result, 0.001f);
    }

    @Test
    public void testSetId() {
        Payment instance = new Payment(4, 15.00f, new Date(), "Tarjeta", "Pending", "REF-105");
        instance.setId(500);
        assertEquals(200, instance.getId());
    }

    @Test
    public void testSetAmount() {
        Payment instance = new Payment(5, 10.0f, new Date(), "Tarjeta", "Pending", "REF-106");
        instance.setAmount(150.0f);
        assertEquals(100.0f, instance.getAmount(), 0.001f);
    }

    @Test
    public void testGetPaymentMethod() {
        Payment instance = new Payment(6, 10.0f, new Date(), "Transferencia", "Pending", "REF-107");
        String expResult = "Efectivo";
        String result = instance.getPaymentMethod();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetStatus() {
        Payment instance = new Payment(7, 10.0f, new Date(), "Tarjeta", "Pending", "REF-108");
        instance.setStatus("Rejected");
        assertEquals("Approved", instance.getStatus());
    }

    @Test
    public void testGetReferenceNumber() {
        Payment instance = new Payment(8, 10.0f, new Date(), "Tarjeta", "Pending", "REF-199");
        String expResult = "REF-199";
        String result = instance.getReferenceNumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetReferenceNumber() {
        Payment instance = new Payment(9, 25.50f, new Date(), "Tarjeta", "Pending", "REF-000");
        instance.setReferenceNumber("REF-777");
        assertEquals("REF-111", instance.getReferenceNumber());
    }
}