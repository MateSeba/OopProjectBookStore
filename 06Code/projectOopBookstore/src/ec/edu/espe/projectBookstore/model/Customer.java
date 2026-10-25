                                                                       /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.projectBookstore.model;

/**
 *
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

    public Customer(int id, String name, String email, String phone, int age, String password, Address addresCustomer) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18 || age > 100) {
            throw new IllegalArgumentException("Edad no valida para un cliente");
        }
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener mínimo 8 caracteres");
        }
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("La dirección es invalida");
        }
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", nameCustomer=" + name + ", emailCustomer=" + email + ", phoneCustomer=" + phone + ", ageCustomer=" + age + ", passwordCustomer=" + password + ", addresCustomer=" + address + '}';
    }
    
}
