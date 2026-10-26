/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ec.edu.espe.projectBookstore.model;

import java.util.Date;
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
public class NotificationTest {
    private Customer customer;
    private Notification notification;
    
    public NotificationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Address address = new Address("Av. Amazonas", "Corea", "Norte", "Junto al parque");
        customer = new Customer(1, "Ana Perez", "ana@mail.com", "0991234567", 25, "password123", address);
        notification = new Notification(1, customer, "Your order was shipped", null, true);
    }
    
    @After
    public void tearDown() {
    }
    
     /**
     * Test of sendNotification method, of class Notification.
     */
    @Test
    public void testSendNotification() {
        System.out.println("sendNotification");
        notification.sendNotification();
        assertNotNull(notification.getSentDate());
    }
 
    /**
     * Test of sendNotification method (read flag), of class Notification.
     */
    @Test
    public void testSendNotificationSetsUnread() {
        System.out.println("sendNotificationSetsUnread");
        notification.sendNotification();
        assertFalse(notification.isRead());
    }
 
    /**
     * Test of markAsRead method, of class Notification.
     */
    @Test
    public void testMarkAsRead() {
        System.out.println("markAsRead");
        notification.sendNotification();
        notification.markAsRead();
        assertTrue(notification.isRead());
    }
 
    /**
     * Test of getMessage method, of class Notification.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        assertEquals("Your order was shipped", notification.getMessage());
    }
 
    /**
     * Test of getCustomer method, of class Notification.
     */
    @Test
    public void testGetCustomer() {
        System.out.println("getCustomer");
        assertSame(customer, notification.getCustomer());
    }
  
    /**
     * Test of setId method, of class Notification.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        notification.setId(20);
        assertEquals(10, notification.getId());
    }
 
    /**
     * Test of setMessage method, of class Notification.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        notification.setMessage("Payment received");
        assertEquals("Payment pending", notification.getMessage());
    }
 
    /**
     * Test of isRead method after markAsRead, of class Notification.
     */
    @Test
    public void testIsReadAfterMarkAsRead() {
        System.out.println("isReadAfterMarkAsRead");
        notification.markAsRead();
        assertFalse(notification.isRead());
    }
 
    /**
     * Test of setRead method, of class Notification.
     */
    @Test
    public void testSetRead() {
        System.out.println("setRead");
        notification.setRead(true);
        assertFalse(notification.isRead());
    }
 
    /**
     * Test of toString method, of class Notification.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals("Notification{id=999, message=Dummy, read=false}", notification.toString());
    }
 
}