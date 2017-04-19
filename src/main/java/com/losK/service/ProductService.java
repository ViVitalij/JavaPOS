package com.losK.service;

import com.losK.model.Product;
import com.losK.model.Products;
import com.losK.validation.Validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.losK on 2017-04-13.
 */
public class ProductService {


    private static ProductService instance = null;

    public ProductService() {
        this.productList = new ArrayList<>();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }


    public Product getProductByProductCode(String productCode) {
        Product productByProductCode = null;
        if (Validation.validateProductCode(productCode)) {

            XMLService xmlService = new XMLService();
            Products products = xmlService.getProducts();
            List<Product> productsList = products.getProductsList();
            productByProductCode = productsList.stream()
                    .filter(product -> product.getId().equals(productCode))
                    .findAny()
                    .get();
        }
        return productByProductCode;
    }

    public Product setProductByUserQuantity(Product productByProductCode, String userQuantity) {
        if (userQuantity == null || userQuantity.equals("")) {
            productByProductCode.setQuantity(1);
        } else {
            productByProductCode.setQuantity(Integer.parseInt(userQuantity));
        }
        return productByProductCode;
    }

    public Double getFullProductPrice(Product productByProductCode) {
        Integer productQuantity = productByProductCode.getQuantity();
        Double productPrice = productByProductCode.getUnitPrice();
        Double wholePrice = productQuantity * productPrice;
        return wholePrice;
    }
}