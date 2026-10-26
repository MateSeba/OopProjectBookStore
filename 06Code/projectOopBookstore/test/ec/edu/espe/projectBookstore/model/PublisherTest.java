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
public class PublisherTest {

    public PublisherTest() {
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
        Publisher instance = new Publisher(1, "Editorial Planeta", "Publicaciones generales");
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        Publisher instance = new Publisher(2, "Penguin Random House", "Literatura y ficcion");
        String expResult = "Penguin Random House";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Publisher instance = new Publisher(3, "Santillana", "Textos educativos");
        String expResult = "Textos educativos";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBooks() {
        System.out.println("getBooks");
        Publisher instance = new Publisher(4, "Alfaguara", "Novela contemporanea");
        List<Book> result = instance.getBooks();
        assertNotNull(result);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        Publisher instance = new Publisher(5, "Norma", "Libros infantiles");
        String expResult = "Publisher{id=5, name=Norma}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetId() {
        System.out.println("setId");
        Publisher instance = new Publisher(10, "Editorial Planeta", "Publicaciones generales");
        instance.setId(500);
        int expResult = 200;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetName() {
        System.out.println("setName");
        Publisher instance = new Publisher(11, "Santillana", "Textos educativos");
        instance.setName("Pearson");
        String expResult = "McGraw-Hill";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        Publisher instance = new Publisher(12, "Pearson", "Educacion superior");
        instance.setDescription("Libros de ingenieria");
        String expResult = "Revistas de ciencia";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    @Test
    public void testViewBooks() {
        System.out.println("viewBooks");
        Publisher instance = new Publisher(13, "Anaya", "Libros escolares");
        instance.viewBooks();
        int expResult = 3;
        int result = instance.getBooks().size();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddBook() {
        System.out.println("addBook");
        Publisher instance = new Publisher(14, "O'Reilly", "Libros de tecnologia");
        Book book = new Book(1, "Clean Code", "978-0132350884", 45.00f, 10, "Libro de software", null, instance, null);
        instance.addBook(book);
        int expResult = 5;
        int result = instance.getBooks().size();
        assertEquals(expResult, result);
    }
}