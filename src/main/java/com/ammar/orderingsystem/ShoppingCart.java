/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ammar.orderingsystem;

import java.util.ArrayList;

/**
 *
 * @author Ammar
 * 
 * ShoppingCart is the class for the application's shopping cart.
 * It stores all items in a list, and calculate total, discount if total>100
 * and updates the cart list and the checkout page.
 */
public class ShoppingCart {
    private final ArrayList<ShoppingCartItem> itemsList;
    
    public ShoppingCart() {
        this.itemsList = new ArrayList<>();
    }
    
    /*
    Calculate the total price of all items in the cart.
    */
    public double totalPrice(){
        double total = 0.0;
        
        for(int i = 0; i < this.itemsList.size(); i++){
            total += this.itemsList.get(i).totalPrice();
        }
        
        return total;
    }
    
    /*
    If price is below 100, show "No discount"
    else show "5% discount"
    */
    public String discountDisplay(){
        if(this.totalPrice() < 100) {
            return "No discount";
        }
        
        return "5% discount";
    }
    
    /*
    Update total, discount, new total and to pay total display on the checkout tab.
    */
    public void updateCheckoutDisplay(){
        OrderingSystemView.totalLabel.setText(Double.toString(this.totalPrice()));
        OrderingSystemView.discountLabel.setText(this.discountDisplay());
        OrderingSystemView.newTotalLabel.setText(Double.toString(this.totalPriceAfterDiscount()));
        OrderingSystemView.toPayLabel.setText(Double.toString(this.totalPriceAfterDiscount()));
    }
    
    /*
    Calculate total price after discount if total price is more or equal to 100.
    If total price is less than 100, return normal total price.
    */
    public double totalPriceAfterDiscount(){
        if(this.totalPrice() < 100){
            return this.totalPrice();
        }
        
        // Give 5% discount if cart total is more than RM100
        return this.totalPrice() * 0.95;
    }
    
    /*
    Empty itemsList, cartListModel and checkoutTableModel
    */
    public void checkout(double paying) {
        if(paying > this.totalPriceAfterDiscount())
            OrderingSystemView.balanceLabel.setText("Your balance is " + (paying - this.totalPriceAfterDiscount()));
        
        int size = this.itemsList.size();
        for(int i = 0; i < size; i++){
            this.removeItem(0);
        }
        
        OrderingSystemView.messageLabel.setText("Successfully checked out!");
    }
    
    /*
    Add item to the cart's itemsList,
    and update all label in the checkout tab.
    */
    public void addItem(ShoppingCartItem item){
        this.itemsList.add(item);
        String str = item.getName() + "\t\tx" + item.getQuantity();
        str = str.replaceAll("\t", "    ");
        OrderingSystemView.cartListModel.addElement(str);
        OrderingSystemView.checkoutTableModel.addRow(new Object[] {
            item.getName(),
            item.getQuantity(),
            item.getPrice()
        });
        this.updateCheckoutDisplay();
    }
    
    /*
    Remove item from the cart's itemList,
    and update all label in the checkout tab.
    */
    public void removeItem(int index) {
        this.itemsList.remove(index);
        OrderingSystemView.cartListModel.remove(index);
        OrderingSystemView.checkoutTableModel.removeRow(index);
        this.updateCheckoutDisplay();
    }

    /*
    Return a list of all items in the cart.
    */
    public ArrayList<ShoppingCartItem> getItems(){
        return this.itemsList;
    }
}
