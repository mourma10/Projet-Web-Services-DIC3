/**
 * ServerSPServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package serversp;

public class ServerSPServiceLocator extends org.apache.axis.client.Service implements serversp.ServerSPService {

    public ServerSPServiceLocator() {
    }


    public ServerSPServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServerSPServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServerSPSoapApiPort
    private java.lang.String ServerSPSoapApiPort_address = "http://192.168.43.198:8989/";

    public java.lang.String getServerSPSoapApiPortAddress() {
        return ServerSPSoapApiPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServerSPSoapApiPortWSDDServiceName = "ServerSPSoapApiPort";

    public java.lang.String getServerSPSoapApiPortWSDDServiceName() {
        return ServerSPSoapApiPortWSDDServiceName;
    }

    public void setServerSPSoapApiPortWSDDServiceName(java.lang.String name) {
        ServerSPSoapApiPortWSDDServiceName = name;
    }

    public serversp.ServerSPSoapApi getServerSPSoapApiPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServerSPSoapApiPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServerSPSoapApiPort(endpoint);
    }

    public serversp.ServerSPSoapApi getServerSPSoapApiPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            serversp.ServerSPSoapApiPortBindingStub _stub = new serversp.ServerSPSoapApiPortBindingStub(portAddress, this);
            _stub.setPortName(getServerSPSoapApiPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServerSPSoapApiPortEndpointAddress(java.lang.String address) {
        ServerSPSoapApiPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (serversp.ServerSPSoapApi.class.isAssignableFrom(serviceEndpointInterface)) {
                serversp.ServerSPSoapApiPortBindingStub _stub = new serversp.ServerSPSoapApiPortBindingStub(new java.net.URL(ServerSPSoapApiPort_address), this);
                _stub.setPortName(getServerSPSoapApiPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ServerSPSoapApiPort".equals(inputPortName)) {
            return getServerSPSoapApiPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://serversp/", "ServerSPService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://serversp/", "ServerSPSoapApiPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServerSPSoapApiPort".equals(portName)) {
            setServerSPSoapApiPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
