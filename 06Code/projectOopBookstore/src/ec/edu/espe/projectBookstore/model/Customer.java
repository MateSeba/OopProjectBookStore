package ec.edu.espe.projectBookstore.model;

import java.util.List;

/**
 * @author Andres
 */
public class Customer {

    private int id;
    private String name;
    private String email;
    private String phone;
    private int age;
    private String password;
    private Address address;

    public Customer(int id, String name, String email, String phone, int age, String password, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        // The setters are used so the validations also apply when the object is created
        setAge(age);
        setPassword(password);
        setAddress(address);
    }
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        if (age < 18 || age > 100) {
            throw new IllegalArgumentException("Invalid age for a customer");
        }
        this.age = age;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("The password must have at least 8 characters");
        }
        this.password = password;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("The address is invalid");
        }
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name=" + name + ", email=" + email
                + ", phone=" + phone + ", age=" + age + ", address=" + address + "}";
    }
    
    public boolean register(List<Customer> customers) {
        if (customers == null) {
            throw new IllegalArgumentException("The customers list is invalid");
        }
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(this.email)) {
                return false;
            }
        }
        customers.add(this);
        return true;
    }
    
    public static Customer login(List<Customer> customers, String email, String password) {
    if (customers == null || email == null || password == null) {
        return null;
    }
    for (Customer customer : customers) {
        if (customer.getEmail().equalsIgnoreCase(email.trim()) && customer.getPassword().equals(password)) {
            return customer;
            }
    }
    return null;
    }
    
}

