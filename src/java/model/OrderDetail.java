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
public class OrderDetail {
    private Order oid;
    private Product pid;
    private int quantity;
    private Product price;
    private int total;

    public OrderDetail() {
    }

    public Order getOid() {
        return oid;
    }

    public void setOid(Order oid) {
        this.oid = oid;
    }

    public Product getPid() {
        return pid;
    }

    public void setPid(Product pid) {
        this.pid = pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getPrice() {
        return price;
    }

    public void setPrice(Product price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


}
