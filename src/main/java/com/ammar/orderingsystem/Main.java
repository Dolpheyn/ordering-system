/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ammar.orderingsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dolpheyn
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // Load items from file
        ArrayList<Item> itemsList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("items.txt"));
            String line;
            StringTokenizer tokenizer;
            while((line = reader.readLine()) != null){
                tokenizer = new StringTokenizer(line, ",");
                String name = tokenizer.nextToken();
                String description = tokenizer.nextToken();
                double price = Double.parseDouble(tokenizer.nextToken());
                Item item = new Item(name, description, price);
                itemsList.add(item);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Invoke View
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderingSystemView(itemsList).setVisible(true);
            }
        });
    }
}
