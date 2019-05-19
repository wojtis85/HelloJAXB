package com.wojtis;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)  //that is default option
public enum EngineType {
    V,
    @XmlEnumValue("LINE") R,
    W,
    @XmlEnumValue("BOXER") B
}
