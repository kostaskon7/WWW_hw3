/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Kostas
 */
@Entity
@Table(name="data")
public class Product_obj implements Serializable{


    @Id
    @Column(name = "Barcode", nullable = true)
    public int Barcode;
    @Basic(optional = false)
    @Column(name = "pname", nullable = true)
    public String pname;
    @Basic(optional = false)
    @Column(name = "color", nullable = true)
    public String Color;
    @Basic(optional = false)
    @Column(name = "Description", nullable = true)
    public String Description;

    public Product_obj() {
    }

    public Product_obj(int aInt, String string, String string0, String string1) {
        Barcode=aInt;
        pname=string;
        Color=string0;
        Description=string1;
    }



    public int getBarcode() {
        return Barcode;
    }

    public void setBarcode(int Barcode) {
        this.Barcode = Barcode;
    }

    
}
