
package com.collect.alipay.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for precreateResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="precreateResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bigPicArray" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bigPicLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bigPicUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detailErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detailErrorDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outTradeNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="picUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smallPicUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="voucherType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "precreateResponse", propOrder = {
    "bigPicArray",
    "bigPicLocal",
    "bigPicUrl",
    "detailErrorCode",
    "detailErrorDesc",
    "error",
    "outTradeNo",
    "picUrl",
    "qrCode",
    "resultCode",
    "smallPicUrl",
    "success",
    "voucherType"
})
public class PrecreateResponse {

    protected String bigPicArray;
    protected String bigPicLocal;
    protected String bigPicUrl;
    protected String detailErrorCode;
    protected String detailErrorDesc;
    protected String error;
    protected String outTradeNo;
    protected String picUrl;
    protected String qrCode;
    protected String resultCode;
    protected String smallPicUrl;
    protected String success;
    protected String voucherType;

    /**
     * Gets the value of the bigPicArray property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBigPicArray() {
        return bigPicArray;
    }

    /**
     * Sets the value of the bigPicArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBigPicArray(String value) {
        this.bigPicArray = value;
    }

    /**
     * Gets the value of the bigPicLocal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBigPicLocal() {
        return bigPicLocal;
    }

    /**
     * Sets the value of the bigPicLocal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBigPicLocal(String value) {
        this.bigPicLocal = value;
    }

    /**
     * Gets the value of the bigPicUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBigPicUrl() {
        return bigPicUrl;
    }

    /**
     * Sets the value of the bigPicUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBigPicUrl(String value) {
        this.bigPicUrl = value;
    }

    /**
     * Gets the value of the detailErrorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailErrorCode() {
        return detailErrorCode;
    }

    /**
     * Sets the value of the detailErrorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailErrorCode(String value) {
        this.detailErrorCode = value;
    }

    /**
     * Gets the value of the detailErrorDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailErrorDesc() {
        return detailErrorDesc;
    }

    /**
     * Sets the value of the detailErrorDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailErrorDesc(String value) {
        this.detailErrorDesc = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

    /**
     * Gets the value of the outTradeNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * Sets the value of the outTradeNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutTradeNo(String value) {
        this.outTradeNo = value;
    }

    /**
     * Gets the value of the picUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * Sets the value of the picUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicUrl(String value) {
        this.picUrl = value;
    }

    /**
     * Gets the value of the qrCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * Sets the value of the qrCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQrCode(String value) {
        this.qrCode = value;
    }

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultCode(String value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the smallPicUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmallPicUrl() {
        return smallPicUrl;
    }

    /**
     * Sets the value of the smallPicUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmallPicUrl(String value) {
        this.smallPicUrl = value;
    }

    /**
     * Gets the value of the success property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccess(String value) {
        this.success = value;
    }

    /**
     * Gets the value of the voucherType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoucherType() {
        return voucherType;
    }

    /**
     * Sets the value of the voucherType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoucherType(String value) {
        this.voucherType = value;
    }

}
