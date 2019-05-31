@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlSchemaType(type = Date.class, name ="date")
//namespace needed for jaxb2-maven-plugin to rename output file
//@XmlSchema(namespace = "http://www.wojtis.com/jaxb", elementFormDefault = XmlNsForm.QUALIFIED)
package com.wojtis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlSchemaType;
import java.util.Date;

//XmlAccessType levels
//FIELD
//NONE
//PROPERTY
//PUBLIC_MEMBER