package serversp;

public class ServerSPSoapApiProxy implements serversp.ServerSPSoapApi {
  private String _endpoint = null;
  private serversp.ServerSPSoapApi serverSPSoapApi = null;
  
  public ServerSPSoapApiProxy() {
    _initServerSPSoapApiProxy();
  }
  
  public ServerSPSoapApiProxy(String endpoint) {
    _endpoint = endpoint;
    _initServerSPSoapApiProxy();
  }
  
  private void _initServerSPSoapApiProxy() {
    try {
      serverSPSoapApi = (new serversp.ServerSPServiceLocator()).getServerSPSoapApiPort();
      if (serverSPSoapApi != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serverSPSoapApi)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serverSPSoapApi)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serverSPSoapApi != null)
      ((javax.xml.rpc.Stub)serverSPSoapApi)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public serversp.ServerSPSoapApi getServerSPSoapApi() {
    if (serverSPSoapApi == null)
      _initServerSPSoapApiProxy();
    return serverSPSoapApi;
  }
  
  public java.lang.String subscribeSalon(int salon, java.lang.String pseudo) throws java.rmi.RemoteException{
    if (serverSPSoapApi == null)
      _initServerSPSoapApiProxy();
    return serverSPSoapApi.subscribeSalon(salon, pseudo);
  }
  
  public java.lang.String postMessageSalon(int salon, java.lang.String pseudo, java.lang.String message) throws java.rmi.RemoteException{
    if (serverSPSoapApi == null)
      _initServerSPSoapApiProxy();
    return serverSPSoapApi.postMessageSalon(salon, pseudo, message);
  }
  
  public java.lang.String unsubscribeSalon(int salon, java.lang.String pseudo) throws java.rmi.RemoteException{
    if (serverSPSoapApi == null)
      _initServerSPSoapApiProxy();
    return serverSPSoapApi.unsubscribeSalon(salon, pseudo);
  }
  
  public java.lang.String requestMessageSalon(int salon, java.lang.String pseudo) throws java.rmi.RemoteException{
    if (serverSPSoapApi == null)
      _initServerSPSoapApiProxy();
    return serverSPSoapApi.requestMessageSalon(salon, pseudo);
  }
  
  
}