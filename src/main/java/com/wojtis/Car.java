package com.wojtis;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(name = "", propOrder = {"brand", "model","tank","fuelConsumption","engine","country","safetyFeatures","ecoCategory","nickname","comment"})
@XmlRootElement(name = "car")
public class Car {
    @XmlAttribute(required = true)
    //@XmlSchemaType(name = "date")
    public Date createDate = new Date();
    private String brand;
    private String model;
    private int tank;
    private double fuelConsumption;
    @XmlTransient //can be use to ignore some field
    public String dummyField = "kopytko";
    private Engine engine;
    private Country country;
    private List<String> safetyFeatures;
    private String ecoCategory;
    @XmlElement(nillable = true )
    public String nickname;
    private JAXBElement<String> comment;

    @XmlElementRef(namespace = "", name = "comment", required = false)
    public JAXBElement<String> getComment() {
        return comment;
    }

    public void setComment(JAXBElement<String> comment) {
        this.comment = comment;
    }

    public String getCommentText() {
        if (comment != null ) {
            if (comment.isNil() == true) {
                return "nil";
            } else {return comment.getValue();}
        }
        return null;

    }



    @XmlElementWrapper(name = "safetyFeatures")
    @XmlElement(name = "safetyFeature", required = false)
    public List<String> getSafetyFeatures() {
        return safetyFeatures;
    }

    public void setSafetyFeatures(List<String> safetyFeatures) {
        this.safetyFeatures = safetyFeatures;
    }
    @XmlElement(defaultValue = "G")
    public String getEcoCategory() {
        return ecoCategory;
    }

    public void setEcoCategory(String ecoCategory) {
        this.ecoCategory = ecoCategory;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    public void setCountry(String name, String code) {
        this.country.setName(name);
        this.country.setCode(code);
    }

    @XmlElement(required = true)
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getTank() {
        return tank;
    }

    public void setTank(int tank) {
        this.tank = tank;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Car() {
    }

    public Car(String brand, String model, int tank, int fuelConsumption, Engine engine) {
        this.brand = brand;
        this.model = model;
        this.tank = tank;
        this.fuelConsumption = fuelConsumption;
        this.engine = engine;
        this.country = new Country();
    }

    public int getRange() {
        return (int) Math.floor(tank/fuelConsumption*100);
    }
}
