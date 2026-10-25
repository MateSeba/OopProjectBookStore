/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.projectBookstore.model;

/**
 *
 * @author Andres
 */
public class OrderDetail {
    private int id;
    private int quantity;
    private Book book;
    private float unitPrice;
    private float subTotal;

    public OrderDetail(int id, int quantity, Book book, float unitPrice, float subTotal) {
        this.id = id;
        this.quantity = quantity;
        this.book = book;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
    
    
    
    
}
