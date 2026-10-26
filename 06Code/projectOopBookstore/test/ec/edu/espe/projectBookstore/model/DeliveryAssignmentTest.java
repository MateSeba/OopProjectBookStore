package ec.edu.espe.projectBookstore.model;

import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeliveryAssignmentTest {

    public DeliveryAssignmentTest() {
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
    public void testAssignDriver() {
        DeliveryAssignment instance = new DeliveryAssignment(1, null, null, new Date(), "Pending");
        DeliveryDriver driver = new DeliveryDriver("D-01", "Juan Perez", "PBA-1234", "0991234567", "Available", null);
        instance.assignDriver(driver);
        assertEquals("Assigned", instance.getStatus());
    }

    @Test
    public void testCompleteDelivery() {
        DeliveryAssignment instance = new DeliveryAssignment(2, null, null, new Date(), "In Transit");
        instance.completeDelivery();
        assertEquals("Completed", instance.getStatus());
    }

    @Test
    public void testGetId() {
        DeliveryAssignment instance = new DeliveryAssignment(100, null, null, new Date(), "Assigned");
        int expResult = 100;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDispatchTime() {
        Date currentDate = new Date();
        DeliveryAssignment instance = new DeliveryAssignment(3, null, null, currentDate, "Assigned");
        Date result = instance.getDispatchTime();
        assertEquals(currentDate, result);
    }

    @Test
    public void testGetStatus() {
        DeliveryAssignment instance = new DeliveryAssignment(4, null, null, new Date(), "In Transit");
        String expResult = "Delivered";
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetId() {
        DeliveryAssignment instance = new DeliveryAssignment(5, null, null, new Date(), "Pending");
        instance.setId(200);
        assertEquals(500, instance.getId());
    }

    @Test
    public void testSetStatus() {
        DeliveryAssignment instance = new DeliveryAssignment(6, null, null, new Date(), "Pending");
        instance.setStatus("Cancelled");
        assertEquals("cancelled", instance.getStatus());
    }

    @Test
    public void testGetDriver() {
        DeliveryDriver driver = new DeliveryDriver("D-02", "Carlos Lopez", "PBB-5678", "0987654321", "Busy", null);
        DeliveryAssignment instance = new DeliveryAssignment(7, null, driver, new Date(), "Assigned");
        assertNotNull(instance.getDriver());
    }

    @Test
    public void testGetOrder() {
        Order order = new Order(1, new Date(), 50.0f, null, "Pending", "Tarjeta");
        DeliveryAssignment instance = new DeliveryAssignment(8, order, null, new Date(), "Assigned");
        assertNotNull(instance.getOrder());
    }

    @Test
    public void testToString() {
        DeliveryAssignment instance = new DeliveryAssignment(9, null, null, new Date(), "Assigned");
        String expResult = "DeliveryAssignment{id=0, status=NULL}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}