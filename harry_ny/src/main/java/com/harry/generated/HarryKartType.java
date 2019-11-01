//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.01 at 12:01:54 AM CET 
//


package com.harry.generated;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for harryKartType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="harryKartType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numberOfLoops" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="startList" type="{}startListType"/>
 *         &lt;element name="powerUps" type="{}powerUpsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "harryKartType", propOrder = {
    "numberOfLoops",
    "startList",
    "powerUps"
})
public class HarryKartType {

    @XmlElement(required = true)
    protected BigInteger numberOfLoops;
    @XmlElement(required = true)
    protected StartListType startList;
    @XmlElement(required = true)
    protected PowerUpsType powerUps;

    /**
     * Gets the value of the numberOfLoops property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberOfLoops() {
        return numberOfLoops;
    }

    /**
     * Sets the value of the numberOfLoops property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberOfLoops(BigInteger value) {
        this.numberOfLoops = value;
    }

    /**
     * Gets the value of the startList property.
     * 
     * @return
     *     possible object is
     *     {@link StartListType }
     *     
     */
    public StartListType getStartList() {
        return startList;
    }

    /**
     * Sets the value of the startList property.
     * 
     * @param value
     *     allowed object is
     *     {@link StartListType }
     *     
     */
    public void setStartList(StartListType value) {
        this.startList = value;
    }

    /**
     * Gets the value of the powerUps property.
     * 
     * @return
     *     possible object is
     *     {@link PowerUpsType }
     *     
     */
    public PowerUpsType getPowerUps() {
        return powerUps;
    }

    /**
     * Sets the value of the powerUps property.
     * 
     * @param value
     *     allowed object is
     *     {@link PowerUpsType }
     *     
     */
    public void setPowerUps(PowerUpsType value) {
        this.powerUps = value;
    }

}
