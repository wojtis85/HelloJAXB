package com.wojtis;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JAXBException {
        System.out.println("Hello");

        Car kiaStinger = new Car("Kia","Stinger",60, 9,new Engine(EngineType.V,6,3333));
        kiaStinger.setCountry("Korea","KOR");
        kiaStinger.setSafetyFeatures(Arrays.asList("Air Bags", "Line Assist"));

        System.out.println("Kia Stinger range: " + kiaStinger.getRange());
        System.out.println("Kia Stinger engine: " + kiaStinger.getEngine().getEngineDesc());

        System.out.println("-----------------------------------");
        System.out.println("Marshall");

        // Create the JAXB context
        JAXBContext context = JAXBContext.newInstance(Car.class);

        // Create a marshaller
        Marshaller marshaller = context.createMarshaller();
        // To make the XML easier to read for humans, specify that we want it to be formatted
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // Create a JAXB element wrapper
        //QName rootElementName = new QName(null, "car");
        //JAXBElement<com.wojtis.Car> rootElement = new JAXBElement<>(rootElementName, com.wojtis.Car.class, kiaStinger);
        // Marshal and output to the console
        //marshaller.marshal(rootElement, System.out);
        marshaller.marshal(kiaStinger, System.out);

        System.out.println("-----------------------------------");
        System.out.println("Unmarshal");

        // Create the JAXB context
        //JAXBContext context = JAXBContext.newInstance(com.wojtis.Car.class);
        // Create an unmarshaller
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // Unmarshal the XML
        //JAXBElement<com.wojtis.Car> rootElement2 = unmarshaller.unmarshal(
        //        new StreamSource(new File("src/main/resources/car2.xml")),
        //        com.wojtis.Car.class);
        // Get the PurchaseOrder object from the JAXB element
        //com.wojtis.Car kiaStinger2 = rootElement2.getValue();
        Car kiaStinger2 = (Car) unmarshaller.unmarshal(new StreamSource(new File("src/main/resources/car2.xml")));
        System.out.println("New car: " + kiaStinger2.getBrand() + " " + kiaStinger2.getModel());
        System.out.println("New car engine: " + kiaStinger2.getEngine().getEngineDesc());
        System.out.println("New car create Date: " + kiaStinger2.createDate);
        System.out.println("New car country: " + kiaStinger2.getCountry().getName());
        System.out.println("New car country code: " + kiaStinger2.getCountry().getCode());
        System.out.println("New car safety features: " + kiaStinger2.getSafetyFeatures());



    }
}
