package com.wojtis;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.PROPERTY)
//@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlType(propOrder = {"type", "noOfCylinders","sizeCc"})
@XmlRootElement
public class Engine {
    @XmlElement(required = true)
    public EngineType type;
    @XmlElement(required = true)
    public int noOfCylinders;
    private int sizeCc;
    private double sizeL;


    public Engine() {
    }

    public Engine(EngineType type, int noOfCylinders, int sizeCc) {
        this.type = type;
        this.noOfCylinders = noOfCylinders;
        setSizeCc(sizeCc);
    }


    public int getSizeCc() {
        return sizeCc;
    }

    public void setSizeCc(int sizeCc) {
        this.sizeCc = sizeCc;
        int places = 1;
        double scale = Math.pow(10, places);
        this.sizeL = Math.round((double)sizeCc/1000 * scale)/scale;
    }

    public double getSizeL() {
        return sizeL;
    }

    public String getEngineDesc() {
        return this.type.toString() + this.noOfCylinders +" "+ this.getSizeL()+"L";
    }
}
