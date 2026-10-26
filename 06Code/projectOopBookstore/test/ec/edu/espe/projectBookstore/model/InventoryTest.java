package ec.edu.espe.projectBookstore.model;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InventoryTest {

    public InventoryTest() {
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
    public void testGetReorderLevel() {
        System.out.println("getReorderLevel");
        Book book = new Book(1, "Cien Anos de Soledad", "978-0307474728", 25.50f, 15, "Realismo magico", null, null, null);
        Date today = new Date();
        Inventory instance = new Inventory(today, 5, book);
        int expResult = 5;
        int result = instance.getReorderLevel();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetLastRestockDate() {
        System.out.println("getLastRestockDate");
        Book book = new Book(2, "Don Quijote", "978-8424115203", 30.00f, 10, "Novela clasica", null, null, null);
        Date today = new Date();
        Inventory instance = new Inventory(today, 5, book);
        Date expResult = today;
        Date result = instance.getLastRestockDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBook() {
        System.out.println("getBook");
        Book book = new Book(3, "Rayuela", "978-8437604572", 22.99f, 8, "Novela", null, null, null);
        Date today = new Date();
        Inventory instance = new Inventory(today, 3, book);
        Book expResult = book;
        Book result = instance.getBook();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBookNotNull() {
        System.out.println("getBookNotNull");
        Book book = new Book(4, "La Ciudad y los Perros", "978-8420471839", 20.00f, 12, "Ficcion", null, null, null);
        Date today = new Date();
        Inventory instance = new Inventory(today, 4, book);
        Book result = instance.getBook();
        assertNotNull(result);
    }

    @Test
    public void testCheckReorderStatus() {
        System.out.println("checkReorderStatus");
        Book book = new Book(10, "El Tunel", "978-8437601502", 14.00f, 15, "Ficcion psicologica", null, null, null);
        Date today = new Date();
        Inventory instance = new Inventory(today, 5, book);
        boolean expResult = true;
        boolean result = instance.checkReorderStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetReorderLevel() {
        System.out.println("setReorderLevel");
        Book book = new Book(11, "Pedro Paramo", "978-8437604183", 15.00f, 20, "Ficcion", null, null, null);
        Date today = new Date();
        Inventory instance = new Inventory(today, 5, book);
        instance.setReorderLevel(10);
        int expResult = 25;
        int result = instance.getReorderLevel();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetLastRestockDate() {
        System.out.println("setLastRestockDate");
        Book book = new Book(12, "La Colmena", "978-8437600925", 18.00f, 10, "Novela", null, null, null);
        Date today = new Date();
        Date newDate = new Date(today.getTime() + 10000);
        Inventory instance = new Inventory(today, 5, book);
        instance.setLastRestockDate(newDate);
        Date expResult = today;
        Date result = instance.getLastRestockDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetBook() {
        System.out.println("setBook");
        Book book1 = new Book(13, "Ficciones", "978-8420633114", 19.00f, 7, "Cuentos", null, null, null);
        Book book2 = new Book(14, "El Aleph", "978-8420633121", 21.00f, 9, "Cuentos", null, null, null);
        Date today = new Date();
        Inventory instance = new Inventory(today, 5, book1);
        instance.setBook(book2);
        Book expResult = book1;
        Book result = instance.getBook();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateStock() {
        System.out.println("updateStock");
        Book book = new Book(15, "Los Pasos Perdidos", "978-8437603117", 17.50f, 10, "Novela", null, null, null);
        Date today = new Date();
        Inventory instance = new Inventory(today, 5, book);
        instance.updateStock(5);
        int expResult = 10;
        int result = instance.getBook().getStock();
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        Book book = new Book(16, "Aura", "978-8437601234", 12.00f, 6, "Fantasia", null, null, null);
        Date today = new Date();
        Inventory instance = new Inventory(today, 2, book);
        String expResult = "Inventory{lastRestockDate=null, reorderLevel=0, book=null}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}