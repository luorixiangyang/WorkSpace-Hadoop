
package com.gsww.gsrhc.hrdi.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "Exception", targetNamespace = "http://soap.hrdi.gsrhc.gsww.com/")
public class Exception_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private com.gsww.gsrhc.hrdi.soap.Exception faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public Exception_Exception(String message, com.gsww.gsrhc.hrdi.soap.Exception faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public Exception_Exception(String message, com.gsww.gsrhc.hrdi.soap.Exception faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.gsww.gsrhc.hrdi.soap.Exception
     */
    public com.gsww.gsrhc.hrdi.soap.Exception getFaultInfo() {
        return faultInfo;
    }

}
