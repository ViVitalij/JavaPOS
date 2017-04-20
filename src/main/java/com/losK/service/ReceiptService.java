package com.losK.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

/**
 * Created by m.losK on 2017-04-19.
 */
public class ReceiptService {

    /**
     * In normal process use Gson and FileUtils. Below for learning purpose only.
     */
    public static void printReceipt(String billList, String amountToPay) {
        String joiner = new StringJoiner("\n")
                .add("Loska sp. z o.o")
                .add("Receipt:")
                .add(billList)
                .add("To pay:")
                .add(amountToPay)
                .add("Hope to see you soon!")
                .toString();
        try {
            Files.write(Paths.get("./Receipt.txt"), joiner.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
