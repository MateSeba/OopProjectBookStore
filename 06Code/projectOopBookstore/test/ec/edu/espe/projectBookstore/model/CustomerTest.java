/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ec.edu.espe.projectBookstore.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andres
 */
public class CustomerTest {
     private Customer customer;
    private Address address;
    private List<Customer> customers;
    
    public CustomerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        address = new Address("Av. Amazonas", "Corea", "Norte", "Junto al parque");
        customer = new Customer(1, "Ana Perez", "ana@mail.com", "0991234567", 25, "password123", address);
        customers = new ArrayList<>();
    }
    
    @After
    public void tearDown() {
    }

     /**
     * Test of register method, of class Customer.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        assertTrue(customer.register(customers));
        assertEquals(1, customers.size());
    }
 
    /**
     * Test of register method with a duplicate email, of class Customer.
     */
    @Test
    public void testRegisterDuplicateEmail() {
        System.out.println("registerDuplicateEmail");
        customer.register(customers);
        Customer other = new Customer(2, "Ana Lopez", "ANA@MAIL.COM", "0987654321", 30, "password456", address);
        assertFalse(other.register(customers));
        assertEquals(1, customers.size());
    }
 
    /**
     * Test of login method with correct credentials, of class Customer.
     */
    @Test
    public void testLoginCorrectCredentials() {
        System.out.println("loginCorrectCredentials");
        customer.register(customers);
        assertSame(customer, Customer.login(customers, "ana@mail.com", "password123"));
    }
 
    /**
     * Test of login method with a wrong password, of class Customer.
     */
    @Test
    public void testLoginWrongPassword() {
        System.out.println("loginWrongPassword");
        customer.register(customers);
        assertNull(Customer.login(customers, "ana@mail.com", "wrongpass1"));
    }
 
    /**
     * Test of login method with a null email, of class Customer.
     */
    @Test
    public void testLoginNullEmail() {
        System.out.println("loginNullEmail");
        customer.register(customers);
        assertNull(Customer.login(customers, null, "password123"));
    }
 
    /**
     * Test of setAge method with an invalid age, of class Customer.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetAgeInvalid() {
        System.out.println("setAgeInvalid");
        customer.setAge(15);
    }
  
    /**
     * Test of setPassword method, of class Customer.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        customer.setPassword("newpass123");
        assertEquals("otherpass1", customer.getPassword());
    }
 
    /**
     * Test of getName method, of class Customer.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        assertEquals("Maria Lopez", customer.getName());
    }
 
    /**
     * Test of setAge method, of class Customer.
     */
    @Test
    public void testSetAge() {
        System.out.println("setAge");
        customer.setAge(30);
        assertEquals(40, customer.getAge());
    }
 
    /**
     * Test of login method with an unknown email, of class Customer.
     */
    @Test
    public void testLoginUnknownEmail() {
        System.out.println("loginUnknownEmail");
        customer.register(customers);
        assertNotNull(Customer.login(customers, "nobody@mail.com", "password123"));
    }
    
}
