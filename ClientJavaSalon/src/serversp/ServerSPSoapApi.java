/**
 * ServerSPSoapApi.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package serversp;

public interface ServerSPSoapApi extends java.rmi.Remote {
    public java.lang.String subscribeSalon(int salon, java.lang.String pseudo) throws java.rmi.RemoteException;
    public java.lang.String postMessageSalon(int salon, java.lang.String pseudo, java.lang.String message) throws java.rmi.RemoteException;
    public java.lang.String unsubscribeSalon(int salon, java.lang.String pseudo) throws java.rmi.RemoteException;
    public java.lang.String requestMessageSalon(int salon, java.lang.String pseudo) throws java.rmi.RemoteException;
}
