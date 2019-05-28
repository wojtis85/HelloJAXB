package com.wojtis;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.xml.validation.Validator;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException, SAXException {
        System.out.println("Hello");

        Car kiaStinger = new Car("Kia","Stinger",60, 9,new Engine(EngineType.V,6,3333));
        kiaStinger.setCountry("Korea","KOR");
        kiaStinger.setSafetyFeatures(Arrays.asList("Air Bags", "Line Assist"));
        kiaStinger.setEcoCategory("");

        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<String> myComment = objectFactory.createItemComment("Super duper");
        //JAXBElement<String> myComment = objectFactory.createItemComment(null);
        //myComment.setNil(true); //it seems not to change anything
        kiaStinger.setComment(myComment);

        System.out.println("Kia Stinger range: " + kiaStinger.getRange());
        System.out.println("Kia Stinger engine: " + kiaStinger.getEngine().getEngineDesc());

        System.out.println("-----------------------------------");
        System.out.println("Marshall");

        // Create the JAXB context
        JAXBContext context = JAXBContext.newInstance(Car.class);
        //JAXBContext context = JAXBContext.newInstance(ObjectFactory.class); //takes class from object factory
        //JAXBContext context = JAXBContext.newInstance("com.wojtis");  //it will look for ObjectFactory and take class from there

        // Create a marshaller
        Marshaller marshaller = context.createMarshaller();
        // To make the XML easier to read for humans, specify that we want it to be formatted
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // Create a JAXB element wrapper
        //QName rootElementName = new QName(null, "car");
        //JAXBElement<com.wojtis.Car> rootElement = new JAXBElement<>(rootElementName, com.wojtis.Car.class, kiaStinger);
        // Marshal and output to the console
        //marshaller.marshal(rootElement, System.out);

        //Add schema validation
        SchemaFactory schemaFactory =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/main/resources/carSchema.xsd"));

        marshaller.setSchema(schema);
        marshaller.setEventHandler(new ExampleValidationEventHandler());

        marshaller.marshal(kiaStinger, System.out);

        System.out.println("-----------------------------------");
        System.out.println("Momeory object validation");

        Validator validator = schema.newValidator();
        validator.setErrorHandler(new ExampleErrorHandler());

        Source source = new JAXBSource(context, kiaStinger);
        validator.validate(source);


        System.out.println("-----------------------------------");
        System.out.println("Unmarshal");

        // Create the JAXB context
        //JAXBContext context = JAXBContext.newInstance(com.wojtis.Car.class);
        // Create an unmarshaller
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //Add schema validation
        //SchemaFactory schemaFactory =
        //        SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        //Schema schema = schemaFactory.newSchema(new File("src/main/resources/carSchema.xsd"));
        unmarshaller.setEventHandler(new ExampleValidationEventHandler());  //schema parsing error handler

        unmarshaller.setSchema(schema);
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
        System.out.println("New car eco category: " + kiaStinger2.getEcoCategory());
        System.out.println("New car comment: " + kiaStinger2.getCommentText());

        System.out.println("-----------------------------------");
        System.out.println("Generate an XML Schema");

        // Generate an XML Schema
        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                File outputFile = new File("car.xsd");
                System.out.println("Generating file: " + outputFile + " for namespace: " + namespaceUri);
                return new StreamResult(outputFile);
            }
        });

        System.out.println("-----------------------------------");
        System.out.println("XXXXX");

    }
}
