
package com.store.integration;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.store.integration package. 
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

    private final static QName _PaymentOperation_QNAME = new QName("http://facade.paymentmgmt.bank.com/", "paymentOperation");
    private final static QName _PaymentOperationResponse_QNAME = new QName("http://facade.paymentmgmt.bank.com/", "paymentOperationResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.store.integration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PaymentOperation }
     * 
     */
    public PaymentOperation createPaymentOperation() {
        return new PaymentOperation();
    }

    /**
     * Create an instance of {@link PaymentOperationResponse }
     * 
     */
    public PaymentOperationResponse createPaymentOperationResponse() {
        return new PaymentOperationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentOperation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facade.paymentmgmt.bank.com/", name = "paymentOperation")
    public JAXBElement<PaymentOperation> createPaymentOperation(PaymentOperation value) {
        return new JAXBElement<PaymentOperation>(_PaymentOperation_QNAME, PaymentOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentOperationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facade.paymentmgmt.bank.com/", name = "paymentOperationResponse")
    public JAXBElement<PaymentOperationResponse> createPaymentOperationResponse(PaymentOperationResponse value) {
        return new JAXBElement<PaymentOperationResponse>(_PaymentOperationResponse_QNAME, PaymentOperationResponse.class, null, value);
    }

}
