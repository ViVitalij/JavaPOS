package com.losK.controller;

import com.losK.model.Product;
import com.losK.service.ProductService;
import com.losK.service.ReceiptService;
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
            refreshReceiptPrice(fullNewProductPrice);
        }
    }

    private void refreshReceiptPrice(Double fullProductPrice) {
        if (sumTextField.getText().equals("")) {
            sumTextField.setText(String.valueOf(fullProductPrice));
        } else {
            Double oldAmountToPay = Double.parseDouble(sumTextField.getText());
            Double newAmountToPay = oldAmountToPay + fullProductPrice;
            sumTextField.setText(String.format(Locale.US, "%.2f", newAmountToPay));
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

    private final String SUMMARY = "Thank you for shopping with us!\n\n Please, take the receipt";

    @FXML
    void cashOnAction(ActionEvent event) {
        makeSummary();
    }

    private void makeSummary() {
        switchToSummaryPane();
        showSummary();
        String receiptList = tableViewProducts.getItems().toString();
        String amountToPay = sumTextField.getText();
        ReceiptService.printReceipt(receiptList, amountToPay);
    }

    @FXML
    void cardOnAction(ActionEvent event) {
        makeSummary();
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

