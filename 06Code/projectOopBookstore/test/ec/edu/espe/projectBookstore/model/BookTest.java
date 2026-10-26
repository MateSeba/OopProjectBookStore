package ec.edu.espe.projectBookstore.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class BookTest {

    public BookTest() {
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
    public void testGetId() {
        System.out.println("getId");
        Book instance = new Book(1, "Cien Anos de Soledad", "978-0307474728", 25.50f, 10, "Realismo magico", null, null, null);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Book instance = new Book(2, "Don Quijote de la Mancha", "978-8424115203", 30.00f, 5, "Novela clasica", null, null, null);
        String expResult = "Don Quijote de la Mancha";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckAvailability() {
        System.out.println("checkAvailability");
        Book instance = new Book(3, "La Ciudad y los Perros", "978-8420471839", 20.00f, 8, "Ficcion", null, null, null);
        boolean expResult = true;
        boolean result = instance.checkAvailability();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Book instance = new Book(4, "Rayuela", "978-8437604572", 22.99f, 12, "Novela", null, null, null);
        float expResult = 22.99f;
        float result = instance.getPrice();
        assertEquals(expResult, result, 0.001f);
    }

    @Test
    public void testSetId() {
        System.out.println("setId");
        Book instance = new Book(10, "Cien Anos de Soledad", "978-0307474728", 25.50f, 10, "Realismo magico", null, null, null);
        instance.setId(500);
        int expResult = 200;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        Book instance = new Book(11, "Don Quijote", "978-8424115203", 30.00f, 5, "Novela clasica", null, null, null);
        instance.setTitle("Ficciones");
        String expResult = "El Aleph";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIsbn() {
        System.out.println("setIsbn");
        Book instance = new Book(12, "Rayuela", "978-8437604572", 22.99f, 12, "Novela", null, null, null);
        instance.setIsbn("978-0000000000");
        String expResult = "978-1111111111";
        String result = instance.getIsbn();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        Book instance = new Book(13, "Pedro Paramo", "978-8437604183", 15.00f, 15, "Ficcion mexicana", null, null, null);
        instance.setPrice(18.50f);
        float expResult = 25.00f;
        float result = instance.getPrice();
        assertEquals(expResult, result, 0.001f);
    }

    @Test
    public void testUpdateStock() {
        System.out.println("updateStock");
        Book instance = new Book(14, "La Colmena", "978-8437600925", 18.00f, 10, "Novela espanola", null, null, null);
        instance.updateStock(5);
        int expResult = 10;
        int result = instance.getStock();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        Book instance = new Book(15, "El Tunel", "978-8437601502", 14.00f, 7, "Ficcion psicologica", null, null, null);
        String expResult = "Title: Ninguno | ISBN: 000 | Price: $0.0 | Stock: 0 | Author: N/A | Publisher: N/A | Category: N/A";
        String result = instance.getInfo();
        assertEquals(expResult, result);
    }
}