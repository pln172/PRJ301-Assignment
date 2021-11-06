/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Import {
    private int id;
    private String importNo;
    private Timestamp date;
    private int total;
    private ArrayList<ImportDetail> importDetails = new ArrayList<>();

    public Import() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImportNo() {
        return importNo;
    }

    public void setImportNo(String importNo) {
        this.importNo = importNo;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<ImportDetail> getImportDetails() {
        return importDetails;
    }

    public void setImportDetails(ArrayList<ImportDetail> importDetails) {
        this.importDetails = importDetails;
    }
    
}
