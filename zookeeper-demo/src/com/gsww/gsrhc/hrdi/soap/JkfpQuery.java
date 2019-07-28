
package com.gsww.gsrhc.hrdi.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>JkfpQuery complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="JkfpQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="JkfpQuery" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JkfpQuery", propOrder = {
    "jkfpQuery"
})
public class JkfpQuery {

    @XmlElement(name = "JkfpQuery", namespace = "http://soap.hrdi.gsrhc.gsww.com/")
    protected String jkfpQuery;

    /**
     * 获取jkfpQuery属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJkfpQuery() {
        return jkfpQuery;
    }

    /**
     * 设置jkfpQuery属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJkfpQuery(String value) {
        this.jkfpQuery = value;
    }

}
