package client;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import serversp.*;

public class ClientJava {
		/**
		 * Titre de la fenetre de chat
		 */
	  	private String title = "Logiciel de discussion en ligne";
	  	
	  	/**
	  	 * Pseudo de l'utilisateur
	  	 */
	    private String pseudo = null;
	    /**
	     * Salon ou le client veut se connecter
	     */
	    private String salon = null;
	    
	    private static List<String> salons = new ArrayList<String>();
	    
	    /**
	     * Fenetre principale
	     */
	    private JFrame window = new JFrame(this.title);
	    
	    /**
	     * Zone de la fenetre ou les messages sont affiches
	     */
	    private JTextArea txtOutput = new JTextArea();
	    
	    /**
	     * Zone de saisie des messages
	     */
	    private JTextField txtMessage = new JTextField();
	    
	    /**
	     * Bouton pour envoyer un message
	     */
	    private JButton btnSend = new JButton("Envoyer");
	    
	    /**
	     * Atribut serveur pour appeller les methodes du ServerSP
	     */
	    private static ServerSPSoapApi server = null;
	    
	    /**
	     * Constructeur
	     */
	    public ClientJava() {
	    		ServerSPServiceLocator ssl = new ServerSPServiceLocator();
	    		salons.add("1");
	    		salons.add("2");
	    		salons.add("3");
	    		try {
	    			server = ssl.getServerSPSoapApiPort();
	    		}catch(Exception ex) {ex.printStackTrace();}
	        this.createIHM();
	        this.requestSalonAndPseudo();
	        this.requestMessage();
	    }

	    /**
	     * Creation de l'interface graphique
	     */
	    public void createIHM() {
	        // Assemblage des composants
	        JPanel panel = (JPanel)this.window.getContentPane();
	        JScrollPane sclPane = new JScrollPane(txtOutput);
	        JPanel southPanel = new JPanel(new BorderLayout());
	        southPanel.add(this.txtMessage, BorderLayout.CENTER);
	        southPanel.add(this.btnSend, BorderLayout.EAST);
	        panel.add(sclPane, BorderLayout.CENTER);
	        panel.add(southPanel, BorderLayout.SOUTH);

	        // Gestion des évènements
	        window.addWindowListener(new WindowAdapter() {
	        		@Override
	            public void windowClosing(WindowEvent e) {
	        			try {
	                deconnecter(e);
	        			}catch(Exception ex) {ex.printStackTrace();}
	        			
	            }
	        });
	        btnSend.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                btnSend_actionPerformed(e);
	            }
	        });
	        txtMessage.addKeyListener(new KeyAdapter() {
	        public void keyReleased(KeyEvent event) {
	        if (event.getKeyChar() == '\n')
	            btnSend_actionPerformed(null);
	        }
	    });

	        // Initialisation des attributs
	        this.txtOutput.setBackground(new Color(220,220,220));
	        this.txtOutput.setEditable(false);
	        this.window.setSize(800,600);
	        this.window.setVisible(true);
	        this.window.setLocationRelativeTo(null);
	        this.txtMessage.requestFocus();
	    }
	 
	    /**
	     * Saisie du pseudo et du salon
	     */
	    public void requestSalonAndPseudo() {
	    		String result = null;
	    		do {
		        this.pseudo = JOptionPane.showInputDialog(
		                this.window, "Entrez votre pseudo : ",
		                this.title,  JOptionPane.OK_OPTION
		        );
		        do {
			        this.salon = JOptionPane.showInputDialog(
			                this.window, "Donnez le salon (1,2,ou 3) : ",
			                this.title,  JOptionPane.OK_OPTION
			        );
			        
			        if(this.salon == null)
			        		System.exit(0);	
		        }while(!salons.contains(salon));
	    			
	    			if(this.pseudo == null)
	    				System.exit(0);
		       
		        try {
		        		result = server.subscribeSalon(Integer.parseInt(this.salon), this.pseudo);
		        		if(result.equals("pseudo non disponible"))
		        			JOptionPane.showMessageDialog(null, "Pseudo deja pris!");
		            }catch (Exception exception) { 
		            		JOptionPane.showMessageDialog(null, "Server SP ou Salon "+salon+" est introuvable!");
		            		System.exit(0);
		            	}
	    		}while(result.equals("pseudo non disponible"));
	        }
	    
	    /**
	     * 
	     */
	    public void requestMessage(){
	        try {
	        		while (true){
	                String resultat = server.requestMessageSalon(Integer.parseInt(this.salon), this.pseudo);
	                txtOutput.setText(resultat);
	            }
	        }
	        catch (Exception exception) { System.err.println("JavaClient: " + exception); }
	    }

	    
	    /**
	     * Envoi de message
	     * @param e event click sur le bouton btnSend
	     */
	    public void btnSend_actionPerformed(ActionEvent e) {
	        this.txtOutput.append("\t\t\t"+this.txtMessage.getText() + "\n");
	        try {
	        		server.postMessageSalon(Integer.parseInt(this.salon), this.pseudo, this.txtMessage.getText());
	            }catch (Exception exception) { System.err.println("JavaClient: " + exception); }
	        
	        this.txtMessage.setText("");
	        this.txtMessage.requestFocus();
	    }
	    
	    /**
	     * Deconnexion 
	     * @param e
	     */
	    public void deconnecter (WindowEvent e) {
	        try{        
	        		server.unsubscribeSalon(Integer.parseInt(this.salon), this.pseudo);
	        }catch(Exception ex){
	            	ex.printStackTrace();
	        }
	        System.exit(0);
	    }

	    public void printMessage(String msg){
	        String text = txtOutput.getText();
	        txtOutput.setText(text+msg+"\n");
	    }

	    public static void main(String[] args) {
	        new ClientJava();     
	}
}
