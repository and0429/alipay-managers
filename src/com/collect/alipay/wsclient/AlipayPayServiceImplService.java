
package com.collect.alipay.wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "AlipayPayServiceImplService", targetNamespace = "http://serviceImpl.alipay.com/", wsdlLocation = "http://123.57.68.107/alipay/AlipayPayService?wsdl")
public class AlipayPayServiceImplService
    extends Service
{

    private final static URL ALIPAYPAYSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.collect.alipay.wsclient.AlipayPayServiceImplService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.collect.alipay.wsclient.AlipayPayServiceImplService.class.getResource(".");
            url = new URL(baseUrl, "http://123.57.68.107/alipay/AlipayPayService?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://123.57.68.107/alipay/AlipayPayService?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        ALIPAYPAYSERVICEIMPLSERVICE_WSDL_LOCATION = url;
    }

    public AlipayPayServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AlipayPayServiceImplService() {
        super(ALIPAYPAYSERVICEIMPLSERVICE_WSDL_LOCATION, new QName("http://serviceImpl.alipay.com/", "AlipayPayServiceImplService"));
    }

    /**
     * 
     * @return
     *     returns AlipayPayService
     */
    @WebEndpoint(name = "AlipayPayServiceImplPort")
    public AlipayPayService getAlipayPayServiceImplPort() {
        return super.getPort(new QName("http://serviceImpl.alipay.com/", "AlipayPayServiceImplPort"), AlipayPayService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AlipayPayService
     */
    @WebEndpoint(name = "AlipayPayServiceImplPort")
    public AlipayPayService getAlipayPayServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://serviceImpl.alipay.com/", "AlipayPayServiceImplPort"), AlipayPayService.class, features);
    }

}
