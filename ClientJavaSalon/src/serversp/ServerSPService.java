/**
 * ServerSPService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package serversp;

public interface ServerSPService extends javax.xml.rpc.Service {
    public java.lang.String getServerSPSoapApiPortAddress();

    public serversp.ServerSPSoapApi getServerSPSoapApiPort() throws javax.xml.rpc.ServiceException;

    public serversp.ServerSPSoapApi getServerSPSoapApiPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
