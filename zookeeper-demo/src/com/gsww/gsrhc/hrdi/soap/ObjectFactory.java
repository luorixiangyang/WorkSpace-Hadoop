
package com.gsww.gsrhc.hrdi.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gsww.gsrhc.hrdi.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _JkfpQueryResponse_QNAME = new QName("http://soap.hrdi.gsrhc.gsww.com/", "JkfpQueryResponse");
    private final static QName _Exception_QNAME = new QName("http://soap.hrdi.gsrhc.gsww.com/", "Exception");
    private final static QName _JkfpQuery_QNAME = new QName("http://soap.hrdi.gsrhc.gsww.com/", "JkfpQuery");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gsww.gsrhc.hrdi.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JkfpQuery }
     * 
     */
    public JkfpQuery createJkfpQuery() {
        return new JkfpQuery();
    }

    /**
     * Create an instance of {@link JkfpQueryResponse }
     * 
     */
    public JkfpQueryResponse createJkfpQueryResponse() {
        return new JkfpQueryResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JkfpQueryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.hrdi.gsrhc.gsww.com/", name = "JkfpQueryResponse")
    public JAXBElement<JkfpQueryResponse> createJkfpQueryResponse(JkfpQueryResponse value) {
        return new JAXBElement<JkfpQueryResponse>(_JkfpQueryResponse_QNAME, JkfpQueryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.hrdi.gsrhc.gsww.com/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JkfpQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.hrdi.gsrhc.gsww.com/", name = "JkfpQuery")
    public JAXBElement<JkfpQuery> createJkfpQuery(JkfpQuery value) {
        return new JAXBElement<JkfpQuery>(_JkfpQuery_QNAME, JkfpQuery.class, null, value);
    }

}
