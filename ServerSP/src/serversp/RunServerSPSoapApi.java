package serversp;

import javax.xml.ws.Endpoint;

public class RunServerSPSoapApi {
	
	public static void main(String[] args) {
		String url = "http://192.168.43.198:8989/";
		Endpoint.publish(url, new ServerSPSoapApi());
		System.out.println("Le Serveur SP a bien demarre sur : "+url);
	}
}
