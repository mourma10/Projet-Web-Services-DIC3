package salon3;

import java.util.*;

import org.apache.xmlrpc.WebServer;


public class Salon3 {
	private static List<String> connected = new ArrayList<String>();
	   private static Map<String, String> discussion = new HashMap<String, String>();
		
		public Salon3() {
	    }

	    public String subscribeSalon3 (String pseudo) {
			if(!connected.contains(pseudo)){
				int i=0;
				System.out.println(pseudo + " vient de se connecter");
				while(i<connected.size()){
					String temp=discussion.get(connected.get(i));
					discussion.put(connected.get(i),temp + pseudo + " vient de se connecter\n");
					i++;
				}
				connected.add(pseudo);
				discussion.put(pseudo,"Vous venez de vous connecter\n");    	
				return (new String("Vous venez de vous connecter "));
			}else
				return(new String("pseudo non disponible"));
	    }

	    public String unsubscribeSalon3 (String pseudo)  {
			int i=0;
	    	System.out.println(pseudo + " vient de se deconnecter");
	    	while(i<connected.size()){
	    		String temp=discussion.get(connected.get(i));
	    		discussion.put(connected.get(i),temp+pseudo + " vient de se deconnecter\n");
	    		i++;
	    	}
	    	connected.remove(pseudo);
	    	discussion.remove(pseudo);    	
	    	return (new String("Vous venez de vous deconnecter "));
	
	    }

	   	public String postMessageSalon3(String pseudo, String message){	
	   			if(connected.contains(pseudo)){
					int i=0;
		   			while(i<connected.size()){
		   				String temp=discussion.get(connected.get(i));
						if(temp.equals(pseudo))
							discussion.put((String)connected.get(i),temp+pseudo+ ": \t\t\t"+message+"\n");
						else
		   					discussion.put((String)connected.get(i),temp+pseudo+ ": "+message+"\n");
		   				i++;
		   			}
		   				return (new String("Vous venez de vous connecter "));
	   			}
	   			else 
	   				return (new String("Vous n'etes pas connecte"));
	   	}

	   	public String requestMessageSalon3 (String pseudo){
	   		if(connected.contains(pseudo))
	   			return (discussion.get(pseudo));
	   		else 
	   			return (new String("Vous n'etes pas connecte"));
	   	}
	   	public static void main (String [] args){
	   		try { 
				System.out.println("Demarrage du Salon 3...");
				WebServer server = new WebServer(9003);
				server.addHandler("sample", new Salon3());
				server.start();
				System.out.println("Salon3 a bien demarré.");
				System.out.println("En attente de requetes.");
			}catch (Exception exception)
				{ 
					System.err.println("JavaServer: " + exception); 
				}
		}
}

