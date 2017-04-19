package com.losK.util;

import com.losK.model.Products;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by m.losK on 2017-04-18.
 */
public class XMLReader {
    public static Products getProducts() {

        Products products = null;
        try {
            File file = new File("src/main/resources/Products.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            products = (Products) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return products;
    }

}
