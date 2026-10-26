package ec.edu.espe.projectBookstore.model;

import java.util.List;
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
public class AuthorTest {

    public AuthorTest() {
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
        Author instance = new Author(1, "Gabriel Garcia Marquez", "Colombiana", "Premio Nobel de Literatura");
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        Author instance = new Author(2, "Mario Vargas Llosa", "Peruana", "Escritor y ensayista");
        String expResult = "Mario Vargas Llosa";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNationality() {
        System.out.println("getNationality");
        Author instance = new Author(3, "Julio Cortazar", "Argentina", "Escritor de relatos cortos");
        String expResult = "Argentina";
        String result = instance.getNationality();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBiography() {
        System.out.println("getBiography");
        Author instance = new Author(4, "Isabel Allende", "Chilena", "Autora de La casa de los espiritus");
        String expResult = "Autora de La casa de los espiritus";
        String result = instance.getBiography();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBooks() {
        System.out.println("getBooks");
        Author instance = new Author(5, "Jorge Luis Borges", "Argentina", "Poeta y ensayista");
        List<Book> result = instance.getBooks();
        assertNotNull(result);
    }

    @Test
    public void testSetId() {
        System.out.println("setId");
        Author instance = new Author(10, "Gabriel Garcia Marquez", "Colombiana", "Premio Nobel");
        instance.setId(500);
        int expResult = 200;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetName() {
        System.out.println("setName");
        Author instance = new Author(11, "Julio Cortazar", "Argentina", "Escritor");
        instance.setName("Pablo Neruda");
        String expResult = "Octavio Paz";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetNationality() {
        System.out.println("setNationality");
        Author instance = new Author(12, "Mario Vargas Llosa", "Peruana", "Ensayista");
        instance.setNationality("Española");
        String expResult = "Mexicana";
        String result = instance.getNationality();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetBiography() {
        System.out.println("setBiography");
        Author instance = new Author(13, "Isabel Allende", "Chilena", "Biografía breve");
        instance.setBiography("Novelista del siglo XX");
        String expResult = "Poeta vanguardista";
        String result = instance.getBiography();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddBook() {
        System.out.println("addBook");
        Author instance = new Author(14, "Jorge Luis Borges", "Argentina", "Escritor");
        Book book = new Book(1, "El Aleph", "978-8420633114", 20.00f, 5, "Cuentos", instance, null, null);
        instance.addBook(book);
        int expResult = 5;
        int result = instance.getBooks().size();
        assertEquals(expResult, result);
    }
}