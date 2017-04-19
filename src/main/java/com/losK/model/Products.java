package com.losK.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by m.losK on 2017-04-17.
 */
@XmlRootElement(name = "products")
public class Products {

    private List<Product> productsList;

    @XmlElement(name = "product")
    public List<Product> getProductsList() {
        return productsList;
    }


    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }
}