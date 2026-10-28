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
public class AddressTest {

    public AddressTest() {
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
    public void testGetFormattedAddress() {
        Address instance = new Address("Av. Amazonas", "Corea", "Norte", "Junto al parque");
        String expResult = "Av. Amazonas and Corea, Norte (Junto al parque)";
        String result = instance.getFormattedAddress();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMainStreet() {
        Address instance = new Address("Calle Caldas", "Guayaquil", "Centro", "Frente al banco");
        String expResult = "Calle Caldas";
        String result = instance.getMainStreet();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSector() {
        Address instance = new Address("Av. Prensa", "Pinto", "Sur", "Cerca del metro");
        String expResult = "Sur";
        String result = instance.getSector();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCoordinates() {
        Address instance = new Address("Av. Shyris", "Holanda", "Norte", "Esquina");
        instance.getCoordinates();
        assertNotNull(instance);
    }

    @Test
    public void testGetSecondaryStreet() {
        Address instance = new Address("Av. Colon", "10 de Agosto", "Centro", "Diagonal al farmacia");
        String expResult = "10 de Agosto";
        String result = instance.getSecondaryStreet();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetReference() {
        Address instance = new Address("Av. Patria", "12 de Octubre", "La Mariscal", "Edificio Azul");
        String expResult = "Edificio Azul";
        String result = instance.getReference();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetMainStreet() {
        Address instance = new Address("Calle A", "Calle B", "Valle", "Km 5");
        instance.setMainStreet("Calle Principal");
        assertEquals("Calle Principal", instance.getMainStreet());
    }

    @Test
    public void testSetSecondaryStreet() {
        Address instance = new Address("Calle A", "Calle B", "Valle", "Km 5");
        instance.setSecondaryStreet("Calle Secundaria");
        assertEquals("Calle Secundaria", instance.getSecondaryStreet());
    }

    @Test
    public void testSetSector() {
        Address instance = new Address("Calle A", "Calle B", "Sur", "Km 5");
        instance.setSector("Norte");
        assertEquals("Norte", instance.getSector());
    }

    @Test
    public void testToString() {
        Address instance = new Address("Av. Napo", "Upano", "Sur", "Parada de bus");
        String expResult = "Address{mainStreet=Av. Napo, sector=Sur}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}