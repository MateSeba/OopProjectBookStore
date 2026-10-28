package ec.edu.espe.projectBookstore.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Lino Diaz, Brain Training, @ESPE
 */
public class DeliveryDriverTest {

    public DeliveryDriverTest() {
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
    public void testCalculateCost() {
        DeliveryDriver instance = new DeliveryDriver("D-01", "Carlos Perez", "PBA-1234", "0991234567", "Available", null);
        float expResult = 5.0f;
        float result = instance.calculateCost();
        assertEquals(expResult, result, 0.001f);
    }

    @Test
    public void testUpdateStatus() {
        DeliveryDriver instance = new DeliveryDriver("D-02", "Juan Lopez", "PBB-5678", "0997654321", "Available", null);
        instance.updateStatus("On Duty");
        assertEquals("On Duty", instance.getStatus());
    }

    @Test
    public void testGetId() {
        DeliveryDriver instance = new DeliveryDriver("D-03", "Ana Gomez", "PBC-9012", "0981112233", "Busy", null);
        String expResult = "D-03";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSeeLocation() {
        Address address = new Address("Av. Shyris", "Holanda", "Norte", "Esquina");
        DeliveryDriver instance = new DeliveryDriver("D-04", "Luis Torres", "PBD-3456", "0984445566", "Available", address);
        instance.seeLocation();
        assertNotNull(instance.getCurrentLocation());
    }

    @Test
    public void testGetName() {
        DeliveryDriver instance = new DeliveryDriver("D-05", "Pedro Picapiedra", "PBE-7890", "0987778899", "Offline", null);
        String expResult = "Pedro Picapiedra";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetVehiclePlate() {
        DeliveryDriver instance = new DeliveryDriver("D-06", "Mario Bros", "PBF-1122", "0990001122", "Available", null);
        String expResult = "PBF-1122";
        String result = instance.getVehiclePlate();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetId() {
        DeliveryDriver instance = new DeliveryDriver("D-07", "Luigi Bros", "PBG-3344", "0993334455", "Available", null);
        instance.setId("D-100");
        assertEquals("D-100", instance.getId());
    }

    @Test
    public void testSetPhone() {
        DeliveryDriver instance = new DeliveryDriver("D-08", "Sonic", "PBH-5566", "0995556677", "Available", null);
        instance.setPhone("0999999999");
        assertEquals("0999999999", instance.getPhone());
    }

    @Test
    public void testSetStatus() {
        DeliveryDriver instance = new DeliveryDriver("D-09", "Tails", "PBI-7788", "0998889900", "Available", null);
        instance.setStatus("Offline");
        assertEquals("Offline", instance.getStatus());
    }

    @Test
    public void testToString() {
        DeliveryDriver instance = new DeliveryDriver("D-10", "Knuckles", "PBJ-9900", "0991113355", "Available", null);
        String expResult = "DeliveryDriver{id=D-10, name=Knuckles, vehiclePlate=PBJ-9900}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}