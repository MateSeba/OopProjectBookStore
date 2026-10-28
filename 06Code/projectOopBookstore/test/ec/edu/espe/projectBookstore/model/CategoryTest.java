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
public class CategoryTest {

    public CategoryTest() {
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
        Category instance = new Category(1, "Ficcion", "Libros de ficcion y narrativa");
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        Category instance = new Category(2, "Historia", "Libros sobre acontecimientos historicos");
        String expResult = "Historia";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Category instance = new Category(3, "Ciencia", "Textos divulgativos y cientificos");
        String expResult = "Textos divulgativos y cientificos";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBooks() {
        System.out.println("getBooks");
        Category instance = new Category(4, "Filosofia", "Obras filosoficas clasicas y modernas");
        List<Book> result = instance.getBooks();
        assertNotNull(result);
    }

    @Test
    public void testGetBooksSize() {
        System.out.println("getBooksSize");
        Category instance = new Category(5, "Arte", "Libros de pintura, escultura y diseño");
        int expResult = 0;
        int result = instance.getBooks().size();
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        Category instance = new Category(6, "Poesia", "Colecciones de obras poeticas");
        String expResult = "Category{id=6, name=Poesia}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddBooksNull() {
        System.out.println("addBooksNull");
        Category instance = new Category(7, "Drama", "Obras teatrales y dramaticas");
        instance.addBooks(null);
        int expResult = 0;
        int result = instance.getBooks().size();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetId() {
        System.out.println("setId");
        Category instance = new Category(10, "Ficcion", "Narrativa contemporanea");
        instance.setId(500);
        int expResult = 500;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetName() {
        System.out.println("setName");
        Category instance = new Category(11, "Historia", "Acontecimientos del siglo XX");
        instance.setName("Biografia");
        String expResult = "Biografia";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        Category instance = new Category(12, "Ciencia", "Fisica y astronomia");
        instance.setDescription("Estudios de la materia");
        String expResult = "Estudios de la materia";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }
}