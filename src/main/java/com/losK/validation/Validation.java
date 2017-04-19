package com.losK.validation;

import com.losK.model.Product;
import com.losK.model.Products;
import com.losK.service.XMLService;
import javafx.scene.control.Alert;

import java.util.List;

/**
 * Created by m.losK on 2017-04-14.
 */
public class Validation {

    private static final String ALERT_INVALID_CODE = "Invalid bar-code";
    private static final String ALERT_NO_PRODUCT = "Product not found";

    protected static void showErrorAlert(String message) {
        showAlert(Alert.AlertType.ERROR, message);
    }

    private static void showAlert(Alert.AlertType alertType, String message) {
        new Alert(alertType, message).show();
    }

    public static boolean validateProductCode(String productCode) {
        boolean flag = false;
        if (isProductInDatabase(productCode)) {
            flag = true;
        } else if (productCode.equals("")) {
            showErrorAlert(ALERT_INVALID_CODE);
        } else {
            showErrorAlert(ALERT_NO_PRODUCT);
        }
        return flag;
    }

    private static boolean isProductInDatabase(String productCode) {
        boolean flag;
        XMLService xmlService = new XMLService();
        Products products = xmlService.getProducts();
        List<Product> productsList = products.getProductsList();
        flag = productsList.stream()
                .anyMatch(product -> product.getId().equals(productCode));
        return flag;
    }
}
