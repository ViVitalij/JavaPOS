package com.losK.controller;

import com.losK.model.Product;
import com.losK.service.BillService;
import com.losK.service.ProductService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Locale;

/**
 * Created by m.losK on 2017-04-14.
 */
public class ScanController extends Controller {

    @FXML
    private SplitPane paneStandard;

    @FXML
    private TextField textFieldProductCode;

    @FXML
    private TextField textFieldProductQuantity;

    @FXML
    private AnchorPane panePayment;

    @FXML
    private TextField sumTextField;

    @FXML
    private AnchorPane paneProductCode;

    @FXML
    private AnchorPane paneSummary;

    @FXML
    private Text summaryTextField;

    @FXML
    private Button btnClose;

    @FXML
    private TableView<Product> tableViewProducts;

    @FXML
    void addProductToBillOnAction(ActionEvent event) {

        String productCode = textFieldProductCode.getText();
        String userQuantity = textFieldProductQuantity.getText();
        ProductService productService = ProductService.getInstance();
        Product productByProductCode = productService.getProductByProductCode(productCode);
        if (productByProductCode != null) {
            productService.setProductByUserQuantity(productByProductCode, userQuantity);
            ObservableList<Product> listOfProducts = tableViewProducts.getItems();
            listOfProducts.add(productByProductCode);
            Double fullNewProductPrice = productService.getFullProductPrice(productByProductCode);
            refreshBillPrice(fullNewProductPrice);
        }
    }

    private void refreshBillPrice(Double fullProductPrice) {
        if (sumTextField.getText().equals("")) {
            sumTextField.setText(String.valueOf(fullProductPrice));
        } else {
            Double oldPriceToPay = Double.parseDouble(sumTextField.getText());
            Double newPriceToPay = oldPriceToPay + fullProductPrice;
            sumTextField.setText(String.format(Locale.US, "%.2f", newPriceToPay));
        }

    }

    @FXML
    void returnOnAction(ActionEvent event) {
        paneProductCode.setVisible(false);
        paneStandard.setVisible(true);
    }

    @FXML
    void enterProductCodeOnAction(ActionEvent event) {
        paneStandard.setVisible(false);
        paneProductCode.setVisible(true);
    }

    @FXML
    void approvePaymentOnAction(ActionEvent event) {
        panePayment.setVisible(false);
        paneStandard.setVisible(true);
    }

    @FXML
    void payOnAction(ActionEvent event) {
        paneStandard.setVisible(false);
        panePayment.setVisible(true);
    }

    @FXML
    void CancelOnAction(ActionEvent event) {
        tableViewProducts.getItems().clear();
        sumTextField.setText("");
    }

    private final String SUMMARY = "Thank you for shopping with us!\n\n Please, take the bill";

    @FXML
    void cashOnAction(ActionEvent event) {
        switchToSummaryPane();
        showSummary();
        BillService.printBill();
    }

    @FXML
    void cardOnAction(ActionEvent event) {
        switchToSummaryPane();
        showSummary();
        BillService.printBill();
    }

    void switchToSummaryPane() {
        panePayment.setVisible(false);
        paneSummary.setVisible(true);
    }

    void showSummary() {
        summaryTextField.setText(SUMMARY);
    }

    @FXML
    private void closeOnAction() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}

