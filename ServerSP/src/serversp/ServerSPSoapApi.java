package serversp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;


@WebService(serviceName="ServerSPService")
public class ServerSPSoapApi {
	private CallRPCSalonMethod salonUrl = null;
	private FileWriter fw;
	private BufferedWriter bw;
	PrintWriter out;
	
	ServerSPSoapApi() {
		try {
			fw = new FileWriter("serverSP.log", true);
		    bw = new BufferedWriter(fw);
		    out = new PrintWriter(bw);
		    out.print("\t\t\t ---- Server SP Log ---- \n");
		    out.print("----\n");
		}catch(Exception e) {e.printStackTrace();}
	}
	
    		
	@WebMethod
	public String subscribeSalon(@WebParam(name="salon")int salon,@WebParam(name="pseudo")String pseudo){
		try {
			salonUrl = new CallRPCSalonMethod();
			out.println(LocalDateTime.now()+": User: "+pseudo.toUpperCase()+" ==> requete subscribe vers salon: "+salon);
			String ip = "192.168.43.229";
			int port = 0;
			switch(salon) {
			case 1:
				port = 9001;
				break;
			case 2:
				port = 9002;
				break;
			case 3:
				port = 9003;
				break;
			}
			try {
				System.out.print("Connexion salon "+salon+" en cours...: ");
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(ip, port), 5000);
				socket.close();
				System.out.println("Connected!");
			}catch(Exception e) 
			{
				System.out.println("Echec : ");
			}
			return salonUrl.subscribe(salon,pseudo);
		}catch(Exception ex) {ex.printStackTrace();}
		return null;
	}
	
	@WebMethod
	public String postMessageSalon(@WebParam(name="salon")int salon,@WebParam(name="pseudo")String pseudo,@WebParam(name="message")String message){
		try {
		salonUrl = new CallRPCSalonMethod();
		out.println(LocalDateTime.now()+": User: "+pseudo.toUpperCase()+" ==> requete postMessage vers salon: "+salon);
        return salonUrl.postMessage(salon,pseudo, message);
		}catch(Exception ex) {ex.printStackTrace();}
		return null;
	}
	
	@WebMethod
	public String unsubscribeSalon(@WebParam(name="salon")int salon,@WebParam(name="pseudo")String pseudo){
		try {
			salonUrl = new CallRPCSalonMethod();
			out.println(LocalDateTime.now()+": User: "+pseudo.toUpperCase()+" ==> requete unsubscribe vers salon: "+salon);
			out.close();
	        return salonUrl.unsubscribe(salon,pseudo);
		}catch(Exception ex) {ex.printStackTrace();}
		return null;
	}
	
	@WebMethod
	public String requestMessageSalon(@WebParam(name="salon")int salon,@WebParam(name="pseudo")String pseudo) {
		try {
		salonUrl = new CallRPCSalonMethod();
        return salonUrl.requestMessage(salon,pseudo);
		}catch(Exception ex) {ex.printStackTrace();}
		return null;
	}
	
}

class CallRPCSalonMethod {
	private static XmlRpcClient client = null;
	private static XmlRpcClientConfigImpl config = null;
	
	CallRPCSalonMethod(){
		config = new XmlRpcClientConfigImpl();
		client = new XmlRpcClient();
		client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
	}
	
	public String subscribe(int salon,String pseudo){
		try {
			Vector<String> params = new Vector<String>();
	        params.addElement(pseudo);
	        
	        switch(salon) {
	        case 1:
	        		config.setServerURL(new URL("http://192.168.43.229:9001"));
	        		client.setConfig(config);
	        		return (String)client.execute("sample.subscribeSalon1", params).toString();
	        case 2:
		    		config.setServerURL(new URL("http://192.168.43.229:9002"));
		    		client.setConfig(config);
	        		return (String)client.execute("sample.subscribeSalon2", params).toString();
	        case 3:
		    		config.setServerURL(new URL("http://192.168.43.229:9003"));
		    		client.setConfig(config);
	        		return (String)client.execute("sample.subscribeSalon3", params).toString();
	        }
	        
		}catch(Exception ex) {ex.printStackTrace();}
        return null;       
	}
	
	public String postMessage(int salon,String pseudo,String message){
		try {
			Vector<String> params = new Vector<String>();
	        params.addElement(pseudo);
	        params.addElement(message);
	        
	        switch(salon) {
	        case 1:
		    		config.setServerURL(new URL("http://192.168.43.229:9001"));
		    		client.setConfig(config);
	        		return (String)client.execute("sample.postMessageSalon1", params).toString();
	        case 2:
		    		config.setServerURL(new URL("http://192.168.43.229:9002"));
		    		client.setConfig(config);
	        		return (String)client.execute("sample.postMessageSalon2", params).toString();
	        case 3:
		    		config.setServerURL(new URL("http://192.168.43.229:9003"));
		    		client.setConfig(config);
	        		return (String)client.execute("sample.postMessageSalon3", params).toString();
	        }
		}catch(Exception ex) {ex.printStackTrace();}
        return null;
	}
	
	public String requestMessage(int salon,String pseudo){
		try {
		Vector<String> params = new Vector<String>();
        params.addElement(pseudo);
        
        switch(salon) {
        case 1:
	    		config.setServerURL(new URL("http://192.168.43.229:9001"));
	    		client.setConfig(config);
        		return (String)client.execute("sample.requestMessageSalon1", params).toString();
        case 2:
	    		config.setServerURL(new URL("http://192.168.43.229:9002"));
	    		client.setConfig(config);
        		return (String)client.execute("sample.requestMessageSalon2", params).toString();
        case 3:
	    		config.setServerURL(new URL("http://192.168.43.229:9003"));
	    		client.setConfig(config);
        		return (String)client.execute("sample.requestMessageSalon3", params).toString();
        }
		}catch(Exception ex) {ex.printStackTrace();}
        return null;
	}
	
	public String unsubscribe(int salon,String pseudo) {
		try {
		Vector<String> params = new Vector<String>();
        params.addElement(pseudo);
        
        switch(salon) {
        case 1:
	    		config.setServerURL(new URL("http://192.168.43.229:9001"));
	    		client.setConfig(config);
        		return (String)client.execute("sample.unsubscribeSalon1", params).toString();
        case 2:
	    		config.setServerURL(new URL("http://192.168.43.229:9002"));
	    		client.setConfig(config);
        		return (String)client.execute("sample.unsubscribeSalon2", params).toString();
        case 3:
	    		config.setServerURL(new URL("http://192.168.43.229:9003"));
	    		client.setConfig(config);
        		return (String)client.execute("sample.unsubscribeSalon3", params).toString();
        }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
        return null;
	}

}
