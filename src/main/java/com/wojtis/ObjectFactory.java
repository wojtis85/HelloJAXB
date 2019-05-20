package com.wojtis;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    @XmlElementDecl(namespace = "", name = "comment", scope = Car.class)
    public JAXBElement<String> createItemComment(String comment) {
        return new JAXBElement<>(new QName("", "comment"), String.class, Car.class, comment);
    }
}
