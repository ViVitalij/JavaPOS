package com.losK.service;

import com.losK.model.Products;
import com.losK.util.XMLReader;

/**
 * Created by m.losK on 2017-04-18.
 */
public class XMLService {
    public Products getProducts() {

        return XMLReader.getProducts();
    }
}
