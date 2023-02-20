/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author ASUS
 */
public class Product {
    private int id;
    private String productNo;
    private String name;
    private int quantity;
    private int priceImport;
    private int priceExport;
    private Group group;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(int priceImport) {
        this.priceImport = priceImport;
    }

    public int getPriceExport() {
        return priceExport;
    }

    public void setPriceExport(int priceExport) {
        this.priceExport = priceExport;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    

}
