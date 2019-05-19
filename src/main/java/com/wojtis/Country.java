package com.wojtis;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

public class Country {
    private String code = "";
    private String name = "";

    //@XmlTransient
    @XmlAttribute
    public String getCode() { return code; }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlValue  //works only if class have only one value
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
