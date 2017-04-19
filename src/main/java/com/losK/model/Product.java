package com.losK.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by m.losK on 2017-04-12.
 */
public class Product {
    private String name;
    private int quantity;
    private double unitPrice;
    private String id;

    public Product() {
    }

    public Product(String name, int quantity, double unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.id = "";
    }

    public Product(String name, int quantity, double unitPrice, String id) {
        this();
        this.id = id;
    }


    public String getName() {
        return name;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    @XmlAttribute(name = "quantity")
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @XmlAttribute(name = "unitPrice")
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }
}