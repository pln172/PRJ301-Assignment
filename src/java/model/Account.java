/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Account {
    private String username;
    private String password;
    private Employee employee;
    private String right;
//    private ArrayList<Feature> features = new ArrayList<>();

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    

//    public ArrayList<Feature> getFeatures() {
//        return features;
//    }
//
//    public void setFeatures(ArrayList<Feature> features) {
//        this.features = features;
//    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
    
    
}
