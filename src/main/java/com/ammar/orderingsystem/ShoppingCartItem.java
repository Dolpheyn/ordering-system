/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ammar.orderingsystem;

/**
 *
 * @author Ammar
 * 
 * ShoppingCartItem is a class for a single item in the shopping cart's item list.
 * It is an inheritance class from the class Item, with an additional attribute - quantity,
 * to represent the quantity of item the user add into the cart.
 */
public class ShoppingCartItem extends Item{
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public ShoppingCartItem(String name, String description, double price, int quantity) {
        super(name, description, price);
        this.quantity = quantity;
    }
    
    public void addQuantity(int qty) {
        this.quantity += qty;
    }
    
    /*
    Returns the price of the item multiplied by the quantity.
    */
    public double totalPrice(){
        return this.quantity * this.getPrice();
    }
}
