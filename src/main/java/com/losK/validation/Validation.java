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

    private static final String INVALID_BAR_CODE = "Invalid bar-code";
    private static final String PRODUCT_NOT_FOUND = "Product not found";
    private static final String POSITIVE_NUMBERS_ONLY = "Incorrect amount has been changed to one";

    protected static void showErrorAlert(String message) {
        showAlert(Alert.AlertType.ERROR, message);
    }

    protected static void showInformationAlert(String message) {
        showAlert(Alert.AlertType.INFORMATION, message);
    }

    private static void showAlert(Alert.AlertType alertType, String message) {
        new Alert(alertType, message).show();
    }

    public static boolean validateProductCode(String productCode) {
        boolean flag = false;
        if (isProductInDatabase(productCode)) {
            flag = true;
        } else if (productCode.equals("")) {
            showErrorAlert(INVALID_BAR_CODE);
        } else {
            showErrorAlert(PRODUCT_NOT_FOUND);
        }
        return flag;
    }

    public static boolean validateUserQuantity(String userQuantity) {
        boolean flag = false;
        if (userQuantity.matches("^[1-9]\\d*$")) {
            flag = true;
        } else {
            showInformationAlert(POSITIVE_NUMBERS_ONLY);
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
